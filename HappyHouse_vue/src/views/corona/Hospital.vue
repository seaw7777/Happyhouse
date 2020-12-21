<template>
  <div>
    <v-container>
      <h1>국가 지정 안심 병원</h1>
      <hr />
      <!-- Heading Row -->
      <v-container>
        <v-row class="float-left">
          <v-col>
            <v-btn color="primary" @click="moveclinicpage">
              코로나 선별 진료소 보기
            </v-btn>
          </v-col>
        </v-row>
        <v-row class="float-right">
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
        <v-simple-table style="width: 100%; display: inline-block">
          <thead>
            <tr>
              <th>시도</th>
              <th>시군구</th>
              <th>병원명</th>
              <th>주소</th>
              <th>전화번호</th>
            </tr>
          </thead>
          <tbody>
            <hospital-data
              v-for="(item, index) in items"
              :key="index"
              :hospital="item"
            />
          </tbody>
        </v-simple-table>
      </v-container>
    </v-container>
  </div>
</template>
<script>
import axios from "axios";
import HospitalData from "@/components/corona/HospitalData.vue";
export default {
  name: "hospital",
  components: {
    HospitalData
  },
  data() {
    return {
      baseUrl: "http://localhost:8000/happyhouse",
      items: [],
      searchType: "",
      searchWord: "",
      selectType: [
        { text: "전체", value: "" },
        { text: "이름", value: "name" },
        { text: "위치", value: "location" }
      ]
    };
  },
  methods: {
    moveclinicpage() {
      this.$router.replace("/corona/clinic");
    },
    searchHandler() {
      if (this.searchWord == null) this.searchWord = "";
      let url = `${this.baseUrl}/corona/hospital?searchType=${this.searchType}&searchWord=${this.searchWord}`;
      axios
        .get(url)
        .then(({ data }) => {
          this.items = data;
        })
        .catch(() => {
          alert("에러가 발생했습니다");
        });
    }
  },
  mounted() {
    axios
      .get(`${this.baseUrl}/corona/hospital`)
      .then(({ data }) => {
        this.items = data;
      })
      .catch(() => {
        alert("error");
      });
  }
};
</script>
