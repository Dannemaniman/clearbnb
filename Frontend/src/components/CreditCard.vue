<template>
    <hr class="separator"/> 
      <div class="credit_container">
       <p class="booking-card">Valt kort:  </p> 
       <div :class="cardColor2" class="credit-card" >
        <div class="credit-title" >{{ cardType }}</div>
        <div class="card-chip">
          <div class="chip-firstline">
            <div class="chip-firstline-leftdent"></div>
            <div class="chip-firstline_rightdent"></div>
          </div>
          <div class="chip-secondline">
            <div class="chip-secondline_middledent_left"></div>
            <div class="chip-secondline_middledent_right"></div>
          </div>
          <div class="chip-thirdline">
            <div class="chip-thirdline_leftdent"></div>
            <div class="chip-thirdline_rightdent"></div>                   
          </div>
        </div>
        <div class="card-number"> 
              <input placeholder="Card Number" type="text" class="card-number" />
        </div>
        <div class="card-validation">
            <input placeholder="CVV" type="text" class="card-cvv" />
          <div class="card-valid">VALID THRU</div>
          <div class="card-triangle"></div>
          <div class="card-monthyear">     
              <input
                  placeholder="09"
                  type="text"
                  class="card-expiration"
              />
              <p class="separator"> / </p>
              <input 
                  placeholder="24"
                  type="text"
                  class="card-expiration"
                  />
          </div>             
        </div>
        <div class="card-name"></div>
      </div>
    </div>  
</template>

<script>
export default {
    props: ['cardType'],
    data() {
        return {
          cardColor: "black"
        }
    },
    computed: {
      cardColor2: function(){
        if(this.cardType === "visa"){
          return "visa-blue"
        }
        else if(this.cardType === "mastercard"){
          return "master-salmon"
        }
        else if(this.cardType === "american-express"){
          return "express-black"
        }
      }
  },

}
</script>

<style scoped>

.master-salmon {
    background-color: orange;
}

.visa-blue {
  background-color: rgb(61, 108, 188);
}

.express-black {
    background-color: rgb(32, 32, 32);
    color: white;
}

.card-cvv {
  width: 2.6rem;
  height: 1.6rem;
  position: absolute;
  left: 0;
}

.separator {
    font-size: 1.6rem;    
    width: 90%;
    height: 1px;
    margin: 0;
    color: rgb(44, 44, 44);
    position: relative;
    top: 0.5rem;
    padding-left: 0.2rem;
}

.card-monthyear {
    font-size: 2rem;
    display: flex;
    flex-direction: row;  
}

.credit_container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding-bottom: 3rem;
}

.card-expiration {
  width: 2rem;
  height: 1.5rem;
  left: -2rem;
}

.card-expiration{
    padding-left: 0.4rem;    
}

.card-number {
  width: 95%;
  position: relative;
  top: 0.2rem;
  height: 1.8rem;
  margin: 0 auto;
}

.credit-card {
  border-radius: 6px;
  height: 12rem;
  width: 20rem;
  margin-left: 0rem;
  display: flex;
  flex-direction: column;
  justify-content: center;
  line-height: 1rem;
  box-shadow: rgba(0, 0, 0, 0.25) 0px 54px 55px,
    rgba(0, 0, 0, 0.12) 0px -12px 30px, rgba(0, 0, 0, 0.12) 0px 4px 6px,
    rgba(0, 0, 0, 0.17) 0px 12px 13px, rgba(0, 0, 0, 0.09) 0px -3px 5px;
  animation: movecard 1s ease-in-out infinite;
}

.credit-card:hover,
.credit-card:active {
  box-shadow: rgba(255, 251, 0, 0.01) 0px 54px 55px,
    rgba(255, 251, 0, 0.25) 0px -12px 30px, rgba(255, 251, 0, 0.25) 0px 4px 6px,
    rgba(255, 251, 0, 0.25) 0px 12px 13px, rgba(255, 251, 0, 0.25) 0px -3px 5px;
  animation: movecard 0.6s ease infinite;
}

.credit-title {
  position: absolute;
  font-size: 1.3rem;
  color: white;
  text-align: right;
  padding-right: 2rem;
  position: relative;
  top: -5px;
}

.card-chip {
  display: flex;
  flex-direction: column;
  height: 1.5rem;
  width: 1.8rem;
  background-color: silver;
  margin-left: 2rem;
  transform: scale(1.5);
}

.chip-firstline {
  border-bottom: 1px solid grey;
  border-radius: 100px 0 0 0;
  flex-grow: 1;
  position: relative;
}

.chip-firstline-leftdent {
  height: calc(100% + 1px);
  width: 0.4rem;
  position: absolute;
  border-left: 1px solid grey;
  border-radius: 60% 0 0 0;
  flex-grow: 1;
  left: 0.66rem;
}

.chip-firstline_rightdent {
  height: calc(100% + 1px);
  width: 0.4rem;
  position: absolute;
  border-right: 1px solid grey;
  border-radius: 0 60% 0 0;
  flex-grow: 1;
  right: 0.66rem;
}

.chip-secondline {
  border-bottom: 1px solid grey;
  flex-grow: 1;
  position: relative;
}

.chip-secondline_middledent_left {
  position: absolute;
  height: calc(100% + 3px);
  width: 30%;
  border-radius: 40%;
  border-right: 1px solid grey;
  left: 0.22rem;
  top: -2px;
}

.chip-secondline_middledent_right {
  position: absolute;
  height: calc(100% + 3px);
  width: 30%;
  border-radius: 40%;
  border-left: 1px solid grey;
  right: 0.22rem;
  top: -2px;
}

.chip-thirdline {
  border-bottom: 1px solid grey;
  flex-grow: 1;
  position: relative;
}

.chip-thirdline_leftdent {
  height: calc(100% + 1px);
  width: 0.4rem;
  position: absolute;
  border-left: 1px solid grey;
  border-radius: 0 0 0 65%;
  flex-grow: 1;
  left: 0.66rem;
}

.chip-thirdline_rightdent {
  height: calc(100% + 1px);
  width: 0.4rem;
  position: absolute;
  border-right: 1px solid grey;
  border-radius: 0 0 65% 0;
  flex-grow: 1;
  right: 0.66rem;
}

.card-number {
  margin-top: 0.2rem;
  color: black;
  font-size: 0.8rem;
  text-align: center;
  letter-spacing: 0rem;
  text-align: left;
  padding-left: 0.3rem;
}

.card-name {
  color: white;
  font-size: 0.6rem;
  position: absolute;
  margin-left: 0.3rem;
  bottom: 0.2rem;
}

.card-validation {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  line-height: 0.2rem;
  margin-top: 1rem;
  position: relative;
  right: -2rem;
}

.card-monthyear {
  margin-left: 0.2rem;
  color: white;
  font-size: 0.6rem;
  letter-spacing: 0.01rem;
  line-height: 0.5rem;
}

.card-valid {
  font-size: 0.6rem;
  font-weight: bold;
  flex-wrap: wrap;
  padding-left: 1rem;
  text-align: right;
}

.card-triangle {
  width: 0;
  height: 0;
  border-top: 3px solid transparent;
  border-bottom: 3px solid transparent;
  border-left: 5px solid black;
  position: relative;
  top: -1px;
}

</style>