<template>
  <v-container>
    <h1 id="title">공지 사항</h1>
    <hr />
    <create-form type="create" />
  </v-container>
</template>

<script>
import CreateForm from "@/components/notice/Form.vue";
export default {
  name: "NoticeWrite",
  components: {
    CreateForm
  }
};
</script>

<style type="text/css">
#nav-top {
  margin-bottom: 20px;
}
#notice {
  text-align: center;
}
#content {
  height: 400px;
  text-align: left;
}
#btn-div {
  text-align: center;
  margin-bottom: 20px;
}
</style>
