<template>
  <v-container>
    <h1>관심 지역</h1>
    <hr />
    <br />
    <v-container class="main" v-if="!getAccessToken">
      <h3>회원만 이용할 수 있습니다.</h3>
      <h3>회원 가입 후 HappyHouse를 이용해 보세요 :)</h3>
      <signup-modal />
    </v-container>
    <v-container class="main" v-else>
      <v-row>
        <router-link class="router-link" to="/region/view/aptDetail">
          <h2>아파트 둘러보기</h2>
        </router-link>
      </v-row>
      <v-row>
        <router-link class="router-link" to="/region/view/juDetail">
          <h2>주택 둘러보기</h2>
        </router-link>
      </v-row>
      <v-row>
        <v-btn large depressed color="primary" @click="moveRegionModify">
          관심 지역 수정
        </v-btn>
      </v-row>
    </v-container>
  </v-container>
</template>

<script>
import { mapGetters } from "vuex";
import SignupModal from "@/components/util/SignupModal.vue";

export default {
  name: "RegionChoose",
  data() {
    return {};
  },
  components: {
    SignupModal
  },
  computed: {
    ...mapGetters(["getAccessToken"])
  },
  methods: {
    moveRegionModify() {
      this.$router.replace(`/mypage/userinfomodify`);
    }
  }
};
</script>

<style scoped>
h2 {
  color: gray;
}
.main {
  margin-bottom: 50px;
  padding: 20px;
  border: 1px solid gray;
  border-radius: 20px;
}
.router-link {
  margin: 10px;
}
</style>
