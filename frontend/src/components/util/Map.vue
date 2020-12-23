<template>
  <v-container>
    <!-- google map api를 이용한 맵 그리기 -->
    <GmapMap
      map-type-id="roadmap"
      :zoom="zoom"
      :center="center"
      style="height: 500px; margin: auto;"
    >
      <GmapMarker
        v-for="(position, index) in positions"
        :position="position"
        :key="index"
        :label="labels[index]"
      />
    </GmapMap>
  </v-container>
</template>

<script>
import axios from "axios";
import MapSearch from "@/components/util/MapSearch";

export default {
  name: "Map",
  props: {
    list: Array,
    index: { type: Number }
  },
  components: {
    MapSearch
  },
  data() {
    return {
      center: { lat: 37.5665734, lng: 126.978179 },
      zoom: 12,
      positions: [{ lat: 37.5665734, lng: 126.978179 }],
      labels: [{ text: "현 위치", fontWeight: "bold" }]
    };
  },
  created() {
    // this.getAptInfo(this.dong);
  },
  watch: {
    list() {
      this.geocode(this.list);
    },
    index() {
      this.center = this.positions[this.index];
    }
  },
  methods: {
    geocode(jsonData) {
      this.positions = [];
      this.labels = [];
      
      var instance = axios.create();
      delete instance.defaults.headers.common["auth-token"];

      jsonData.forEach(vo => {
        instance
          .get("https://maps.googleapis.com/maps/api/geocode/json", {
            params: {
              key: "AIzaSyANHmuFg33a00Xxoa3ri4xAq5SNDa2-6q0",
              address: vo.dong + "+" + vo.aptName + "+" + vo.jibun
            }
          })
          .then(({ data }) => {
            let lat = data.results[0].geometry.location.lat;
            let lng = data.results[0].geometry.location.lng;
            this.positions.push({ lat: lat, lng: lng });
            this.labels.push({
              text: vo.aptName,
              fontWeight: "bold"
            });
            this.center = this.positions[0];
            this.zoom = 14;
          })
          .catch(() => {});
      });
    }
  }
};
</script>
