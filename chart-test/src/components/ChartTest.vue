<template>
    <div id="ChartTest">
        <apexcharts width="500" height="350" type="bar" :options="chartOptions" :series="series" />
    </div>
</template>
  
<script>
import VueApexCharts from 'apexcharts';

export default {
    name: "ChartTest",
    components: {
        apexcharts: VueApexCharts,
    },
    data: function() {
        return {
            chartOptions: {
                chart: { id: "basic-bar"},
                xaxis: { categories: [1991, 1992, 1993, 1994, 1995, 1996, 1997, 1998,]
                }
            },
            series: [ { name: "series-1", data: [30, 40, 45, 50, 49, 60, 70, 9]} ]
        }
    }
};

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
margin: 40px 0 0;
}
ul {
list-style-type: none;
padding: 0;
}
li {
display: inline-block;
margin: 0 10px;
}
a {
color: #42b983;
}
</style>
  