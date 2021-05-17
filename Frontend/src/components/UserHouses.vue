<template>
  <button @click="showCreateHome = !showCreateHome">New House</button>
  <UserHouseItem
    v-for="(userHouse, index) of userHouses"
    v-bind:key="index"
    v-bind:house="userHouse"
  />
  <BasicInfo v-if="showCreateHome" @basicInfo="getBasicInfo" />
  <UserAmenities v-if="showCreateHome" @amenities="getAmenities" />
  <PhotoUploader v-if="showCreateHome" @photo="getPhoto" />
  <!-- <button type="reset">Reset</button> -->
  <button @click="submitHome">Submit Home</button>
</template>

<script>
import UserHouseItem from './UserHouseItem.vue';
import BasicInfo from './BasicInfo.vue';
import PhotoUploader from './PhotoUploader.vue';
import UserAmenities from './UserAmenities.vue';

export default {
  components: {
    UserHouseItem,
    BasicInfo,
    PhotoUploader,
    UserAmenities,
  },
  data() {
    return {
      // user: null,
      //userHouses: [],
      showCreateHome: false,
      amenities: [],
      images: [],
      basicInfo: [],
      ownerId: '',
      images: [],
    };
  },
  computed: {
    houses() {
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
    async submitHome() {
      //  console.log( this.userHouses)
      //  console.log(this.showCreateHome),
      //  console.log(this.amenities),
      //  console.log(this.images),
      //  console.log(this.basicInfo)

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
        // images: this.images
      };

      console.log(hostObject);

      this.$store.dispatch('createHouse', hostObject);
      // }
    },
    async created() {
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
      this.userHouses = userHouses;
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
