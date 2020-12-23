<template>
  <tr>
    <td class="title">
      <a :href="news.link" target="_blank"><p v-html="title"></p></a>
    </td>
    <td style="white-space:pre-wrap;" v-html="des"></td>
    <td>{{ news.pubDate }}</td>
  </tr>
</template>

<script>
export default {
  name: "news",
  props: {
    news: Object
  },
  data() {
    return {
      title: this.news.title,
      des: this.news.description
    };
  }
};
</script>

<style>
.title {
  text-align: left;
}
</style>
