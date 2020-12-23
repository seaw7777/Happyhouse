<template>
  <v-app-bar app flat>
    <v-app-bar-nav-icon class="hidden-md-and-up" @click="toggleDrawer" />

    <v-container class="mx-auto py-0">
      <v-row align="center">
        <v-img
          :src="require('@/assets/logo.png')"
          class="mr-5"
          contain
          height="48"
          width="48"
          max-width="48"
          @click="$router.replace('/')"
        />

        <v-btn class="hidden-sm-and-down" text @click="onClick('/')">
          Home
        </v-btn>
        <v-btn class="hidden-sm-and-down" text @click="onClick('/notice')">
          Notice
        </v-btn>
        <v-btn
          class="hidden-sm-and-down"
          text
          @click="onClick('/corona/clinic')"
        >
          Corona
        </v-btn>
        <v-btn class="hidden-sm-and-down" text @click="onClick('/todaynews')">
          Today's News
        </v-btn>
        <v-btn class="hidden-sm-and-down" text @click="onClick('/store')">
          Area Info
        </v-btn>
        <v-btn class="hidden-sm-and-down" text @click="onClick('/region')">
          My Region
        </v-btn>
        <template v-if="!getAccessToken">
          <div>
            <login-modal />

            <signup-modal />
          </div>
        </template>
        <!-- 로그인 시 보여줌 -->
        <template v-else>
          <div>
            <v-btn
              class="hidden-sm-and-down"
              text
              @click.prevent="onClickLogout"
            >
              Logout
            </v-btn>
            <v-btn
              class="hidden-sm-and-down"
              text
              @click="onClick('/mypage/userinfo')"
            >
              MyPage
            </v-btn>
            <v-btn
              v-if="getUserState == 100"
              class="hidden-sm-and-down"
              text
              @click="onClick('/admin/list')"
            >
              Member List
            </v-btn>
          </div>
        </template>

        <v-spacer />
      </v-row>
    </v-container>
  </v-app-bar>
</template>

<script>
// Utilities
import LoginModal from "@/components/util/LoginModal.vue";
import SignupModal from "@/components/util/SignupModal.vue";
import { mapMutations, mapGetters } from "vuex";

export default {
  name: "CoreAppBar",
  data: () => ({
    user: {
      id: "",
      pw: ""
    },
    message: "",
    id: "",
    pw: "",
    email: ""
  }),
  components: {
    LoginModal,
    SignupModal
  },
  computed: {
    ...mapGetters([
      "getAccessToken",
      "getUserId",
      "getUserName",
      "getUserState",
      "links"
    ]),
    nextRoute() {
      return this.$route.params.nextRoute ? this.$route.params.nextRoute : "";
    }
  },
  methods: {
    ...mapMutations(["toggleDrawer"]),
    onClickLogout() {
      this.$store
        .dispatch("LOGOUT")
        .then(() => alert(`로그아웃 되었습니다`))
        .catch(() => {});
    },
    onClick(link) {
      this.$router.replace(link);
    }
  }
};
</script>
