<template>
  <section>
    <p class="title">Home Info</p>
    <form @change.prevent="getFormData">
      <label for="title">Title</label>
      <input
        name="title"
        placeholder="Title..."
        type="text"
        class="long-input"
        v-model.trim="title"
        @blur="validateInput('title')" 
        :class="{invalid: titleValidity === 'invalid'}"
      />
      <label for="price">Price</label>
      <p>{{ price }}</p>
      <input
        name="price"
        type="range"
        min="1"
        max="3000"
        step="50"
        v-model="price"
      />
      <label for="childDiscount">Child Discount</label>
      <p>{{ childDiscount }} %</p>
      <input
        name="childDiscount"
        type="range"
        min="0"
        max="100"
        step="1"
        v-model="childDiscount"
      />
      <label for="seniorDiscount">Senior Discount</label>
      <p>{{ seniorDiscount }} %</p>
      <input
        name="seniorDiscount"
        type="range"
        min="0"
        max="100"
        step="1"
        v-model="seniorDiscount"
      />
      <label for="description"></label>
      <textarea
        name="description"
        placeholder="Please Enter a Accurate Description of the Home."
        rows="10"
        cols="50"
        v-model.trim="description"
         @blur="validateInput('description')" 
        :class="{invalid: descriptionValidity === 'invalid'}"
      />
      <label for="address">Address</label>
      <input
        name="address"
        placeholder="Enter address..."
        type="text"
        class="long-input"
        v-model.trim="address"
         @blur="validateInput('address')" 
        :class="{invalid: addressValidity === 'invalid'}"
      />
      <label for="zip">Zip Code</label>
      <input
        name="zip"
        placeholder="Zip code..."
        type="text"
        class="state-input"
        v-model="zipcode"
         @blur="validateInput('zipcode')" 
        :class="{invalid: zipcodeValidity === 'invalid'}"
      />
      <input
        name="zip"
        placeholder="City..."
        type="text"
        class="short-input"
        v-model.trim="city"
         @blur="validateInput('city')" 
        :class="{invalid: cityValidity === 'invalid'}"
      />
      <label for="properties">Property Type</label>
      <select id="property-select" name="properties" v-model="propertyType"  @blur="validateInput('property')" 
        :class="{invalid: propertyValidity === 'invalid'}">
        <option disabled value="">Please Choose...</option>
        <option value="apartment">Apartment</option>
        <option value="house">House</option>
        <option value="bed-n-breakfast">Bed and Breakfast</option>
      </select>
      <div class="accomodate-info">
        <p>How many guests can your place accomodate?</p>
        <div class="basicinfo-buttonbar">
          <p>Guests</p>
          <div class="button-container">
            <button
              type="button"
              @click="guestCounter <= 1 ? (guestCounter = 1) : guestCounter--"
            >
              -
            </button>
            <p>{{ guestCounter }}</p>
            <button type="button" @click="guestCounter++">+</button>
          </div>
        </div>
        <div class="basicinfo-buttonbar">
          <p>Beds</p>
          <div class="button-container">
            <button
              type="button"
              @click="bedCounter <= 0 ? (bedCounter = 0) : bedCounter--"
            >
              -
            </button>
            <p>{{ bedCounter }}</p>
            <button type="button" @click="bedCounter++">+</button>
          </div>
        </div>
        <div class="basicinfo-buttonbar">
          <p>Bathrooms</p>
          <div class="button-container">
            <button
              type="button"
              @click="
                bathroomCounter <= 0 ? (bathroomCounter = 0) : bathroomCounter--
              "
            >
              -
            </button>
            <p>{{ bathroomCounter }}</p>
            <button type="button" @click="bathroomCounter++">+</button>
          </div>
        </div>
      </div>
    </form>
  </section>
</template>

<script>
export default {
  emit: ['basicInfo'],
  data() {
    return {
      title: '',
      price: 300,
      childDiscount: 1,
      seniorDiscount: 1,
      description: '',
      address: '',
      zipcode: '',
      city: '',
      propertyType: '',
      guestCounter: 1,
      bedCounter: 0,
      bathroomCounter: 0,

      titleValidity: 'pending',
      descriptionValidity: 'pending',
      addressValidity: 'pending',
      zipcodeValidity: 'pending',
      cityValidity: 'pending',
      propertyValidity: 'pending',
    };
  },
  methods: {
    getFormData() {
      this.$emit('basicInfo', this.$data);
    },
    validateInput(type){
      if(type === 'title') {
        if(this.title === ""){
          this.titleValidity = 'invalid'
        } else {
          this.titleValidity = 'valid'
        }
      }
      else if(type === 'description'){
        if(this.description === ""){
          this.descriptionValidity = 'invalid'
        } else {
          this.descriptionValidity = 'valid'
        }
      }
      else if(type === 'address'){
        if(this.address === ""){
          this.addressValidity = 'invalid'
        } else {
          this.addressValidity = 'valid'
        }
      }
      else if(type === 'zipcode'){
        if(this.zipcode === ""){
          this.zipcodeValidity = 'invalid'
        } else {
          this.zipcodeValidity = 'valid'
        }
      }
      else if(type === 'city'){
        if(this.city === ""){
          this.cityValidity = 'invalid'
        } else {
          this.cityValidity = 'valid'
        }
      }
      else if(type === 'property'){
        if(this.propertyType === ""){
          this.propertyValidity = 'invalid'
        } else {
          this.propertyValidity = 'valid'
        }
      }
    }
  },
};
</script>

<style scoped>

.invalid {
  border-color: red;
}

section {
  margin: 0 auto;
  margin-top: 2rem;
  background-color: #a9a9a9;
  border-radius: 10px;
  width: 80%;
  color: black;
  padding: 1rem;
}

label {
  display: block;
  margin: 1rem;
}

input {
  padding: 1rem;
}

input[type='range'] {
  padding: 0;
}

textarea {
  padding: 1rem;
}

select {
  height: 2rem;
  width: 30%;
  outline: none;
  padding: 0.2rem;
}

.title {
  font-size: 2rem;
}

.accomodate-info {
  margin-top: 3rem;
}

.basicinfo-buttonbar {
  display: flex;
  flex-direction: row;
  width: 70%;
  margin: 0 auto;
  position: relative;
}

.basicinfo-buttonbar > p {
  flex-grow: 4;
  text-align: left;
  margin-left: 2rem;
}

.basicinfo-buttonbar div {
  flex-grow: 1;
  display: flex;
  position: absolute;
  right: 1rem;
}

button {
  align-self: center;
  height: 2rem;
  width: 4rem;
  border-radius: 10px;
  margin: 0 1rem;
  outline: none;
}

input {
  height: 2rem;
  outline: none;
  border-radius: 8px;
  border: 1p solid black;
}

.long-input {
  width: 60%;
}

.state-input {
  width: 35%;
  margin-right: 2rem;
}

.short-input {
  width: 20%;
}
</style>
