<template>
  <v-container>
    <h1 id="title">공지 사항</h1>
    <hr />
    <v-container>
      <v-row class="float-left">
        <v-col>
          <v-btn
            v-if="getAccessToken && getUserState == 100"
            large
            depressed
            color="primary"
            @click="moveWritePage"
            >글쓰기</v-btn
          >
        </v-col>
      </v-row>
      <v-row class="float-right">
        <v-col>
          <v-select
            :items="selectType"
            v-model="searchType"
            item-text="text"
            item-value="value"
            style="width: 100px;"
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
            style="width: 200px"
            @keypress.enter="searchHandler"
          ></v-text-field>
        </v-col>

        <v-col>
          <v-btn large depressed color="primary" @click="searchHandler"
            >검색</v-btn
          >
        </v-col>
      </v-row>
    </v-container>

    <v-simple-table style="width: 100%; display: inline-block">
      <template v-slot:default>
        <colgroup>
          <col style="width: 10%; text-align: center;" />
          <col style="width: 60%;" />
          <col style="width: 10%; text-align: center;" />
          <col style="width: 10%; text-align: center;" />
          <col style="width: 10%; text-align: center;" />
        </colgroup>
        <thead>
          <tr>
            <th class="text-left">
              글번호
            </th>
            <th class="text-left">
              제목
            </th>
            <th class="text-left">
              작성자
            </th>
            <th class="text-left">
              조회수
            </th>
            <th class="text-left">
              작성일
            </th>
          </tr>
        </thead>
        <tbody>
          <list-row
            v-for="(item, index) in items.slice(
              start + max * (page - 1),
              end + max * (page - 1)
            )"
            :key="index"
            :board="item"
          />
        </tbody>
      </template>
    </v-simple-table>
    <div class="text-center">
      <v-pagination v-model="page" :length="length"></v-pagination>
    </div>
  </v-container>
</template>

<script>
import axios from "axios";
import ListRow from "@/components/notice/Row.vue";
import { mapGetters } from "vuex";

export default {
  name: "noticeList",
  components: {
    ListRow
  },
  data() {
    return {
      baseUrl: "http://localhost:8000/happyhouse",
      searchType: "",
      searchWord: "",
      selectType: [
        { text: "전체", value: "" },
        { text: "제목", value: "title" },
        { text: "내용", value: "content" }
      ],
      items: [],
      max: 4,
      page: 1,
      length: 1,
      start: 0,
      end: 4
    };
  },
  created() {
    axios
      .get(`${this.baseUrl}/notice/list`)
      .then(({ data }) => {
        this.length = Math.ceil(data.length / this.max);
        this.items = data;
      })
      .catch(() => {
        alert("에러가 발생했습니다");
      });
  },
  computed: {
    ...mapGetters(["getAccessToken", "getUserState"])
  },
  methods: {
    moveWritePage() {
      this.$router.replace("/notice/write");
    },
    searchHandler() {
      if (this.searchWord == null) this.searchWord = "";
      let url = `${this.baseUrl}/notice/list?searchType=${this.searchType}&searchWord=${this.searchWord}`;
      axios
        .get(url)
        .then(({ data }) => {
          this.items = data;
        })
        .catch(() => {
          alert("에러가 발생했습니다");
        });
    }
  }
};
</script>

<style scoped>
.margin-auto {
  margin: 0 auto;
  text-align: center;
}
.float-left {
  float: left;
  margin: auto;
  text-align: center;
}
.float-right {
  float: right;
  margin: auto;
  text-align: center;
}
</style>
