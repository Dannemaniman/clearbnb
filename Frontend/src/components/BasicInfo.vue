<template>
  <section>
    <p class="title">Create New Home</p>
    <form @change.prevent="getFormData">
      <table>
        <tr>
          <th colspan="3"><label for="title">Title</label></th>
        </tr>
        <tr>
          <td colspan="3">
            <input
              name="title"
              placeholder="Title..."
              type="text"
              class="title-input"
              v-model.trim="title"
              @blur="validateInput('title')"
              :class="{ invalid: titleValidity === 'invalid' }"
            />
          </td>
        </tr>
        <tr>
          <th>
            <label for="price">Price: {{ price }} SEK</label>
          </th>
          <td>
            <input
              name="price"
              type="range"
              min="1"
              max="6000"
              step="50"
              v-model="price"
            />
          </td>
        </tr>
        <tr>
          <th>
            <label for="childDiscount"
              >Child Discount: {{ childDiscount }} %</label
            >
          </th>
          <td>
            <input
              name="childDiscount"
              type="range"
              min="0"
              max="100"
              step="1"
              v-model="childDiscount"
            />
          </td>
        </tr>
        <tr>
          <th>
            <label for="seniorDiscount"
              >Senior Discount {{ seniorDiscount }} %</label
            >
          </th>
          <td>
            <input
              name="seniorDiscount"
              type="range"
              min="0"
              max="100"
              step="1"
              v-model="seniorDiscount"
            />
          </td>
        </tr>
        <tr>
          <th colspan="3">
            <label for="description">Description: </label>
          </th>
        </tr>
        <tr>
          <td colspan="3">
            <textarea
              name="description"
              placeholder="Please Enter a Accurate Description of the Home."
              rows="10"
              cols="80"
              v-model.trim="description"
              @blur="validateInput('description')"
              :class="{ invalid: descriptionValidity === 'invalid' }"
            />
          </td>
        </tr>
        <tr>
          <th colspan="3">
            <label for="address">Address</label>
          </th>
        </tr>
        <tr>
          <td colspan="3">
            <input
              name="address"
              placeholder="Enter address..."
              type="text"
              class="long-input"
              v-model.trim="address"
              @blur="validateInput('address')"
              :class="{ invalid: addressValidity === 'invalid' }"
            />
          </td>
        </tr>
        <tr>
          <th colspan="3">
            <label for="zip">Zip Code</label>
          </th>
        </tr>
        <tr>
          <td>
            <input
              name="zip"
              placeholder="Zip code..."
              type="text"
              class="state-input"
              v-model="zipcode"
              @blur="validateInput('zipcode')"
              :class="{ invalid: zipcodeValidity === 'invalid' }"
            />
          </td>
          <td>
            <input
              name="zip"
              placeholder="City..."
              type="text"
              class="short-input"
              v-model.trim="city"
              @blur="validateInput('city')"
              :class="{ invalid: cityValidity === 'invalid' }"
            />
          </td>
        </tr>
        <tr>
          <th colspan="3">
            <label for="properties">Property Type</label>
          </th>
        </tr>
        <tr>
          <td colspan="3">
            <select
              id="property-select"
              name="properties"
              v-model="propertyType"
              @blur="validateInput('property')"
              :class="{ invalid: propertyValidity === 'invalid' }"
            >
              <option disabled value="">Please Choose...</option>
              <option value="apartment">Apartment</option>
              <option value="house">House</option>
              <option value="bed-n-breakfast">Bed and Breakfast</option>
            </select>
          </td>
        </tr>
        <tr>
          <th colspan="3" class="accomodation">
            How many guests can your place accomodate?
          </th>
        </tr>
        <tr class="accomodation-control">
          <th>Guests</th>
          <td>
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
          </td>
        </tr>
        <tr class="accomodation-control">
          <th>Beds</th>
          <td>
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
          </td>
        </tr>
        <tr class="accomodation-control">
          <th>Bathrooms</th>
          <td>
            <div class="button-container">
              <button
                type="button"
                @click="
                  bathroomCounter <= 0
                    ? (bathroomCounter = 0)
                    : bathroomCounter--
                "
              >
                -
              </button>
              <p>{{ bathroomCounter }}</p>
              <button type="button" @click="bathroomCounter++">+</button>
            </div>
          </td>
        </tr>
      </table>
    </form>
  </section>
</template>

<script>
export default {
  emit: ['basicInfo'],
  data() {
    return {
      guestCounter: 1,
      bedCounter: 0,
      bathroomCounter: 0,
      title: '',
      price: 300,
      description: '',
      address: '',
      zipcode: '',
      city: '',
      propertyType: '',
      seniorDiscount: 1,
      childDiscount: 1,
    };
  },
  methods: {
    getFormData() {
      this.$emit('basicInfo', this.$data);
    },
  },
};
</script>

<style scoped>
.invalid {
  border-bottom: 1px solid black;
  background-color: rgb(255, 216, 212);
}

.button-container {
  display: flex;
  flex-direction: row;
}

.button-container th {
  text-align: left;
  font-size: 3rem;
}

table {
  margin: 0 auto;
  width: 80%;
}

section {
  margin: 0 auto;
  margin-top: 2rem;
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
  font-size: 3rem;
}

.title-input {
  width: 100%;
  max-width: 30rem;
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

.accomodation {
  padding-top: 4rem;
  padding-bottom: 4rem;
  font-size: 1.7rem;
}

.accomodation-control th {
  text-align: center;
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
  border-bottom: 1px solid black;
}

.long-input {
  width: 80%;
  max-height: 334px;
}

.state-input {
  width: 75%;
  margin-left: 3rem;
}

.short-input {
  width: 50%;
}
</style>
