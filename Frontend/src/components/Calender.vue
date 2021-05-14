<template>
  <section class='calender' @click='popModal'>
    <div class='checkin-container'>
      <p>{{firstDate}}</p>
    </div>
    <div class='checkout-container'>
      <p>{{secondDate}}</p>
    </div>
    <div v-if='showModal' class='calender-modal'>
      <header class='calender-title'>
        <p class='calender-nights'>1 night</p>
        <p class='calender-datetitle'>May 15, 2021 - May 16, 2021</p>
      </header>
      <div class='arrow-left' @click='moveLeft'></div>
      <article class='calender-month'>
        <CalenderTable v-for='(days, month, index) in daysPerMonth' :key="index" :month='month' :days='days' @setDate="setDate"/>
      </article>
       <div class='arrow-right' @click='moveRight'></div>
      <button @click='closeModal'>Close</button>
    </div>
  </section>
</template>

<script>
import CalenderTable from "./CalenderTable.vue"

export default {
  components: {
    CalenderTable
  },
  data() {
    return {
      showModal: false,
      firstDate: "Check-in",
      secondDate: "Check-out",
      moveCounter: 0,
      daysPerMonth: {
        "January": 31,
        "February": 28,
        "March": 31,
        "April": 30,
        "May": 31, 
        "June": 30,
        "July": 31,
        "August": 31,
        "September": 30,
        "October": 31,
        "November": 30,
        "December": 31
      },
      current: 0
    }
  },
  methods: {
    popModal() {
      this.showModal = true
    },
    closeModal(e) {
      this.showModal = false;
      e.stopPropagation();
    },
    setDate(date) {
      this.current === 0 ? this.firstDate = date : this.secondDate = date
      this.current === 0 ? this.current = 1 : this.current = 0
    },
    moveRight() {
      let calenders =  document.querySelectorAll('table')
      this.moveCounter >= 12-2 ? this.moveCounter = 0 : this.moveCounter += 1

      calenders.forEach((item, index) => {
         item.style.transform = `translateX(-${307 * this.moveCounter}px)`
      })
    },
    moveLeft() {
      let calenders =  document.querySelectorAll('table')
      this.moveCounter <= 0 ? this.moveCounter = 12-2 : this.moveCounter -= 1

      calenders.forEach((item, index) => {
         item.style.transform = `translateX(-${307 * (this.moveCounter)}px)`
      })
    }
  }
}
</script>

<style scoped>

  .calender {
    display: flex;
    flex-direction: row;
    border: 1px solid black;
    box-shadow: rgb(0 0 0 / 12%) 0px 6px 16px;
    margin-bottom: 1rem;
    position: relative;
  }

  section:hover, section:active {
    cursor: pointer;
    background-color: rgba(240, 220, 45, 0.1)
  }

  p {
    margin: 0;
  }

  .checkin-container {
    margin: 0;
    border-right: 1px solid black;
    padding-right: 0.5rem;
    position: relative;
    z-index: 3;
  }

  .checkout-container {
    margin: 0;
    padding-left: 0.5rem;
    position: relative;
    z-index: 3;
  }

  .calender-modal {
    width: 40rem;
    background-color: whitesmoke;
    position: absolute;
    z-index: 2;
    border-radius: 10px;
    border: 2px solid black;
    top: -1rem;
    right: -1rem;
    overflow: hidden;
    padding-bottom: 2rem;
    cursor: default;
  }

  .calender-title {
    position: absolute;
    top: 0;
    left: 0;
    padding-left: 3rem;
    padding-top: 1.5rem;
  }

  .calender-nights {
    text-align: left;
  }

  .calender-datetitle {
    font-size: 0.6rem;
  }

  .calender-month {
    display: flex;
    flex-direction: row;
  }

  .arrow-right {
    position: absolute;
    z-index:3;
    top: 50%;
    right: 2%;
    width: 0; 
    height: 0; 
    border-top: 20px solid transparent;
    border-bottom: 20px solid transparent;
    border-left: 25px solid rgba(0, 0, 0, 0.472);
    cursor: pointer;
  }

  .arrow-left {
    position: absolute;
    z-index:3;
    top: 50%;
    left: 2%;
    width: 0; 
    height: 0; 
    border-top: 20px solid transparent;
    border-bottom: 20px solid transparent;
    border-right: 25px solid rgba(0, 0, 0, 0.472);
    cursor: pointer;    
    transition: all 0.1s ease-in;
  }

  .arrow-left:active, .arrow-left:hover {
    border-right: 25px solid rgba(104, 104, 104, 0.472);
  } 
  .arrow-right:active, .arrow-right:hover{
     border-left: 25px solid rgba(104, 104, 104, 0.472);
  }

  button {
    width: 10rem;
    height: 3rem;
    border-radius: 10px;
    outline: none;
    font-weight: 400;
    cursor: pointer;
    background-color:rgb(235, 235, 235);
    transition: all 0.2s ease;
  }

  button:hover, button:active {
    box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
  }
</style>