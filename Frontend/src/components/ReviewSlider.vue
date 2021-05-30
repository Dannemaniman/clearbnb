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
        @click="popReview(review.id)"
        :ref="review.id"
      >
        <div class="review-header">
          <img class="avatar-icon" :src="user.image" alt="" />
          <strong class="review-topcard">{{ user.fullName }}</strong>
        </div>
        <div class="review-grade">{{ review.grade }} / 5 &#9733;</div>
        <div class="review-body">
          {{ review.review }}
        </div>
        <img
          class="conversation-icon"
          src="../../public/icons/conversation.png"
          @click="popReplySection(review)"
        />
      </article>
      <div class="arrow-right" @click="moveRight" />
    </div>
    <transition name="fade">
      <section v-if="showReview">
        <article
          v-for="review in replyReviews"
          :key="review.id"
          class="top-review reply"
        >
          <div class="review-header smaller">
            <img class="avatar-icon" :src="review.avatar" alt="" />
            <strong class="reply-author">{{ review.author }}</strong>
          </div>
          <p class="reply-content">{{ review.reply }}</p>
        </article>
        <div class="create-comment-container">
          <textarea
            name="description"
            placeholder="Must be logged in to write comment.."
            rows="3"
            cols="50"
            v-model="reviewText"
          />
          <button class="send-button" @click="postReply">Post</button>
        </div>
      </section>
    </transition>
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
      showReview: false,
      replyReviews: null,
      reviewText: '',
      replyId: null,
      user: '',
    };
  },
  methods: {
    async getUserName() {
      await this.$store.state.users.forEach((user) => {
        this.reviews.forEach((review) => {
          if (user.id === review.authorId) {
            this.user = user;
          }
        });
      });
    },
    postReply() {
      if (!this.$store.state.user) {
        this.$router.push('/login-page');
        return;
      }
      let reply = {
        reviewId: this.replyId,
        avatar: this.$store.state.user.image,
        author: this.$store.state.user.fullName,
        reply: this.reviewText,
      };
      this.$store.dispatch('postReply', reply);
      this.reviewText = '';
      this.popReview(this.replyId);
    },
    moveRight() {
      this.moveCounter >= this.reviews.length - 1
        ? (this.moveCounter = 0)
        : (this.moveCounter += 1);

      for (let ref in this.$refs) {
        this.$refs[ref].style.transform = `translateX(-${
          272 * this.moveCounter
        }px)`;
      }
    },
    moveLeft() {
      this.moveCounter <= 0 ? (this.moveCounter = 0) : (this.moveCounter -= 1);

      for (let ref in this.$refs) {
        this.$refs[ref].style.transform = `translateX(-${
          272 * this.moveCounter
        }px)`;
      }
    },
    async popReview(reviewId) {
      let element = this.$refs[reviewId];
      this.showReview = !this.showReview;
      this.replyId = reviewId;
      await this.$store.dispatch('fetchReplies', reviewId);
      this.replyReviews = await this.$store.state.replies;

      if (this.showReview) {
        element.style.maxWidth = '100%';
        element.style.width = '100%';
        element.style.height = '20rem';
        element.style.position = 'absolute';
        element.style.zIndex = '7';
        element.style.marginRight = '0';
        element.style.marginLeft = '0';
        element.children[0].children[1].style.paddingLeft = '2rem';
      } else {
        element.style.maxWidth = '';
        element.style.width = '';
        element.style.position = '';
        element.style.zIndex = '';
        element.style.marginRight = '';
        element.style.marginLeft = '';
        element.style.height = '';
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
.smaller {
  font-size: 1.6rem;
}

.section-slider {
  width: 100%;
  background-color: grey;
  border-top: 3px solid black;
  box-shadow: rgba(50, 50, 93, 0.25) 0px 30px 60px -12px inset,
    rgba(0, 0, 0, 0.3) 0px 18px 36px -18px inset;
  padding: 0.5rem;
  border-radius: 10px;
  margin-top: 5rem;
  font-size: 2rem;
  line-height: 110%;
}

.slider-container {
  width: 100%;
  min-height: 240px;
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
  height: 50px;
  width: 50px;
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
  border-radius: 10px;
  margin: 0.3rem 1rem;
  transition: all 0.2s ease;
  box-shadow: rgba(50, 50, 93, 0.25) 0px 2px 5px -1px,
    rgba(0, 0, 0, 0.3) 0px 1px 3px -1px;
}

.review-card:hover,
.review-card:active {
  background-color: white;
}

.reply-header {
  text-align: left;
  justify-content: left;
  height: 100%;
  position: absolute;
  top: 1rem;
  left: 1rem;
  display: flex;
  flex-direction: row;
  justify-content: center;
}

.reply-content {
  width: 90%;
  padding: 0.3rem;
  min-height: 5rem;
  text-align: left;
  margin: 1rem auto;
  margin-bottom: 0;
  margin-top: 3rem;
  border-radius: 10px;
  background: white;
  box-shadow: rgba(50, 50, 93, 0.25) 0px 2px 5px -1px,
    rgba(0, 0, 0, 0.3) 0px 1px 3px -1px;
  font-size: 1.6rem;
  /* overflow:scroll; */
}

.avatar-icon {
  margin-right: 1rem;
  position: relative;
  background-color: #c7c7c7;
  border-radius: 50px;
  box-shadow: rgba(0, 0, 0, 0.39) 0px 2px 5px -1px,
    rgba(0, 0, 0, 0.3) 0px 1px 3px -1px;
}

strong {
  position: relative;
  left: -1rem;
}

.reply-author {
  /* white-space: nowrap;
  overflow: hidden;
  scroll-behavior: smooth;
  /* text-overflow: ellipsis; */
  /* max-width: 100px;  
  overflow:scroll; */
  line-height: 125%;
}

.review-header {
  display: flex;
  align-items: center;
  align-self: flex-start;
  position: absolute;
  top: 2rem;
}

.review-header strong {
  padding-left: 1rem;
  font-size: 1rem;
}

.author {
  margin-top: 8px;
  font-size: 0.85rem;
  line-height: 1rem;
}

.review-grade {
  position: absolute;
  bottom: 1rem;
  left: 2rem;
}

.review-body {
  margin-top: 16px;
  font-size: 0.9rem;
  line-height: 1.25rem;
  position: absolute;
  right: 0;
  left: 0;
  margin: 0 auto;
  width: 50%;
  /* border-radius: 10px; */
  /* background: white;
  box-shadow: rgba(50, 50, 93, 0.25) 0px 2px 5px -1px,
    rgba(0, 0, 0, 0.3) 0px 1px 3px -1px;
    font-size: 1.7rem;
    padding: 1rem;
    height: 50%;
    bottom: 1rem;
    text-align: left; */
}

.conversation-icon {
  height: 1.9rem;
  width: 1.9rem;
  position: absolute;
  bottom: 1rem;
  right: 1rem;
  cursor: pointer;
  border-bottom: 3px solid transparent;
}

.conversation-icon:hover,
.conversation-icon:active {
  border-bottom: 3px solid black;
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s;
}
.fade-enter,
.fade-leave-to {
  opacity: 0;
}

section {
  width: 100%;
  display: flex;
  flex-direction: column;
}

.top-review {
  background-color: whitesmoke;
  padding: 2rem;
  position: relative;
  border-bottom: 1px solid black;
  padding: 0.5rem;
  border-radius: 10px;
  margin: 1rem auto;
  box-shadow: rgba(50, 50, 93, 0.25) 0px 2px 5px -1px,
    rgba(0, 0, 0, 0.3) 0px 1px 3px -1px;
}

.reply {
  width: 80%;
  align-self: center;
  padding: 3rem;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  height: 100%;
  padding-bottom: 1rem;
  padding-left: 1.7rem;
}

.user-bar {
  position: absolute;
  top: 0.5rem;
  left: 1rem;
  display: flex;
  flex-direction: row;
}

.text-bar p {
  margin-bottom: 0;
  text-align: left;
  margin-left: 5rem;
}

.create-comment-container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  padding: 2rem;
}

textarea {
  padding-top: 0.3rem;
  padding-left: 0.5rem;
  border-radius: 10px;
  height: 10rem;
  width: 100%;
  font-size: 1.2rem;
}

.send-button {
  /* height: 4rem;
    width: 7rem; */
  padding: 1rem;
  font-size: 2.5rem;
  border-top: none;
  border-right: none;
  outline: none;
  border-radius: 10px 10px 10px 10px;
  margin-left: 1rem;
  cursor: pointer;
  background-color: white;
  border-color: green;
  margin: 0 auto;
  margin-top: 2rem;
  cursor: pointer;
}

.send-button:hover,
.send-button:active {
  background-color: rgb(190, 190, 190);
}

.reply:hover,
.reply:active {
  background: rgb(211, 211, 211);
}

@media screen and (max-width: 430px) {
  .reply {
    margin: auto 0;
    width: 100%;
  }
  .reply-content {
    width: 100%;
  }
  .reply-author {
    font-size: 6vw;
  }
  .review-topcard {
    font-size: 8vw;
  }

  .reply-content {
    font-size: 6vw;
    overflow: hidden;
  }
}
</style>
