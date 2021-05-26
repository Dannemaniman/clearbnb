<template>
  <ErrorModal errorMessage="Invalid fields!" @closeModal="closeModal" v-if="showError === true" />
  <Spinner v-if="showSpinner" />
  <div v-else>
    <BasicInfo v-if="showCreateHome" @basicInfo="getBasicInfo" />
    <UserAmenities v-if="showCreateHome" @amenities="getAmenities" :invalid="amenitiesValidity"/>
    <PhotoUploader v-if="showCreateHome" />
    <button v-if="showCreateHome" @click="addNewHouse">Submit Home</button>
    <p v-if="showValidityError === 'invalid'" style="color: red;">Something went wrong.. Check for Red fields!</p>
  </div>
  <h1>My Homes</h1>
    <div v-if="userObjects.length > 0" class="user-home-container">
      <UserHouseItem
        v-for="(userHouse, index) of userObjects"
        v-bind:key="index"
        v-bind:house="userHouse"
      />
    </div>
    <div v-else style="font-size: 2rem;">You have none of your homes available to be rented out!</div> 
  <button @click="showCreateHome = !showCreateHome" class="new-house-btn">New House</button>
</template>

<script>
import UserHouseItem from './UserHouseItem.vue';
import BasicInfo from './BasicInfo.vue';
import PhotoUploader from './PhotoUploader.vue';
import UserAmenities from './UserAmenities.vue';
import { OpenStreetMapProvider } from 'leaflet-geosearch';
import Spinner from './Spinner.vue';
import ErrorModal from "./ErrorModal.vue"

export default {
  components: {
    UserHouseItem,
    BasicInfo,
    PhotoUploader,
    UserAmenities,
    Spinner,
    ErrorModal
  },
  props: ['userObjects'],
  data() {
    return {
      showSpinner: false,
      showCreateHome: false,
      amenities: [],
      amenitiesValidity: 'pending',
      images: null,
      showError: false,
      basicInfo: {
        price: "",
        childDiscount: this.childDiscount,
        seniorDiscount: this.seniorDiscount,
        title: "",
        city: "",
        address: "",
        zipcode: "",
        description: "",
        propertyType: "",
      },
      ownerId: '',
      position: [],
      provider: new OpenStreetMapProvider(),
      showValidityError: 'pending',
      sendObject: {
        amenities: [],
        accommodation: {
          bathrooms: [],
          beds: [],
        },
        ownerId: "",
        position: "",
        images: "",
    }
    }
  },
  computed: {
    houses() {
      return this.$store.state.houses;
    },
  },
  methods: {
    checkAmenityValidity(){
      if(this.amenities.length >= 1 && this.images !== null){
        this.amenitiesValidity = 'invalid'
        return 'invalid'
      } else {
        return 'valid'
      }
    },
    getAmenities(amen) {
      this.amenities = amen;
    },
    getBasicInfo(info) {
      this.basicInfo = info;
    },
    closeModal(){
      this.showError = false;
    },
    addNewHouse() {
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
            let x_coor = value[i].x;
            let y_coor = value[i].y;
            this.position = [y_coor, x_coor];
          }
        },
        (reason) => {
          console.log(reason); // Error!
        }
      );
      if (this.position.length <= 0) {
        this.position = [-74.2183050512854, 26.899583900684352];
      }
      // this.showSpinner = true;
      // setTimeout(() => {
      //   this.submitHome();
      //   this.showSpinner = false;
      // }, 2000);
        this.submitHome();
    },

    async submitHome() {
      this.images = this.$store.state.uploadedNames;

      if (this.images.length == 0) {
        console.log('tjohojs');
        this.images = ['/images/No-Image.jpg'];
      }
      let ownerId = await this.$store.state.user.id;
      console.log(ownerId);

      let hostObject = {
        propertyType: this.basicInfo.propertyType,
        amenities: this.amenities,
        price: this.basicInfo.price,
        childDiscount: this.basicInfo.childDiscount,
        seniorDiscount: this.basicInfo.seniorDiscount,
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

      if(!this.basicInfo.propertyType === '' || this.basicInfo.zipcode === '' || this.basicInfo.address === '' || this.basicInfo.city === '' || this.checkAmenityValidity === 'invalid' || this.basicInfo.title === '' || this.basicInfo.description === '' || this.$route.params.id === '') {
        this.showValidityError = 'invalid'
        this.showError = true
        return
      } else {
        this.showValidityError = 'valid'
        this.$store.dispatch('createHouse', hostObject);
        this.showCreateHome = false;
        this.userObjects.push(hostObject);
      }
    },
  },
};
</script>

<style scoped>

  section {
    padding: 2rem;
  }

  .user-home-container {
    background-color: rgb(235, 235, 235);
    padding: 2rem;
    box-shadow: rgba(38, 57, 77, 0.3) 0px 20px 30px -10px;
  }

  h1 {
    margin: 6rem auto;
    font-size: 3rem;
  }


.invalid {
  border: 1px solid red;
  background-color: salmon;
}

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
  border: 0;
  background-image: url('https://www.getaccept.com/hubfs/Product%20pages%202.0/Product%20tour/bottom%20wave.svg');
  background-repeat: no-repeat;
  background-size: contain;
  background-size: cover;
  filter:brightness(130%)
}

button:hover, button:active  {
  box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
  background-color: #a9a9a9;
}

.new-house-btn {
  margin-top: 7rem;
}
</style>
