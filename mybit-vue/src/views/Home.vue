<template>
    <div id="main">
      <Chart/>
      <CoinList/>
    </div>
</template>

<style scoped>
#main {
  display: flex;
  align-items:flex-start;
  height: 70%;
}
</style>

<script>
/*import dayjs from 'dayjs'*/
import CoinList from '@/components/layout/CoinList.vue';
import Chart from "@/components/layout/Chart.vue";

var data =  {

}

export default {   
    components: {
      Chart,
      CoinList,
    },
    data: function(){return data},
    created: function() {
      this.$parent.updateLoginInfo();
    },
    mounted : function() {

    },
    methods : {

    }
};

</script>