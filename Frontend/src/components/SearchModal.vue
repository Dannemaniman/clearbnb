<template>
  <section class="modal-container">
    <button class="button" @click="popModal">Refine search</button>
    <transition name="fade" appear>
      <div class="modal-overlay" v-if="showModal" @click="closeModal"></div>
    </transition>
    <transition name="slide" mode="out-in" appear>
      <div class="modal" v-if="showModal">
        <h1>Lorem Ipsum</h1>
        <p>
          Lorem ipsum dolor sit amet consectetur adipisicing elit. Voluptatem
          provident explicabo accusamus laudantium voluptatum nobis sed nesciunt
          neque possimus molestiae?
        </p>
        <button class="button" @click="closeModal">Search</button>
      </div>
    </transition>
  </section>
</template>

<script>
export default {
  data() {
    return {
      showModal: false,
    };
  },
  methods: {
    popModal() {
      this.showModal = true;
    },
    closeModal(e) {
      this.showModal = false;
      e.stopPropagation();
    },
  },
};
</script>

<style scoped>
.modal-container {
  margin-top: 2rem;
}

.button {
  appearance: none;
  outline: none;
  border: none;
  background: none;
  cursor: pointer;

  display: inline-block;
  padding: 15px 25px;
  background-image: linear-gradient(to right, #6b6b6b, #808080);
  border-radius: 8px;

  color: #fff;
  font-size: 18px;
  font-weight: 700;

  box-shadow: 3px 3px rgba(0, 0, 0, 0.4);
  transition: 0.4s ease-out;
}
.button:hover {
  box-shadow: 6px 6px rgba(0, 0, 0, 0.6);
}

.modal-overlay {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: 98;
  background-color: rgba(0, 0, 0, 0.5);
}

.modal {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -100%);
  z-index: 99;

  width: 100%;
  max-width: 700px;
  background-color: #fff;
  border-radius: 16px;

  padding: 25px;
}

.modal h1 {
  color: #222;
  font-size: 32px;
  font-weight: 900;
  margin-bottom: 15px;
}
.modal p {
  color: #666;
  font-size: 18px;
  font-weight: 400;
  margin-bottom: 15px;
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s;
}

.fade-enter,
.fade-leave-to {
  opacity: 0.5;
}

.slide-enter-active,
.slide-leave-active {
  transition: transform 1s;
}
.slide-enter-to {
  transform: translateY(-100vh) translateX(-50%);
}

.slide-leave-to {
  transform: translateY(-100vh) translateX(-50%);
}
</style>
