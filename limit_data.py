import csv
import random

from_file = "laptops_full_data.csv"
to_file = "laptops_some_data.csv"

num_rows = 50
data = []

with open(from_file, 'r') as csv_file:
    for row in csv.reader(csv_file, delimiter='|'):
        data.append(row)

with open(to_file, 'w') as csv_file:
    writer = csv.writer(csv_file, delimiter='|')
    counter = 0
    while counter < num_rows:
      writer.writerow(data[random.randint(0, len(data)-1)])
      counter += 1
