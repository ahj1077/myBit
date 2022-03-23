<template>
  <div id="left">
    <div id="coin_name" style="height: 10%; font-size: 20px">
      <div id="coin_divs">{{selectedCoinData.korean_name}} 종토방</div>
    </div>
    <div class="line"/>
    <div style="width: 100%;">
      <div id="header" style="text-align: center; padding-top: 8px; height:40px; border-bottom: 1px solid #FFFFFF;">
        <ul>
          <li style="width: 65%">제목</li>
          <li style="width: 12%">작성자</li>
          <li style="width: 11%">추천수</li>
          <li style="width: 12%">작성일</li>
        </ul>
      </div>
      <table id="tbl_posts">
        <tr class="post" style="height : 30px; text-align: left; border-bottom: 1px solid #2B3547;">
          <td class="post_title" style="width: 70%; padding-left: 3%;">테스트제목1</td>
          <td style="width: 10%;">안형준</td>
          <td style="width: 10%; text-align: center;">1</td>
          <td style="width: 10%; font-size: 10px">2022-03-23 20:51</td>
        </tr>
        <tr class="post" style="height : 30px; text-align: left; border-bottom: 1px solid #2B3547;">
          <td class="post_title" style="width: 70%; padding-left: 3%;">테스트제목1</td>
          <td style="width: 10%;">안형준</td>
          <td style="width: 10%; text-align: center;">1</td>
          <td style="width: 10%; font-size: 10px">2022-03-23 20:51</td>
        </tr>
      </table>
      <div style="padding-top: 8px; padding-right: 10px">
          <span style="float: right">
            <a href="/newPost" id="btn_write">글쓰기</a>
          </span>
      </div>
      <div style="width: 100%; text-align: center">
        이전 1 2 3 4 5 6 7 8 9 다음
      </div>
    </div>
  </div>
</template>

<style>

#left {
  width: 60%;
  height: 650px;
  margin-top: 0.5%;
  margin-left: 10%;
  background-color: #1A2436;
  color: #FFFFFF;
}

.line {
  height : 1px;
  background-color: #FFFFFF;
}
#tbl_posts {
  width : 100%;
  height: 100%;
  background-color: #000000;
}

#header{
  display: flex;
}
#header ul{
  display: flex;
  width: 100%;
  list-style: none;
}
#header ul li{
  display: inline;
}

.post_title:hover{
  text-decoration: underline;
}
#btn_write{
  background-color: #0C66C6;
  font-size: 15px;
  color: #FFFFFF;
  text-decoration-line: none;
  border: 1px solid #0C66C6;
}


</style>


<script>
//import axios from "axios";
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
  name: "board",
  components: {
  },
  data: function(){return data},
  created: function() {
    eventBus.$on("clickCoinList", (clickedCoinData) => {
      this.selectedCoinData = clickedCoinData;
    });
  },
  beforeDestroy : function(){
    eventBus.$off("clickCoinList");
  },
  mounted : function() {

  },
  methods : {

  }
};

</script>


