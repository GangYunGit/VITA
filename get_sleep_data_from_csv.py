import csv
import datetime


def get_date(date_string):
    year = int(date_string[:4])
    month = int(date_string[5:7])
    day = int(date_string[8:10])
    hour = int(date_string[11:13])
    minute = int(date_string[14:16])
    return datetime.datetime(year, month, day, hour, minute, 0) + datetime.timedelta(hours=9)


def insert_date(date, stage_type, start_time, end_time):
    # sleep_id가 다르거나, sleep_id가 같고 날짜의 "일"이 다를 때
    my_sleep_info.append({"date": date, "stage_type": stage_type, "time_duration": [start_time, end_time]})


f = open('sleep_data_2.csv', 'r')
raw_data = csv.reader(f)
stage_name = ("비수면", "렘수면", "깊은수면", "얕은수면")
default_datetime = datetime.datetime(2023, 1, 1, 0, 0, 0, 0)
stages = [default_datetime, default_datetime, default_datetime, default_datetime]
my_sleep_info = []
cnt = 0
date = ""
former_date = ""
former_id = ""
for line in sorted(raw_data, key=lambda x: x[0], reverse=True):
    if len(line) == 12:
        if line[5] == "40001":
            stage_type = "비수면"
        elif line[5] == "40002":
            stage_type = "렘수면"
        elif line[5] == "40003":
            stage_type = "깊은수면"
        else:
            stage_type = "얕은수면"

        if line[1] != former_id:
            date = f'{get_date(line[9]).year}-{get_date(line[9]).month}-{get_date(line[9]).day}'

        start_time = f'{get_date(line[0]).hour}:{get_date(line[0]).minute}:{get_date(line[0]).second}'
        end_time = f'{get_date(line[9]).hour}:{get_date(line[9]).minute}:{get_date(line[9]).second}'

        insert_date(date, stage_type, start_time, end_time)

        former_date = get_date(line[9]).day
        former_id = line[1]


i = 0
for stage in stages:
    i += 1

for data in my_sleep_info:
    print(data, end=",\n")

f.close()