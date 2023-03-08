<template>
  <div>
    <apexChart type="rangeBar" height="450" :options="chartOptions" :series="series"></apexChart>
  </div>
</template>

<script>
const sleep_data = [
  { 'date': '2023-3-8', 'stage_type': '비수면', 'time_duration': ['6:40:0', '6:44:0'] },
  { 'date': '2023-3-8', 'stage_type': '렘수면', 'time_duration': ['6:33:0', '6:40:0'] },
  { 'date': '2023-3-8', 'stage_type': '비수면', 'time_duration': ['6:22:0', '6:33:0'] },
  { 'date': '2023-3-8', 'stage_type': '얕은수면', 'time_duration': ['6:16:0', '6:22:0'] },
  { 'date': '2023-3-8', 'stage_type': '렘수면', 'time_duration': ['6:12:0', '6:16:0'] },
  { 'date': '2023-3-8', 'stage_type': '얕은수면', 'time_duration': ['6:6:0', '6:12:0'] },
  { 'date': '2023-3-8', 'stage_type': '비수면', 'time_duration': ['6:2:0', '6:6:0'] },
  { 'date': '2023-3-8', 'stage_type': '얕은수면', 'time_duration': ['5:59:0', '6:2:0'] },
  { 'date': '2023-3-8', 'stage_type': '비수면', 'time_duration': ['5:58:0', '5:59:0'] },
  { 'date': '2023-3-8', 'stage_type': '얕은수면', 'time_duration': ['5:54:0', '5:58:0'] },
  { 'date': '2023-3-8', 'stage_type': '비수면', 'time_duration': ['5:53:0', '5:54:0'] },
  { 'date': '2023-3-8', 'stage_type': '얕은수면', 'time_duration': ['5:50:0', '5:53:0'] },
  { 'date': '2023-3-8', 'stage_type': '렘수면', 'time_duration': ['5:49:0', '5:50:0'] },
  { 'date': '2023-3-8', 'stage_type': '비수면', 'time_duration': ['5:44:0', '5:49:0'] },
  { 'date': '2023-3-8', 'stage_type': '렘수면', 'time_duration': ['5:40:0', '5:44:0'] },
  { 'date': '2023-3-8', 'stage_type': '비수면', 'time_duration': ['5:38:0', '5:40:0'] },
  { 'date': '2023-3-8', 'stage_type': '얕은수면', 'time_duration': ['5:34:0', '5:38:0'] },
  { 'date': '2023-3-8', 'stage_type': '렘수면', 'time_duration': ['5:32:0', '5:34:0'] },
  { 'date': '2023-3-8', 'stage_type': '비수면', 'time_duration': ['5:11:0', '5:32:0'] },
  { 'date': '2023-3-8', 'stage_type': '얕은수면', 'time_duration': ['5:4:0', '5:11:0'] },
  { 'date': '2023-3-8', 'stage_type': '깊은수면', 'time_duration': ['4:51:0', '5:4:0'] },
  { 'date': '2023-3-8', 'stage_type': '렘수면', 'time_duration': ['4:50:0', '4:51:0'] },
  { 'date': '2023-3-8', 'stage_type': '깊은수면', 'time_duration': ['4:48:0', '4:50:0'] },
  { 'date': '2023-3-8', 'stage_type': '렘수면', 'time_duration': ['4:26:0', '4:48:0'] },
  { 'date': '2023-3-8', 'stage_type': '비수면', 'time_duration': ['4:24:0', '4:26:0'] },
  { 'date': '2023-3-8', 'stage_type': '렘수면', 'time_duration': ['4:16:0', '4:24:0'] },
  { 'date': '2023-3-8', 'stage_type': '비수면', 'time_duration': ['4:15:0', '4:16:0'] },
  { 'date': '2023-3-8', 'stage_type': '깊은수면', 'time_duration': ['3:56:0', '4:15:0'] },
  { 'date': '2023-3-8', 'stage_type': '렘수면', 'time_duration': ['3:46:0', '3:56:0'] },
  { 'date': '2023-3-8', 'stage_type': '비수면', 'time_duration': ['3:45:0', '3:46:0'] },
  { 'date': '2023-3-8', 'stage_type': '깊은수면', 'time_duration': ['3:37:0', '3:45:0'] },
  { 'date': '2023-3-8', 'stage_type': '렘수면', 'time_duration': ['3:22:0', '3:37:0'] },
  { 'date': '2023-3-8', 'stage_type': '비수면', 'time_duration': ['3:19:0', '3:22:0'] },
  { 'date': '2023-3-8', 'stage_type': '얕은수면', 'time_duration': ['3:18:0', '3:19:0'] },
  { 'date': '2023-3-8', 'stage_type': '렘수면', 'time_duration': ['3:17:0', '3:18:0'] },
  { 'date': '2023-3-8', 'stage_type': '얕은수면', 'time_duration': ['3:7:0', '3:17:0'] },
  { 'date': '2023-3-8', 'stage_type': '비수면', 'time_duration': ['3:5:0', '3:7:0'] },
  { 'date': '2023-3-8', 'stage_type': '얕은수면', 'time_duration': ['3:4:0', '3:5:0'] },
  { 'date': '2023-3-8', 'stage_type': '비수면', 'time_duration': ['3:3:0', '3:4:0'] },
  { 'date': '2023-3-8', 'stage_type': '얕은수면', 'time_duration': ['2:59:0', '3:3:0'] },
  { 'date': '2023-3-8', 'stage_type': '렘수면', 'time_duration': ['2:58:0', '2:59:0'] },
  { 'date': '2023-3-8', 'stage_type': '얕은수면', 'time_duration': ['2:57:0', '2:58:0'] },
  { 'date': '2023-3-8', 'stage_type': '렘수면', 'time_duration': ['2:56:0', '2:57:0'] },
  { 'date': '2023-3-8', 'stage_type': '비수면', 'time_duration': ['2:55:0', '2:56:0'] },
  { 'date': '2023-3-8', 'stage_type': '렘수면', 'time_duration': ['2:54:0', '2:55:0'] },
  { 'date': '2023-3-8', 'stage_type': '비수면', 'time_duration': ['2:52:0', '2:54:0'] },
  { 'date': '2023-3-8', 'stage_type': '렘수면', 'time_duration': ['2:13:0', '2:52:0'] },
  { 'date': '2023-3-8', 'stage_type': '비수면', 'time_duration': ['23:53:0', '0:11:0'] },
  { 'date': '2023-3-8', 'stage_type': '얕은수면', 'time_duration': ['23:31:0', '23:53:0'] },
  { 'date': '2023-3-8', 'stage_type': '깊은수면', 'time_duration': ['23:28:0', '23:31:0'] },
  { 'date': '2023-3-8', 'stage_type': '렘수면', 'time_duration': ['23:4:0', '23:28:0'] },
  { 'date': '2023-3-7', 'stage_type': '비수면', 'time_duration': ['6:36:0', '6:43:0'] },
  { 'date': '2023-3-7', 'stage_type': '렘수면', 'time_duration': ['6:34:0', '6:36:0'] },
  { 'date': '2023-3-7', 'stage_type': '비수면', 'time_duration': ['6:30:0', '6:34:0'] },
  { 'date': '2023-3-7', 'stage_type': '렘수면', 'time_duration': ['6:23:0', '6:30:0'] },
  { 'date': '2023-3-7', 'stage_type': '비수면', 'time_duration': ['6:16:0', '6:23:0'] },
  { 'date': '2023-3-7', 'stage_type': '렘수면', 'time_duration': ['6:15:0', '6:16:0'] },
  { 'date': '2023-3-7', 'stage_type': '비수면', 'time_duration': ['6:8:0', '6:15:0'] },
  { 'date': '2023-3-7', 'stage_type': '렘수면', 'time_duration': ['6:7:0', '6:8:0'] },
  { 'date': '2023-3-7', 'stage_type': '비수면', 'time_duration': ['6:6:0', '6:7:0'] },
  { 'date': '2023-3-7', 'stage_type': '렘수면', 'time_duration': ['6:2:0', '6:6:0'] },
  { 'date': '2023-3-7', 'stage_type': '비수면', 'time_duration': ['5:44:0', '6:2:0'] },
  { 'date': '2023-3-7', 'stage_type': '렘수면', 'time_duration': ['5:42:0', '5:44:0'] },
  { 'date': '2023-3-7', 'stage_type': '비수면', 'time_duration': ['5:28:0', '5:42:0'] },
  { 'date': '2023-3-7', 'stage_type': '렘수면', 'time_duration': ['5:23:0', '5:28:0'] },
  { 'date': '2023-3-7', 'stage_type': '비수면', 'time_duration': ['5:21:0', '5:23:0'] },
  { 'date': '2023-3-7', 'stage_type': '얕은수면', 'time_duration': ['4:42:0', '5:21:0'] },
  { 'date': '2023-3-7', 'stage_type': '렘수면', 'time_duration': ['4:40:0', '4:42:0'] },
  { 'date': '2023-3-7', 'stage_type': '얕은수면', 'time_duration': ['4:39:0', '4:40:0'] },
  { 'date': '2023-3-7', 'stage_type': '렘수면', 'time_duration': ['4:38:0', '4:39:0'] },
  { 'date': '2023-3-7', 'stage_type': '얕은수면', 'time_duration': ['4:37:0', '4:38:0'] },
  { 'date': '2023-3-7', 'stage_type': '렘수면', 'time_duration': ['4:35:0', '4:37:0'] },
  { 'date': '2023-3-7', 'stage_type': '얕은수면', 'time_duration': ['4:34:0', '4:35:0'] },
  { 'date': '2023-3-7', 'stage_type': '렘수면', 'time_duration': ['4:29:0', '4:34:0'] },
  { 'date': '2023-3-7', 'stage_type': '비수면', 'time_duration': ['4:28:0', '4:29:0'] },
  { 'date': '2023-3-7', 'stage_type': '렘수면', 'time_duration': ['4:12:0', '4:28:0'] },
  { 'date': '2023-3-7', 'stage_type': '비수면', 'time_duration': ['4:11:0', '4:12:0'] },
  { 'date': '2023-3-7', 'stage_type': '렘수면', 'time_duration': ['4:4:0', '4:11:0'] },
  { 'date': '2023-3-7', 'stage_type': '비수면', 'time_duration': ['4:3:0', '4:4:0'] },
  { 'date': '2023-3-7', 'stage_type': '렘수면', 'time_duration': ['3:39:0', '4:3:0'] },
  { 'date': '2023-3-7', 'stage_type': '비수면', 'time_duration': ['3:38:0', '3:39:0'] },
  { 'date': '2023-3-7', 'stage_type': '렘수면', 'time_duration': ['3:31:0', '3:38:0'] },
  { 'date': '2023-3-7', 'stage_type': '비수면', 'time_duration': ['3:22:0', '3:31:0'] },
  { 'date': '2023-3-7', 'stage_type': '렘수면', 'time_duration': ['3:18:0', '3:22:0'] },
  { 'date': '2023-3-7', 'stage_type': '비수면', 'time_duration': ['3:17:0', '3:18:0'] },
  { 'date': '2023-3-7', 'stage_type': '렘수면', 'time_duration': ['3:16:0', '3:17:0'] },
  { 'date': '2023-3-7', 'stage_type': '비수면', 'time_duration': ['3:14:0', '3:16:0'] },
  { 'date': '2023-3-7', 'stage_type': '렘수면', 'time_duration': ['2:30:0', '3:14:0'] },
  { 'date': '2023-3-6', 'stage_type': '비수면', 'time_duration': ['6:39:0', '6:43:0'] },
  { 'date': '2023-3-6', 'stage_type': '얕은수면', 'time_duration': ['6:37:0', '6:39:0'] },
  { 'date': '2023-3-6', 'stage_type': '렘수면', 'time_duration': ['6:31:0', '6:37:0'] },
  { 'date': '2023-3-6', 'stage_type': '비수면', 'time_duration': ['6:29:0', '6:31:0'] },
  { 'date': '2023-3-6', 'stage_type': '렘수면', 'time_duration': ['6:28:0', '6:29:0'] },
  { 'date': '2023-3-6', 'stage_type': '비수면', 'time_duration': ['6:27:0', '6:28:0'] },
  { 'date': '2023-3-6', 'stage_type': '렘수면', 'time_duration': ['6:12:0', '6:27:0'] },
  { 'date': '2023-3-6', 'stage_type': '비수면', 'time_duration': ['6:11:0', '6:12:0'] },
  { 'date': '2023-3-6', 'stage_type': '렘수면', 'time_duration': ['6:6:0', '6:11:0'] },
  { 'date': '2023-3-6', 'stage_type': '비수면', 'time_duration': ['6:4:0', '6:6:0'] },
  { 'date': '2023-3-6', 'stage_type': '렘수면', 'time_duration': ['6:0:0', '6:4:0'] },
  { 'date': '2023-3-6', 'stage_type': '깊은수면', 'time_duration': ['5:59:0', '6:0:0'] },
  { 'date': '2023-3-6', 'stage_type': '렘수면', 'time_duration': ['5:58:0', '5:59:0'] },
  { 'date': '2023-3-6', 'stage_type': '깊은수면', 'time_duration': ['5:54:0', '5:58:0'] },
  { 'date': '2023-3-6', 'stage_type': '렘수면', 'time_duration': ['5:35:0', '5:54:0'] },
  { 'date': '2023-3-6', 'stage_type': '얕은수면', 'time_duration': ['5:0:0', '5:35:0'] },
  { 'date': '2023-3-6', 'stage_type': '비수면', 'time_duration': ['4:59:0', '5:0:0'] },
  { 'date': '2023-3-6', 'stage_type': '얕은수면', 'time_duration': ['4:57:0', '4:59:0'] },
  { 'date': '2023-3-6', 'stage_type': '렘수면', 'time_duration': ['4:54:0', '4:57:0'] },
  { 'date': '2023-3-6', 'stage_type': '비수면', 'time_duration': ['4:34:0', '4:54:0'] },
  { 'date': '2023-3-6', 'stage_type': '얕은수면', 'time_duration': ['4:33:0', '4:34:0'] },
  { 'date': '2023-3-6', 'stage_type': '깊은수면', 'time_duration': ['4:31:0', '4:33:0'] },
  { 'date': '2023-3-6', 'stage_type': '렘수면', 'time_duration': ['3:52:0', '4:31:0'] },
  { 'date': '2023-3-6', 'stage_type': '비수면', 'time_duration': ['3:51:0', '3:52:0'] },
  { 'date': '2023-3-6', 'stage_type': '렘수면', 'time_duration': ['3:49:0', '3:51:0'] },
  { 'date': '2023-3-6', 'stage_type': '비수면', 'time_duration': ['3:48:0', '3:49:0'] },
  { 'date': '2023-3-6', 'stage_type': '렘수면', 'time_duration': ['3:41:0', '3:48:0'] },
  { 'date': '2023-3-6', 'stage_type': '비수면', 'time_duration': ['3:35:0', '3:41:0'] },
  { 'date': '2023-3-6', 'stage_type': '렘수면', 'time_duration': ['3:28:0', '3:35:0'] },
  { 'date': '2023-3-6', 'stage_type': '비수면', 'time_duration': ['3:21:0', '3:28:0'] },
  { 'date': '2023-3-6', 'stage_type': '렘수면', 'time_duration': ['3:18:0', '3:21:0'] },
  { 'date': '2023-3-6', 'stage_type': '비수면', 'time_duration': ['3:17:0', '3:18:0'] },
  { 'date': '2023-3-6', 'stage_type': '렘수면', 'time_duration': ['3:11:0', '3:17:0'] },
  { 'date': '2023-3-6', 'stage_type': '비수면', 'time_duration': ['3:10:0', '3:11:0'] },
  { 'date': '2023-3-6', 'stage_type': '깊은수면', 'time_duration': ['2:56:0', '3:10:0'] },
  { 'date': '2023-3-6', 'stage_type': '렘수면', 'time_duration': ['2:37:0', '2:56:0'] },
  { 'date': '2023-3-6', 'stage_type': '비수면', 'time_duration': ['2:36:0', '2:37:0'] },
  { 'date': '2023-3-6', 'stage_type': '렘수면', 'time_duration': ['2:31:0', '2:36:0'] },
  { 'date': '2023-3-6', 'stage_type': '비수면', 'time_duration': ['2:28:0', '2:31:0'] },
  { 'date': '2023-3-6', 'stage_type': '깊은수면', 'time_duration': ['2:26:0', '2:28:0'] },
  { 'date': '2023-3-6', 'stage_type': '렘수면', 'time_duration': ['2:18:0', '2:26:0'] },
  { 'date': '2023-3-6', 'stage_type': '비수면', 'time_duration': ['2:17:0', '2:18:0'] },
  { 'date': '2023-3-6', 'stage_type': '렘수면', 'time_duration': ['2:7:0', '2:17:0'] },
  { 'date': '2023-3-6', 'stage_type': '비수면', 'time_duration': ['2:4:0', '2:7:0'] },
  { 'date': '2023-3-6', 'stage_type': '렘수면', 'time_duration': ['2:1:0', '2:4:0'] },
  { 'date': '2023-3-6', 'stage_type': '비수면', 'time_duration': ['1:55:0', '2:1:0'] },
  { 'date': '2023-3-6', 'stage_type': '렘수면', 'time_duration': ['1:37:0', '1:55:0'] },
  { 'date': '2023-3-5', 'stage_type': '비수면', 'time_duration': ['7:48:0', '7:51:0'] },
  { 'date': '2023-3-5', 'stage_type': '렘수면', 'time_duration': ['7:41:0', '7:48:0'] },
  { 'date': '2023-3-5', 'stage_type': '얕은수면', 'time_duration': ['7:40:0', '7:41:0'] },
  { 'date': '2023-3-5', 'stage_type': '비수면', 'time_duration': ['7:39:0', '7:40:0'] },
  { 'date': '2023-3-5', 'stage_type': '얕은수면', 'time_duration': ['7:38:0', '7:39:0'] },
  { 'date': '2023-3-5', 'stage_type': '렘수면', 'time_duration': ['7:15:0', '7:38:0'] },
  { 'date': '2023-3-5', 'stage_type': '비수면', 'time_duration': ['7:13:0', '7:15:0'] },
  { 'date': '2023-3-5', 'stage_type': '렘수면', 'time_duration': ['6:59:0', '7:13:0'] },
  { 'date': '2023-3-5', 'stage_type': '비수면', 'time_duration': ['6:52:0', '6:59:0'] },
  { 'date': '2023-3-5', 'stage_type': '얕은수면', 'time_duration': ['6:50:0', '6:52:0'] },
  { 'date': '2023-3-5', 'stage_type': '비수면', 'time_duration': ['6:44:0', '6:50:0'] },
  { 'date': '2023-3-5', 'stage_type': '얕은수면', 'time_duration': ['6:37:0', '6:44:0'] },
  { 'date': '2023-3-5', 'stage_type': '깊은수면', 'time_duration': ['6:36:0', '6:37:0'] },
  { 'date': '2023-3-5', 'stage_type': '렘수면', 'time_duration': ['6:14:0', '6:36:0'] },
  { 'date': '2023-3-5', 'stage_type': '얕은수면', 'time_duration': ['6:5:0', '6:14:0'] },
  { 'date': '2023-3-5', 'stage_type': '렘수면', 'time_duration': ['5:46:0', '6:5:0'] },
  { 'date': '2023-3-5', 'stage_type': '비수면', 'time_duration': ['5:45:0', '5:46:0'] },
  { 'date': '2023-3-5', 'stage_type': '렘수면', 'time_duration': ['5:20:0', '5:45:0'] },
  { 'date': '2023-3-5', 'stage_type': '비수면', 'time_duration': ['5:19:0', '5:20:0'] },
  { 'date': '2023-3-5', 'stage_type': '렘수면', 'time_duration': ['5:18:0', '5:19:0'] },
  { 'date': '2023-3-5', 'stage_type': '얕은수면', 'time_duration': ['4:44:0', '5:18:0'] },
  { 'date': '2023-3-5', 'stage_type': '렘수면', 'time_duration': ['4:43:0', '4:44:0'] },
  { 'date': '2023-3-5', 'stage_type': '비수면', 'time_duration': ['4:41:0', '4:43:0'] },
  { 'date': '2023-3-5', 'stage_type': '렘수면', 'time_duration': ['4:36:0', '4:41:0'] },
  { 'date': '2023-3-5', 'stage_type': '비수면', 'time_duration': ['4:35:0', '4:36:0'] },
  { 'date': '2023-3-5', 'stage_type': '렘수면', 'time_duration': ['4:34:0', '4:35:0'] },
  { 'date': '2023-3-5', 'stage_type': '비수면', 'time_duration': ['4:21:0', '4:34:0'] },
  { 'date': '2023-3-5', 'stage_type': '렘수면', 'time_duration': ['4:20:0', '4:21:0'] },
  { 'date': '2023-3-5', 'stage_type': '비수면', 'time_duration': ['4:14:0', '4:20:0'] },
  { 'date': '2023-3-5', 'stage_type': '렘수면', 'time_duration': ['4:1:0', '4:14:0'] },
  { 'date': '2023-3-5', 'stage_type': '비수면', 'time_duration': ['4:0:0', '4:1:0'] },
  { 'date': '2023-3-5', 'stage_type': '렘수면', 'time_duration': ['3:56:0', '4:0:0'] },
  { 'date': '2023-3-5', 'stage_type': '비수면', 'time_duration': ['3:54:0', '3:56:0'] },
  { 'date': '2023-3-5', 'stage_type': '렘수면', 'time_duration': ['3:44:0', '3:54:0'] },
  { 'date': '2023-3-5', 'stage_type': '비수면', 'time_duration': ['3:43:0', '3:44:0'] },
  { 'date': '2023-3-5', 'stage_type': '깊은수면', 'time_duration': ['3:31:0', '3:43:0'] },
  { 'date': '2023-3-5', 'stage_type': '렘수면', 'time_duration': ['3:19:0', '3:31:0'] },
  { 'date': '2023-3-5', 'stage_type': '비수면', 'time_duration': ['3:16:0', '3:19:0'] },
  { 'date': '2023-3-5', 'stage_type': '렘수면', 'time_duration': ['3:15:0', '3:16:0'] },
  { 'date': '2023-3-5', 'stage_type': '비수면', 'time_duration': ['3:14:0', '3:15:0'] },
  { 'date': '2023-3-5', 'stage_type': '렘수면', 'time_duration': ['3:8:0', '3:14:0'] },
  { 'date': '2023-3-5', 'stage_type': '비수면', 'time_duration': ['3:6:0', '3:8:0'] },
  { 'date': '2023-3-5', 'stage_type': '렘수면', 'time_duration': ['3:3:0', '3:6:0'] },
  { 'date': '2023-3-5', 'stage_type': '비수면', 'time_duration': ['2:59:0', '3:3:0'] },
  { 'date': '2023-3-5', 'stage_type': '깊은수면', 'time_duration': ['2:47:0', '2:59:0'] },
  { 'date': '2023-3-5', 'stage_type': '렘수면', 'time_duration': ['2:41:0', '2:47:0'] },
  { 'date': '2023-3-5', 'stage_type': '깊은수면', 'time_duration': ['2:39:0', '2:41:0'] },
  { 'date': '2023-3-5', 'stage_type': '렘수면', 'time_duration': ['2:27:0', '2:39:0'] },
  { 'date': '2023-3-5', 'stage_type': '비수면', 'time_duration': ['2:25:0', '2:27:0'] },
  { 'date': '2023-3-5', 'stage_type': '깊은수면', 'time_duration': ['2:23:0', '2:25:0'] },
  { 'date': '2023-3-5', 'stage_type': '렘수면', 'time_duration': ['2:12:0', '2:23:0'] },
  { 'date': '2023-3-5', 'stage_type': '비수면', 'time_duration': ['2:7:0', '2:12:0'] },
  { 'date': '2023-3-5', 'stage_type': '렘수면', 'time_duration': ['1:36:0', '2:7:0'] },
  { 'date': '2023-3-4', 'stage_type': '얕은수면', 'time_duration': ['7:36:0', '7:52:0'] },
  { 'date': '2023-3-4', 'stage_type': '비수면', 'time_duration': ['7:35:0', '7:36:0'] },
  { 'date': '2023-3-4', 'stage_type': '얕은수면', 'time_duration': ['7:34:0', '7:35:0'] },
  { 'date': '2023-3-4', 'stage_type': '비수면', 'time_duration': ['7:33:0', '7:34:0'] },
  { 'date': '2023-3-4', 'stage_type': '얕은수면', 'time_duration': ['7:26:0', '7:33:0'] },
  { 'date': '2023-3-4', 'stage_type': '비수면', 'time_duration': ['7:25:0', '7:26:0'] },
  { 'date': '2023-3-4', 'stage_type': '얕은수면', 'time_duration': ['7:19:0', '7:25:0'] },
  { 'date': '2023-3-4', 'stage_type': '렘수면', 'time_duration': ['7:14:0', '7:19:0'] },
  { 'date': '2023-3-4', 'stage_type': '비수면', 'time_duration': ['7:13:0', '7:14:0'] },
  { 'date': '2023-3-4', 'stage_type': '렘수면', 'time_duration': ['7:9:0', '7:13:0'] },
  { 'date': '2023-3-4', 'stage_type': '비수면', 'time_duration': ['7:8:0', '7:9:0'] },
  { 'date': '2023-3-4', 'stage_type': '렘수면', 'time_duration': ['7:1:0', '7:8:0'] },
  { 'date': '2023-3-4', 'stage_type': '깊은수면', 'time_duration': ['7:0:0', '7:1:0'] },
  { 'date': '2023-3-4', 'stage_type': '렘수면', 'time_duration': ['6:11:0', '7:0:0'] },
  { 'date': '2023-3-4', 'stage_type': '비수면', 'time_duration': ['6:8:0', '6:11:0'] },
  { 'date': '2023-3-4', 'stage_type': '렘수면', 'time_duration': ['6:7:0', '6:8:0'] },
  { 'date': '2023-3-4', 'stage_type': '얕은수면', 'time_duration': ['5:17:0', '6:7:0'] },
  { 'date': '2023-3-4', 'stage_type': '렘수면', 'time_duration': ['5:8:0', '5:17:0'] },
  { 'date': '2023-3-4', 'stage_type': '비수면', 'time_duration': ['5:4:0', '5:8:0'] },
  { 'date': '2023-3-4', 'stage_type': '렘수면', 'time_duration': ['5:0:0', '5:4:0'] },
  { 'date': '2023-3-4', 'stage_type': '깊은수면', 'time_duration': ['4:49:0', '5:0:0'] },
  { 'date': '2023-3-4', 'stage_type': '렘수면', 'time_duration': ['4:37:0', '4:49:0'] },
  { 'date': '2023-3-4', 'stage_type': '비수면', 'time_duration': ['4:36:0', '4:37:0'] },
  { 'date': '2023-3-4', 'stage_type': '깊은수면', 'time_duration': ['4:35:0', '4:36:0'] },
  { 'date': '2023-3-4', 'stage_type': '렘수면', 'time_duration': ['4:22:0', '4:35:0'] },
  { 'date': '2023-3-4', 'stage_type': '비수면', 'time_duration': ['4:20:0', '4:22:0'] },
  { 'date': '2023-3-4', 'stage_type': '렘수면', 'time_duration': ['4:11:0', '4:20:0'] },
  { 'date': '2023-3-4', 'stage_type': '비수면', 'time_duration': ['3:49:0', '4:11:0'] },
  { 'date': '2023-3-4', 'stage_type': '렘수면', 'time_duration': ['3:11:0', '3:49:0'] },
]

export default {
  name: "ChartTest",
  data: function () {
    return {
      chartOptions: {
        chart: {
          heigth: 450,
          type: 'rangeBar'
        },
        xaxis: {
          type: 'datetime'
        },
        plotOptions: {
          bar: {
            horizontal: true,
            barHeight: '80%'
          }
        },
        stroke: {
          width: 1
        },
        fill: {
          type: 'solid',
          opacity: 0.6
        },
        legend: {
          position: 'top',
          horizontalAlign: 'left'
        },
      },
      series: [
        {
          name: 'Bob',
          data: [
            {
              x: 'Design',
              y: [
                new Date('2019-03-05').getTime(),
                new Date('2019-03-08').getTime()
              ]
            },
            {
              x: 'Code',
              y: [
                new Date('2019-03-02').getTime(),
                new Date('2019-03-05').getTime()
              ]
            },
            {
              x: 'Code',
              y: [
                new Date('2019-03-05').getTime(),
                new Date('2019-03-07').getTime()
              ]
            },
            {
              x: 'Test',
              y: [
                new Date('2019-03-03').getTime(),
                new Date('2019-03-09').getTime()
              ]
            },
            {
              x: 'Test',
              y: [
                new Date('2019-03-08').getTime(),
                new Date('2019-03-11').getTime()
              ]
            },
            {
              x: 'Validation',
              y: [
                new Date('2019-03-11').getTime(),
                new Date('2019-03-16').getTime()
              ]
            },
            {
              x: 'Design',
              y: [
                new Date('2019-03-01').getTime(),
                new Date('2019-03-03').getTime()
              ],
            }
          ]
        },
        {
          name: 'Joe',
          data: [
            {
              x: 'Design',
              y: [
                new Date('2019-03-02').getTime(),
                new Date('2019-03-05').getTime()
              ]
            },
            {
              x: 'Test',
              y: [
                new Date('2019-03-06').getTime(),
                new Date('2019-03-16').getTime()
              ],
              goals: [
                {
                  name: 'Break',
                  value: new Date('2019-03-10').getTime(),
                  strokeColor: '#CD2F2A'
                }
              ]
            },
            {
              x: 'Code',
              y: [
                new Date('2019-03-03').getTime(),
                new Date('2019-03-07').getTime()
              ]
            },
            {
              x: 'Deployment',
              y: [
                new Date('2019-03-20').getTime(),
                new Date('2019-03-22').getTime()
              ]
            },
            {
              x: 'Design',
              y: [
                new Date('2019-03-10').getTime(),
                new Date('2019-03-16').getTime()
              ]
            }
          ]
        },
        {
          name: 'Dan',
          data: [
            {
              x: 'Code',
              y: [
                new Date('2019-03-10').getTime(),
                new Date('2019-03-17').getTime()
              ]
            },
            {
              x: 'Validation',
              y: [
                new Date('2019-03-05').getTime(),
                new Date('2019-03-09').getTime()
              ],
              goals: [
                {
                  name: 'Break',
                  value: new Date('2019-03-07').getTime(),
                  strokeColor: '#CD2F2A'
                }
              ]
            },
          ]
        }
      ],
    }
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
