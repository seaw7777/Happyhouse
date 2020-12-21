<template>
  <div id="home">
    <v-container>
      <!-- 구글 맵 그리기 -->
      <v-row>
        <v-col cols="12" lg="9">
          <Map :list="list" :index="index" @update-dong="updateDong" />
        </v-col>
        <v-col cols="12" lg="3">
          <MapSearch @patch-dong="updateDong" />
        </v-col>
      </v-row>
      <v-row>
        <!-- 공지사항 카드 -->
        <v-col cols="12" lg="6">
          <NoticeCard />
        </v-col>
        <v-col cols="12" lg="6">
          <NewsCard />
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import axios from "axios";
import { mapGetters } from "vuex";

import Map from "@/components/util/Map";
import MapSearch from "@/components/util/MapSearch";
import NoticeCard from "@/components/home/NoticeCard";
import NewsCard from "@/components/home/NewsCard";

export default {
  name: "Home",
  data() {
    return {
      dong: "동",
      list: [],
      index: 0
    };
  },
  components: {
    Map,
    MapSearch,
    NoticeCard,
    NewsCard
  },
  computed: {
    ...mapGetters(["getUserCity", "getUserGu", "getUserDong"])
  },
  methods: {
    updateDong(dong) {
      this.dong = dong;
      this.getAllInfo(dong);
    },
    getAllInfo(dong) {
      if (!dong || dong == "동") return;

      axios
        .get(`http://localhost:8000/happyhouse/map/all/${dong}`)
        .then(({ data }) => {
          this.list = data;
        })
        .catch(() => {
          console.log("동 코드 로딩 실패");
        });
    }
  }
};
</script>
