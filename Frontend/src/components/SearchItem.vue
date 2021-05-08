<template>
  
     <div class="main" @click="goToDetails">
    <div class="main-content">
      <span>300+ stays</span>
      <h1>Unique stays</h1>
     
      <div class="main-searchcity">
        <span>
          Review COVID-19 travel restrictions before you book.<a href="/#/">Learn more</a>
        </span>
      </div>
    
    
          <div class="list">
            <div class="list-item">
              <div class="list-image">
                <img :src="house.images[0]" />
              </div>
              <div class="list-content">
                <div class="list-title">
                  <div class="list-text">
                    <span class="greyTxt">Private house in {{ house.city }}, {{ house.propertyType }}</span>
                    <h2>{{ house.address }}</h2>
                  
                  </div>
                  <div class="favorite-icon">
                   <router-link to="/login-page"> <button ><i class="far fa-heart "></i></button> </router-link>
                  </div>
          
                </div> 
          
                <div class="seperator"></div>
                <div class="list-diescription">
                  <span class="greyTxt">2 Guest</span>
                  <span class="greyTxt">1 bedroom.2 beds.1.5 baths</span>
                </div>
                <div class="list-detailis">
                  <div class="rating">
                    <i class="fas fa-star"></i>
                    <span class="rating-star">4.5</span>
                    <span>(14 reviews)</span>
                  </div>
                </div>
          
              </div>
            </div>
          </div>
      </div>
      <div class="main-map">
        <div class="main-googleMap">
          <span>Map</span>
        </div>
      </div>
     </div>
   
  
</template>

<script>
export default {
  props: ['house'],
  methods: {
    goToDetails() {
      this.$router.push('/house/' + this.house.id);
    },
  },
};
</script>

<style scoped>


*{
 margin: 0;
  box-sizing: border-box;
  padding: 0;
}

:root{
  --grey--med:#ddd;
  --grey--light:#f7f7f7;
  --grey--text--light:#717171;
  --grey-text-dark:#222;
  --pink: #ff385c;
}






.main {
  position: relative;
  padding-top: 80px;

}
.main-map{
background:rgb(185, 185, 247);
width: calc(100% - 0px);
position: absolute;


bottom:0 ;
}
.main-googleMap{
  font-size: 300px;
  height: 40vh;
  display: flex;
  position: sticky;
  margin:auto;
  align-items: center;
  justify-content: center;

}
.main-content{
min-height: calc(100vh - 80px);
width: 840px;
padding: 60px 24px 0;

}

.main-filters{
  padding-top: 34px;
}
.main-filters button{
  margin-right: 6px;
}
.main h1{
  margin-top: 18px;
  font-size: 34px;
}
.main-filters button{
  padding: 8px 16px;
  outline: none;
  font-size: 15px;
  background: transparent;
  margin: 0;
}
.outlined{
  border: 1px solid #b0b0bb;
}
.outlined:hover{
  border-color: rgb(79, 79, 79) ;
}
.curved{
  border-radius: 30px;
}
.main-scarcity{
  margin-top: 23px;
  
}
.main-scarcity a{
  color: black;
}
.list{
  padding: 44px 0;
}
.list-item{
  border-top: 1px solid #ebebeb;
  padding: 24px 0;
  display: flex;
}
.list-image{
  width: 300px;
  height: 200px;
  border-radius: 10px;
  overflow: hidden;
}
.list-item img{
  object-fit: cover;
  height: 100%;
  width: 100%;
}
.list-content{
  width: calc(100% - 326px);
  padding-left: 15px;
  display: flex;
  flex-direction: column;
}
.list-text h2 {
  padding-top: 10px;
  font-weight: lighter;
}

.greyTxt{
  font-size: 14px;
  line-height:18px;
  color:#717171 ;
  margin-bottom: 60px;
  
}
.list-title{
  display: flex;
  justify-content: space-between;
}
.favorite-icon button{
  background: none;
  border: none;
  position: relative;
  display: flex;
  align-items: center;
  justify-content: center;
 
}
.favorite-icon button:hover::after{
  opacity: 1;
}

.favorite-icon button:after{
  opacity: 0;
  background:#71717112 ;
  position: absolute;
  content: '';
  width: 48px;
  height: 48px;
  border-radius: 50%;
  z-index: -1;

}
.fa-heart{
  font-size: 23px;

}
.separator{
  width: 32px;
  height: 1px;
  
}
.list-diescription{
  padding-top: 15px;
}
.rating{
  display: flex;
  margin-top: auto;
  
}
 .rating-star{
   font-size: 17px;
  font-weight: bold;
}

.fa-star{
  font-size: 15px;
  color: #ff385c;
}


.list-detailis{
  
    -webkit-box-flex: 1 t;
    -ms-flex-positive: 1 ;
    -webkit-box-align: end ;
    -ms-flex-align: end ;
    display: -webkit-box ;
    display: -moz-box ;
    display: -ms-flexbox ;
    display: -webkit-flex ;
    display: flex ;
    -webkit-align-items: flex-end ;
    align-items: flex-end ;
    -webkit-flex-grow: 1 ;
    flex-grow: 1 ;
    margin-top: -10px ;
}
.list-content{
   
    display: -webkit-box ;
    display: -moz-box ;
    display: -ms-flexbox ;
    display: -webkit-flex ;
    display: flex ;
    -webkit-flex-direction: column ;
    -ms-flex-direction: column ;
    flex-direction: column ;
    -webkit-flex-grow: 1 ;
    flex-grow: 1 ;
    height: 200px ;
    margin-left: 16px ;
}
</style>
