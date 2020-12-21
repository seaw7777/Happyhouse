<template>
  <v-form>
    <v-container>
      <v-row>
        <v-col cols="12" md="12">
          <v-text-field
            v-model="title"
            :rules="rules"
            counter
            maxlength="100"
            label="제목"
            ref="title"
          ></v-text-field>
        </v-col>
        <v-col cols="12" md="12">
          <v-text-field
            v-model="author"
            :rules="rules"
            counter
            maxlength="50"
            label="작성자"
            ref="author"
          ></v-text-field>
        </v-col>
        <v-col cols="12" md="12">
          <v-textarea
            id="content"
            :rules="rules"
            v-model="content"
            outlined
            label="내용"
            ref="content"
          ></v-textarea>
        </v-col>
      </v-row>
      <v-row class="float-right">
        <v-col>
          <v-btn
            v-if="type == 'create'"
            large
            depressed
            color="primary"
            @click="checkHandler"
            >글작성</v-btn
          >
          <v-btn
            v-else
            large
            depressed
            color="primary"
            class="float-left"
            @click="checkHandler"
            >수정</v-btn
          >
        </v-col>
        <v-col>
          <v-btn large depressed color="primary" @click="moveList">목록</v-btn>
        </v-col>
      </v-row>
    </v-container>
  </v-form>
</template>

<script>
import axios from "axios";

export default {
  props: {
    type: { type: String },
    no: { type: Number },
    title: { type: String },
    author: { type: String },
    content: { type: String }
  },
  data() {
    return {
      rules: [
        value => !!value || "Required.",
        value => (value && value.length >= 3) || "Min 3 characters"
      ]
    };
  },
  methods: {
    checkHandler() {
      let err = true;
      let msg = "";
      !this.title &&
        ((msg = "제목을 입력해주세요"),
        (err = false),
        this.$refs.title.focus());
      err &&
        !this.author &&
        ((msg = "작성자를 입력해주세요"),
        (err = false),
        this.$refs.author.focus());
      err &&
        !this.content &&
        ((msg = "내용을 입력해주세요"),
        (err = false),
        this.$refs.content.focus());

      if (!err) alert(msg);
      else this.type == "create" ? this.createHandler() : this.updateHandler();
    },
    createHandler() {
      axios
        .post(`http://localhost:8000/happyhouse/notice/write`, {
          title: this.title,
          author: this.author,
          content: this.content
        })
        .then(({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data == "success") msg = "등록이 완료되었습니다.";
          alert(msg);
          this.moveList();
        })
        .catch(() => {
          alert("등록 처리시 에러가 발생했습니다");
        });
    },
    updateHandler() {
      axios
        .put(`http://localhost:8000/happyhouse/notice/modify`, {
          no: this.no,
          title: this.title,
          author: this.author,
          content: this.content
        })
        .then(({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data == "success") msg = "등록이 완료되었습니다.";
          alert(msg);
          this.moveDetail();
        })
        .catch(() => {
          alert("등록 처리시 에러가 발생했습니다");
        });
    },
    moveList() {
      this.$router.replace("/notice");
    },
    moveDetail() {
      this.$router.replace(`/notice/detail?no=${this.no}`);
    }
  }
};
</script>

<style scoped>
#content {
  height: 300px;
  text-align: left;
}
.float-right {
  float: right;
  margin: auto;
  text-align: center;
}
</style>
