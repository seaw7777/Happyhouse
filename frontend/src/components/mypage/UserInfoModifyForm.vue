<template>
  <v-form>
    <v-container>
      <v-row>
        <v-col cols="12" md="12">
          <v-text-field v-model="name" label="NAME" ref="NAME"></v-text-field>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12" md="12">
          <v-text-field
            type="password"
            v-model="pw"
            label="PW"
            ref="PW"
          ></v-text-field>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12" md="12">
          <v-select
            id="city"
            :items="citys"
            v-model="city"
            item-text="sidoName"
            item-value="sidoCode"
            dense
            outlined
            @change="getGugunCode"
          ></v-select>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12" md="12">
          <v-select
            id="gugun"
            :items="guguns"
            v-model="gu"
            v-if="city != '0'"
            item-text="gugunName"
            item-value="gugunCode"
            dense
            outlined
            @change="getDongCode"
          ></v-select>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12" md="12">
          <v-select
            id="dong"
            :items="dongs"
            v-model="dong"
            v-if="gu != '0'"
            item-text="dong"
            item-value="dong"
            dense
            outlined
          ></v-select>
        </v-col>
      </v-row>
      <!-- button -->
      <v-row class="float-left">
        <v-col>
          <v-btn large depressed color="primary" @click="checkHandler"
            >수정</v-btn
          >
        </v-col>
        <v-col>
          <v-btn large depressed color="primary" @click="moveMain">메인</v-btn>
        </v-col>
      </v-row>
    </v-container>
  </v-form>
</template>

<script>
import axios from "axios";
export default {
  props: {
    ID: { type: String },
    NAME: { type: String },
    CITY: { type: String },
    GU: { type: String },
    DONG: { type: String }
  },
  data() {
    return {
      citys: Array,
      guguns: Array,
      dongs: Array,
      name: "",
      pw: "",
      city: "0",
      gu: "0",
      dong: "0"
    };
  },
  created() {
    this.name = this.NAME;

    this.citys = [];
    this.citys.push({
      sidoCode: "0",
      sidoName: "도/광역시"
    });
    axios
      .get("http://localhost:8000/happyhouse/map/city")
      .then(({ data }) => {
        data.forEach(city => {
          this.citys.push(city);
        });
      })
      .catch(() => {
        console.log("시 코드 로딩 실패");
      });
  },
  methods: {
    getGugunCode() {
      if (this.city == "0") return;
      this.gu = "0";
      this.dong = "동";

      this.guguns = [];
      this.guguns.push({
        gugunCode: "0",
        gugunName: "시/군/구"
      });
      axios
        .get(`http://localhost:8000/happyhouse/map/gu/${this.city}`)
        .then(({ data }) => {
          data.forEach(gugun => {
            this.guguns.push(gugun);
          });
        })
        .catch(() => {
          console.log("구군 코드 로딩 실패");
        });
    },
    getDongCode() {
      if (this.gu == "0") return;

      this.dong = "동";

      this.dongs = [];
      this.dongs.push({
        code: "0",
        dong: "동"
      });
      axios
        .get(`http://localhost:8000/happyhouse/map/dong/${this.gu}`)
        .then(({ data }) => {
          data.forEach(dong => {
            this.dongs.push(dong);
          });
        })
        .catch(() => {
          console.log("동 코드 로딩 실패");
        });
    },
    checkHandler() {
      let err = true;
      let msg = "";
      !this.pw &&
        ((msg = "비밀번호를를 입력해주세요"),
        (err = false),
        this.$refs.PW.focus());
      err &&
        !this.name &&
        ((msg = "이름을 입력해주세요"), (err = false), this.$refs.NAME.focus());
      err &&
        !this.city &&
        ((msg = "도시를 입력해주세요"), (err = false), this.$refs.CITY.focus());

      console.log(this.pw + " , " + this.gu);
      if (!err) alert(msg);
      else this.type == this.updateHandler();
    },
    updateHandler() {
      axios
        .put(`http://localhost:8000/happyhouse/mypage/modify`, {
          id: this.ID,
          pw: this.pw,
          name: this.name,
          city: this.city,
          gu: this.gu,
          dong: this.dong
        })
        .then(({ data }) => {
          let msg = "처리시 문제가 발생했습니다.";
          if (data == "success") {
            msg = "수정이 완료되었습니다. 다시 로그인 해주세요.";
            this.$store
              .dispatch("LOGOUT")
              .then(() => this.$router.replace("/").catch(() => {}));
          } else {
            msg = "오류가 발생하였습니다. 다시 시도해주세요.";
          }
          alert(msg);
        })
        .catch(() => {
          alert("등록 처리시 에러가 발생했습니다");
        });
    },
    moveMain() {
      this.$router.replace("/");
    }
  }
};
</script>
