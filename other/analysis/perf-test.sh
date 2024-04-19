#!/bin/bash

java_service_name="backend"
postgres_service_name="postgresql"

java_output_file="java_perf_metrics.csv"
postgres_output_file="postgres_perf_metrics.csv"

interval=1 # In seconds
duration=7200 # When script stops in seconds

end_time=$((SECONDS + duration))

java_pid=$(systemctl show -p MainPID --value "$java_service_name")
postgres_pid=$(systemctl show -p MainPID --value "$postgres_service_name")

if [ -z "$java_pid" ] || [ -z "$postgres_pid" ]; then
  echo "Either Java process or PostgreSQL process not found."
  exit 1
fi

print_header() {
   # https://linux.die.net/man/1/pidstat
   echo -e "Timestamp;PID;PID_NAME;COMMAND;CPU%;CPU#;CPUWait%;UserCPU%;SystemCPU%;GuestCPU%;AppThreads;fdNR;" \
   "MinorFaults minflt/s;MajorFaults majflt/s ;MemVSZ(KB);MemRSS(KB);Mem%;StackSize;StackRef;" \
   "DiskRead(kB/s);DiskWrite(kB/s);kB_ccwr/s;iodelay;CSWCH;NS_CSWCH"
}

collect_metrics() {
    local pid="$1"

    local timestamp=$(date "+%d.%m.%Y %H:%M:%S")
    local pid_name=$(ps -p $pid -o command | awk 'NR>1');
    local top=$(top -bn 1 -p $pid | awk 'NR==8')
    local pidstat_output=$(sudo pidstat -uvrsdw -h -p $pid | awk 'NR==4')

    local pid_command=$(echo "$pidstat_output" | awk '{print $25}')

    local cpu_percent=$(echo "$top" | awk '{print $9}')
    local cpu_nr=$(echo "$pidstat_output" | awk '{print $9}')
    local wait_percent=$(echo "$pidstat_output" | awk '{print $7}')
    local user_cpu=$(echo "$pidstat_output" | awk '{print $4}')
    local system_cpu=$(echo "$pidstat_output" | awk '{print $5}')
    local guest_cpu=$(echo "$pidstat_output" | awk '{print $6}')

    local app_threads=$(echo "$pidstat_output" | awk '{print $23}')
    local app_fd_nr=$(echo "$pidstat_output" | awk '{print $24}')

    local minor_faults=$(echo "$pidstat_output" | awk '{print $10}')
    local major_faults=$(echo "$pidstat_output" | awk '{print $11}')
    local mem_vsz=$(echo "$pidstat_output" | awk '{print $12}')
    local mem_rss=$(echo "$pidstat_output" | awk '{print $13}')
    local mem_percentage=$(echo "$pidstat_output" | awk '{print $14}')

    local stk_size=$(echo "$pidstat_output" | awk '{print $15}')
    local stk_ref=$(echo "$pidstat_output" | awk '{print $16}')

    local disk_read=$(echo "$pidstat_output" | awk '{print $17}')
    local disk_write=$(echo "$pidstat_output" | awk '{print $18}')
    local ccwr=$(echo "$pidstat_output" | awk '{print $19}')
    local iodelay=$(echo "$pidstat_output" | awk '{print $20}')

    local cswch=$(echo "$pidstat_output" | awk '{print $21}')
    local ns_cswch=$(echo "$pidstat_output" | awk '{print $22}')


    echo -e "$timestamp;$pid;$pid_name;$pid_command;$cpu_percent;$cpu_nr;$wait_percent;$user_cpu;$system_cpu;" \
    "$guest_cpu;$app_threads;$app_fd_nr;$minor_faults;$major_faults;$mem_vsz;" \
    "$mem_rss;$mem_percentage;$stk_size;$stk_ref;$disk_read;$disk_write;$ccwr;$iodelay;$cswch;$ns_cswch"
}

print_header > "$java_output_file"
print_header > "$postgres_output_file"

while [ $SECONDS -lt $end_time ]; do
    collect_metrics "$java_pid" "$java_output_file" >> "$java_output_file"

    postgre_pids=$(ps aux | grep "post" | awk '/grep/ {next} {print $2}')
    for pid in $postgre_pids; do
        collect_metrics "$pid" "$postgres_output_file" >> "$postgres_output_file"
    done

    sleep $interval
done
