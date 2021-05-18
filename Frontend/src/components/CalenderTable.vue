<template>
  <table :class='tableClass' @click="markSelection" ref="masterRow">
    <p class='table-header'>
      {{month}} 2021
    </p>
    <tr ref="rowFirst">
      <td>1</td><td>2</td><td>3</td><td>4</td><td>5</td><td>6</td><td>7</td>
    </tr>
    <tr ref="rowSecond">
      <td>8</td><td>9</td><td>10</td><td>11</td><td>12</td><td>13</td><td>14</td>
    </tr>
    <tr ref="rowThird">
      <td>15</td><td>16</td><td>17</td><td>18</td><td>19</td><td>20</td><td>21</td>
    </tr>
    <tr ref="rowFourth">
      <td>22</td><td>23</td><td>24</td><td>25</td><td>26</td><td>27</td><td>28</td>
    </tr>
    <tr v-if='days > 28'  ref="rowFifth">
      <td>29</td><td>30</td><td v-if='days > 30'>31</td>
    </tr>
  </table>
</template>

<script>
export default {
  props: ['days', 'month'],
  emit: ['setDate'],
  computed: {
    disabled() {
      return days - firstOne
    }
  },
  data() {
    return {
      tableClass: '',
      currentMarker: 0,
      firstOne: null,
      secondOne: null,
      firstElement: null,
      secondElement: null,
      monthNames: ["January", "February", "March", "April", "May", "June",
  "July", "August", "September", "October", "November", "December"
]
    }
  },
  methods: {
    markSelection(event) {
      let yearMonth = event.target.attributes[0].ownerElement.parentElement.parentElement.firstChild.innerHTML.split(" ")
      let month = yearMonth[0].trim()
      let numberMonth = this.monthNames.indexOf(month)
      let year = yearMonth[1].trim()
      let day = event.target.textContent.trim()

      let date = new Date(year, numberMonth, day).toLocaleDateString()
      // console.log(date)
      // for(let ref in this.$refs) {
      //   console.log(this.$refs[ref])
      // }

      // console.log(this.$refs.rowFirst)
      // console.log(this.$refs.masterRow.children)

      // console.log(this.$refs.masterRow.children)

      // for(let item in this.$refs[rowFirst]) {
      //   console.log("hej")
      //   console.log(this.$refs[rowFirst][item])
      //   // console.log(this.$refs.masterRow.children[item])
      //   // for(let item in items) {
      //   //   console.log(item)
      //   // }
      // }

  // console.log(this.$refs.rowFirst)

    // console.log(typeof this.$refs.rowFirst)

      //  for(let item in this.$refs.rowFirst) {
      //    console.log(this.$refs.rowFirst[item])
      //  }

    console.log(this.$refs.masterRow.childNodes)

    // if()
    let counter = 1
    for(let items of this.$refs.masterRow.childNodes) {
      // console.log(items)

      // for(let item of items) {
      //   console.log(items[item])
      // }
    }

    // console.log(this.$refs.length)

    for(let i = 1; i < this.$refs.masterRow.childNodes.length; i++) {
       console.dir(this.$refs.masterRow.childNodes[i])

      for(let x = 0; x < this.$refs.masterRow.childNodes[i].childNodes.length; x++){
        console.log("hej")
        console.log(this.$refs.masterRow.childNodes[i].childNodes)
      }
    }

      if(this.currentMarker === 0){
          this.currentMarker = 1
          this.firstElement ? this.firstElement.style.backgroundColor = "" : this.firstElement = null
          // this.firstOne = event.target.textContent
          let target = event.target.attributes[0].name
          // event.target.style.backgroundColor = "salmon"
          this.firstOne = date
          this.firstElement = event.target
          this.$emit('setDate', date, this.firstElement)
      } else if (this.currentMarker === 1) {
        this.secondElement ? this.secondElement.style.backgroundColor = "" : this.secondElement = null
          // event.target.style.backgroundColor = "salmon"
          this.currentMarker = 0
          // this.secondOne = event.target.textContent
          this.firstOne = date
          this.secondElement = event.target
          this.$emit('setDate', date, this.secondElement)
      }
    }
  },
  mounted() {
    setTimeout(() => {
       this.tableClass = 'table-pop'
    }, 100)
  }
}
</script>

<style scoped>

  .table-header {
    position: absolute;
    top: -1.5rem;
    font-weight: bold;
    width: 100%;
  }

  table {
    margin: 5rem 1.6rem 2rem 3rem;
    position: relative;
    border-spacing: 10px;
    transition: all 0.3s ease-in-out;
    cursor: pointer;
  }

  .table-pop {
    margin-top: 8rem;
  }

  tr {
    padding: 1rem 0;
    margin: 1rem auto;
  }

  td:hover, td:active {
    background-color: lightblue;
    border-radius: 10px;
  }

  td:focus {
    background-color: blue;
  }
</style>