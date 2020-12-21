<template>
  <v-container>
    <h1>오늘의 뉴스</h1>
    <hr />
    <v-row>
      <v-col>
        <!-- <v-select
          style="width: 200px; float: right;"
          :items="list"
          label="Solo field"
          v-model="searchType"
          dense
          solo
        ></v-select> -->
      </v-col>
    </v-row>
    <v-row style="display: inline-block;">
      <!-- <div id="write-search">
      <div class="form-inline" id="search">
        <select
          class="form-control"
          id="searchType"
          name="searchType"
          v-model="searchType"
        >
          <option disabled value="">전체</option>
          <option value="title" selected>네이버</option>
          <option value="content">다음</option>
        </select>
      </div> -->
      <!-- </div> -->
      <v-simple-table style="width: 100%; display: inline-block">
        <template v-slot:default>
          <colgroup>
            <col style="width: 20%; text-align: center;" />
            <col style="width: 60%;" />
            <col style="width: 10%; text-align: center;" />
          </colgroup>
          <thead>
            <tr>
              <th>제목</th>
              <th>설명</th>
              <th>작성일</th>
            </tr>
          </thead>
          <tbody>
            <news-list
              v-for="(item, index) in items"
              :key="index"
              :news="item"
            />
          </tbody>
        </template>
      </v-simple-table>
    </v-row>
  </v-container>
</template>

<script>
import NewsList from "@/components/base/News.vue";
import axios from "axios";
export default {
  name: "newslist",
  components: {
    NewsList
  },
  data() {
    return {
      items: [],
      list: ["Naver", "Daum"],
      searchType: "Naver",
      news: ""
    };
  },
  created() {
    axios
      .get("http://localhost:8000/happyhouse/news/list")
      .then(({ data }) => {
        this.items = data;
        console.log(this.items);
      })
      .catch(() => {
        alert("에러가 발생했습니다");
      });
  }
};
</script>

<style>
#content {
  text-align: center;
}
#write {
  float: left;
}
#write-search {
  margin-bottom: 20px;
}
#search {
  float: right;
}
#board tr {
  text-align: center;
}
.title {
  text-align: left;
}
</style>
