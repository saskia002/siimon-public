#!/bin/bash

php_fpm_output_file="php_fpm_perf_metrics.csv"
maria_db_output_file="maria_db_perf_metrics.csv"

interval=5 # In seconds
duration=7200 # When the script stops in seconds
end_time=$((SECONDS + duration))

php_fpm_pids=$(ps aux | grep "php-fpm: " | awk '/grep/ {next} {print $2}')
maria_db_pid=$(systemctl show -p MainPID --value "mariadb")

if [ -z "$php_fpm_pids" ] || [ -z "$maria_db_pid" ]; then
  echo "No PHP-FPM processes found or MariaDB process not found."
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
    local pidstat_output_UP=$(sudo pidstat -u -p $pid | awk 'NR>3')
    local pidstat_output_VP=$(sudo pidstat -v -p $pid | awk 'NR>3')
    local pidstat_output_RP=$(sudo pidstat -r -p $pid | awk 'NR>3')
    local pidstat_output_SP=$(sudo pidstat -s -p $pid | awk 'NR>3')
    local pidstat_output_DP=$(sudo pidstat -d -p $pid | awk 'NR>3')
    local pidstat_output_WP=$(sudo pidstat -w -p $pid | awk 'NR>3')
    local top=$(top -bn 1 -p $pid | awk 'NR==8')

    local pid_name=$(ps -p $pid -o command | awk 'NR>1');

    local pid_command=$(echo "$pidstat_output_UP" | awk '{print $10}')

    local cpu_percent=$(echo "$top" | awk '{print $9}')
    local cpu_nr=$(echo "$pidstat_output_UP" | awk '{print $9}')
    local wait_percent=$(echo "$pidstat_output_UP" | awk '{print $7}')
    local user_cpu=$(echo "$pidstat_output_UP" | awk '{print $4}')
    local system_cpu=$(echo "$pidstat_output_UP" | awk '{print $5}')
    local guest_cpu=$(echo "$pidstat_output_UP" | awk '{print $6}')


    local app_threads=$(echo "$pidstat_output_VP" | awk '{print $4}')
    local app_fd_nr=$(echo "$pidstat_output_VP" | awk '{print $5}')

    local minor_faults=$(echo "$pidstat_output_RP" | awk '{print $4}')
    local major_faults=$(echo "$pidstat_output_RP" | awk '{print $5}')
    local mem_vsz=$(echo "$pidstat_output_RP" | awk '{print $6}')
    local mem_rss=$(echo "$pidstat_output_RP" | awk '{print $7}')
    local mem_percentage=$(echo "$pidstat_output_RP" | awk '{print $8}')

    local stk_size=$(echo "$pidstat_output_SP" | awk '{print $4}')
    local stk_ref=$(echo "$pidstat_output_SP" | awk '{print $5}')

    local disk_read=$(echo "$pidstat_output_DP" | awk '{print $4}')
    local disk_write=$(echo "$pidstat_output_DP" | awk '{print $5}')
    local ccwr=$(echo "$pidstat_output_DP" | awk '{print $5}')
    local iodelay=$(echo "$pidstat_output_DP" | awk '{print $7}')

    local cswch=$(echo "$pidstat_output_WP" | awk '{print $4}')
    local ns_cswch=$(echo "$pidstat_output_WP" | awk '{print $5}')

    echo -e "$timestamp;$pid;$pid_name;$pid_command;$cpu_percent;$cpu_nr;$wait_percent;$user_cpu;$system_cpu;" \
    "$guest_cpu;$app_threads;$app_fd_nr;$minor_faults;$major_faults;$mem_vsz;" \
    "$mem_rss;$mem_percentage;$stk_size;$stk_ref;$disk_read;$disk_write;$ccwr;$iodelay;$cswch;$ns_cswch"
}

print_header > "$php_fpm_output_file"
print_header > "$maria_db_output_file"

while [ $SECONDS -lt $end_time ]; do

    collect_metrics "$maria_db_pid" "$maria_db_output_file" >> "$maria_db_output_file"

    for pid in $php_fpm_pids; do
        collect_metrics "$pid" "$php_fpm_output_file" >> "$php_fpm_output_file"
    done
    
    sleep $interval
done
