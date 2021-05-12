<template>
  <table :class='tableClass' @click="markSelection">
    <p class='table-header'>
      {{month}} 2021
    </p>
    <tr>
      <td>1</td><td>2</td><td>3</td><td>4</td><td>5</td><td>6</td><td>7</td>
    </tr>
    <tr>
      <td>8</td><td>9</td><td>10</td><td>11</td><td>12</td><td>13</td><td>14</td>
    </tr>
    <tr>
      <td>15</td><td>16</td><td>17</td><td>18</td><td>19</td><td>20</td><td>21</td>
    </tr>
    <tr>
      <td>22</td><td>23</td><td>24</td><td>25</td><td>26</td><td>27</td><td>28</td>
    </tr>
    <tr v-if='days > 28'>
      <td>29</td><td>30</td><td v-if='days > 30'>31</td>
    </tr>
  </table>
</template>

<script>
export default {
  props: ['days', 'month'],
  data() {
    return {
      tableClass: '',
      currentMarker: 0,
      firstOne: null,
      secondOne: null
    }
  },
  methods: {
    markSelection(event) {
      console.dir(event.target.textContent)
      console.dir(event.target.attributes[0])
      console.dir(event.parent)
      if(this.currentMarker === 0){
          this.currentMarker = 1
          this.firstOne = event.target.textContent
          let target = event.target.attributes[0].name
          // let firstElement = document.querySelector([target])
          // console.log(firstElement)
      } else if (this.currentMarker === 1) {
          this.currentMarker = 0
          this.secondOne = event.target.textContent
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