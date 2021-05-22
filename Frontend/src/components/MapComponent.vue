<template>
  <div id="container">
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
    };
  },
  props: ['home'],
  methods: {
    customMarker() {
      var userIcon = L.icon({
        iconUrl: '/munch.png',

        iconSize: [30, 30], // size of the icon
        iconAnchor: [0, 0], // point of the icon which will correspond to marker's location
        popupAnchor: [0, 0], // point from which the popup should open relative to the iconAnchor
      });
      return userIcon;
    },

    setupLeafletMap() {
      this.mapDiv = L.map('mapContainer')
        .locate({
          setView: true,
          watch: true,
          maxZoom: 9,
          maximumAge: 4000,
        })
        .on('locationfound', (e) => {
          let usermarker = new L.marker([e.latitude, e.longitude], {
            icon: this.customMarker(),
          });
          if (usermarker !== null) {
            this.mapDiv.removeLayer(usermarker);
          }
          //L.marker([e.latitude, e.longitude], { icon: this.customMarker() })
          usermarker.bindPopup('Detta är du!').addTo(this.mapDiv);
        })
        .on('locationerror', (error) => {
          console.log(error);
        });
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
        keepResult: true,
      });
      this.mapDiv.addControl(searchControl);
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
            .bindPopup(
              '<h3>' +
                house.title +
                '</h3>' +
                '<img src="' +
                house.images[0] +
                '" width="150" />' +
                '<br>' +
                house.description +
                '<br>' +
                'Price: ' +
                '<strong>' +
                house.price +
                '</strong>' +
                '/night',
              { maxWidth: 160 }
            )
            .openPopup()
            .addTo(this.mapDiv);
        });
      } else {
        L.circle(this.home.position, {
          color: 'red',
          fillColor: '#f03',
          fillOpacity: 0.5,
          radius: 500,
          className: 'popup',
        })
          .bindPopup(
            '<h3>' +
              this.home.title +
              '</h3>' +
              '<img src="' +
              this.home.images[0] +
              '" width="150" />' +
              '<br>' +
              this.home.description +
              '<br>' +
              'Price: ' +
              '<strong>' +
              this.home.price +
              '</strong>' +
              '/night',
            { maxWidth: 160 }
          )
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
    /*  this.mapDiv.on('locationfound', this.onLocationFound);
    this.mapDiv.on('locationerror', this.onLocationError); */
  },
};
</script>

<style scoped>
#mapContainer {
  margin-top: 7rem;
  width: 100%;
  height: 35rem;
}
</style>
