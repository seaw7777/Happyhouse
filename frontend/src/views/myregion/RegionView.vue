<template>
  <v-container>
    <h1>관심 지역</h1>
    <hr />
    <v-container v-if="getUserDong == null || getUserDong == '0'">
      <v-row class="router-link">
        <h2>관심 지역이 없습니다.</h2>
      </v-row>
      <v-row class="router-link">
        <h2>관심 지역을 설정해주세요.</h2>
      </v-row>
      <v-row class="router-link">
        <v-btn large depressed color="primary" @click="moveRegionModify">
          관심 지역 수정
        </v-btn>
      </v-row>
    </v-container>

    <v-container v-else>
      <h2>거래 정보({{ getUserDong }})</h2>
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
            :src="
              require(`@/assets/${image[Math.floor(Math.random() * 3)].apt}`)
            "
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
  </v-container>
</template>

<script>
import axios from "axios";
import { mapGetters } from "vuex";

export default {
  name: "RegionView",
  data() {
    return {
      type: { type: String },
      list: Array,
      limit: 12,
      total: 0,
      image: [{ apt: "apt01.png" }, { apt: "apt02.jpg" }, { apt: "apt03.jpg" }]
    };
  },
  computed: {
    ...mapGetters(["getAccessToken", "getUserDong"])
  },
  created() {
    this.type = this.$route.params.type;
    console.log(this.getUserDong);
    if (this.getUserDong == null || this.getUserDong == "동") return;

    this.getHouseList();
  },
  methods: {
    moveRegionModify() {
      this.$router.replace(``);
    },
    getHouseList() {
      axios
        .get(
          `http://localhost:8000/happyhouse/apt/${this.type}/${this.getUserDong}`
        )
        .then(({ data }) => {
          this.total = data.length;
          this.list = data.slice(0, this.limit);
        });
    },
    moreView() {
      this.limit += 12;
      this.getHouseList();
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
