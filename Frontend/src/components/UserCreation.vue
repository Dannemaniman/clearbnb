<template>
  <section>
    <ErrorModal
      errorMessage="Invalid fields!"
      errorDescription="Please look over the fields again."
      @closeModal="closeModal"
      v-if="showError === true"
    />
    <form @submit.prevent="submit">
      <table>
        <tr>
          <th>
            <label class="label-name" for="fname">First name:</label>
            <p v-if="firstNameValidity === 'invalid'" style="color: red">
              Invalid first name
            </p>
          </th>
          <td>
            <input
              class="long-input"
              name="fname"
              type="text"
              v-model.trim="userInfo.firstName"
              @blur="validateInput('fname')"
              :class="{ invalid: firstNameValidity === 'invalid' }"
            />
          </td>
        </tr>
        <tr>
          <th>
            <label class="label-name" for="lname">Last name:</label>
            <p v-if="lastNameValidity === 'invalid'" style="color: red">
              Invalid last name
            </p>
          </th>
          <td>
            <input
              class="long-input"
              name="lname"
              type="text"
              v-model.trim="userInfo.lastName"
              @blur="validateInput('lname')"
              :class="{ invalid: lastNameValidity === 'invalid' }"
            />
          </td>
        </tr>
      </table>
      <div class="description-container">
        <label class="label-description">Description:</label>
        <textarea
          name="description"
          placeholder="Please Enter a Accurate Description yourself."
          rows="10"
          cols="50"
          v-model="userInfo.description"
          @blur="validateInput('textarea')"
          :class="{ invalid: textareaValidity === 'invalid' }"
        />
      </div>
      <p v-if="textareaValidity === 'invalid'" style="color: red; margin-top: 0;">
        Invalid description</p>
      <p class="gender-title">Gender:</p>
      <div class="radio">
        <input
          type="radio"
          name="drone"
          value="male"
          checked
          v-model="userInfo.gender"
        />
        <label class="radio-label" for="male">Male</label>
        <input type="radio" name="drone" value="female" v-model="gender" />
        <label class="radio-label" for="female">Female</label>
        <input type="radio" name="drone" value="other" v-model="gender" />
        <label class="radio-label" for="other">Other</label>
      </div>
      <p class="profile-title">Profile Picture:</p>
      <PhotoUploader />
      <button type="submit">Submit</button>
    </form>
  </section>
</template>

<script>
import PhotoUploader from './PhotoUploader.vue';
import ErrorModal from './ErrorModal.vue';

export default {
  components: {
    PhotoUploader,
    ErrorModal,
  },
  data() {
    return {
      userInfo: {
        firstName: '',
        lastName: '',
        description: '',
        gender: 'male',
        image: '',
        id: this.user.id,
      },
      firstNameValidity: 'pending',
      lastNameValidity: 'pending',
      textareaValidity: 'pending',
      showError: false,
    };
  },
  props: ['user'],
  methods: {
    submit() {
      this.userInfo.image = this.$store.state.uploadedNames.toString();
      if (
        this.firstNameValidity === 'valid' &&
        this.lastNameValidity === 'valid' &&
        this.textareaValidity === 'valid'
      ) {
        this.$store.dispatch('updateUser', this.userInfo);
        this.$router.go();
      } else {
        this.showError = true;
      }
    },
    closeModal() {
      this.showError = false;
    },
    validateInput(type) {
      if (type === 'fname') {
        if (this.userInfo.firstName === '') {
          this.firstNameValidity = 'invalid';
        } else {
          this.firstNameValidity = 'valid';
        }
      } else if (type === 'lname') {
        if (this.userInfo.lastName === '') {
          this.lastNameValidity = 'invalid';
        } else {
          this.lastNameValidity = 'valid';
        }
      } else if (type === 'textarea') {
        if (this.userInfo.description === '') {
          this.textareaValidity = 'invalid';
        } else {
          this.textareaValidity = 'valid';
        }
      }
    },
  },
};
</script>

<style scoped>
.description-container {
  display: flex;
  flex-direction: row;
  align-items: baseline;
  position: relative;
  margin-top: 2rem;
}

.description-container label {
  position: relative;
  right: -1rem;
  font-weight: bold;
}

table {
  margin: 0 auto;
  width: 100%;
}

tr {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: baseline;
  width: 100%;
  margin-top: 2rem;
}

td {
  padding-left: 2rem;
  width: 100%;
}

.label-description {
  font-size: 1.7rem;
}

.invalid {
 background-color: salmon;
}

.profile-title {
  font-size: 1.6rem;
  margin: 4rem auto 2rem auto;
  text-align: left;
  position: relative;
  left: 1rem;
  font-weight: bold;
}

.gender-title {
  font-size: 1.6rem;
  text-align: left;
  position: relative;
  left: 1rem;
  font-weight: bold;
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

.radio {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  text-align: left;
}

.radio label {
  margin: 0;
}

.radio input {
  margin: 0;
  margin-right: 1rem;
}

.long-input {
  width: 100%;
  margin-top: 1rem;
}

label {
  display: block;
}

.label-name {
  font-size: 1.7rem;
  width: 10rem;
}

input {
  padding-left: 0.6rem;
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
  width: 20%;
  height: 4rem;
  border-radius: 10px;
  outline: none;
  font-weight: 700;
  cursor: pointer;
  background: transparent;
  transition: all 0.2s ease;
  border: 0;
  background-image: url('https://www.getaccept.com/hubfs/Product%20pages%202.0/Product%20tour/bottom%20wave.svg');
  background-repeat: no-repeat;
  background-size: contain;
  background-size: cover;
  filter: brightness(130%);
}

button:hover,
button:active {
  box-shadow: rgba(0, 0, 0, 0.25) 0px 5px 15px;
  filter: brightness(100%);
}

input[type='radio'] {
  margin-left: 3rem;
}
</style>
