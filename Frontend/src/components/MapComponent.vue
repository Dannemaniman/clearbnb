<template>
  <div id="container">
    <p class="map-title">Homes in your vicinity</p>
    <div id="mapContainer"></div>
  </div>
</template>

<script>
import 'leaflet/dist/leaflet.css';
import L from 'leaflet';
import { GeoSearchControl, OpenStreetMapProvider } from 'leaflet-geosearch';

export default {
  name: 'Map',
  data() {
    return {
      center: [55.68370479244602, 13.608292932699289],
      houses: '',
      provider: new OpenStreetMapProvider(),
      user: this.$store.state.user,
      userBookings: this.$store.state.userBookings,
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
          minZoom: 2,
          id: 'mapbox/streets-v11',
          tileSize: 512,
          zoomOffset: -1,
          accessToken:
            'pk.eyJ1IjoibWFsZWwiLCJhIjoiY2tvbWk3aDZsMDN2MTJwcDF0NW4wYmUxOSJ9.VLAe6Cp447l7BygcRzPkTg',
        }
      ).addTo(this.mapDiv);
      this.provider = new OpenStreetMapProvider();

      const searchControl = new GeoSearchControl({
        provider: this.provider,
        style: 'button',
        autoComplete: true,
        autoClose: true,
        keepResult: false,
      });
      this.mapDiv.addControl(searchControl);
    },

    addMarkers() {
      if (!this.$route.params.id) {
        this.houses.forEach((house) => {
          let content =
            '<h3>' +
            house.title +
            '</h3>' +
            '<a href=/house/' +
            house.id +
            '><img src="' +
            house.images[0] +
            '" width="150" /></a>' +
            '<br>' +
            house.city +
            '<br>' +
            'Price: ' +
            '<strong>' +
            house.price +
            '</strong>' +
            '/night';
          if (this.user == null) {
            L.circle(house.position, {
              color: 'red',
              fillColor: '#f03',
              fillOpacity: 0.5,
              radius: 500,
            })
              .bindPopup(content, { maxWidth: 160 })
              .openPopup()
              .addTo(this.mapDiv);
          } else if (
            this.userBookings.some((booking) =>
              booking.houseId.includes(house.id)
            ) ||
            this.user.id == house.ownerId ||
            this.user.email === 'admin@admin'
          ) {
            L.marker(house.position)
              .bindPopup(content, { maxWidth: 160 })
              .openPopup()
              .addTo(this.mapDiv);
          } else {
            L.circle(house.position, {
              color: 'red',
              fillColor: '#f03',
              fillOpacity: 0.5,
              radius: 500,
            })
              .bindPopup(content, { maxWidth: 160 })
              .openPopup()
              .addTo(this.mapDiv);
          }
        });
      } else {
        let singleContent =
          '<h3>' +
          this.home.title +
          '</h3>' +
          '<a href=/house/' +
          this.home.id +
          '><img src="' +
          this.home.images[0] +
          '" width="150" /></a>' +
          '<br>' +
          this.home.city +
          '<br>' +
          'Price: ' +
          '<strong>' +
          this.home.price +
          '</strong>' +
          '/night';

        if (this.user == null) {
          L.circle(this.home.position, {
            color: 'red',
            fillColor: '#f03',
            fillOpacity: 0.5,
            radius: 500,
            className: 'popup',
          })
            .bindPopup(singleContent, { maxWidth: 160 })
            .openPopup()
            .addTo(this.mapDiv);
        } else if (
          this.userBookings.some((booking) =>
            booking.houseId.includes(this.home.id)
          ) ||
          this.user.id == this.home.ownerId ||
          this.user.email === 'admin@admin'
        ) {
          L.marker(this.home.position)
            .bindPopup(singleContent, { maxWidth: 160 })
            .openPopup()
            .addTo(this.mapDiv);
        } else {
          L.circle(this.home.position, {
            color: 'red',
            fillColor: '#f03',
            fillOpacity: 0.5,
            radius: 500,
            className: 'popup',
          })
            .bindPopup(singleContent, { maxWidth: 160 })
            .openPopup()
            .addTo(this.mapDiv);
        }
      }
    },
  },
  async mounted() {
    this.houses = this.$store.state.houses;

    this.setupLeafletMap();
    this.addMarkers();
  },
};
</script>

<style scoped>
.container {
  background: white;
  box-shadow: rgb(0 0 0 / 50%) 0px 1px 12px;
  padding: 1rem;
  border-radius: 10px;
}

.map-title {
  font-size: 2.5rem;
  line-height: 125%;
  margin-left: 2rem;
  text-align: left;
  color: #4b4b4b;
}

#mapContainer {
  margin-top: 3rem;
  width: 100%;
  height: 35rem;

  background: white;
  box-shadow: rgba(0 0 0 / 60%) 0px 1px 6px;
  padding: 1rem;
  border-radius: 10px;
}
.leaflet-popup-content {
  color: aqua;
}
</style>
