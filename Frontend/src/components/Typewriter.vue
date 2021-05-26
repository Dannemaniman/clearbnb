<template>
  <section>
    <div>
      <p>
        We will
        <span class="text">{{ typeValue }}</span>
        <span class="text-cursor"></span>
        you
      </p>
    </div>
  </section>
</template>

<script>
export default {
  data() {
    return {
      typeValue: '',
      typeStatus: false,
      typeArray: ['help', 'show', 'teach', 'advise'],
      typingSpeed: 100,
      erasingSpeed: 100,
      newTextDelar: 2000,
      typeArrayIndx: 0,
      charIndex: 0,
    };
  },
  methods: {
    typeText() {
      if (this.charIndex < this.typeArray[this.typeArrayIndx].length) {
        if (!this.typeStatus) {
          this.typeStatus = true;
        }

        this.typeValue += this.typeArray[this.typeArrayIndx].charAt(
          this.charIndex
        );
        this.charIndex += 1;

        setTimeout(this.typeText, this.typingSpeed);
      } else {
        this.typeStatus = false;
        setTimeout(this.eraseText, this.newTextDelar);
      }
    },
    eraseText() {
      if (this.charIndex > 0) {
        if (!this.typeStatus) {
          this.typeStatus = true;
        }
        this.typeValue = this.typeArray[this.typeArrayIndx].substring(
          0,
          this.charIndex - 1
        );
        this.charIndex -= 1;
        setTimeout(this.eraseText, this.erasingSpeed);
      } else {
        this.typeStatus = false;
        this.typeArrayIndx += 1;
        if (this.typeArrayIndx >= this.typeArray.length) {
          this.typeArrayIndx = 0;
        }
        setTimeout(this.typeText, this.typingSpeed);
      }
    },
  },
  created() {
    setTimeout(this.typeText, this.newTextDelar);
  },
};
</script>

<style scoped>
.text-cursor {
  display: inline-block;
  margin-left: 3px;
  width: 4px;
  background-color: #fff;
}

div {
}

div p {
  font-size: 3rem;
}
</style>
