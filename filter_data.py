import csv
import random

from_file = "laptops_original.csv"
to_file = "laptops_full_data.csv"
data = []
data_filtered = []
colors = ["White", "Black", "Green", "Blue", "Gray", "Silver", "Gold"]

with open(from_file, 'r', errors='ignore') as csv_file:
    for row in csv.reader(csv_file, delimiter=',', quotechar='"'):
        data.append(row)

def get_model(raw_model):
    if '(' in raw_model:
        return raw_model.split('(')[0].strip()
    return raw_model

def getCPU(full_cpu_name):
    cpu_name_parts = full_cpu_name.split()
    return cpu_name_parts[0], " ".join(cpu_name_parts[1:-1])

def getStorage(full_storage_name):
    storage_parts = full_storage_name.split()
    if len(storage_parts[1:]) > 2:
        if storage_parts[1] == "Flash": storage_type = " ".join(storage_parts[1:3])
        else: storage_type = storage_parts[1]
    else: storage_type = storage_parts[1]
    return storage_parts[0].replace("GB", "").replace("TB", ""), storage_type

def getSystem(os, os_vers):
    if os_vers: return os+" "+os_vers
    return os

def getColor():
    return colors[random.randint(0, len(colors)-1)]

data_filtered.append(["Manufacturer", "Model", "Screen size", "CPU Manufacturer", "CPU Model", "RAM Size", "Storage size", "Storage Type", "System", "Color"])
for row in data[1:]:
    manufacturer = row[0]
    model = get_model(row[1]).replace('"', '')
    screenSize = row[3].replace('"', '')
    cpuManufacturer, cpuModel = getCPU(row[5])
    ramSize = row[6].replace('GB', '')
    storageSize, storageType = getStorage(row[7])
    system = getSystem(row[9], row[10])
    color = getColor()
    data_filtered.append([manufacturer, model, screenSize, cpuManufacturer, cpuModel,
                          ramSize, storageSize, storageType, system, color])

with open(to_file, 'w') as csv_file:
    writer = csv.writer(csv_file, delimiter='|')
    writer.writerows(data_filtered)
