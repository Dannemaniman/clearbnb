<template>
  <section>
    <p class="title">Home Info</p>
    <form>
      <p>Address</p>
      <input type="text" class="address-input"/>
      <p>Zip Code</p>
      <input type="text" class="state-input"/>
      <input type="text" class="zipcode-input"/>
      <p>Property Type</p>
      <select id="property-select" name="properties">
        <option value="">Please Choose...</option>
        <option value="apartment">Apartment</option>
        <option value="house">House</option>
        <option value="bed-n-breakfast">Bed and Breakfast</option>
      </select>
      <div class="accomodate-info">
        <p>How many guests can your place accomodate?</p>
        <div class="basicinfo-buttonbar">
          <p>Guests</p>
          <div class='button-container'>
            <button type="button" @click='guestCounter <= 0 ? guestCounter = 0 : guestCounter--'>-</button>
            <p>{{guestCounter}}</p>
            <button type="button" @click='guestCounter++'>+</button>
          </div>                    
        </div>
        <div class="basicinfo-buttonbar">
          <p>Beds</p>
          <div class='button-container'>
            <button type="button" @click='bedCounter <= 0 ? bedCounter = 0 : bedCounter--'>-</button>
            <p>{{bedCounter}}</p>
            <button type="button" @click='bedCounter++'>+</button>
          </div>
        </div>
        <div class="basicinfo-buttonbar">
          <p>Bathrooms</p>
          <div class='button-container'>
            <button type="button" @click='bathroomCounter <= 0 ? bathroomCounter = 0 : bathroomCounter--'>-</button>
            <p>{{bathroomCounter}}</p>
            <button type="button" @click='bathroomCounter++'>+</button>
          </div>
        </div>
      </div>
    </form>
  </section>
</template>

<script>
export default {
  data() {
    return {
      guestCounter: 0,
      bedCounter: 0,
      bathroomCounter: 0
    }
  }
}
</script>

<style scoped>

section {
  margin: 0 auto;
  margin-top: 2rem;
  background-color: #a9a9a9;
  border-radius: 10px;
  width: 80%;
  color: black;
  padding: 1rem;
}

.title {
  font-size: 2rem;
}

select {
  height: 2rem;
  width: 30%;
  outline: none;
  padding: 0.2rem;
}

.accomodate-info {
  margin-top: 3rem;
}

.basicinfo-buttonbar{
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

.address-input {
  width: 60%;
}

.state-input {
  width: 35%;
  margin-right: 2rem;
}

.zipcode-input {
  width: 20%;

}
</style>