<template>
  <v-container>
    <h1>동네 업종 정보</h1>
    <hr />
    <v-row>
      <v-col>
        <v-select
          id="city"
          :items="citys"
          v-model="city"
          item-text="sidoName"
          item-value="sidoCode"
          dense
          outlined
          label="도/광역시"
          @change="getGugunCode"
        ></v-select>
      </v-col>
      <v-col>
        <v-select
          id="gugun"
          v-if="city != '0'"
          :items="guguns"
          v-model="gu"
          item-text="gugunName"
          item-value="gugunCode"
          dense
          outlined
          label="시/군/구"
          @change="getDongCode"
        ></v-select>
      </v-col>
      <v-col>
        <v-select
          id="dong"
          v-if="gu != '0'"
          :items="dongs"
          v-model="dong"
          item-text="dong"
          item-value="dong"
          dense
          outlined
          label="동"
          @change="getStoreList"
        ></v-select>
      </v-col>
    </v-row>
    <!-- 상권 정보 출력 -->
    <v-row>
      <v-simple-table style="width: 100%; display: inline-block">
        <template v-slot:default>
          <colgroup>
            <col style="width: 40%;" />
            <col style="width: 20%; text-align: center;" />
            <col style="width: 20%; text-align: center;" />
            <col style="width: 20%; text-align: center;" />
          </colgroup>
          <thead>
            <tr>
              <th>상호명</th>
              <th>대분류</th>
              <th>중분류</th>
              <th>법정동명</th>
            </tr>
          </thead>
          <tbody>
            <store-row
              v-for="(item, index) in stores"
              :key="index"
              :store="item"
            />
          </tbody>
        </template>
      </v-simple-table>
    </v-row>
    <!-- more button -->
    <v-row
      v-if="
        stores.length % 10 == 0 && stores.length != 0 && stores.length != total
      "
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
import { mapGetters } from "vuex";

import StoreRow from "@/components/base/StoreRow";

export default {
  name: "Store",
  data() {
    return {
      citys: Array,
      guguns: Array,
      dongs: Array,
      city: "0",
      gu: "0",
      dong: "동",
      stores: Array,
      limit: 10,
      total: 0
    };
  },
  components: {
    StoreRow
  },
  computed: {
    ...mapGetters(["getAccessToken", "getUserCity", "getUserGu", "getUserDong"])
  },
  created() {
    if (this.getAccessToken) {
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

    this.getStoreList();
  },
  watch: {
    city() {
      this.getGugunCode();
    },
    gu() {
      this.getDongCode();
    },
    dong() {
      this.getStoreList();
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
          this.dongs = data;
        })
        .catch(() => {
          console.log("동 코드 로딩 실패");
        });
    },
    getStoreList() {
      console.log(`getStoreList()`);
      axios
        .get(`http://localhost:8000/happyhouse/store/list/${this.dong}`)
        .then(({ data }) => {
          this.total = data.length;
          this.stores = data.slice(0, this.limit);
        });
    },
    moreView() {
      this.limit += 10;
      this.getStoreList();
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
