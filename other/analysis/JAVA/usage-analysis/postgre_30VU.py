import matplotlib.pyplot as plt
from matplotlib.dates import MinuteLocator, DateFormatter
from matplotlib.ticker import FuncFormatter
from datetime import datetime, timedelta
import csv
import numpy as np

data = []

temp_ram = 0
temp_cpu = 0
count = 0
c = 0

with open('../3/postgres_perf_metrics.csv', 'r') as csvfile:
    plots = csv.reader(csvfile, delimiter=';')
    next(plots)

    for row in plots:
        count += 1
        try:
            datetime_str = row[0]
            current_time = datetime.strptime(datetime_str[-8:], '%H:%M:%S')
            current_cpu = float(row[4])
            RSS_MiB = int(row[15]) / 1000 / 1.024
            
            temp_ram += RSS_MiB
            temp_cpu += current_cpu
            
        except:
            next
        
        if count == 18:
            data.append((current_time, temp_cpu, temp_ram))
            temp_ram = 0
            temp_cpu = 0
            count = 0 

min_cpu = min(item[1] for item in data)
max_cpu = max(item[1] for item in data)
min_ram = min(item[2] for item in data)
max_ram = max(item[2] for item in data)

start_time = data[0][0]
end_time = start_time + timedelta(minutes=15)
limit_time = end_time + timedelta(minutes=60)
filtered_data = [item for item in data if end_time <= item[0] <= limit_time]

mean_cpu = np.mean([item[1] for item in filtered_data])
median_cpu = np.median([item[1] for item in filtered_data])
mean_ram = np.mean([item[2] for item in filtered_data])
median_ram = np.median([item[2] for item in filtered_data])
std_cpu = np.std([item[1] for item in filtered_data])
std_ram = np.std([item[2] for item in filtered_data])

fig, ax1 = plt.subplots()

color = 'tab:green'
ax1.set_xlabel('Aeg')
ax1.set_ylabel('Protsessori kasutus (%)', color=color)
ax1.plot([item[0] for item in data], [item[1] for item in data], color=color)
ax1.tick_params(axis='y', labelcolor=color)

ax2 = ax1.twinx()  
color = 'tab:purple'
ax2.set_ylabel('Vahemälu kasutus (MiB)', color=color)
ax2.plot([item[0] for item in data], [item[2] for item in data], color=color)
ax2.tick_params(axis='y', labelcolor=color)

plt.xticks(rotation=90)
ax1.xaxis.set_major_locator(MinuteLocator(interval=5))
ax1.xaxis.set_major_formatter(DateFormatter('%H:%M'))

ax1.yaxis.set_major_formatter(FuncFormatter(lambda y, _: '{:.0f}%'.format(y)))
ax2.yaxis.set_major_formatter(FuncFormatter(lambda y, _: '{:.0f} MiB'.format(y)))

ax1.set_ylim(0, 100)
ax2.set_ylim(0, max_ram * 1.1)
ax1.grid()

plt.title('PostgreSQL protsessori ja vahemälu kasutus ajas, 30 virtuaalkasutajat', fontsize=12)

box_text = f'Protsessori kasutus:\nMin: {min_cpu:.2f}%\nMax: {max_cpu:.2f}%\nKeskmine: {mean_cpu:.2f}%\nMediaan: {median_cpu:.2f}%\nStandardhälve: {std_cpu:.2f}%'
plt.text(0.128, 0.99, box_text, transform=fig.transFigure, fontsize=10, verticalalignment='top', bbox=dict(facecolor='white', alpha=0.5))

box_text = f'Vahemälu kasutus:\nMin: {min_ram:.2f} MiB\nMax: {max_ram:.2f} MiB\nKeskmine: {mean_ram:.2f} MiB\nMediaan: {median_ram:.2f} MiB\nStandardhälve: {std_ram:.2f} MiB'
plt.text(0.808, 0.99, box_text, transform=fig.transFigure, fontsize=10, verticalalignment='top', bbox=dict(facecolor='white', alpha=0.5))

plt.show()
