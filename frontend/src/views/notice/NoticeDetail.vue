<template>
  <v-container>
    <h1 id="title">공지 사항</h1>
    <hr />
    <v-row id="btn-row">
      <v-col>
        <v-btn
          v-if="getAccessToken && getUserState == 100"
          large
          depressed
          color="primary"
          @click="movePage('write')"
          >새 글쓰기</v-btn
        >
      </v-col>
      <v-col>
        <v-btn
          v-if="getAccessToken && getUserState == 100"
          large
          depressed
          color="primary"
          @click="movePage(`modify?no=${board.no}`)"
          >수정</v-btn
        >
      </v-col>
      <!-- 삭제 버튼은 관리자에게만 나타납니다 -->
      <v-col>
        <v-btn
          v-if="getAccessToken && getUserState == 100"
          large
          depressed
          color="error"
          @click="deleteBoard"
          >삭제</v-btn
        >
      </v-col>
    </v-row>

    <Detail :board="board" />

    <v-row class="float-right">
      <v-col>
        <v-btn large depressed color="primary" @click="movePage(``)"
          >목록으로</v-btn
        >
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from "axios";
import Detail from "@/components/notice/Detail.vue";
import { mapGetters } from "vuex";

export default {
  name: "NoticeDetail",
  data() {
    return {
      board: {}
    };
  },
  components: {
    Detail
  },
  computed: {
    ...mapGetters(["getAccessToken", "getUserState"])
  },
  created() {
    axios
      .get(
        `http://localhost:8000/happyhouse/notice/view/${this.$route.query.no}`
      )
      .then(data => {
        this.board = data.data;
      })
      .catch(() => {
        alert("에러가 발생했습니다");
      });
  },
  methods: {
    deleteBoard() {
      axios
        .delete(
          `http://localhost:8000/happyhouse/notice/delete/${this.board.no}`
        )
        .then(({ data }) => {
          let msg = "삭제 처리시 문제가 발생했습니다.";
          if (data == "success") msg = "삭제가 완료되었습니다.";
          alert(msg);
          this.movePage(``);
        })
        .catch(() => {
          alert("삭제 처리시 에러가 발생했습니다");
        });
    },
    movePage(link) {
      this.$router.replace(`/notice/${link}`);
    }
  }
};
</script>

<style scoped>
#btn-row {
  float: left;
}
.float-right {
  float: right;
  margin: auto;
}
</style>
