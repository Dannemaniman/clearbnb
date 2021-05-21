<template>
  <article class="review-card">
    <div class="review-header">
      <img :src="review.avatar" alt="" />
      {{ review.grade }} / 5
    </div>
    <div class="author">
      <strong>{{ user }}</strong>
      reviewed
      <strong>{{ house }}</strong>
    </div>
    <div class="review-body">
      {{ review.review }}
    </div>
    <!-- <img class="conversation-icon" src="../../public/icons/conversation.png" v-if="review.replies"/> -->
    <img class="conversation-icon" src="../../public/icons/conversation.png" @click="$emit('popSection', review)"/>
  </article>
</template>

<script>
export default {
  props: ['review'],
  emit: ['popSection'],
  data() {
    return {
      houses: [],
      house: '',
      users: '',
      user: '',
      showReview: true
    };
  },
  methods: {
    getHouseTitle() {
      this.houses = this.$store.state.houses;
      this.houses.forEach((house) => {
        if (house.id == this.review.gradedHouse) {
          this.house = house.title;
          console.log(this.house);
        }
      });
    },
    getUserName() {
      this.users = this.$store.state.users;
      this.users.forEach((user) => {
        if (user.id == this.review.author) {
          this.user = user.fullName;
        }
      });
    },
  },
  created() {
    this.getHouseTitle();
    this.getUserName();
    console.log(this.review)
  },
};
</script>

<style scoped>
.review-card {
  min-width: 240px;
  max-width: 30%;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  padding: 24px;
  margin: 0 0 16px 0;
  background-color: #f2f2f2;
  color: #4b4b4b;
  position: relative;
}
.review-header {
  display: flex;
  align-items: center;
}
.review-header img {
  margin-right: 3rem;
  background-color: #c7c7c7;
  border-radius: 50px;
}
.author {
  margin-top: 8px;
  font-size: 0.85rem;
  line-height: 1rem;
}
.review-body {
  margin-top: 16px;
  font-size: 0.9rem;
  line-height: 1.25rem;
}

.conversation-icon {
  height: 1.4rem;
  width: 1.4rem;
  position: absolute;
  bottom: 0.5rem;
  right: 0.5rem;
  cursor: pointer;
  border-bottom: 3px solid transparent;
}

.conversation-icon:hover, .conversation-icon:active {
  border-bottom: 3px solid black;
}

</style>
