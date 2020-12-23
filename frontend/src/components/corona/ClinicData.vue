<template>
  <tr>
    <td>{{ clinic.csido }}</td>
    <td>{{ clinic.cgugun }}</td>
    <td>{{ clinic.cname }}</td>
    <td>{{ clinic.caddress }}</td>
    <td>{{ clinic.csaturday }}</td>
    <td>{{ clinic.choliday }}</td>
    <td>{{ clinic.cphone }}</td>
  </tr>
</template>

<script>
export default {
  name: "clinic",
  props: {
    clinic: Object
  }
};
</script>
