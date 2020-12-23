<template>
  <v-container>
    <v-simple-table id="view-table">
      <template v-slot:default>
        <colgroup>
          <col style="width: 16%; text-align: center;" />
          <col style="width: 16%; text-align: center;" />
          <col style="width: 16%; text-align: center;" />
          <col style="width: 16%; text-align: center;" />
          <col style="width: 16%; text-align: center;" />
          <col style="width: 20%; text-align: center;" />
        </colgroup>
        <tbody>
          <tr>
            <td><b>제목</b></td>
            <td colspan="5" style="text-align: left;">
              <b>{{ board.title }}</b>
            </td>
          </tr>
          <tr>
            <td><b>작성자</b></td>
            <td>{{ board.author }}</td>
            <td><b>조회수</b></td>
            <td>{{ board.views }}</td>
            <td><b>작성일</b></td>
            <td>{{ board.regtime }}</td>
          </tr>
          <tr height="400px">
            <td colspan="6" id="content">
              <pre>{{ board.content }}</pre>
            </td>
          </tr>
        </tbody>
      </template>
    </v-simple-table>
  </v-container>
</template>

<script>
export default {
  name: "detail",
  props: {
    board: Object
  }
};
</script>

<style scoped>
#view-table {
  width: 100%;
  display: inline-block;
  border: 10em thin;
}
#content {
  text-align: left;
  vertical-align: top;
}
tbody > tr:hover {
  background-color: transparent !important;
}
</style>
