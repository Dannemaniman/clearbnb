<template>
  <section class="section-host">
    <header>
      <img class="avatar" :src="owner.image" />
      <div class="user-container">
        <h1>
          Hosted by <span class="span-name">{{ owner.fullName }}</span>
        </h1>
      </div>
    </header>
    <article>
      <h2>About {{ owner.fullName }}</h2>
      <p class="info-text">
        {{ owner.description }}
        <br /><br />
        {{ owner.email }}
      </p>
    </article>
    <footer>
      <div class="security-container">
        <img class="secure-icon" src="../../public/icons/keycard.png" />
        <p>
          To protect your payment, never transfer money or communicate outside
          of the Clearbnb website.
        </p>
      </div>
    </footer>
  </section>
</template>

<script>
export default {
  data() {
    return {
      owner: '',
    };
  },
  props: ['home'],
  methods: {
    getOwner() {
      this.$store.state.users.forEach((user) => {
        if (user.id === this.home.ownerId) {
          this.owner = user;
        }
      });
    },
  },
  mounted() {
    this.getOwner();
  },
};
</script>

<style scoped>
.section-host {
  margin-top: 6rem;
}

header {
  display: flex;
  flex-direction: row;
  align-items: center;
}
.user-container h1 {
  display: flex;
  flex-direction: column;
}

.avatar {
  height: 130px;
  width: 120px;
  background-color: whitesmoke;
  border-radius: 0 0 10px 0;
  border-left: 5px solid black;
  box-shadow: inset 0 -3em 3em rgba(0, 0, 0, 0.1), 0 0 0 2px rgb(255, 255, 255),
    0.3em 0.3em 1em rgba(0, 0, 0, 0.3);
}

h1 {
  font-weight: 400;
  line-height: 125%;
  color: black;
  margin-left: 1rem;
}

.span-name {
  font-size: 2rem;
  font-weight: 300;
  margin-left: 0.2rem;
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
  border-radius: 10px 0 0 10px;
  background: white;
  padding: 2rem;
  border-right: 5px solid rgba(0, 0, 0, 0.281);
  box-shadow: inset 0 -3em 3em rgb(0 0 0 / 3%), 0 0 0 2px rgb(255 255 255),
    0.3em 0.3em 1em rgb(0 0 0 / 30%);
  position: relative;
  right: -1.4rem;
  width: 90%;
}

h2 {
  margin: 0;
  color: black;
  font-size: 1.4rem;
  font-weight: 600;
  text-align: left;
  margin-bottom: 2rem;
  line-height: 125%;
}

.secure-icon {
  height: 30px;
  width: 30px;
}

.info-text {
  color: black;
  text-align: left;
  margin-left: 2rem;
  line-height: 125%;
}

footer {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-top: 2rem;
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

@media screen and (max-width: 430px) {
  .section-host {
    overflow: hidden;
  }
}
</style>
