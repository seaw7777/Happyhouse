<template>
  <v-container>
    <h1 id="title">공지 사항</h1>
    <hr />
    <update-form
      type="update"
      :no="`${board.no}`"
      :title="`${board.title}`"
      :author="`${board.author}`"
      :content="`${board.content}`"
    />
  </v-container>
</template>

<script>
import UpdateForm from "@/components/notice/Form.vue";
import axios from "axios";

export default {
  name: "NoticeModify",
  components: {
    UpdateForm
  },
  data() {
    return {
      board: {}
    };
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
  }
};
</script>
