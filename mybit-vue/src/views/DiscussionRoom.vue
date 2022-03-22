<template>
  <div id="main">
    <div id="left">
      <div id="coin_name">
        <div id="coin_divs">{{selectedCoinData.korean_name}}</div>
      </div>
      <div id="divs">
      </div>
    </div>
    <CoinList/>
  </div>
</template>

<style scoped>
#main {
  display: flex;
  align-items:flex-start;
  height: 70%;
}

#left {
  width: 60%;
  height: 750px;
  margin-top: 0.5%;
  margin-left: 10%;
  background-color: #1A2436;
  color: #FFFFFF;
}

#divs {
  height : 20px;
  background-color: #FFFFFF;
}

</style>

<script>
import CoinList from "@/components/layout/CoinList.vue";
import { eventBus } from "@/main.js";

var data =  {
  selectedCoinData: {
    marketCode: 'KRW-BTC',
    korean_name: '비트코인',
    currentPrice: 0,
    signed_change_rate: '0',
    signed_change_price: '0',
    high_price: 0,
    low_price: 0,
    acc_trade_price_24h: 0,
    acc_trade_volume_24h: 0,
  },
}

export default {
  components: {CoinList},
  data: function(){return data},
  created: function() {
    eventBus.$on("clickCoinList", (clickedCoinData) => {
      this.selectedCoinData = clickedCoinData;
    });
  },
  beforeDestroy : function(){
    eventBus.$off("clickCoinList");
  },
};
</script>