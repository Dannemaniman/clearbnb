<template>
  <div id="container">
    <div id="mapContainer"></div>
  </div>
</template>

<script>
import 'leaflet/dist/leaflet.css';
import L from 'leaflet';

export default {
  name: 'Map',
  data() {
    return {
      center: [55.68370479244602, 13.608292932699289],
      houses: '',
    };
  },
  props: ['home'],
  methods: {
    setupLeafletMap() {
      this.mapDiv = L.map('mapContainer').setView(this.center, 9);
      L.tileLayer(
        'https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token={accessToken}',
        {
          attribution:
            'Map data &copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
          maxZoom: 18,
          id: 'mapbox/streets-v11',
          tileSize: 512,
          zoomOffset: -1,
          accessToken:
            'pk.eyJ1IjoibWFsZWwiLCJhIjoiY2tvbWk3aDZsMDN2MTJwcDF0NW4wYmUxOSJ9.VLAe6Cp447l7BygcRzPkTg',
        }
      ).addTo(this.mapDiv);
    },
    addMarkers() {
      if (!this.$route.params.id) {
        this.houses.forEach((house) => {
          L.circle(house.position, {
            color: 'red',
            fillColor: '#f03',
            fillOpacity: 0.5,
            radius: 500,
          })
            .bindPopup(house.title + '<br>' + house.price)
            .openPopup()
            .addTo(this.mapDiv);
        });
      } else {
        L.circle(this.home.position, {
          color: 'red',
          fillColor: '#f03',
          fillOpacity: 0.5,
          radius: 500,
        })
          .bindPopup(this.home.title + '<br>' + this.home.price)
          .openPopup()
          .addTo(this.mapDiv);
      }
      /*  L.marker(this.home.position).addTo(this.mapDiv); */
    },
  },
  async mounted() {
    let res = await fetch('/rest/houses');
    let houses = await res.json();
    this.houses = houses;
    this.setupLeafletMap();
    this.addMarkers();
  },
};
</script>

<style scoped>
#mapContainer {
  width: 100%;
  height: 35rem;
}
</style>
