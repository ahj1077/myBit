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
import dayjs from 'dayjs'
import VueApexCharts from 'vue-apexcharts'
import axios from 'axios'

var data =  {    
  series: [{
    name: 'candle',
    data: [
      {
        x: new Date(1538778600000),
        y: [6629.81, 6650.5, 6623.04, 6633.33]
      },
      {
        x: new Date(1538780400000),
        y: [6632.01, 6643.59, 6620, 6630.11]
      },
      {
        x: new Date(1538782200000),
        y: [6630.71, 6648.95, 6623.34, 6635.65]
      },
      {
        x: new Date(1538784000000),
        y: [6635.65, 6651, 6629.67, 6638.24]
      },
      {
        x: new Date(1538785800000),
        y: [6638.24, 6640, 6620, 6624.47]
      },
      {
        x: new Date(1538787600000),
        y: [6624.53, 6636.03, 6621.68, 6624.31]
      },
      {
        x: new Date(1538789400000),
        y: [6624.61, 6632.2, 6617, 6626.02]
      },
      {
        x: new Date(1538791200000),
        y: [6627, 6627.62, 6584.22, 6603.02]
      },
      {
        x: new Date(1538793000000),
        y: [6605, 6608.03, 6598.95, 6604.01]
      },
      {
        x: new Date(1538794800000),
        y: [6604.5, 6614.4, 6602.26, 6608.02]
      },
      {
        x: new Date(1538796600000),
        y: [6608.02, 6610.68, 6601.99, 6608.91]
      },
      {
        x: new Date(1538798400000),
        y: [6608.91, 6618.99, 6608.01, 6612]
      },
      {
        x: new Date(1538800200000),
        y: [6612, 6615.13, 6605.09, 6612]
      },
      {
        x: new Date(1538802000000),
        y: [6612, 6624.12, 6608.43, 6622.95]
      },
      {
        x: new Date(1538803800000),
        y: [6623.91, 6623.91, 6615, 6615.67]
      },
      {
        x: new Date(1538805600000),
        y: [6618.69, 6618.74, 6610, 6610.4]
      },
      {
        x: new Date(1538807400000),
        y: [6611, 6622.78, 6610.4, 6614.9]
      },
      {
        x: new Date(1538809200000),
        y: [6614.9, 6626.2, 6613.33, 6623.45]
      },
      {
        x: new Date(1538811000000),
        y: [6623.48, 6627, 6618.38, 6620.35]
      },
      {
        x: new Date(1538812800000),
        y: [6619.43, 6620.35, 6610.05, 6615.53]
      },
      {
        x: new Date(1538814600000),
        y: [6615.53, 6617.93, 6610, 6615.19]
      },
      {
        x: new Date(1538816400000),
        y: [6615.19, 6621.6, 6608.2, 6620]
      },
      {
        x: new Date(1538818200000),
        y: [6619.54, 6625.17, 6614.15, 6620]
      },
      {
        x: new Date(1538820000000),
        y: [6620.33, 6634.15, 6617.24, 6624.61]
      },
      {
        x: new Date(1538821800000),
        y: [6625.95, 6626, 6611.66, 6617.58]
      },
      {
        x: new Date(1538823600000),
        y: [6619, 6625.97, 6595.27, 6598.86]
      },
      {
        x: new Date(1538825400000),
        y: [6598.86, 6598.88, 6570, 6587.16]
      },
      {
        x: new Date(1538827200000),
        y: [6588.86, 6600, 6580, 6593.4]
      },
      {
        x: new Date(1538829000000),
        y: [6593.99, 6598.89, 6585, 6587.81]
      },
      {
        x: new Date(1538830800000),
        y: [6587.81, 6592.73, 6567.14, 6578]
      },
      {
        x: new Date(1538832600000),
        y: [6578.35, 6581.72, 6567.39, 6579]
      },
      {
        x: new Date(1538834400000),
        y: [6579.38, 6580.92, 6566.77, 6575.96]
      },
      {
        x: new Date(1538836200000),
        y: [6575.96, 6589, 6571.77, 6588.92]
      },
      {
        x: new Date(1538838000000),
        y: [6588.92, 6594, 6577.55, 6589.22]
      },
      {
        x: new Date(1538839800000),
        y: [6589.3, 6598.89, 6589.1, 6596.08]
      },
      {
        x: new Date(1538841600000),
        y: [6597.5, 6600, 6588.39, 6596.25]
      },
      {
        x: new Date(1538843400000),
        y: [6598.03, 6600, 6588.73, 6595.97]
      },
      {
        x: new Date(1538845200000),
        y: [6595.97, 6602.01, 6588.17, 6602]
      },
      {
        x: new Date(1538847000000),
        y: [6602, 6607, 6596.51, 6599.95]
      },
      {
        x: new Date(1538848800000),
        y: [6600.63, 6601.21, 6590.39, 6591.02]
      },
      {
        x: new Date(1538850600000),
        y: [6591.02, 6603.08, 6591, 6591]
      },
      {
        x: new Date(1538852400000),
        y: [6591, 6601.32, 6585, 6592]
      },
      {
        x: new Date(1538854200000),
        y: [6593.13, 6596.01, 6590, 6593.34]
      },
      {
        x: new Date(1538856000000),
        y: [6593.34, 6604.76, 6582.63, 6593.86]
      },
      {
        x: new Date(1538857800000),
        y: [6593.86, 6604.28, 6586.57, 6600.01]
      },
      {
        x: new Date(1538859600000),
        y: [6601.81, 6603.21, 6592.78, 6596.25]
      },
      {
        x: new Date(1538861400000),
        y: [6596.25, 6604.2, 6590, 6602.99]
      },
      {
        x: new Date(1538863200000),
        y: [6602.99, 6606, 6584.99, 6587.81]
      },
      {
        x: new Date(1538865000000),
        y: [6587.81, 6595, 6583.27, 6591.96]
      },
      {
        x: new Date(1538866800000),
        y: [6591.97, 6596.07, 6585, 6588.39]
      },
      {
        x: new Date(1538868600000),
        y: [6587.6, 6598.21, 6587.6, 6594.27]
      },
      {
        x: new Date(1538870400000),
        y: [6596.44, 6601, 6590, 6596.55]
      },
      {
        x: new Date(1538872200000),
        y: [6598.91, 6605, 6596.61, 6600.02]
      },
      {
        x: new Date(1538874000000),
        y: [6600.55, 6605, 6589.14, 6593.01]
      },
      {
        x: new Date(1538875800000),
        y: [6593.15, 6605, 6592, 6603.06]
      },
      {
        x: new Date(1538877600000),
        y: [6603.07, 6604.5, 6599.09, 6603.89]
      },
      {
        x: new Date(1538879400000),
        y: [6604.44, 6604.44, 6600, 6603.5]
      },
      {
        x: new Date(1538881200000),
        y: [6603.5, 6603.99, 6597.5, 6603.86]
      },
      {
        x: new Date(1538883000000),
        y: [6603.85, 6605, 6600, 6604.07]
      },
      {
        x: new Date(1538884800000),
        y: [6604.98, 6606, 6604.07, 6606]
      },
    ]
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
                upward: '#3C90EB',
                downward: '#DF7D46'
            },
            wick: {
                useFillColor: true,
            }
        }
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
          return dayjs(val).format('MMM DD HH:mm')
        }
      }
    },
    yaxis: {
      tooltip: {
        enabled: true
      }
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
      setInterval(() => {
        this.retrieve_coin_list_info();
      }, 500);
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

                  data[j].trade_price = Math.floor(data[j].trade_price).toLocaleString();
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

    }
};

</script>