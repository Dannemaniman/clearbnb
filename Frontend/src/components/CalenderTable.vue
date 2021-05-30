<template>
  <table :class="tableClass" @click="markSelection" ref="masterRow">
    <p class="table-header">{{ month }} 2021</p>
    <tr ref="rowFirst">
      <td>1</td>
      <td>2</td>
      <td>3</td>
      <td>4</td>
      <td>5</td>
      <td>6</td>
      <td>7</td>
    </tr>
    <tr ref="rowSecond">
      <td>8</td>
      <td>9</td>
      <td>10</td>
      <td>11</td>
      <td>12</td>
      <td>13</td>
      <td>14</td>
    </tr>
    <tr ref="rowThird">
      <td>15</td>
      <td>16</td>
      <td>17</td>
      <td>18</td>
      <td>19</td>
      <td>20</td>
      <td>21</td>
    </tr>
    <tr ref="rowFourth">
      <td>22</td>
      <td>23</td>
      <td>24</td>
      <td>25</td>
      <td>26</td>
      <td>27</td>
      <td>28</td>
    </tr>
    <tr v-if="days > 28" ref="rowFifth">
      <td>29</td>
      <td>30</td>
      <td v-if="days > 30">31</td>
    </tr>
  </table>
</template>

<script>
export default {
  props: ['days', 'month', 'firstDate', 'secondDate', 'current', 'bookedDates'],
  emit: ['setDate'],
  created() {
    //  this.renderInactiveFromProps()
    //  console.log(this.bookedDates, this.month)
  },
  mounted() {
    // this.checkBookedDates()
    this.checkBookedDates();
  },
  updated() {
    // this.renderInactiveFromProps()
    this.currentMarker = this.current;
  },
  computed: {
    disabled() {
      return days - firstOne;
    },
    currentMarker() {
      return this.current;
    },
  },
  data() {
    return {
      tableClass: '',
      firstOne: null,
      secondOne: null,
      firstElement: null,
      secondElement: null,
      currentElement: null,
      monthNames: [
        'January',
        'February',
        'March',
        'April',
        'May',
        'June',
        'July',
        'August',
        'September',
        'October',
        'November',
        'December',
      ],
    };
  },
  methods: {
    markSelection(event) {
      console.log('AAA');
      let yearMonth =
        event.target.attributes[0].ownerElement.parentElement.parentElement.firstChild.innerHTML.split(
          ' '
        );
      let month = this.monthNames.indexOf(yearMonth[0].trim());
      let year = yearMonth[1].trim();
      let day = event.target.textContent.trim();
      let date = new Date(year, month, day).toLocaleDateString();

      if (this.currentMarker === 0 && event.target.children.length === 0) {
        this.currentMarker = 1;
        this.firstElement
          ? (this.firstElement.style.backgroundColor = '')
          : (this.firstElement = null);
        this.firstOne = date;
        this.firstElement = event.target;
        this.currentElement = event.target;

        if (Number(event.target.textContent.trim()) <= this.currentElement)
          return;

        this.$emit('setDate', date, this.firstElement);
      } else if (
        this.currentMarker === 1 &&
        event.target.children.length === 0
      ) {
        this.secondElement
          ? (this.secondElement.style.backgroundColor = '')
          : (this.secondElement = null);
        this.currentMarker = 0;
        this.firstOne = date;
        this.secondElement = event.target;
        this.$emit('setDate', date, this.secondElement);
      }
      this.renderInactiveSelections();
    },
    renderInactiveFromProps() {
      if (this.firstDate !== 'Check-in') {
        let stuff = this.firstDate.split('-');
        let monthz = Number(stuff[1]);

        if (String(monthz).charAt(0) === 0) {
          monthz = String(monthz.substring(1));
        }

        let realFirstMonth = Number(monthz) - 1;

        if (this.monthNames.indexOf(this.month) < realFirstMonth) {
          if (this.currentMarker === 0) {
            for (let i = 1; i < this.$refs.masterRow.childNodes.length; i++) {
              for (
                let x = 0;
                x < this.$refs.masterRow.childNodes[i].childNodes.length;
                x++
              ) {
                if (
                  this.days === 30 &&
                  this.$refs.masterRow.childNodes[i].childNodes[x].childNodes[0]
                    .data === '30'
                ) {
                  this.$refs.masterRow.childNodes[i].childNodes[
                    x
                  ].style.opacity = '0.2';
                  this.$refs.masterRow.childNodes[i].childNodes[
                    x
                  ].style.cursor = 'default';
                  this.$refs.masterRow.childNodes[i].childNodes[
                    x
                  ].style.pointerEvents = 'none';
                  return;
                }
                this.$refs.masterRow.childNodes[i].childNodes[x].style.opacity =
                  '0.2';
                this.$refs.masterRow.childNodes[i].childNodes[x].style.cursor =
                  'default';
                this.$refs.masterRow.childNodes[i].childNodes[
                  x
                ].style.pointerEvents = 'none';
              }
            }
          }
        }
      }
      if (this.secondDate !== 'Check-out') {
        let stuff = this.secondDate.split('-');
        let monthz = Number(stuff[1]);

        if (String(monthz).charAt(0) === 0) {
          monthz = String(monthz.substring(1));
        }

        let realFirstMonth = Number(monthz) - 1;
        if (this.monthNames.indexOf(this.month) > realFirstMonth) {
          if (this.currentMarker === 1) {
            for (let i = 1; i < this.$refs.masterRow.childNodes.length; i++) {
              for (
                let x = 0;
                x < this.$refs.masterRow.childNodes[i].childNodes.length;
                x++
              ) {
                if (
                  this.days === 30 &&
                  this.$refs.masterRow.childNodes[i].childNodes[x].childNodes[0]
                    .data === '30'
                ) {
                  this.$refs.masterRow.childNodes[i].childNodes[
                    x
                  ].style.opacity = '0.2';
                  this.$refs.masterRow.childNodes[i].childNodes[
                    x
                  ].style.cursor = 'default';
                  this.$refs.masterRow.childNodes[i].childNodes[
                    x
                  ].style.pointerEvents = 'none';
                  return;
                }
                this.$refs.masterRow.childNodes[i].childNodes[x].style.opacity =
                  '0.2';
                this.$refs.masterRow.childNodes[i].childNodes[x].style.cursor =
                  'default';
                this.$refs.masterRow.childNodes[i].childNodes[
                  x
                ].style.pointerEvents = 'none';
              }
            }
          }
        }
      }
    },
    renderInactiveSelections() {
      if (this.currentMarker === 1) {
        for (let i = 1; i < this.$refs.masterRow.childNodes.length; i++) {
          for (
            let x = 0;
            x < this.$refs.masterRow.childNodes[i].childNodes.length;
            x++
          ) {
            if (
              this.days === 30 &&
              this.$refs.masterRow.childNodes[i].childNodes[x].childNodes[0]
                .data === '30'
            ) {
              this.$refs.masterRow.childNodes[i].childNodes[x].style.opacity =
                '0.2';
              this.$refs.masterRow.childNodes[i].childNodes[x].style.cursor =
                'default';
              this.$refs.masterRow.childNodes[i].childNodes[
                x
              ].style.pointerEvents = 'none';
              return;
            }
            if (this.firstElement !== null) {
              if (
                Number(
                  this.$refs.masterRow.childNodes[i].childNodes[x].childNodes[0]
                    .data
                ) < Number(this.firstElement.childNodes[0].data)
              ) {
                this.$refs.masterRow.childNodes[i].childNodes[x].style.opacity =
                  '0.2';
                this.$refs.masterRow.childNodes[i].childNodes[x].style.cursor =
                  'default';
                this.$refs.masterRow.childNodes[i].childNodes[
                  x
                ].style.pointerEvents = 'none';
              }
            }
            // else if(
            //   Number(this.$refs.masterRow.childNodes[i].childNodes[x].childNodes[0].data) >
            //   Number(this.firstElement.childNodes[0].data)){
            //     this.$refs.masterRow.childNodes[i].childNodes[x].style.backgroundColor = "salmon"
            //     this.$refs.masterRow.childNodes[i].childNodes[x].style.opacity = "0.7"
            //     this.$refs.masterRow.childNodes[i].childNodes[x].style.cursor = "default"
            //     this.$refs.masterRow.childNodes[i].childNodes[x].style.pointerEvents = "none"
            // }
          }
        }
      } else if (this.currentMarker === 0) {
        for (let i = 1; i < this.$refs.masterRow.childNodes.length; i++) {
          for (
            let x = 0;
            x < this.$refs.masterRow.childNodes[i].childNodes.length;
            x++
          ) {
            if (
              this.days === 30 &&
              this.$refs.masterRow.childNodes[i].childNodes[x].childNodes[0]
                .data === '30'
            ) {
              this.$refs.masterRow.childNodes[i].childNodes[x].style.opacity =
                '0.2';
              this.$refs.masterRow.childNodes[i].childNodes[x].style.cursor =
                'default';
              this.$refs.masterRow.childNodes[i].childNodes[
                x
              ].style.pointerEvents = 'none';
              return;
            }
            if (this.secondElement !== null) {
              if (
                Number(
                  this.$refs.masterRow.childNodes[i].childNodes[x].childNodes[0]
                    .data
                ) > Number(this.secondElement.childNodes[0].data)
              ) {
                this.$refs.masterRow.childNodes[i].childNodes[x].style.opacity =
                  '0.2';
                this.$refs.masterRow.childNodes[i].childNodes[x].style.cursor =
                  'default';
                this.$refs.masterRow.childNodes[i].childNodes[
                  x
                ].style.pointerEvents = 'none';
              }
            }
            // else if(
            //   Number(this.$refs.masterRow.childNodes[i].childNodes[x].childNodes[0].data) <
            //   Number(this.secondElement.childNodes[0].data)){
            //     this.$refs.masterRow.childNodes[i].childNodes[x].style.backgroundColor = "salmon"
            //     this.$refs.masterRow.childNodes[i].childNodes[x].style.opacity = "0.7"
            //     this.$refs.masterRow.childNodes[i].childNodes[x].style.cursor = "default"
            //     this.$refs.masterRow.childNodes[i].childNodes[x].style.pointerEvents = "none"
            // }
          }
        }
      }
    },
    checkBookedDates() {
      this.bookedDates.forEach((dateSet) => {
        console.log(
          'Start: ' +
            new Date(dateSet[0]).toLocaleDateString() +
            '|| End: ' +
            new Date(dateSet[1]).toLocaleDateString()
        );
        // console.log("bookedDateMonth: " + this.monthNames[bookedDateMonth] + " vs " + "tableMonth: " + this.month + " day: " + day)
        let startDate = new Date(dateSet[0]).toLocaleDateString();
        let endDate = new Date(dateSet[1]).toLocaleDateString();
        let start = new Date(dateSet[0]).getMonth();
        let end = new Date(dateSet[1]).getMonth();
        let startDay = new Date(dateSet[0]).getDay();
        let endDay = new Date(dateSet[1]).getDay();
        let type = '';

        // console.log(start < end)
        // console.log(`start: ${start} end: ${end}`)

        if (start < end || start > end) {
          // console.log(start + " < " + end)
          type = 'lesser';
        }
        if (start === end) {
          // console.log(start + " === " + end)
          type = 'same';
        }
        console.log(start);
        console.log(this.monthNames.indexOf(this.month));
        console.log(end);

        //FIRST DATE
        let dateString = new Date(dateSet[0]).toLocaleDateString();
        let firstIndex = dateString.indexOf('-');
        let bookedDateMonth = Number(dateString.substr(firstIndex + 1, 2)) - 1;
        let lastIndex = dateString.lastIndexOf('-');
        let firstDay = dateString.substr(lastIndex + 1, 2);

        //SECOND DATE
        let secondDateString = new Date(dateSet[1]).toLocaleDateString();
        let secondFirstIndex = secondDateString.indexOf('-');
        let secondbookedDateMonth =
          Number(secondDateString.substr(secondFirstIndex + 1, 2)) - 1;
        let secondlastIndex = secondDateString.lastIndexOf('-');
        let secondDay = secondDateString.substr(secondlastIndex + 1, 2);

        if (
          this.monthNames.indexOf(this.month) > start &&
          this.monthNames.indexOf(this.month) < end
        ) {
          type = 'all';
          this.detoggleFromBookings(
            Number(secondDay),
            Number(firstDay),
            'second',
            type,
            endDate
          );
        } else if (
          this.monthNames[secondbookedDateMonth].trim() === this.month.trim()
        ) {
          console.log('SECOND NUMBER TRIGGERED DETOGGLE');
          console.log(
            `starMonth: ${start}, day: ${startDay} || endMonth: ${end}, day: ${endDay} `
          );
          this.detoggleFromBookings(
            Number(secondDay),
            Number(firstDay),
            'second',
            type,
            endDate
          );
        } else if (
          this.monthNames[bookedDateMonth].trim() === this.month.trim()
        ) {
          console.log('FIRST NUMBER TRIGGERED DETOGGLE FOR ' + this.month);
          console.log(
            `starMonth: ${start}, day: ${startDay} || endMonth: ${end}, day: ${endDay} `
          );
          this.detoggleFromBookings(
            Number(secondDay),
            Number(firstDay),
            'first',
            type,
            dateSet
          );
        }
      });
    },
    detoggleFromBookings(
      secondDay,
      firstDay,
      order,
      sameOrLesser,
      dateObjects
    ) {
      console.log(sameOrLesser);

      if (order === 'second') {
        for (let i = 1; i < this.$refs.masterRow.childNodes.length; i++) {
          for (
            let x = 0;
            x < this.$refs.masterRow.childNodes[i].childNodes.length;
            x++
          ) {
            if (
              this.days === 30 &&
              this.$refs.masterRow.childNodes[i].childNodes[x].childNodes[0]
                .data === '30'
            ) {
              this.$refs.masterRow.childNodes[i].childNodes[x].style.opacity =
                '0.2';
              this.$refs.masterRow.childNodes[i].childNodes[x].style.cursor =
                'default';
              this.$refs.masterRow.childNodes[i].childNodes[
                x
              ].style.pointerEvents = 'none';
              return;
            }

            if (sameOrLesser === 'all') {
              this.$refs.masterRow.childNodes[i].childNodes[x].style.opacity =
                '0.2';
              this.$refs.masterRow.childNodes[i].childNodes[x].style.cursor =
                'default';
              this.$refs.masterRow.childNodes[i].childNodes[
                x
              ].style.pointerEvents = 'none';
              console.log(
                'GHEEEEEEEEEEEEEEEEEEEEEEEEEAWDAWEDAWDAWDAWDAWDAWDAWD'
              );
              continue;
            }

            if (
              Number(
                this.$refs.masterRow.childNodes[i].childNodes[x].childNodes[0]
                  .data
              ) >= secondDay
            )
              return;

            if (sameOrLesser === 'lesser') {
              if (
                Number(
                  this.$refs.masterRow.childNodes[i].childNodes[x].childNodes[0]
                    .data
                ) <= secondDay
              ) {
                this.$refs.masterRow.childNodes[i].childNodes[x].style.opacity =
                  '0.2';
                this.$refs.masterRow.childNodes[i].childNodes[x].style.cursor =
                  'default';
                this.$refs.masterRow.childNodes[i].childNodes[
                  x
                ].style.pointerEvents = 'none';
                console.log('delete lesser || second');
              }
            } else if (sameOrLesser === 'same')
              if (
                Number(
                  this.$refs.masterRow.childNodes[i].childNodes[x].childNodes[0]
                    .data
                ) >= firstDay
              ) {
                this.$refs.masterRow.childNodes[i].childNodes[x].style.opacity =
                  '0.2';
                this.$refs.masterRow.childNodes[i].childNodes[x].style.cursor =
                  'default';
                this.$refs.masterRow.childNodes[i].childNodes[
                  x
                ].style.pointerEvents = 'none';
                console.log('delete same || first');
              }
          }
        }
      } else if (order === 'first') {
        for (let i = 1; i < this.$refs.masterRow.childNodes.length; i++) {
          for (
            let x = 0;
            x < this.$refs.masterRow.childNodes[i].childNodes.length;
            x++
          ) {
            if (
              this.days === 30 &&
              this.$refs.masterRow.childNodes[i].childNodes[x].childNodes[0]
                .data === '30'
            ) {
              this.$refs.masterRow.childNodes[i].childNodes[x].style.opacity =
                '0.2';
              this.$refs.masterRow.childNodes[i].childNodes[x].style.cursor =
                'default';
              this.$refs.masterRow.childNodes[i].childNodes[
                x
              ].style.pointerEvents = 'none';
              return;
            }
            console.log(sameOrLesser);
            console.log(
              this.$refs.masterRow.childNodes[i].childNodes[x].childNodes[0]
                .data +
                ' ||| ' +
                firstDay
            );

            // if(Number(this.$refs.masterRow.childNodes[i].childNodes[x].childNodes[0].data) >= secondDay) return

            if (sameOrLesser === 'all') {
              this.$refs.masterRow.childNodes[i].childNodes[x].style.opacity =
                '0.2';
              this.$refs.masterRow.childNodes[i].childNodes[x].style.cursor =
                'default';
              this.$refs.masterRow.childNodes[i].childNodes[
                x
              ].style.pointerEvents = 'none';
              continue;
            }

            if (sameOrLesser === 'lesser') {
              if (
                Number(
                  this.$refs.masterRow.childNodes[i].childNodes[x].childNodes[0]
                    .data
                ) >= firstDay
              ) {
                this.$refs.masterRow.childNodes[i].childNodes[x].style.opacity =
                  '0.2';
                this.$refs.masterRow.childNodes[i].childNodes[x].style.cursor =
                  'default';
                this.$refs.masterRow.childNodes[i].childNodes[
                  x
                ].style.pointerEvents = 'none';
                console.log('delete lesser || first');
              }
            } else if (sameOrLesser === 'same')
              if (
                !firstDay <=
                Number(
                  this.$refs.masterRow.childNodes[i].childNodes[x].childNodes[0]
                    .data
                )
              ) {
                this.$refs.masterRow.childNodes[i].childNodes[x].style.opacity =
                  '0.2';
                this.$refs.masterRow.childNodes[i].childNodes[x].style.cursor =
                  'default';
                this.$refs.masterRow.childNodes[i].childNodes[
                  x
                ].style.pointerEvents = 'none';
                console.log('delete same || first');
              }
          }
        }
      }
      // } else if(order === "first"){
      //   console.log(firstDay)
      //     if(sameOrLesser === "same")   {
      //     // console.log("delete same --- first")
      //         if((Number(this.$refs.masterRow.childNodes[i].childNodes[x].childNodes[0].data)) >= firstDay && (Number(this.$refs.masterRow.childNodes[i].childNodes[x].childNodes[0].data)) <= secondDay ){
      //            this.$refs.masterRow.childNodes[i].childNodes[x].style.opacity = "0.2"
      //            this.$refs.masterRow.childNodes[i].childNodes[x].style.cursor = "default"
      //            this.$refs.masterRow.childNodes[i].childNodes[x].style.pointerEvents = "none"
      //            console.log("delete same --- first SUPER TRIGGERED")
      //            }
      //         }
      //       else if(sameOrLesser === "lesser"){
      //           if(Number(this.$refs.masterRow.childNodes[i].childNodes[x].childNodes[0].data)){
      //            this.$refs.masterRow.childNodes[i].childNodes[x].style.opacity = "0.2"
      //            this.$refs.masterRow.childNodes[i].childNodes[x].style.cursor = "default"
      //            this.$refs.masterRow.childNodes[i].childNodes[x].style.pointerEvents = "none"
      //            console.log("delete lesser")
      //        }
      //       }
      // }
    },
    mounted() {
      setTimeout(() => {
        this.tableClass = 'table-pop';
      }, 100);
    },
  },
};
</script>

<style scoped>
.table-header {
  position: absolute;
  top: -1.5rem;
  font-weight: bold;
  width: 100%;
}

table {
  margin: 5rem 2.95rem 2rem 2.95rem;
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

td:hover,
td:active {
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
