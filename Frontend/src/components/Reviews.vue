<template>
  <review-item v-for="review of reviews" :review="review" :key="review.id" @popSection="popReplySection"/>
  <section v-if="showReview">
    <article class="top-review">
      <div class="user-bar">
        <img :src="replyReview.avatar" alt="" />
        <p class="review-author"><strong><!--{{ replyReview.user }}--> Kalle</strong></p>
      </div>
      <div class="text-bar">
        <p><strong><!--{{ replyReview.house }}-->Classic Estate In Nature -- </strong> <strong>Grade:</strong> {{ replyReview.grade }} / 5 </p>
        
        <p>{{ replyReview.review }}</p>
      </div>
      
    </article>
    <article v-for="review in replyReviews" :key="review.id" class="top-review reply">
      <div class="user-bar">
        <img :src="replyReview.avatar" alt="" />
        <p class="review-author"><strong><!--{{ replyReview.user }}--> Kalle</strong></p>
      </div>
       <p>{{ replyReview.review }}</p>
    </article>
    
    <div class="create-comment-container">
      <textarea name="description" placeholder="Must be logged in to write comment.." rows="3" cols="50" v-model="reviewText"/>
      <button class="send-button" @click="postReply">Post</button>
    </div>
  </section> 
</template>

<script>
import ReviewItem from "./ReviewItem.vue"

export default {
  props: ['reviews'],
  components: {
    ReviewItem
  },
  data() {
    return {
      showReview: false,
      replyReviews: [
        {
          review: "awdawdawdawdawdawd",
          author: "Jolm",    //if no user user "3333sawdawd!"
          avatar: ""
      },
      {
          review: "awdawdawdawdawdawd",
          author: "Jolm",    //if no user user "3333sawdawd!"
          avatar: ""
      },
      {
          review: "awdawdawdawdawdawd",
          author: "Jolm",    //if no user user "3333sawdawd!"
          avatar: ""
      },
      {
          review: "awdawdawdawdawdawd",
          author: "Jolm",    //if no user user "3333sawdawd!"
          avatar: ""
      },
      ],
      reviewText: "",
      replyReview: null,
    };
  },
  methods: {
    popReplySection(review) {
      console.log(review)
      this.showReview = !this.showReview
      this.replyReview = review 
    },
    postReply() {

      if(!this.$store.state.user) {
        this.$router.push('/login-page')
      }

      //check if user is logged in firsts
       let review = {
         review: this.reviewText,
         author: this.$store.state.user,    //if no user user "3333sawdawd!"
         originalReviewId: this.replyReview,
         avatar: ""
       }

     
        console.log(review)
      


      // console.log(this.replyReview)
      // this.$store.dispatch('postReply', review)
    }
  }
};
</script>

<style scoped>
  section {
    /* background-color: whitesmoke; */
    width: 100%;
    display: flex;
    flex-direction: column;
  }

  .top-review {
    background-color: whitesmoke;
    padding: 2rem;
    position: relative;
    border: 2px solid black;
  }

  .user-bar {
    position: absolute;
    top: 0.5rem;
    left: 1rem;
  }

  .text-bar p {
    margin-bottom: 0;
    text-align: left;
    margin-left: 5rem;
  }

  .create-comment-container {
    display: flex;
    flex-direction: row;
    justify-content: center;
    padding: 2rem;
  }

  textarea {
    padding-top: 0.3rem;
    padding-left: 0.5rem;
  }

  .send-button {
    height: 3rem;
    width: 4rem;
    border-top: none;
    border-right: none;
    outline: none;
    border-radius: 10px 10px 10px 10px;
    margin-left: 1rem;
    cursor: pointer;
    background-color: lightgreen;
    border-color: green;
  }

  .reply {
    width: 80%;
    align-self: flex-end;
    padding: 3rem;
    display: flex;
    flex-direction: row;
    justify-content: center;
  }

  .reply > div {
    flex-grow: 1;
  }
  
</style>
