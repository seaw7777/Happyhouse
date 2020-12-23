<template>
  <tr>
    <td>{{ board.no }}</td>
    <td class="title">
      <router-link :to="`/notice/detail?no=${board.no}`">{{
        board.title
      }}</router-link>
    </td>
    <td>{{ board.author }}</td>
    <td>{{ board.views }}</td>
    <td>{{ board.regtime }}</td>
  </tr>
</template>

<script>
export default {
  name: "row",
  props: {
    board: Object
  }
};
</script>

<style>
.title {
  text-align: left;
}
</style>
