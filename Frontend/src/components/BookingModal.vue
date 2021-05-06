<template>
  <section>
    <header>
      <h1>1,350 kr <span class='span-night'>/ night</span></h1>
      <h2>★ 4.25 <span class='span-night'>(23 Reviews)</span></h2>
    </header>
    <article>
      <Calender />
      <GuestModal />
      <button>Check Availability</button>
    </article>
    <footer>
      <div class='price-bar'>
        <p>1,500 kr x 1 night</p>
        <p>1,500 kr</p>
      </div>
      <div class='service-bar'>
        <p>Service fee</p>
        <p>265 kr</p>
      </div>
      <hr />
      <div class='total-bar'>
        <p>Total</p>
        <p>1,765 kr</p>
      </div>
    </footer>
  </section>
</template>

<script>
import Calender from '../components/Calender.vue'
import GuestModal from '../components/GuestModal.vue'

export default {
  components: {
    Calender,
    GuestModal
  },
  data() {
    return {
      adultCounter: 0,
      childCounter: 0,
      seniorCounter: 0
    }
  }
}
</script>

<style scoped>

  section {
    border-radius: 10px;
    box-shadow: rgb(0 0 0 / 12%) 0px 6px 16px;
    background-color: whitesmoke;
    width: fit-content;
    padding: 1rem;
    color: black;
    margin: 0 auto;
  }

  button {
    height: 2rem;
    background-color: red;
    color: white;
    border-radius: 10px;
    outline: none;
  }

  article {
    display: flex;
    flex-direction: column;
  }

  .price-bar {
    display: flex;
    flex-direction: row;
  }

  .price-bar p:last-of-type {
    text-align: right;
    flex-grow: 2;
  }
  .service-bar {
    display: flex;
    flex-direction: row;
  }

  .service-bar p:last-of-type{
    text-align: right;
    flex-grow: 2;    
  }  

  .total-bar {
    display: flex;
    flex-direction: row;
  }

.total-bar p:last-of-type{
    text-align: right;
    flex-grow: 2;      
}
</style>