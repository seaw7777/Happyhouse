<template>
  <div>
    <!-- Page Content -->
    <v-container>
      <h1>코로나 선별 진료소</h1>
      <hr />
      <!-- Heading Row -->
      <v-container>
        <v-row class="float-left">
          <v-col>
            <v-btn color="primary" @click="movehospitalpage">
              국가 지정 안심 병원 보기
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
          <template v-slot:default>
            <!-- <colgroup>
              <col style="width: 10%; text-align: center;" />
              <col style="width: 60%;" />
              <col style="width: 10%; text-align: center;" />
              <col style="width: 10%; text-align: center;" />
              <col style="width: 10%; text-align: center;" />
              <col style="width: 10%; text-align: center;" />
              <col style="width: 10%; text-align: center;" />
            </colgroup> -->
            <thead>
              <tr>
                <th>시도</th>
                <th>시군구</th>
                <th>이름</th>
                <th>위치</th>
                <th>토요일 운영시간</th>
                <th>일요일 운영시간</th>
                <th>전화번호</th>
              </tr>
            </thead>
            <tbody>
              <clinic-data
                v-for="(item, index) in items"
                :key="index"
                :clinic="item"
              />
            </tbody>
          </template>
        </v-simple-table>
      </v-container>
    </v-container>
  </div>
</template>
<script>
import axios from "axios";
import ClinicData from "@/components/corona/ClinicData.vue";
export default {
  name: "clinic",
  components: {
    ClinicData
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
    movehospitalpage() {
      this.$router.replace("/corona/hospital");
    },
    searchHandler() {
      if (this.searchWord == null) this.searchWord = "";
      let url = `${this.baseUrl}/corona/clinic?searchType=${this.searchType}&searchWord=${this.searchWord}`;
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
      .get(`${this.baseUrl}/corona/clinic`)
      .then(({ data }) => {
        this.items = data;
      })
      .catch(() => {
        alert("error");
      });
  }
};
</script>
