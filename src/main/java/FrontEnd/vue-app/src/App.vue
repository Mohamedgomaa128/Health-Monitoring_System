<template >
<div class="ahmed">
<h1>System Analytics</h1>
<br>
<br>
 <div class="grid-container">

    <div> Start Date <br>
          <Datepicker v-model="date1" :format="format1"  />
    </div>
    <div> End Date 
          <Datepicker v-model="date2" :format="format2" />
    </div>
    <br>
</div>
<br><br>
<div class="getcontainer">
<button  @click=getAnalytics() class="get">
  Get Analytics
</button>
</div>
<br><br><br><br><br>
  <h3 style="font-family:verdana;" >- The mean CPU utilization for each service: {{ cpu }}</h3>
  <br><h3 style="font-family:verdana;" >- The mean Disk utilization for each service: {{ disk }}</h3>
  <br><h3 style="font-family:verdana;" >- The mean RAM utilization for each service: {{ ram }}</h3>
  <br><h3 style="font-family:verdana;" >- The count of health messages received for each service: {{ count }}</h3>
  <br><h3 style="font-family:verdana;" >- The peak time of utilization for each resource for each service: {{ peaktime }}</h3>
</div>
</template>

<script>
  import Datepicker from '@vuepic/vue-datepicker';
  import '@vuepic/vue-datepicker/dist/main.css';
  import { ref } from 'vue';
  import axios from 'axios'

  export default {
    
      name: 'App',
      components: { Datepicker },
      data() {
        return {
          result1:"",
          result2:"",
          cpu:"",
          disk:"",
          ram:"",
          peaktime:"",
          count:""
        }
      },
      setup() {
          const date1 = ref(new Date());
          const format1 = (date1) => {
              const day = date1.getDate();
              const month = date1.getMonth() + 1;
              const year = date1.getFullYear();
              return `${day}/${month}/${year}`;
          }
          const date2 = ref(new Date());
          const format2 = (date2) => {
              const day = date2.getDate();
              const month = date2.getMonth() + 1;
              const year = date2.getFullYear();
              return `${day}/${month}/${year}`;
          }
          return {
              date1,
              date2,
              format1,
              format2,
          } 
        },
        methods: {
          getAnalytics() {
            const dayString1 = this.date1.getDate().toString();
            const monthString1 = this.date1.getMonth() + 1;
            const yearString1 = this.date1.getFullYear().toString();
            const space = "/";
            //let result1;
            this.result1 = dayString1.concat(space);
            this.result1 = this.result1.concat(monthString1)
            this.result1 = this.result1.concat(space)
            this.result1 = this.result1.concat(yearString1)
            const dayString2 = this.date2.getDate().toString();
            const monthString2 = this.date2.getMonth() + 1;
            const yearString2 = this.date2.getFullYear().toString();
            //let result2;
            this.result2 = dayString2.concat(space);
            this.result2 = this.result2.concat(monthString2)
            this.result2 = this.result2.concat(space)
            this.result2 = this.result2.concat(yearString2)
            console.log(this.result1);
            console.log(this.result2);

            axios.get('http://localhost:8085/getAnalytics',{
                params: {
                    result1:this.result1,
                    result2:this.result2
                }
            }).then(Response=>{
                const Data = Response.data;
                  this.cpu = Data ;
                  this.disk = Data ;
                  this.ram = Data ;
                  this.peaktime = Data ;
                  this.count = Data ;

            });

          }
        }

  }

</script>

<style>
.grid-container {
  display: grid;
  grid-template-columns: auto auto ;
  gap: 10px;
  background-color: #00176b;
  padding: 10px;
  color: aliceblue;
    text-align-last: center;

}
.get {
  background-color: #00176b; /* Green */
  border: none;
  color: white;
  padding: 20px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
  border-radius: 12px;

}
.get:hover {
  box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);
}
h3 {
   background-color: #00176b; /* Green */
  color: #ffffff;
  font-size:100%;
}
h1 {
  text-align: center;
  text-transform: uppercase;
  color: #00176b;
}

.getcontainer {
   text-align: center;
}
.ahmed {
  margin-left: 25%;
  margin-right: 25%;

}
</style>
