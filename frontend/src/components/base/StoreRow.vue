<template>
  <tr>
    <td>{{ store.name }}</td>
    <td>{{ store.bigkind }}</td>
    <td>{{ store.middlekind }}</td>
    <td>{{ store.dong }}</td>
  </tr>
</template>

<script>
export default {
  name: "StoreRow",
  props: {
    store: { type: Object }
  }
};
</script>
