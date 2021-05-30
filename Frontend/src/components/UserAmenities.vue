<template>
  <section
    class="section-amenities"
    :class="{ invalid: invalid === 'invalid' }"
  >
    <h1>
      Amenities
      <span v-if="failedValidity" style="font-size: 2rem; color: red">
        You must choose atleast 1!</span
      >
    </h1>
    <div class="amenities-container">
      <article
        v-for="(amenity, index) of amenities"
        :key="index"
        @click="addAmenity(amenity, index)"
        :ref="amenity"
      >
        <img :src="getPath(index)" :alt="amenity" />
        <p>{{ amenity }}</p>
      </article>
    </div>
  </section>
</template>

<script>
export default {
  props: ['invalid'],
  emit: ['amenities'],
  data() {
    return {
      amenities: [
        'Bed',
        'Breakfast',
        'Camera',
        'CCTV',
        'Dinner',
        'Elevator',
        'Fridge',
        'Heating',
        'Iron',
        'Mirror',
        'Parking',
        'Room Service',
        'Sink',
        'Tea',
        'Television',
        'Toothbrush',
        'Towel',
        'Wifi',
      ],
      chosenAmenities: [],
      failedValidity: false,
    };
  },
  created() {
    if (this.invalid === 'invalid') {
      this.failedValidity = true;
    }
  },
  methods: {
    getPath(index) {
      return (
        '../../public/icons/' +
        this.amenities[index].replace(/\s+/g, '') +
        '.png'
      );
    },
    addAmenity(amenity) {
      for (let ref in this.$refs) {
        if (ref === amenity) {
          if (
            this.$refs[ref].style.background === '' ||
            this.$refs[ref].style.border === '3px solid transparent' ||
            this.$refs[ref].style.background === 'white'
          ) {
            this.$refs[ref].style.background = 'orange';
            this.chosenAmenities.push(amenity);
          } else {
            this.$refs[ref].style.background = 'white';
            this.chosenAmenities = this.chosenAmenities.filter(
              (item) => item !== amenity
            );
          }
        }
      }
      this.$emit('amenities', this.chosenAmenities);
    },
  },
};
</script>

<style scoped>
.invalid {
  border: 4px solid red;
}

.section-amenities {
  display: flex;
  flex-direction: column;
  border-radius: 8px 8px 8px 2px;
}

h1 {
  font-size: 3rem;
  color: black;
}

.amenities-container {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: center;
}
.amenities img {
  height: 25px;
  width: 25px;
}
.amenities p {
  display: none;
}

article {
  cursor: pointer;
  margin: 1rem;
  padding: 1rem;
  border: 3px solid transparent;
  border-radius: 10px;
}

article:hover,
article:active {
  border: 3px solid orange;
}

p {
  color: black;
}

img {
  height: 50px;
  width: 50px;
}
</style>
