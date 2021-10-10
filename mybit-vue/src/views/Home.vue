<template>
    <div id="main">
        <div id="left">
            <div id="coin_name">
                <div id="coin_divs">{{selectedCoinData.korean_name}}</div>
                <div id="coin_name_tab"> 
                    <div><a href='#'>시세</a></div>
                    <div><a href='#'>정보</a></div>
                </div>         
            </div>
            <div id="coin_price">
              <span id="coin_price_left" v-bind:style="textColor">
                <div id="price_now" ><h2 style="font-weight : bold;">{{selectedCoinData.currentPrice}}</h2> KRW</div>
                <span v-bind:style="grayColor"><span style="font-weight : bold; font-size:13px">전일대비</span></span> <span style="font-weight : bold;">{{selectedCoinData.signed_change_rate}}% </span>
                <span style="font-weight : bold;">
                  <span v-if="selectedCoinData.signed_change_rate.startsWith('+')">▲</span>
                  <span v-if="selectedCoinData.signed_change_rate.startsWith('-')">▼</span>
                  <span>{{selectedCoinData.signed_change_price}}</span>
                </span>
              </span>

              <span id="coin_price_mid">
                <div style="display:flex; height:50%; border-bottom:1px solid #333333; padding-bottom : 1%;">
                  <span class="tbx_gray" style="padding-top:3%">고가</span>
                  <span style="width:80%; padding-top:3%; text-align:right; font-weight : bold;" v-bind:style="redColor">{{selectedCoinData.high_price}}</span>
                </div>
                <div style="display:flex; height:50%; padding-top : 3%; padding-bottom : 3%;">
                  <span class="tbx_gray" style="padding-top:3%">저가</span>
                  <span style="width:80%; padding-top:3%; text-align:right; font-weight : bold;" v-bind:style="blueColor">{{selectedCoinData.low_price}}</span>
                </div>                
              </span>

              <span id="coin_price_right">
                 <div style="display:flex; height:50%; border-bottom:1px solid #333333; padding-bottom : 3%;">
                  <span class="tbx_gray" style="width:50%; text-align:left; padding-top:3%;">거래량(24H)</span>
                  <span style="width:50%; text-align:right; padding-top:3%; padding-right:2%; font-size:14px; font-weight:1;">{{selectedCoinData.acc_trade_volume_24h}}</span>
                  <span class="tbx_gray" style="padding-top : 3%;">{{selectedCoinData.marketCode.split('-')[1]}}</span>
                </div>
                 <div style="display:flex; height:50%; padding-top : 3%; padding-bottom : 3%;">
                  <span class="tbx_gray" style="width:50%; text-align:left; padding-top:3%;">거래대금(24H)</span>
                  <span style="width:50%; padding-top:3%; text-align:right; padding-right:2%; font-size:14px; font-weight:1;">{{selectedCoinData.acc_trade_price_24h}}</span>
                  <span class="tbx_gray" style="padding-top : 3%;">{{selectedCoinData.marketCode.split('-')[0]}}</span>
                </div>
              </span>
            </div>

            <div style="padding : 1% 0% 1% 3%;" v-on:change="sbx_candle_onchange($event)">
              <select name="candle" style="background-color: #1A2436; color: white;" v-model="unit">
                <option value=1>1분</option>
                <option value=3>3분</option>
                <option value=5>5분</option>
                <option value=10>10분</option>
                <option value=15>15분</option>
                <option value=30>30분</option>
                <option value=60>1시간</option>
                <option value=240>4시간</option>
                <option value='day'>1일</option>
                <option value='week'>1주</option>
                <option value='month'>한 달</option>
              </select>
            </div>

            <div id="chart">
                <apexcharts type="candlestick" :options="chartOptions" :series="series"></apexcharts>
            </div>        
        </div>
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
                          text_white : coin.signed_change_rate.startsWith('+') == false && coin.signed_change_rate.startsWith('-') == false,
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
    </div>
</template>

<style scoped>
    #main {
        display: flex;
        align-items:flex-start;  
        height: 70%;   
    }

    #left {
        width: 51%;
        height: 750px;
        margin-top: 0.5%;
        margin-left: 14%;        
        background-color: #1A2436;
        color: #FFFFFF;
    }

    #coin_name {
         display:flex;
         align-items: center;
         position:relative;
         height: 6%;
         border-bottom: 1px solid #333333;
    }

    #coin_divs {
        display: table;
        text-align: center;
        vertical-align: middle;
        padding-left: 4%;
        font-weight: bold;
        font-size: 130%;
    }

    #coin_name_tab {
        display:table;
        position: absolute;
        right: 0px;
        height: 100%;
        width: 20%;
        margin-right: 3%;
    } 
    
    #coin_name_tab div{
        display: table-cell;
        text-align: center;
        vertical-align: middle;        
        font-weight: bold;
        width: 50%;
        height: 100%;
        margin-left: 1%;
    }  

    #coin_name_tab div a {
        text-decoration: none;
        color: #FFFFFF;
    }

    #coin_price {      
      padding: 1.5% 1.5% 1% 1.5%;
      border-bottom: 1px solid #333333;
      display: flex;
    }

    #coin_price_left {      
      width: 50%;
    }

    #coin_price_mid {           
      width: 25%;
    }

    #coin_price_right {      
      width: 25%;
    }

    #price_now h2{
        display : inline;
    }

    #chart {
        width: 100%;
        height: 100%;
    }

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

    .border_red {
      border:#B84042;
      border-width: 1px;
      border-style: solid;  
    }

    .border_blue {
      border: #0C66C6;  
      border-width: 1px;
      border-style: solid;  
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

    .tbx_gray {
      color: #AAAAAA;
      font-size: 13px;
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
/*import dayjs from 'dayjs'*/
import VueApexCharts from 'vue-apexcharts'
import axios from 'axios'

var data =  {    
  series: [{
    name: 'candle',
    data: [],
  }],
  chartOptions: {      
    chart: {
      height: '100%',
      type: 'candlestick',
    },
    title: {
      text: 'CandleStick Chart - Category X-axis',
      align: 'left'
    },
    plotOptions: {
        candlestick: {
            colors: {
                upward: '#B84042',
                downward: '#0C66C6'
            },
            wick: {
                useFillColor: true,
            }
        },
    },
    annotations: {
      xaxis: [
        {
          x: 'Oct 06 14:00',
          borderColor: '#00E396',
          label: {
            borderColor: '#00E396',
            style: {
              fontSize: '12px',
              color: '#fff',
              background: '#00E396'
            },
            orientation: 'horizontal',
            offsetY: 7,
            text: 'Annotation Test'
          }
        }
      ],
      yaxis:[
        {
          y: null,
          strokeDashArray: 5,
          borderColor: '#00E396',
          label: {
            borderColor: '#00E396',
            style: {
              fontSize: '12px',
              color: '#fff',
              background: '#00E396',
            },            
            text: 'Annotation Test',
            offsetX : 70,
          }
        }
      ]

    },
    tooltip: {
      enabled: true,
    },
    xaxis: {
      type: 'category',
      labels: {
        formatter: function(val) {
            val = String(val);
            if(val.length == 6){
              if(1==2 || val.substr(2,2) == '30' || val.substr(2,2) == '00') return val.substr(0,2) + ':' + val.substr(2,2);
              else return '';
            }
            else return val;

        },
        style : {
          colors : '#666666',
        },
      },
      axisBorder: {
          show: true,
          color: '#666666',
          offsetX: 0,
          offsetY: 0
      },
    },
    yaxis: {
      opposite: true, 
      labels : {
        formatter : function(val){
          return val.toLocaleString();
        },
        style : {
          colors : '#666666',
        },
      },
      axisBorder: {
          show: true,
          color: '#666666',
          offsetX: 0,
          offsetY: 0
      },
    },
  },
  langflag : true,
  coins : [],
  coins_info : [],
  coins_list_data : [],
  selectedCoinData : {
    marketCode : 'KRW-BTC',
    korean_name : '비트코인',
    currentPrice : 0,
    signed_change_rate : 0,
    signed_change_price : 0,
    high_price : 0,
    low_price : 0,
    acc_trade_price_24h : 0,
    acc_trade_volume_24h : 0,
  },
  unit : 1,
  textColor : {
      color: '',      
    },
  redColor: { color : '#B84042'},
  blueColor: { color : '#0C66C6'},
  grayColor: { color : '#666666'},
  interval_coin_list_info : null,
  interval_current_candle: null,

}

function makeMarketCodeList_KRW(data){

  var ret = '';

  for(var i=0; i< data.length; i++){
     ret += (data[i].market + ',');
  }
  
  return ret.substr(0, ret.length-1);
}


export default {   
    components: {
        apexcharts: VueApexCharts,
    },    
    data: function(){return data}, 
    created: function() {
      this.retrieve_coin_list_info();
    },    
    mounted : function() {
      this.retrieve_candles_init();

      this.interval_coin_list_info = setInterval(() => {
        this.retrieve_coin_list_info();
      }, 500);

      this.interval_current_candle = setInterval(() => {
        this.retrieve_current_candle();
      }, 300);

    },
    watched : {
        
    },
    methods : {
      sbx_candle_onchange : function(){

          //현재 캔들 데이터 가져오는거 중지
          clearInterval(this.interval_current_candle);  
          this.retrieve_candles_init();
      },

      /** 
       *  코인 리스트를 가져옴
       */
      retrieve_coin_list_info : function(){
                        
        axios.get('http://localhost:8080/marketCode?isDetails=true')
        .then((result) => {
            data.coins = result.data;  

            var marketCodeList_KRW = makeMarketCodeList_KRW(result.data);

            axios.get('http://localhost:8080/currentCoinInfo', {params: { marketCode : marketCodeList_KRW }})
                .then((result) => {
                    
                    this.setCoinsListData(result.data);                                    
                })         
        })                 
      },
      /**
       * 
       * 코인 리스트 데이터 세팅
       */
      setCoinsListData : function(data){
          this.coins_list_data = [];

          for(var i=0; i<this.coins.length; i++){
            for(var j=0; j<data.length; j++){

              if(this.coins[i].market == data[j].market) {

                   //현재가에 소수점 이하가 존재하는경우 4자리까지
                  if(data[j].trade_price % 1 > 0){
                    data[j].trade_price = data[j].trade_price.toLocaleString('ko-KR');
                  }
                  else{
                    data[j].trade_price = Math.floor(data[j].trade_price).toLocaleString('ko-KR');
                  }
                  data[j].signed_change_rate =  (data[j].signed_change_rate * 100).toFixed(2);
                  if(data[j].signed_change_rate > 0) data[j].signed_change_rate = "+" + String(data[j].signed_change_rate);
                  
                  // 전일대비 증감액이 2자릿수 이하면 소수점 둘째자리까지 표시
                  if(Math.floor(Math.abs(data[j].signed_change_price) / 100) > 0)
                    data[j].signed_change_price = Math.floor(data[j].signed_change_price).toLocaleString('ko-KR');
                  else
                    data[j].signed_change_price = parseFloat(data[j].signed_change_price).toFixed(2).toLocaleString('ko-KR');
                  
                  var selectedCoinAccTragePrice24h = Math.floor(data[j].acc_trade_price_24h).toLocaleString('ko-KR');
                  data[j].acc_trade_price_24h =  Math.floor(data[j].acc_trade_price_24h / 1000000).toLocaleString('ko-KR');   //거래대금
                  data[j].acc_trade_volume_24h = parseFloat(parseFloat(data[j].acc_trade_volume_24h).toFixed(3)).toLocaleString('ko-KR'); //거래량

                  if(Math.floor(data[j].high_price / 100) >0)
                    data[j].high_price = Math.floor(data[j].high_price).toLocaleString('ko-KR');
                  else
                    data[j].high_price = Math.floor(data[j].high_price).toFixed(2).toLocaleString('ko-KR');
                  
                  if(Math.floor(data[j].low_price / 100) >0)
                    data[j].low_price = Math.floor(data[j].low_price).toLocaleString('ko-KR');
                  else
                    data[j].low_price = Math.floor(data[j].low_price).toFixed(2).toLocaleString('ko-KR');                                      

                  // 현재 선택된 코인데이터 세팅
                  if(data[j].market == this.selectedCoinData.marketCode){
                    this.selectedCoinData.korean_name = this.coins[i].korean_name;
                    this.selectedCoinData.currentPrice = data[j].trade_price;
                    this.selectedCoinData.signed_change_rate = data[j].signed_change_rate;
                    this.selectedCoinData.signed_change_price = data[j].signed_change_price;
                    this.selectedCoinData.high_price = data[j].high_price;
                    this.selectedCoinData.low_price = data[j].low_price;  
                    this.selectedCoinData.acc_trade_price_24h = selectedCoinAccTragePrice24h;
                    this.selectedCoinData.acc_trade_volume_24h = data[j].acc_trade_volume_24h;

                    //현재 선택된 코인 증감률에따라 색상 변경
                    if(this.selectedCoinData.signed_change_rate.startsWith('+')){
                      this.textColor.color = this.redColor.color;
                    }
                    else if(this.selectedCoinData.signed_change_rate.startsWith('-')){
                      this.textColor.color = this.blueColor.color;
                    }
                    else{
                      this.textColor.color = '#ffffff';
                    }
                  }

                  
                  this.coins_list_data.push({market : this.coins[i].market, korean_name : this.coins[i].korean_name, english_name : this.coins[i].english_name, market_warning : this.coins[i].market_warning,
                    trade_price : data[j].trade_price, signed_change_price : data[j].signed_change_price, signed_change_rate : data[j].signed_change_rate, acc_trade_price_24h : data[j].acc_trade_price_24h
                  });

              }        
            }     
          }     
      },     

      /**
       * 코인명 검색 입력박스 변경이벤트 
       */
      search_onchange : function(){
        var search_value = document.getElementById('coin_search').value;
        var tr_coin_list = document.getElementById('tbl_coin_list').childNodes;


        for(var i=0; i<tr_coin_list.length; i++){
            if(search_value.length == 0) {
              tr_coin_list[i].style.display = '';
              continue;
            }

            var td_korean_name = String(tr_coin_list[i].childNodes[0].textContent).toLowerCase();
            
            if(!td_korean_name.includes(search_value)){
              tr_coin_list[i].style.display = 'none';
            }
            else{
              tr_coin_list[i].style.display = '';
            }
        }
      },

      /**
       * 코인 리스트의 항목 클릭 이벤트. 현재 선택된 코인 정보 변경
       */
      coinItem_onclick : function(coin){
        this.selectedCoinData.marketCode = coin.market;
        this.selectedCoinData.korean_name = coin.korean_name;
        this.selectedCoinData.currentPrice = coin.currentPrice;
        this.selectedCoinData.signed_change_rate = coin.signed_change_rate;
        this.selectedCoinData.signed_change_price = coin.signed_change_price;
        this.selectedCoinData.high_price = coin.high_price;
        this.selectedCoinData.low_price = coin.low_price;  
        this.selectedCoinData.acc_trade_price_24h = coin.acc_trade_price_24h;
        this.selectedCoinData.acc_trade_volume_24h = coin.acc_trade_volume_24h;

        this.retrieve_candles_init();
      },

      /**
       * 현재 선택된 코인의 캔들을 가져와 초기 세팅
       */
      retrieve_candles_init : function(){

        var baseUrl = 'http://localhost:8080/';
        var cnt = null;
        var today = new Date();

        if(this.unit == 'day' || this.unit == 'week' || this.unit == 'month'){          
          cnt = 200;
          baseUrl += (this.unit + 'Candles/');
        }
        else{
          
          baseUrl += ('minuteCandles/' + this.unit );
          cnt = Math.floor((today.getHours() * 60 + today.getMinutes()) / this.unit);        
        }

        axios.get(baseUrl, {params: { market : this.selectedCoinData.marketCode, count : cnt}})
                        .then((result) => {
                            
                            this.series[0].data = [];

                            for(var i =result.data.length - 1; i >= 0; i--){
                              var data = result.data[i];
                              var candle_date_time_kst = data.candle_date_time_kst;
                              var opening_price =  Math.floor(data.opening_price);
                              var high_price = Math.floor(data.high_price);
                              var low_price =  Math.floor(data.low_price);
                              var trade_price =  Math.floor(data.trade_price);

                              if(this.unit == 'day' || this.unit == 'week' || this.unit == 'month'){
                                candle_date_time_kst = candle_date_time_kst.substr(0,10);
                              }

                              var candle_data = {
                                  x : candle_date_time_kst, 
                                  y : [opening_price, high_price, low_price, trade_price]
                              };                              

                              this.series[0].data.push(candle_data);                      
                            }

                            var lastIdx = this.series[0].data.length - 1;

                            this.chartOptions.annotations.yaxis[0].y = this.series[0].data[lastIdx].y[3];
                            this.chartOptions.annotations.yaxis[0].label.text = this.series[0].data[lastIdx].y[3];

                            window.dispatchEvent(new Event('resize'));     
                            
                            //현재 선택된 코인의 캔들 주기적으로 받아옴
                            this.interval_current_candle = setInterval(() => {
                              this.retrieve_current_candle();
                            }, 300);
                        }) 
             

      },

      /**
       * 현재 선택된 코인의 정보를 받아옴 (캔들 갱신)
       */
      retrieve_current_candle : function(){

        var baseUrl = 'http://localhost:8080/';

        if(this.unit == 'day' || this.unit == 'week' || this.unit == 'month'){
          baseUrl += (this.unit + 'Candles/');
        }
        else{          
          baseUrl += ('minuteCandles/' + this.unit );  
        }

        axios.get(baseUrl, {params: { market : this.selectedCoinData.marketCode, count : 1}})
                      .then((result) => {                        
                       
                          var data = result.data[0];
                          var candle_date_time_kst = data.candle_date_time_kst;
                          var opening_price =  Math.floor(data.opening_price);
                          var high_price = Math.floor(data.high_price);
                          var low_price =  Math.floor(data.low_price);
                          var trade_price =  Math.floor(data.trade_price);

                          if(this.unit == 'day' || this.unit == 'week' || this.unit == 'month'){
                            candle_date_time_kst = candle_date_time_kst.substr(0,10);
                          }

                          var candle_data = {
                              x : candle_date_time_kst, 
                              y : [opening_price, high_price, low_price, trade_price]
                          };          
                          
                          var lastIdx = this.series[0].data.length - 1;

                          if(this.series[0].data[lastIdx].x == candle_date_time_kst){
                            this.series[0].data[lastIdx].y = candle_data.y;
                          }
                          else{
                            this.series[0].data.push(candle_data);  
                          }                
                          
                          this.chartOptions.annotations.yaxis[0].y = trade_price;
                          this.chartOptions.annotations.yaxis[0].label.text = trade_price.toLocaleString();
                          
                          window.dispatchEvent(new Event('resize'));     
                      }) 
        },     

    }
};

</script>