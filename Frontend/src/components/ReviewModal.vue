<template>
  <div class="backdrop"></div>
  <section class="reviewModal">
    <div class="info-container">
      <h1>Create Review!</h1>
      <p>Please write an accurate review of your experience of your stay!</p>
      <textarea name="description" placeholder="Please Enter a Accurate Description of the Home." rows="10" cols="50" v-model="reviewText"/>
      <p>Grade: </p>
      <select name="grade" v-model="grade">
        <option disabled selected>Please Choose...</option>
        <option value="1">1 Star</option>
        <option value="2">2 Stars</option>
        <option value="3">3 Stars</option>
        <option value="4">4 Stars</option>
        <option value="5">5 Stars</option>
      </select>
    </div>
    <button class="cancel-button" @click="$emit('dismiss')">Dismiss</button>
    <button class="send-button" @click="send">Send</button>
  </section>
</template>

<script>
export default {
  props: ['houseId'],
  emit:['dismiss'],
  data() {
    return {
      reviewText: "",
      grade: "Please Choose...",
    }
  },
  methods: {
    send() {
      if(this.grade === "Please Choose...") return
      
      let review = {
        review: this.reviewText,
        grade: this.grade,
        author: this.$store.state.user.id,    //if no user user "3333sawdawd!"
        gradedHouse: this.houseId,
        avatar: ""
      }
      console.dir(review)
      this.$store.dispatch('postReview', review)
      this.$emit("dismiss")
    }
  }
}
</script>

<style scoped>

.backdrop {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0,0,0,0.7);
  z-index: 4;
}

.reviewModal {
  position: absolute;
  z-index: 5;
  right: 0;
  left: 0;
  margin: 0 auto;
  top: 10rem;
  background-color: white;
  width: 60%;
  max-width: 50rem;
  min-height: 70%;
  border-radius: 10px 10px 10px 10px;
  padding: 3rem;
}

.info-container {
  margin-bottom: 3rem;
}

 .cancel-button {
    height: 3rem;
    width: 4rem;    
    border-top: none;
    border-right: none;
    outline: none;
    border-radius: 10px 10px 10px 10px;
    cursor: pointer;
    margin-right: 2rem;
  }

  .send-button {
    height: 3rem;
    width: 4rem;
    border-top: none;
    border-right: none;
    outline: none;
    border-radius: 10px 10px 10px 10px;
    cursor: pointer;
    background-color: lightgreen;
    border-color: green;
  }

</style>