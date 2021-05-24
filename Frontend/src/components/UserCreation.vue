<template>
  <section>
    <form @submit.prevent="submit">
      <label for="fname">First name:</label>
      <input name="fname" type="text" class="long-input" v-model="firstName" />
      <label for="lname">Last name:</label>
      <input name="lname" type="text" class="long-input" v-model="lastName" />
      <p class="profile-title">Profile Picture:</p>
      <PhotoUploader />
      <textarea
        name="description"
        placeholder="Please Enter a Accurate Description yourself."
        rows="10"
        cols="50"
        v-model="description"
      />
      <p class="gender-title">Gender:</p>
      <div class="radio">
        <input
          type="radio"
          name="drone"
          value="male"
          checked
          v-model="gender"
        />
        <label for="male">Male</label>
      </div>
      <div class="radio">
        <input type="radio" name="drone" value="female" v-model="gender" />
        <label for="female">Female</label>
      </div>
      <div class="radio">
        <input type="radio" name="drone" value="other" v-model="gender" />
        <label for="other">Other</label>
      </div>
      <button type="submit">Submit</button>
    </form>
  </section>
</template>

<script>
import PhotoUploader from './PhotoUploader.vue';

export default {
  components: {
    PhotoUploader,
  },
  data() {
    return {
      firstName: '',
      lastName: '',
      description: '',
      gender: 'male',
      image: '',
      id: this.user.id,
    };
  },
  props: ['user'],
  methods: {
    submit() {
      this.image = this.$store.state.uploadedNames.toString();
      this.$store.dispatch('updateUser', this.$data);
    },
  },
};
</script>

<style scoped>
.profile-title {
  font-size: 1.6rem;
  margin-bottom: 0;
}

.gender-title {
  font-size: 1.6rem;
}

section {
  margin-top: 5rem;
  background-color: white;
  border-radius: 10px;
  width: 80%;
  padding-top: 2rem;
  margin: 0 auto;
}

input {
  height: 2rem;
  outline: none;
  border-radius: 8px;
  border: 1p solid black;
}

input:last-of-type {
  margin-bottom: 2rem;
}

.radio {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  text-align: left;
}

.radio label {
  margin: 0;
  /* text-align: center; */
}

.radio input {
  margin: 0;
  margin-right: 1rem;
}

.long-input {
  width: 40%;
  margin-top: 1rem;
}

label {
  display: block;
  margin-top: 1rem;
}

textarea {
  display: block;
  margin: 0 auto;
  padding: 1rem;
  margin-bottom: 3rem;
  margin-top: 2rem;
}

button {
  margin-top: 3rem;
  margin-bottom: 2rem;
  height: 2rem;
  background-color: red;
  color: white;
  border-radius: 10px;
  outline: none;
  cursor: pointer;
}
</style>
