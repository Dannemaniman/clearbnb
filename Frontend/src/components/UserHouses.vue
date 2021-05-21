<template>
  <button @click="showCreateHome = !showCreateHome">New House</button>
  <Spinner v-if="showSpinner" />
  <div v-else>
    <BasicInfo v-if="showCreateHome" @basicInfo="getBasicInfo" />
    <UserAmenities v-if="showCreateHome" @amenities="getAmenities" />
    <PhotoUploader v-if="showCreateHome" @photo="getPhoto" />
    <!-- <button type="reset">Reset</button> -->
    <button @click="addNewHouse">Submit Home</button>
  </div>
  <UserHouseItem
    v-for="(userHouse, index) of userObjects"
    v-bind:key="index"
    v-bind:house="userHouse"
  />
</template>

<script>
import UserHouseItem from './UserHouseItem.vue';
import BasicInfo from './BasicInfo.vue';
import PhotoUploader from './PhotoUploader.vue';
import UserAmenities from './UserAmenities.vue';
import { OpenStreetMapProvider } from 'leaflet-geosearch';
import Spinner from './Spinner.vue';

export default {
  components: {
    UserHouseItem,
    BasicInfo,
    PhotoUploader,
    UserAmenities,
    Spinner,
  },
  props: ['userObjects'],
  data() {
    return {
      // user: null,
      showSpinner: false,
      userHouses: [],
      showCreateHome: false,
      amenities: [],
      images: [],
      basicInfo: [],
      ownerId: '',
      images: null,
      position: [],
      provider: new OpenStreetMapProvider(),
    };
  },
  computed: {
    houses() {
      console.log('computed');
      return this.$store.state.houses;
    },
  },
  methods: {
    getAmenities(amen) {
      this.amenities = amen;
    },
    getPhoto(photos) {
      this.images = photos;
    },
    getBasicInfo(info) {
      this.basicInfo = info;
    },

    addNewHouse() {
      //let userAddress = 'Sunnanväg 209, Lund, SE';
      if (this.basicInfo.zipcode == null) {
        this.basicInfo.zipcode = 'xxx';
      }
      let userAddress =
        this.basicInfo.address +
        ' ' +
        this.basicInfo.city +
        ' ' +
        this.basicInfo.zipcode.replace(/\s+/g, '') +
        ' ' +
        'SE';
      //Adress  --, Stad postnummer, Land
      let query_promise = this.provider.search({ query: userAddress });

      query_promise.then(
        (value) => {
          for (let i = 0; i < value.length; i++) {
            // Success!
            let x_coor = value[i].x;
            let y_coor = value[i].y;
            //let label = value[i].label;

            this.position = [y_coor, x_coor];
            console.log(this.position);
            console.log(value);
          }
        },
        (reason) => {
          console.log(reason); // Error!
        }
      );
      if (this.position.length <= 0) {
        this.position = [-74.2183050512854, 26.899583900684352];
      }
      this.showSpinner = true;
      setTimeout(() => {
        this.submitHome();
        this.showSpinner = false;
      }, 2000);
    },

    async submitHome() {
      //  console.log( this.userHouses)
      //  console.log(this.showCreateHome),
      //  console.log(this.amenities),
      //  console.log(this.images),
      //  console.log(this.basicInfo)
      if (this.images == null) {
        console.log('hej');
        this.images = ['/images/No-Image.jpg'];
      }
      // if (this.$store.state.user) {
      let ownerId = await this.$store.state.user.id;
      console.log(ownerId);

      let hostObject = {
        propertyType: this.basicInfo.propertyType,
        amenities: this.amenities,
        price: this.basicInfo.price,
        title: this.basicInfo.title,
        city: this.basicInfo.city,
        address: this.basicInfo.address,
        zipcode: this.basicInfo.zipcode,
        description: this.basicInfo.description,
        accommodation: {
          bathrooms: this.basicInfo.bathroomCounter,
          beds: this.basicInfo.bedCounter,
        },
        ownerId: this.$route.params.id,
        position: this.position,
        images: this.images,
      };

      console.log(hostObject);

      this.$store.dispatch('createHouse', hostObject);
      this.showCreateHome = false;
      // }
    },
    created() {
      /*   console.log('tjo');
      let userId = this.$route.params.id;
      let userRes = await fetch('/rest/users/' + userId);
      let user = await userRes.json();
      this.user = user;

      let userHouses = [];
      for (let house of this.houses) {
        if (this.user.id == house.ownerId) {
          userHouses.push(house);
        }
      }
      this.userHouses = userHouses; */
    },
  },
};
</script>

<style scoped>
button {
  margin-top: 1rem;
  width: 20%;
  height: 4rem;
  border-radius: 10px;
  outline: none;
  font-weight: 700;
  cursor: pointer;
  background-color: rgb(235, 235, 235);
  transition: all 0.2s ease;
}

button:hover {
  box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
  background-color: #a9a9a9;
}

button:active {
  background-color: #a9a9a9;
}
</style>
