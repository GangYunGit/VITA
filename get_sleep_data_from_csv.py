import csv
from datetime import datetime

def get_date(date_string):
    year = int(date_string[:4])
    month = int(date_string[5:7])
    day = int(date_string[8:10])
    hour = int(date_string[11:13])
    minute = int(date_string[14:16])
    return datetime(year, month, day, hour, minute, 0)


f = open('sleep_data.csv', 'r')
raw_data = csv.reader(f)
stages = [datetime(2023, 1, 1, 0, 0, 0, 0), datetime(2023, 1, 1, 0, 0, 0, 0), datetime(2023, 1, 1, 0, 0, 0, 0), datetime(2023, 1, 1, 0, 0, 0, 0)]
for line in raw_data:
    if len(line) == 12:
        if line[5] == '40001':
            stages[0] += get_date(line[9]) - get_date(line[0])
        elif line[5] == '40002':
            stages[1] += get_date(line[9]) - get_date(line[0])
        elif line[5] == '40003':
            stages[2] += get_date(line[9]) - get_date(line[0])
        else:
            stages[3] += get_date(line[9]) - get_date(line[0])

i = 1
for stage in stages:
    print(f'수면단계{i} {stage.hour}시간 {stage.minute}분')
    i += 1

f.close()