<template>
  <div id="main">
    <Board/>
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
import Board from "@/components/layout/Board.vue";
import CoinList from "@/components/layout/CoinList.vue";

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
  components: {Board, CoinList},
  data: function(){return data},
  created: function() {

  },
  beforeDestroy : function(){

  },
  methods : {
  }
};
</script>