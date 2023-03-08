import Vue from 'vue'
import App from './App.vue'

Vue.config.productionTip = false

import VueApexCharts from 'vue-apexcharts';
Vue.use(VueApexCharts)

Vue.component('apexChart', VueApexCharts)

new Vue({
  render: h => h(App),
}).$mount('#app')
