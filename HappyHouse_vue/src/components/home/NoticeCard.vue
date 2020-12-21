<template>
  <v-container>
    <v-card class="mx-auto" max-width="100%">
      <v-card-text>
        <p class="display-1 text--primary">
          공지 사항
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
                  <td @click="moveNoticeDetailPage(item.no)">
                    {{ item.title }}
                  </td>
                </tr>
              </tbody>
            </template>
          </v-simple-table>
        </div>
      </v-card-text>
      <v-card-actions>
        <v-btn text color="deep-purple accent-4" @click="moveNoticeListPage">
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
      .get("http://localhost:8000/happyhouse/notice/list")
      .then(({ data }) => {
        this.items = data.slice(0, 3);
      });
  },
  methods: {
    moveNoticeListPage() {
      this.$router.replace(`/notice`);
    },
    moveNoticeDetailPage(no) {
      this.$router.replace(`/notice/detail?no=${no}`);
    }
  }
};
</script>
