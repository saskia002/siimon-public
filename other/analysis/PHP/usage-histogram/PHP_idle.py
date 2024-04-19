import matplotlib.pyplot as plt
import csv
import numpy as np

cpu_data = []
ram_data = []

temp_ram = 0
temp_cpu = 0
count = 0

with open('../1/php_fpm_perf_metrics.csv', 'r') as csvfile:
    plots = csv.reader(csvfile, delimiter=';')
    next(plots)

    for row in plots:
        current_cpu = float(row[4])
        RSS_MiB = int(row[15]) / 1000 / 1.024
        
        temp_ram += RSS_MiB
        temp_cpu += current_cpu
        count += 1
        
        if count == 6:
            cpu_data.append(temp_cpu)
            ram_data.append(temp_ram)
            temp_ram = 0
            temp_cpu = 0
            count = 0
            
cpu_step = (max(cpu_data) - min(cpu_data)) / 15 if len(cpu_data) > 1 else 1

if all(x == 0 for x in cpu_data):
    cpu_bins = np.linspace(0, 1, 11)
else:
    cpu_bins = np.arange(min(cpu_data), max(cpu_data) + cpu_step, cpu_step)
    
fig, ax1 = plt.subplots(figsize=(8, 6))

ax1.hist(cpu_data, color='green', alpha=0.7, edgecolor='black', range=(0, 100))
ax1.set_xlabel('Protsessor (%)')
ax1.set_ylabel('Sagedus')
ax1.set_title('PHP protsessori kasutuse histogramm, jõudeolekus')

plt.tight_layout()
plt.show()
