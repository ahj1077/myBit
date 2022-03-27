<template>
  <div id="right">
    <div id="coin_list">
      <input type="text" id="coin_search" placeholder="코인명 검색" @input="search_onchange()">
      <div><b-icon-search id="btn_search">sdf</b-icon-search></div>
    </div>
    <div id="tab_coinDivs">
      <ul>
        <li class="tab_current">원화</li>
        <li class="tab_current">보유</li>
      </ul>
    </div>
    <div id="coin_list_col_header">
      <ul>
        <li class="center_align" style="width : 35%;">
          <span v-show="!langFlag" v-on:click="langFlag = !langFlag">한글명</span>
          <span v-show="langFlag" v-on:click="langFlag = !langFlag">영문명</span>
        </li>
        <li class="center_align" style="width : 22%;">현재가</li>
        <li class="center_align" style="width : 20%;">전일대비</li>
        <li class="center_align" style="width : 23%;">거래대금</li>
      </ul>
    </div>
    <div id="coin_list_contents" style="background-color: #1A2436;">
      <table id="tbl_coin_list">
        <tr class="coin_list_content" v-for="coin in coins_list_data" v-bind:key="coin.market"
            v-bind:class="{
                          text_red : coin.signed_change_rate.startsWith('+'),
                          text_blue : coin.signed_change_rate.startsWith('-'),
                          text_white : coin.signed_change_rate.startsWith('+') === false && coin.signed_change_rate.startsWith('-') === false,
                          selected_color : coin.market == selectedCoinData.marketCode
                        }"
            v-on:click="coinItem_onclick(coin)">

          <td class="left_align" style="text-align: left; color: white; width:32%;"><span v-show="!langFlag">{{coin.korean_name}}</span><span v-show="langFlag">{{coin.english_name}}</span>
            <div style="font-weight : normal; color: #AAAAAA; font-size : 11px;">{{coin.market.split('-')[1] + '/' + coin.market.split('-')[0]}}</div>
          </td>
          <td class="right_align" style="width:30%;">{{coin.trade_price}}</td>
          <td class="right_align" style="font-weight : normal; width:15%;">{{coin.signed_change_rate}}%
            <div>{{coin.signed_change_price}}</div>
          </td>
          <td class="right_align" v-bind:style="{color:'#CCCCCC', width:'23%'}">{{coin.acc_trade_price_24h}}<span  v-bind:style="grayColor" style="font-size:11px;">백만</span></td>
        </tr>
      </table>
    </div>
  </div>
</template>

<style>
#right {
  margin-top: 0.5%;
  margin-left: 1%;
  width: 20.5%;
  background-color: #1A2436;
}

#coin_list {
  display: flex;
  align-items: center;
  height: 6%;
  padding: 2%;
  border-bottom: 1px solid #333333;
}

#coin_list div {
  display: flex;
  align-items: center;
  height: 100%;
  width: 15%;
}

#coin_search {
  border: none;
  color: #AAAAAA;
  background-color: #1A2436;
  height: 100%;
  width: 85%;
}

#btn_search {
  border: none;
  color: #aaaaaa;
  background-color: #1A2436;
  margin-left: 40%;
  height: 50%;
  width: 50%;
}

#tab_coinDivs {
  display: flex;
  height: 4%;
  border-bottom: 1px solid #333333;
}

#tab_coinDivs ul {
  display: flex;
  align-items: center;
  list-style: none;
  padding: 0px;
  width: 100%;
  height: 100%;
}

#tab_coinDivs ul li{
  display: inline;
  padding-top: 2%;
  padding-bottom: 1%;
  text-align: center;
  color: #FFFFFF;
  height: 100%;
  width: 100%;
  font-weight: bold;
}

#tab_coinDivs ul li:hover {
  text-decoration: underline;
}

#coin_list_contents {
  height: 1000px;
  overflow: auto;

}

#coin_list_contents table {
  width: 100%;
}

#coin_list_contents table tr{
  width: 100%;
  border-bottom: 1px solid #333333;
}

#coin_list_col_header {
  top: 0px;
  height: 30px;
  align-items: center;
  color: #666666;
  background-color: #1A2436;

}

#coin_list_col_header ul {
  display: flex;
  align-items: center;
  list-style: none;
  padding: 0px;
  height: 100%;

}

#coin_list_col_header ul li{
  font-size: 12px;
  height: 100%;
  display: inline;
  width: 100%;
  font-weight: bold;
  padding-top: 1%;
}

#coin_list_col_header ul li:hover {
  text-decoration: underline;
}

.coin_list_content {
  height: 40px;
  padding-top: 5px;
}

.coin_list_content td{
  font-size: 13px;
  font-weight: bold;
  height: 100%;
  width: 25%;
}

.coin_list_content .left_align {
  text-align: left;
  padding-left: 10px;
}

.coin_list_content:hover {
  background-color : #1F2A3C;
}

.right_align {
  text-align: right;
}

.center_align {
  text-align: center;
}

.text_blue {
  color: #0C66C6;
}

.text_red {
  color: #B84042;
}

.text_white {
  color: #FFFFFF;
}

.selected_color {
  background-color: #1F2A3C;
}

::-webkit-scrollbar {
  width: 8px; /*스크롤바의 너비*/
}

::-webkit-scrollbar-thumb {
  background-color: #151E2E; /*스크롤바의 색상*/
  border: 1px solid;
}

::-webkit-scrollbar-track {
  background-color: #1A2436; /*스크롤바 트랙 색상*/
}
</style>


<script>
import axios from "axios";
import { eventBus } from "@/main.js";

let coinListData = {
  coins: [],
  coins_info: [],
  coins_list_data: [],
  selectedCoinData: {
    marketCode: 'KRW-BTC',
    korean_name: '비트코인',
    currentPrice: 0,
    signed_change_rate: 0,
    signed_change_price: 0,
    high_price: 0,
    low_price: 0,
    acc_trade_price_24h: 0,
    acc_trade_volume_24h: 0,
  },
  langFlag : false,
  textColor : {
    color: '',
  },
  redColor: { color : '#B84042'},
  blueColor: { color : '#0C66C6'},
  grayColor: { color : '#666666'},
};

function makeMarketCodeList_KRW(data){

  let ret = '';

  for(let i=0; i< data.length; i++){
    ret += (data[i].market + ',');
  }

  return ret.substr(0, ret.length-1);
}

export default {
  name: "coinList",
  data: function(){return coinListData},
  created: function() {
    this.retrieve_coin_list_info();
  },
  mounted : function() {
    this.interval_coin_list_info = setInterval(() => {
      this.retrieve_coin_list_info();
    }, 500);
  },
  beforeDestroy : function() {
    clearInterval(this.interval_coin_list_info);''
  },
  methods : {
    /**
     *  코인 리스트를 가져옴
     */
    retrieve_coin_list_info: function () {

      axios.get('http://localhost:8080/marketCode?isDetails=true')
          .then((result) => {
            coinListData.coins = result.data;

            var marketCodeList_KRW = makeMarketCodeList_KRW(result.data);

            axios.get('http://localhost:8080/currentCoinInfo', {params: {marketCode: marketCodeList_KRW}})
                .then((result) => {
                  this.setCoinsListData(result.data);
                })
          })
    },

    /**
     *
     * 코인 리스트 데이터 세팅
     */
    setCoinsListData: function (data) {
      this.coins_list_data = [];

      for (var i = 0; i < this.coins.length; i++) {
        for (var j = 0; j < data.length; j++) {

          if (this.coins[i].market == data[j].market) {

            //현재가에 소수점 이하가 존재하는경우 4자리까지
            if (data[j].trade_price % 1 > 0) {
              data[j].trade_price = data[j].trade_price.toLocaleString('ko-KR');
            } else {
              data[j].trade_price = Math.floor(data[j].trade_price).toLocaleString('ko-KR');
            }
            data[j].signed_change_rate = (data[j].signed_change_rate * 100).toFixed(2);
            if (data[j].signed_change_rate > 0) data[j].signed_change_rate = "+" + String(data[j].signed_change_rate);

            // 전일대비 증감액이 2자릿수 이하면 소수점 둘째자리까지 표시
            if (Math.floor(Math.abs(data[j].signed_change_price) / 100) > 0)
              data[j].signed_change_price = Math.floor(data[j].signed_change_price).toLocaleString('ko-KR');
            else
              data[j].signed_change_price = parseFloat(data[j].signed_change_price).toFixed(2).toLocaleString('ko-KR');

            var selectedCoinAccTragePrice24h = Math.floor(data[j].acc_trade_price_24h).toLocaleString('ko-KR');
            data[j].acc_trade_price_24h = Math.floor(data[j].acc_trade_price_24h / 1000000).toLocaleString('ko-KR');   //거래대금
            data[j].acc_trade_volume_24h = parseFloat(parseFloat(data[j].acc_trade_volume_24h).toFixed(3)).toLocaleString('ko-KR'); //거래량

            if (Math.floor(data[j].high_price / 100) > 0)
              data[j].high_price = Math.floor(data[j].high_price).toLocaleString('ko-KR');
            else
              data[j].high_price = Math.floor(data[j].high_price).toFixed(2).toLocaleString('ko-KR');

            if (Math.floor(data[j].low_price / 100) > 0)
              data[j].low_price = Math.floor(data[j].low_price).toLocaleString('ko-KR');
            else
              data[j].low_price = Math.floor(data[j].low_price).toFixed(2).toLocaleString('ko-KR');

            // 현재 선택된 코인데이터 세팅
            if (data[j].market == this.selectedCoinData.marketCode) {
              this.selectedCoinData.korean_name = this.coins[i].korean_name;
              this.selectedCoinData.currentPrice = data[j].trade_price;
              this.selectedCoinData.signed_change_rate = data[j].signed_change_rate;
              this.selectedCoinData.signed_change_price = data[j].signed_change_price;
              this.selectedCoinData.high_price = data[j].high_price;
              this.selectedCoinData.low_price = data[j].low_price;
              this.selectedCoinData.acc_trade_price_24h = selectedCoinAccTragePrice24h;
              this.selectedCoinData.acc_trade_volume_24h = data[j].acc_trade_volume_24h;

              //현재 선택된 코인 증감률에따라 색상 변경
              if (this.selectedCoinData.signed_change_rate.startsWith('+')) {
                this.textColor.color = this.redColor.color;
              } else if (this.selectedCoinData.signed_change_rate.startsWith('-')) {
                this.textColor.color = this.blueColor.color;
              } else {
                this.textColor.color = '#ffffff';
              }
            }

            this.coins_list_data.push({
              market: this.coins[i].market,
              korean_name: this.coins[i].korean_name,
              english_name: this.coins[i].english_name,
              market_warning: this.coins[i].market_warning,
              trade_price: data[j].trade_price,
              signed_change_price: data[j].signed_change_price,
              signed_change_rate: data[j].signed_change_rate,
              acc_trade_price_24h: data[j].acc_trade_price_24h
            });

            eventBus.$emit("updateCoinData", this.selectedCoinData);
          }
        }
      }
    },


    /**
     * 코인명 검색 입력박스 변경이벤트
     */
    search_onchange: function () {
      var search_value = document.getElementById('coin_search').value;
      var tr_coin_list = document.getElementById('tbl_coin_list').childNodes;


      for (var i = 0; i < tr_coin_list.length; i++) {
        if (search_value.length === 0) {
          tr_coin_list[i].style.display = '';
          continue;
        }

        var td_korean_name = String(tr_coin_list[i].childNodes[0].textContent).toLowerCase();

        if (!td_korean_name.includes(search_value)) {
          tr_coin_list[i].style.display = 'none';
        } else {
          tr_coin_list[i].style.display = '';
        }
      }
    },

    coinItem_onclick : function(coin){

      eventBus.$emit("clickCoinList", coin);
    },

  },  //method
};

</script>


