<template>
  <section class='section-host'>
    <header>
      <img class='avatar' src='../../public/pngegg.png'/>
      <div class='user-container'>
        <h1>Hosted by <span class='span-name'>Greger</span> </h1>
        <p class='reviews-number'>★ 23 Reviews</p>
      </div>
    </header>
    <article>
      <h2>About Greger</h2>
      <p class='info-text'>
        Tillgänglig på telefon under hela vistelsen, personligt mottagande och incheckning.
        <br/><br/> 
        Bjuder på kaffe och sex på morgonen, därefter vandrar vi tillsammans genom skogen i Munkebäck.
        <br/><br/>
        Jag spelar Heavy Metal på full volym från klockan 11 till 3 på natten. 
      </p>
    </article>
    <footer>
      <button class='button-contact'>Contact</button>
      <div class='security-container'>
        <img class='secure-icon' src='../../public/icons/keycard.png'/>
        <p>To protect your payment, never transfer money or communicate outside of the Clearbnb website.</p>
      </div>
    </footer>
  </section>
</template>

<script>

export default {
  props: ['host']
}
</script>

<style scoped>

.section-host {
  background-color:  #a9a9a9;
    /* padding-left: 1rem; */
  overflow: hidden
}

header {
  display: flex;
  flex-direction: row;
  align-items: center;
}

.avatar {
  height: 130px;
  width: 120px;
  background-color: whitesmoke;
  border-radius: 0 0 10px 0;
   /* border-bottom: 2px solid black; */
  border-left:  5px solid black; 
    box-shadow:
       inset 0 -3em 3em rgba(0,0,0,0.1),
             0 0  0 2px rgb(255,255,255),
             0.3em 0.3em 1em rgba(0,0,0,0.3);
}

h1 {
  font-weight: 400;
  color: black;
  margin-left: 1rem;
}

.span-name {
  font-size: 1.8rem;
  font-weight: 300;
  /* margin-left: 1rem; */
}

.reviews-number {
  font-size: 1.2rem;
  text-align: left;
  color: black;
  font-weight: 300;
  margin-top: 0;
  position: absolute;
  margin-left: 3rem;
}

article {
 margin-top: 2rem;
 background-color: whitesmoke;
 border-radius: 10px 0 0 10px;
 padding: 2rem;
 margin-left: 1rem;
 border-right: 5px solid black;
 box-shadow:
       inset 0 -3em 3em rgba(0,0,0,0.1),
             0 0  0 2px rgb(255,255,255),
             0.3em 0.3em 1em rgba(0,0,0,0.3);

}

h2 {
  margin: 0;
  color: black;
  font-size: 1.4rem;
  font-weight: 600;
  text-align: left;
  margin-bottom: 2rem;
}

.secure-icon {
  height: 30px;
  width: 30px;
}


.info-text {
  color: black;
  text-align: left;
  margin-left: 2rem;
}

footer {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-top: 2rem;
}

.button-contact {
  height: 3rem;
  width: 8rem;
  border-radius: 8px 8px 8px 8px;
  outline: none;
  text-align: center;
  font-size: 1.2rem;
}

.security-container {
  margin-top: 2rem;
  display: flex;
  justify-content: center;
  align-items: center;
}

.security-container p {
  color: black;
  font-size: 0.7rem;
  margin-left: 1rem;
}

</style>