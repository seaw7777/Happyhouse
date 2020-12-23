<template>
  <v-container>
    <v-card class="mx-auto" max-width="100%">
      <v-card-text>
        <p class="display-1 text--primary">
          오늘의 뉴스
        </p>
        <div class="text--primary">
          <v-simple-table style="width: 100%; display: inline-block">
            <template v-slot:default>
              <thead>
                <tr>
                  <th class="text-left">
                    제목
                  </th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(item, index) in items" :key="index">
                  <td
                    @click="moveNoticeDetailPage(item.no)"
                    v-html="item.title"
                  ></td>
                </tr>
              </tbody>
            </template>
          </v-simple-table>
        </div>
      </v-card-text>
      <v-card-actions>
        <v-btn text color="deep-purple accent-4" @click="moveTodayNewsPage">
          More
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-container>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      items: Array
    };
  },
  created() {
    axios
      .get("http://localhost:8000/happyhouse/news/list")
      .then(({ data }) => {
        this.items = data.slice(0, 3);
      })
      .catch(() => {
        alert("에러가 발생했습니다");
      });
  },
  methods: {
    moveTodayNewsPage() {
      this.$router.replace(`/todaynews`);
    }
  }
};
</script>
