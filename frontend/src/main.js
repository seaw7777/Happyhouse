import Vue from "vue";
import vuetify from "./plugins/vuetify";
import "./plugins/base";
import App from "./App.vue";
import router from "./router";
import store from "./store";

import * as VueGoogleMaps from "vue2-google-maps";

Vue.config.productionTip = false;
Vue.use(VueGoogleMaps, {
  load: {
    key: "AIzaSyANHmuFg33a00Xxoa3ri4xAq5SNDa2-6q0",
    libraries: "places"
  }
  // installComponents: true,
});

new Vue({
  vuetify,
  router,
  store,
  render: h => h(App)
}).$mount("#app");
