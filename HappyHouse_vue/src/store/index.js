import Vue from "vue";
import Vuex from "vuex";
import axios from "axios";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    accessToken: null,
    id: "",
    name: "",
    state: { type: Number },
    city: "0",
    gu: "0",
    dong: "동",
    drawer: false,
    items: [
      {
        text: "Home",
        href: "/"
      },
      {
        text: "Notice",
        href: "/notice"
      },
      {
        text: "Corona",
        href: "/corona/clinic"
      },
      {
        text: "Today's News",
        href: "/todaynews"
      },
      {
        text: "Area Info",
        href: "/store"
      }
    ]
  },
  getters: {
    getAccessToken(state) {
      return state.accessToken;
    },
    getUserId(state) {
      return state.id;
    },
    getUserName(state) {
      return state.name;
    },
    getUserState(state) {
      return state.state;
    },
    getUserCity(state) {
      return state.city;
    },
    getUserGu(state) {
      return state.gu;
    },
    getUserDong(state) {
      return state.dong;
    },
    links(state) {
      return state.items;
    }
  },
  mutations: {
    LOGIN(state, payload) {
      state.accessToken = payload["auth-token"];
      state.id = payload["user-id"];
      state.name = payload["user-name"];
      state.state = payload["user-state"];
      state.city = payload["user-city"];
      state.dong = payload["user-dong"];
      state.gu = payload["user-gu"];
    },
    LOGOUT(state) {
      state.accessToken = null;
      state.id = "";
      state.name = "";
      state.state = null;
      state.city = "0";
      state.gu = "0";
      state.dong = "동";
    },
    setDrawer: (state, payload) => (state.drawer = payload),
    toggleDrawer: state => (state.drawer = !state.drawer)
  },
  actions: {
    LOGIN(context, user) {
      console.log(user);
      return axios
        .post("http://localhost:8000/happyhouse/member/confirm/login", user)
        .then(response => {
          context.commit("LOGIN", response.data);
          axios.defaults.headers.common[
            "auth-token"
          ] = `${response.data["auth-token"]}`;
        });
    },
    LOGOUT(context) {
      context.commit("LOGOUT");
      axios.defaults.headers.common["auth-token"] = undefined;
    }
  },
  modules: {}
});
