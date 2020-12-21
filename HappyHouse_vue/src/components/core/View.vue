<template>
  <v-content id="home">
    <v-img
      max-height="300"
      max-width="100%"
      :src="require('@/assets/banner_name.jpg')"
    ></v-img>
    <!-- <v-parallax
      height="500"
      :src="require('@/assets/banner_name.jpg')"
    ></v-parallax> -->
    <router-view />
  </v-content>
</template>

<script>
export default {
  name: "CoreView"
};
</script>
