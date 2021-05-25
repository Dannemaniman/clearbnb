<template>
  <section class="section-slider">
    <div class="slider-container">
      <div class="arrow-left" @click="moveLeft" />
      <article
        class="review-card"
        :class="`slide${String(Math.random()).replace('.', '')}`"
        v-for="review of reviews"
        :review="review"
        :key="review.id"
        @popSection="popReplySection(review)"
        :ref="String(Math.random()).replace('.', '')"
      >
        <div class="review-header">
          <img :src="user.image" alt="" />
          <strong>{{ user.fullName }}</strong>
        </div>
        <div class="review-grade">{{ review.grade }} / 5</div>
        <div class="review-body">
          {{ review.review }}
        </div>
        <img
          class="conversation-icon"
          src="../../public/icons/conversation.png"
          @click="$emit('popSection', review)"
        />
      </article>
      <div class="arrow-right" @click="moveRight" />
    </div>
  </section>
</template>

<script>
import ReviewItem from './ReviewItem.vue';

export default {
  props: ['reviews'],
  components: {
    ReviewItem,
  },
  data() {
    return {
      moveCounter: 0,
      showReview: true,
      users: '',
      firstReviews: '',
      user: '',
    };
  },
  methods: {
    getUserName() {
      this.users = this.$store.state.users;
      this.firstReviews = this.$store.state.reviews;
      this.users.forEach((user) => {
        this.firstReviews.forEach((review) => {
          if (user.id == review.authorId) {
            this.user = user;
          }
        });
      });
    },
    moveRight() {
      this.moveCounter >= this.reviews.length - 1
        ? (this.moveCounter = 0)
        : (this.moveCounter += 1);

      for (let ref in this.$refs) {
        this.$refs[ref].style.transform = `translateX(-${
          240 * this.moveCounter
        }px)`;
      }
    },
    moveLeft() {
      this.moveCounter <= 0 ? (this.moveCounter = 0) : (this.moveCounter -= 1);

      for (let ref in this.$refs) {
        this.$refs[ref].style.transform = `translateX(-${
          240 * this.moveCounter
        }px)`;
      }
    },
  },
  created() {
    this.getUserName();
  },
  updated() {
    this.getUserName();
  },
};
</script>

<style scoped>
.section-slider {
  width: 100%;
  background-color: grey;
  border-top: 3px solid black;
}

.slider-container {
  width: 100%;
  display: flex;
  flex-direction: row;
  overflow: hidden;
  position: relative;
}

article {
  transition: all 0.3s ease;
  cursor: pointer;
}

img {
  height: 30px;
  width: 30px;
}

.arrow-right {
  position: absolute;
  z-index: 3;
  top: 40%;
  right: 0;
  width: 0;
  height: 0;
  border-top: 30px solid transparent;
  border-bottom: 30px solid transparent;
  border-left: 30px solid black;
  cursor: pointer;
}

.arrow-right:hover,
.arrow-right:active {
  border-left: 30px solid white;
}

.arrow-left {
  z-index: 3;
  position: absolute;
  top: 40%;
  width: 0;
  height: 0;
  border-top: 30px solid transparent;
  border-bottom: 30px solid transparent;
  border-right: 30px solid black;
  cursor: pointer;
}

.arrow-left:hover,
.arrow-left:active {
  border-right: 30px solid white;
}

.review-card {
  min-width: 240px;
  max-width: 30%;
  height: 240px;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  padding: 24px;
  margin: 0;
  background-color: #f2f2f2;
  color: #4b4b4b;
  position: relative;
  border-right: 1px solid black;
}
.review-header {
  display: flex;
  align-items: center;
  align-self: flex-start;
  position: absolute;
  top: 2rem;
}
.review-header img {
  margin-right: 2rem;
  background-color: #c7c7c7;
  border-radius: 50px;
}
.author {
  margin-top: 8px;
  font-size: 0.85rem;
  line-height: 1rem;
}

.review-grade {
  position: absolute;
  bottom: 1rem;
  left: 1rem;
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

.conversation-icon:hover,
.conversation-icon:active {
  border-bottom: 3px solid black;
}
</style>
