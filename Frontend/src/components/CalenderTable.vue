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
      currentElement : null,
      monthNames: ["January", "February", "March", "April", "May", "June",
  "July", "August", "September", "October", "November", "December"
]
    }
  },
  methods: {
    markSelection(event) {
      let yearMonth = event.target.attributes[0].ownerElement.parentElement.parentElement.firstChild.innerHTML.split(" ")
      let month = this.monthNames.indexOf(yearMonth[0].trim())
      let year = yearMonth[1].trim()
      let day = event.target.textContent.trim()
      let date = new Date(year, month, day).toLocaleDateString()

      if(this.currentMarker === 0 && event.target.children.length === 0){
          this.currentMarker = 1
          this.firstElement ? this.firstElement.style.backgroundColor = "" : this.firstElement = null
          this.firstOne = date
          this.firstElement = event.target
          this.currentElement = event.target

          if(Number(event.target.textContent.trim()) <= this.currentElement ) return

          this.$emit('setDate', date, this.firstElement)
      } else if (this.currentMarker === 1  && event.target.children.length === 0) {
          this.secondElement ? this.secondElement.style.backgroundColor = "" : this.secondElement = null
          this.currentMarker = 0
          this.firstOne = date
          this.secondElement = event.target
          this.$emit('setDate', date, this.secondElement)
      }
      this.renderInactiveSelections();
    },
    renderInactiveSelections() {
      if(this.currentMarker === 1) {
        for(let i = 1; i < this.$refs.masterRow.childNodes.length; i++) {
          for(let x = 0; x < this.$refs.masterRow.childNodes[i].childNodes.length; x++){
            if(this.days === 30 && this.$refs.masterRow.childNodes[i].childNodes[x].childNodes[0].data === "30"){
                return
            }
            if(
              Number(this.$refs.masterRow.childNodes[i].childNodes[x].childNodes[0].data) < 
              Number(this.firstElement.childNodes[0].data)){
                this.$refs.masterRow.childNodes[i].childNodes[x].style.opacity = "0.2"
                this.$refs.masterRow.childNodes[i].childNodes[x].style.cursor = "default" 
                this.$refs.masterRow.childNodes[i].childNodes[x].style.pointerEvents = "none"
            }
          }
        }
      } else if(this.currentMarker === 0) {
        for(let i = 1; i < this.$refs.masterRow.childNodes.length; i++) {
          for(let x = 0; x < this.$refs.masterRow.childNodes[i].childNodes.length; x++){
            if(this.days === 30 && this.$refs.masterRow.childNodes[i].childNodes[x].childNodes[0].data === "30"){
                return
            }
            if(
              Number(this.$refs.masterRow.childNodes[i].childNodes[x].childNodes[0].data) > 
              Number(this.secondElement.childNodes[0].data)){
                this.$refs.masterRow.childNodes[i].childNodes[x].style.opacity = "0.2"
                this.$refs.masterRow.childNodes[i].childNodes[x].style.cursor = "default" 
                this.$refs.masterRow.childNodes[i].childNodes[x].style.pointerEvents = "none"
            }
          }
        }
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
  }

  td {
    cursor: pointer;
    border-radius: 10px;
  }

  td:focus {
    background-color: blue;
  }
</style>