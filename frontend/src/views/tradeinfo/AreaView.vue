<template>
  <v-container>
    <v-row>
      <v-col cols="12" lg="9">
        <Map :list="list" :index="index" @update-dong="updateDong" />
      </v-col>
      <v-col cols="12" lg="3">
        <MapSearch @patch-dong="updateDong" @update-type="updateType" />
      </v-col>
    </v-row>

    <v-row v-if="type == 'aptName'">
      <h2>아파트 거래 정보({{ dong }})</h2>
    </v-row>
    <v-row v-if="type == 'juName'">
      <h2>주택 거래 정보({{ dong }})</h2>
    </v-row>
    <hr />

    <v-row class="text-align-left">
      <v-card
        v-for="(deal, index) in list"
        :key="index"
        class="mx-auto apt-card"
        width="400"
      >
        <v-img
          class="white--text align-end"
          height="200px"
          :src="require(`@/assets/${image[Math.floor(Math.random() * 3)].apt}`)"
          @click="moveHouseView(deal.aptName)"
        >
          <v-card-title>{{ deal.aptName }}</v-card-title>
        </v-img>

        <v-card-subtitle class="pb-0">
          {{ deal.dong }} {{ deal.jibun }}
        </v-card-subtitle>

        <v-card-text class="text--primary">
          <div>거래 금액 : {{ deal.dealAmount }}</div>

          <div>면적 : {{ deal.area }}</div>
        </v-card-text>
      </v-card>
    </v-row>
    <!-- more button -->
    <v-row
      v-if="list.length % 12 == 0 && list.length != 0 && list.length != total"
    >
      <v-col cols="12" lg="5">
        <hr />
      </v-col>
      <v-col cols="12" lg="2" style="text-align: center">
        <v-btn text color="deep-purple accent-4" @click="moreView">
          More
        </v-btn>
      </v-col>
      <v-col cols="12" lg="5">
        <hr />
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from "axios";
import Map from "@/components/util/Map";
import MapSearch from "@/components/util/MapSearch";

export default {
  name: "AreaView",
  data() {
    return {
      type: { type: String },
      city: { type: String },
      gu: { type: String },
      dong: { type: String },
      list: [],
      index: 0,
      limit: 12,
      total: 0,
      image: [{ apt: "apt01.png" }, { apt: "apt02.jpg" }, { apt: "apt03.jpg" }]
    };
  },
  components: {
    Map,
    MapSearch
  },
  created() {
    this.type = this.$route.query.type;
    this.dong = this.$route.query.dong;
  },
  watch: {
    dong() {
      if (this.type == "aptName") this.getHouseDealInfo();
      else this.getJuteakDealInfo();
    }
  },
  methods: {
    updateDong(dong) {
      this.dong = dong;
    },
    updateType(type) {
      console.log(`type : ${type}`);
      this.type = type;
      this.$route.query.type = type;
      if (this.type == "aptName") this.getHouseDealInfo();
      else this.getJuteakDealInfo();
    },
    getHouseDealInfo() {
      let url = `http://localhost:8000/happyhouse/apt/aptDetail/${this.dong}`;
      axios
        .get(url)
        .then(({ data }) => {
          this.total = data.length;
          this.list = data.slice(0, this.limit);
        })
        .catch(() => {
          alert(`에러 발생`);
        });
    },
    getJuteakDealInfo() {
      let url = `http://localhost:8000/happyhouse/apt/juDetail/${this.dong}`;
      axios
        .get(url)
        .then(({ data }) => {
          this.total = data.length;
          this.list = data.slice(0, this.limit);
        })
        .catch(() => {
          alert(`에러 발생`);
        });
    },
    moveHouseView(houseName) {
      this.$router.replace(`/house?houseName=${houseName}&type=${this.type}`);
    },
    moreView() {
      this.limit += 12;
      if (this.type == "aptName") this.getHouseDealInfo();
      else this.getJuteakDealInfo();
    }
  }
};
</script>

<style scoped>
.text-align-left {
  float: left;
}
.apt-card {
  margin: 10px !important;
}
</style>
