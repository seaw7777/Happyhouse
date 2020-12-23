<template>
  <v-navigation-drawer v-model="drawer" app dark temporary>
    <v-list>
      <v-list-item v-for="(link, i) in links" :key="i" @click="onClick(link)">
        <v-list-item-title v-text="link.text" />
      </v-list-item>
    </v-list>
  </v-navigation-drawer>
</template>

<script>
// Utilities
import { mapGetters, mapMutations } from "vuex";

export default {
  name: "CoreDrawer",

  computed: {
    ...mapGetters(["links"]),
    drawer: {
      get() {
        return this.$store.state.drawer;
      },
      set(val) {
        this.setDrawer(val);
      }
    }
  },

  methods: {
    ...mapMutations(["setDrawer"]),
    onClick(item) {
      if (item.to || !item.href) return;

      this.$router.replace(item.href);
      this.setDrawer(false);
    }
  }
};
</script>
