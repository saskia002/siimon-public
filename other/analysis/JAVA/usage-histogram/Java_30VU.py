import matplotlib.pyplot as plt
import csv
import numpy as np

cpu_data = []
ram_data = []

with open('../3/java_perf_metrics.csv', 'r') as csvfile:
    plots = csv.reader(csvfile, delimiter=';')
    next(plots)

    for row in plots:
        current_cpu = float(row[4])
        current_ram = int(row[15]) / 1000 / 1.024  # Convert MB to MiB
        
        cpu_data.append(current_cpu)
        ram_data.append(current_ram)

cpu_step = (max(cpu_data) - min(cpu_data)) / 15
ram_step = (max(ram_data) - min(ram_data)) / 16
cpu_bins = np.arange(min(cpu_data), max(cpu_data) + cpu_step, cpu_step)
ram_bins = np.arange(min(ram_data), max(ram_data) + ram_step, ram_step)


fig, (ax1, ax2) = plt.subplots(1, 2, figsize=(12, 6))

ax1.hist(cpu_data, color='green', alpha=0.7, edgecolor='black', range=(0, 100) )
ax1.set_xlabel('Protsessor (%)')
ax1.set_ylabel('Sagedus')
ax1.set_title('Protsessori kasutus')

ax2.hist(ram_data, color='blue', alpha=0.7, edgecolor='black', range=(0, 1200))
ax2.set_xlabel('Vahemälu (MiB)')
ax2.set_ylabel('Sagedus')
ax2.set_title('Vahemälu kasutus')

plt.suptitle('Java tagarakenduse protsessori ja vahemälu histogrammid, 30 virtuaalkasutajat', fontsize=13)
plt.tight_layout()
plt.show()
