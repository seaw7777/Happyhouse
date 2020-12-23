<template>
  <v-container>
    <v-row>
      <v-col cols="12" lg="12">
        <Map :list="list" :index="index" />
      </v-col>
    </v-row>
    <v-row class="text-align-left">
      <v-col>
        <v-select
          :items="selectType"
          v-model="searchType"
          item-text="text"
          item-value="value"
          style="width: 120px;"
          dense
          outlined
        ></v-select>
      </v-col>

      <v-col>
        <v-text-field
          v-model="searchWord"
          placeholder="검색어를 입력..."
          single-line
          clearable
          dense
          solo
          style="width: 400px"
          @keypress.enter="searchHandler"
        ></v-text-field>
      </v-col>
      <v-col>
        <v-btn large depressed color="primary" @click="searchHandler"
          >검색</v-btn
        >
      </v-col>
    </v-row>
    <v-row style="width: 100%; display: inline-block">
      <v-col>
        <h2>
          <span v-if="type == 'aptName'">아파트</span>
          <span v-else>주택</span>
          거래 정보 : {{ houseName }}
        </h2>
      </v-col>
    </v-row>
    <hr />
    <v-row>
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
          @click="moveHouseView(index)"
        >
          <v-card-title>{{ deal.aptName }}</v-card-title>
        </v-img>

        <v-card-subtitle class="pb-0">
          {{ deal.dong }} {{ deal.jibun }}
          <span v-if="deal.floor != -1">{{ deal.floor }}층</span>
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

export default {
  name: "HouseView",
  components: {
    Map
  },
  data() {
    return {
      type: { type: String },
      houseName: { type: String },
      list: [],
      searchType: "",
      searchWord: "",
      selectType: [
        { text: "아파트", value: "aptName" },
        { text: "주택", value: "juName" }
      ],
      limit: 12,
      total: 0,
      index: 0,
      image: [{ apt: "apt01.png" }, { apt: "apt02.jpg" }, { apt: "apt03.jpg" }]
    };
  },
  created() {
    this.type = this.$route.query.type;
    this.searchType = this.type;

    this.houseName = this.$route.query.houseName;
    this.searchWord = this.houseName;
    this.getHouseList();
  },
  methods: {
    getHouseList() {
      axios
        .get(
          `http://localhost:8000/happyhouse/apt/${this.type}/${this.houseName}`
        )
        .then(({ data }) => {
          this.total = data.length;
          this.list = data.slice(0, this.limit);
        });
    },
    moveHouseView(index) {
      this.index = index;
    },
    searchHandler() {
      this.type = this.searchType;
      this.houseName = this.searchWord;
      this.limit = 12;
      this.getHouseList();
    },
    moreView() {
      this.limit += 12;
      this.getHouseList();
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
