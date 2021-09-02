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
                <div id="price_now"><h2 style="font-weight : bold;">{{selectedCoinData.currentPrice}}</h2> KRW</div>
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
            <div id="coin_list_contents">
                <table id="tbl_coin_list">
                    <tr id="coin_list_col_header">
                        <th class="center_align">한글명</th>
                        <th class="right_align">현재가</th>
                        <th class="right_align">전일대비</th>
                        <th class="right_align">거래대금</th>
                    </tr>
                    <tr class="coin_list_content" v-for="coin in coins_list_data" v-bind:key="coin.market">                        
                        <td class="left_align" style="text-align: left; color: #FFFFFF;">{{coin.korean_name}}</td>
                        <td class="right_align">{{coin.trade_price}}</td>
                        <td class="right_align">{{coin.signed_change_rate}}%</td>
                        <td class="right_align" style="color: #CCCCCC;">{{coin.acc_trade_price_24h}}백만</td>
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
        padding: 1%;
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
        height: 1000px;
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
        margin-left: 5px;
        height: 80%;
        width: 90%;
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
        width: 100%;
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
        
        position: sticky;
        top: 0px;
        align-items: center;
        color: #666666;
        background-color: #1A2436;
        
    }

    #coin_list_col_header th{
        font-size: 12px;
        height: 100%;
        width: 25%;
    }

    #coin_list_col_header th:hover {
        text-decoration: underline;
    }

    .coin_list_content {
        height: 40px;
        padding-top: 5px;
    }

    .coin_list_content td{
        font-size: 13px;
        font-weight: bold;
        background-color: #1A2436;
        height: 100%;
        width: 25%;
    }

    .coin_list_content .left_align {
        text-align: left;
        padding-left: 10px;
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
            if(val.substr(2,2) == '30' || val.substr(2,2) == '00') return val.substr(0,2) + ':' + val.substr(2,2);
            return '';
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
  coins : [],
  coins_info : [],
  coins_list_data : [],
  selectedCoinData : {
    marketCode : 'KRW-BTC',
    korean_name : '비트코인',
    currentPrice : 0,
    signed_change_rate : 0,
  },
  unit : 1,
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

      setInterval(() => {
        this.retrieve_coin_list_info();
      }, 500);

      setInterval(() => {
        this.retrieve_current_candle();
      }, 300);

    },
    methods : {

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

      setCoinsListData : function(data){
          this.coins_list_data = [];

          for(var i=0; i<this.coins.length; i++){
            for(var j=0; j<data.length; j++){
              if(this.coins[i].market == data[j].market) {

                  data[j].trade_price = Math.floor(data[j].trade_price).toLocaleString('ko-KR');
                  data[j].signed_change_rate =  (data[j].signed_change_rate * 100).toFixed(2);
                  if(data[j].signed_change_rate > 0) data[j].signed_change_rate = "+"+String(data[j].signed_change_rate);
                  data[j].acc_trade_price_24h = Math.floor(data[j].acc_trade_price_24h / 1000000);

                  if(data[j].market == this.selectedCoinData.marketCode){
                    this.selectedCoinData.korean_name = this.coins[i].korean_name;
                    this.selectedCoinData.currentPrice = data[j].trade_price;
                    this.selectedCoinData.signed_change_rate = data[j].signed_change_rate;
                  }

                  this.coins_list_data.push({market : this.coins[i].market, korean_name : this.coins[i].korean_name, english_name : this.coins[i].english_name, market_warning : this.coins[i].market_warning,
                    trade_price : data[j].trade_price, signed_change_price : data[j].signed_change_price, signed_change_rate : data[j].signed_change_rate, acc_trade_price_24h : data[j].acc_trade_price_24h
                  });
              }        
            }     
          }

          this.setClass();          
      },

      setClass : function (){

        var tr_coin_list = document.getElementById('tbl_coin_list').childNodes;

        for(var i=1; i<tr_coin_list.length; i++){
            var td_signed_change_rate = tr_coin_list[i].childNodes[2];
            var txt_signed_change_rate = td_signed_change_rate.textContent;
            this.toggleColorStyle(tr_coin_list[i], txt_signed_change_rate);
        }

        var seletedCoinCurrentPrice = document.getElementById('price_now');
        this.toggleColorStyle(seletedCoinCurrentPrice, this.selectedCoinData.signed_change_rate);
      },

      toggleColorStyle : function(element, signed_change_rate){
          signed_change_rate = String(signed_change_rate);

          if(signed_change_rate.startsWith('+')){  
            element.style.color = '#B84042';
          }
          else if(signed_change_rate.startsWith('-')){
            element.style.color = '#0C66C6';
          }
          else {
            element.style.color = 'white';
          }
      },

      search_onchange : function(){
        var search_value = document.getElementById('coin_search').value;
        var tr_coin_list = document.getElementById('tbl_coin_list').childNodes;


        for(var i=1; i<tr_coin_list.length; i++){
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
      retrieve_candles_init : function(){
        axios.get('http://localhost:8080/minuteCandles/'+this.unit, {params: { market : this.selectedCoinData.marketCode, count : 200}})
                        .then((result) => {
                            
                            for(var i =0; i<result.data.length; i++){
                              var data = result.data[i];
                              var candle_date_time_kst = data.candle_date_time_kst;
                              var opening_price =  Math.floor(data.opening_price);
                              var high_price = Math.floor(data.high_price);
                              var low_price =  Math.floor(data.low_price);
                              var trade_price =  Math.floor(data.trade_price);

                              var candle_data = {
                                  x : candle_date_time_kst, 
                                  y : [opening_price, high_price, low_price, trade_price]
                              };                              

                              this.series[0].data.push(candle_data);                      
                            }
                            window.dispatchEvent(new Event('resize'));                          
                        }) 

      },
      retrieve_current_candle : function(){

          axios.get('http://localhost:8080/minuteCandles/'+this.unit, {params: { market : this.selectedCoinData.marketCode}})
                        .then((result) => {
                            
                            var data = result.data[0];
                            var candle_date_time_kst = data.candle_date_time_kst;
                            var opening_price =  Math.floor(data.opening_price);
                            var high_price = Math.floor(data.high_price);
                            var low_price =  Math.floor(data.low_price);
                            var trade_price =  Math.floor(data.trade_price);
                            
                            var candle_data = {
                                x : candle_date_time_kst, 
                                y : [opening_price, high_price, low_price, trade_price]
                            };

                            var lastIdx = this.series[0].data.length - 1;
                            if(lastIdx == -1) {
                              lastIdx = 0;
                              this.series[0].data.push(candle_data);
                            }
                            else{
                              if(this.series[0].data[lastIdx].x == candle_date_time_kst){
                                this.series[0].data[lastIdx].y = candle_data.y;
                              }
                              else{
                                this.series[0].data.push(candle_data);
                              }
                            }  
                            window.dispatchEvent(new Event('resize'));                          
                        }) 
      },

    }
};

</script>