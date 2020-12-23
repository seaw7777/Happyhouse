<template>
  <v-dialog v-model="close" persistent max-width="600px">
    <template v-slot:activator="{ on, attrs }">
      <v-btn text :color="btnColor" dark v-bind="attrs" v-on="on">
        SIGNUP
      </v-btn>
    </template>
    <v-card>
      <v-card-title>
        <span class="headline">SignUp</span>
      </v-card-title>
      <v-card-text>
        <v-container>
          <v-row>
            <v-col cols="12">
              <v-text-field
                label="ID* 를 입력해주세요"
                required
                v-model="user.id"
                ref="id"
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="12">
              <v-text-field
                label="Password* 를 입력해주세요"
                type="password"
                required
                v-model="user.pw"
                ref="pw"
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="12">
              <v-text-field
                label="name* 를 입력해주세요"
                required
                v-model="user.name"
                ref="name"
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="12">
              <v-select
                id="city"
                :items="citys"
                v-model="user.city"
                item-text="sidoName"
                item-value="sidoCode"
                dense
                outlined
                @change="getGugunCode"
              ></v-select>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="12">
              <v-select
                id="gugun"
                :items="guguns"
                v-model="user.gu"
                v-if="user.city != '0'"
                item-text="gugunName"
                item-value="gugunCode"
                dense
                outlined
                @change="getDongCode"
              ></v-select>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="12">
              <v-select
                id="dong"
                :items="dongs"
                v-model="user.dong"
                v-if="user.gu != '0'"
                item-text="dong"
                item-value="dong"
                dense
                outlined
              ></v-select>
            </v-col>
          </v-row>
        </v-container>
      </v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="indigo darken-3" text @click="sign"> SignUp </v-btn>
        <v-btn color="indigo darken-3" text @click="close = false">
          CLOSE
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      btnColor: "black",
      close: false,
      citys: [],
      guguns: Array,
      dongs: Array,
      user: {
        id: "",
        pw: "",
        name: "",
        city: "0",
        gu: "0",
        dong: "0"
      }
    };
  },
  created() {
    console.log(`signup modal created`);
    this.citys = [
      {
        sidoCode: "0",
        sidoName: "도/광역시"
      }
    ];
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
      if (this.user.city == "0") return;
      this.user.gu = "0";
      this.user.dong = "동";

      this.guguns = [];
      this.guguns.push({
        gugunCode: "0",
        gugunName: "시/군/구"
      });
      axios
        .get(`http://localhost:8000/happyhouse/map/gu/${this.user.city}`)
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
      if (this.user.gu == "0") return;

      this.user.dong = "동";

      this.dongs = [];
      this.dongs.push({
        code: "0",
        dong: "동"
      });
      axios
        .get(`http://localhost:8000/happyhouse/map/dong/${this.user.gu}`)
        .then(({ data }) => {
          data.forEach(dong => {
            this.dongs.push(dong);
          });
        })
        .catch(() => {
          console.log("동 코드 로딩 실패");
        });
    },
    moveInfoPage(type) {
      if (!this.$route.query.type)
        this.$router.replace(`/apt?type=${type}&dong=${this.dong}`);
      else if (this.$route.query.type != type) this.$emit(`update-type`, type);
    },
    sign() {
      console.log("진입");
      let err = true;
      let msg = "";
      !this.user.id &&
        ((msg = "아이디를 입력해주세요"), (err = false), this.$refs.id.focus());
      err &&
        !this.user.pw &&
        ((msg = "비밀번호를 입력해주세요"),
        (err = false),
        this.$refs.pw.focus());
      err &&
        !this.user.name &&
        ((msg = "이름을 입력해주세요"), (err = false), this.$refs.name.focus());

      if (!err) alert(msg);
      else this.createHandler();
    },
    createHandler() {
      axios
        .post(`http://localhost:8000/happyhouse/member/sign`, this.user)
        .then(({ data }) => {
          let msg = "회원 가입중 문제가 발생했습니다. 다시 시도 해주세요.";
          if (data == "success") msg = "등록이 완료되었습니다.";
          alert(msg);
          this.close = false;
        });
    }
  }
};
</script>
