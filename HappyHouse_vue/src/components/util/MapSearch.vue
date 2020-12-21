<template>
  <v-container>
    <h1><b>지역 선택</b></h1>
    <v-row>
      <v-select
        id="city"
        :items="citys"
        v-model="city"
        item-text="sidoName"
        item-value="sidoCode"
        label="도/광역시"
        dense
        outlined
      ></v-select>
    </v-row>
    <v-row>
      <v-select
        id="gugun"
        v-if="city != '0'"
        :items="guguns"
        v-model="gu"
        item-text="gugunName"
        item-value="gugunCode"
        label="시/군/구"
        dense
        outlined
      ></v-select>
    </v-row>
    <v-row>
      <v-select
        id="dong"
        v-if="gu != '0'"
        :items="dongs"
        v-model="dong"
        item-text="dong"
        item-value="dong"
        label="동"
        dense
        outlined
      ></v-select>
    </v-row>
    <v-row v-if="dong != '동'">
      <v-col>
        <v-btn large depressed color="primary" @click="moveInfoPage(`aptName`)">
          아파트 정보 보기
        </v-btn>
      </v-col>
      <v-col>
        <v-btn large depressed color="primary" @click="moveInfoPage(`juName`)">
          주택 정보 보기
        </v-btn>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from "axios";
import { mapGetters } from "vuex";

export default {
  data() {
    return {
      citys: Array,
      guguns: Array,
      dongs: Array,
      city: "0",
      gu: "0",
      dong: "동"
    };
  },
  created() {
    if (this.$route.query.type != undefined) {
      this.city = this.$route.query.city;
      this.gu = this.$route.query.gu;
      this.dong = this.$route.query.dong;
    } else if (this.getAccessToken) {
      this.city = this.getUserCity;
      this.gu = this.getUserGu;
      this.dong = this.getUserDong;
    }
    this.citys = [];

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
  computed: {
    ...mapGetters(["getAccessToken", "getUserCity", "getUserGu", "getUserDong"])
  },
  watch: {
    city() {
      this.getGugunCode();
    },
    gu() {
      this.getDongCode();
    },
    dong() {
      this.patchDong();
    },
    getUserCity() {
      this.city = this.getUserCity;
    },
    getUserGu() {
      this.gu = this.getUserGu;
    },
    getUserDong() {
      this.dong = this.getUserDong;
    }
  },
  methods: {
    getGugunCode() {
      if (this.city == "0") return;

      this.guguns = [];
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

      this.dongs = [];
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
    patchDong() {
      console.log("MapSearch : " + this.dong);
      this.$emit("patch-dong", this.dong);
    },
    moveInfoPage(type) {
      if (!this.$route.query.type)
        this.$router.replace(
          `/apt?type=${type}&city=${this.city}&gu=${this.gu}&dong=${this.dong}`
        );
      else if (this.$route.query.type != type) this.$emit(`update-type`, type);
    }
  }
};
</script>

<style>
#search-apt-btn {
  margin: 2px;
  width: 150px;
}
#search-ju-btn {
  margin: 2px;
  width: 150px;
}

#city,
#gu,
#dong,
#search-btn {
  width: 150px;
  margin-bottom: 5px;
}
</style>
