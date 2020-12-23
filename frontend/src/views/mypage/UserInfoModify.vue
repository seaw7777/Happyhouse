<template>
  <v-container>
    <h2 id="title">유저 정보</h2>
    <hr />
    <user-update
      :ID="`${getUserId}`"
      :NAME="`${getUserName}`"
      :CITY="`${getUserCity}`"
      :GU="`${getUserGu}`"
      :STATE="`${getUserState}`"
      :DONG="`${getUserDong}`"
    />
  </v-container>
</template>

<script>
import UserUpdate from "@/components/mypage/UserInfoModifyForm.vue";
import axios from "axios";
import { mapGetters } from "vuex";

export default {
  computed: {
    ...mapGetters([
      "getAccessToken",
      "getUserId",
      "getUserName",
      "getUserCity",
      "getUserState",
      "getUserGu",
      "getUserDong"
    ])
  },
  components: {
    UserUpdate
  }
};
</script>
