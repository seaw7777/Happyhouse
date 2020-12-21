<template>
  <tr>
    <td>{{ hospital.sido }}</td>
    <td>{{ hospital.gugun }}</td>
    <td>{{ hospital.name }}</td>
    <td>{{ hospital.address }}</td>
    <td>{{ hospital.phone }}</td>
  </tr>
</template>

<script>
export default {
  name: "hospital",
  props: {
    hospital: Object
  }
};
</script>
