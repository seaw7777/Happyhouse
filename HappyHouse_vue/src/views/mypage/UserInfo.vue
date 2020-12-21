<template>
  <v-container>
    <h2 id="title">유저 정보</h2>
    <hr />
    <v-container>
      <v-row>
        <v-col cols="12" lg="8">
          <v-text-field
            label="아이디"
            required
            readonly
            :value="getUserId"
          ></v-text-field>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12" lg="8">
          <v-text-field
            label="이름"
            required
            readonly
            :value="getUserName"
          ></v-text-field>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12" lg="8">
          <v-text-field
            label="동"
            required
            readonly
            :value="getUserDong"
          ></v-text-field>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12" lg="8">
          <v-text-field
            label="직급"
            required
            readonly
            :value="getUserState == 100 ? '관리자' : '회원'"
          ></v-text-field>
        </v-col>
      </v-row>

      <v-row>
        <v-btn color="primary" class="btn" @click="moveuserinfomodifypage">
          수정
        </v-btn>
        <v-btn color="primary" class="btn" @click="removeuser">탈퇴</v-btn>
      </v-row>
    </v-container>

    <div id="btnArea">
      <br />
      <br />
      <br />
      <br />
    </div>
  </v-container>
</template>

<script>
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
  methods: {
    moveuserinfomodifypage() {
      this.$router.replace("/mypage/userinfomodify");
    },
    removeuser() {
      axios
        .delete(
          // `http://localhost:8000/happyhouse/mypage/delete?id=${this.getUserId}`
          `http://localhost:8000/happyhouse/mypage/delete/${this.getUserId}`
        )
        .then(() => {
          alert("탈퇴 완료");
          this.$store
            .dispatch("LOGOUT")
            .then(() => this.$router.replace("/").catch(() => {}));
        })
        .catch(() => {
          alert("error");
        });
    }
  }
};
</script>

<style scope>
.margin-auto {
  margin: 0 auto;
  text-align: center;
}
.float-left {
  float: left;
  margin: auto;
  text-align: center;
}
.float-right {
  float: right;
  margin: auto;
  text-align: center;
}
.btn {
  text-align: right;
  float: right;
  margin: 5px;
  size: 50px;
}
</style>
