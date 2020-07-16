import Vue from 'vue'
import app from './app.vue'
import router from './router'
import axios from 'axios'

Vue.config.productionTip = false
Vue.prototype.$ajax = axios

/**
 * axios filter
 */
axios.interceptors.request.use(function (config) {
  console.log("Request：", config);
  return config;
}, error => {});
axios.interceptors.response.use(function (response) {
  console.log("Response：", response);
  return response;
}, error => {});

new Vue({
  router,
  render: h => h(app)
}).$mount('#app')
