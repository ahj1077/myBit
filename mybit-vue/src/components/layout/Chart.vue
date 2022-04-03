<template>
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
</template>

<style>

#left {
  width: 60%;
  height: 750px;
  margin-top: 0.5%;
  margin-left: 10%;
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

.right_align {
  text-align: right;
}

.center_align {
  text-align: center;
}
</style>


<script>
import { eventBus } from "@/main.js";
import VueApexCharts from 'vue-apexcharts'
import axios from "axios";

let coinListData = {
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
  textColor : {
    color: '',
  },
  redColor: { color : '#B84042'},
  blueColor: { color : '#0C66C6'},
  grayColor: { color : '#666666'},
  unit : 1,

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
      text: '',
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
          borderColor: '#111111',
          label: {
            borderColor: '#111111',
            style: {
              fontSize: '12px',
              color: '#FFFFFF',
              background: '#111111'
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
          borderColor: '#B84042',
          label: {
            borderColor: '#B84042',
            style: {
              fontSize: '12px',
              color: '#fff',
              background: '#B84042',
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
            if(1==2 || val.substr(2,2) == '30' || val.substr(2,2) == '00')
              return val.substr(0,2) + ':' + val.substr(2,2);
            else
              return '';
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
};

export default {
  name: "chart",
  components: {
    apexcharts : VueApexCharts
  },
  data: function(){return coinListData},
  created: function() {
    eventBus.$on("updateCoinData", (currentCoinData) => {
      this.selectedCoinData = currentCoinData;

      //현재 선택된 코인 증감률에따라 색상 변경
      if (this.selectedCoinData.signed_change_rate.startsWith('+')) {
        this.textColor.color = this.redColor.color;
      } else if (this.selectedCoinData.signed_change_rate.startsWith('-')) {
        this.textColor.color = this.blueColor.color;
      } else {
        this.textColor.color = '#ffffff';
      }
    });

    eventBus.$on("clickCoinList", (clickedCoinData) => {
      clearInterval(this.interval_current_candle);

      this.selectedCoinData.marketCode = clickedCoinData.market;
      this.selectedCoinData.currentPrice = clickedCoinData.trade_price;
      this.selectedCoinData.korean_name = clickedCoinData.korean_name;
      this.selectedCoinData.signed_change_rate = clickedCoinData.signed_change_rate;
      this.selectedCoinData.signed_change_price = clickedCoinData.signed_change_price;
      this.selectedCoinData.acc_trade_price_24h = clickedCoinData.acc_trade_price_24h;
      this.selectedCoinData.acc_trade_volume_24h = clickedCoinData.acc_trade_volume_24h;

      this.retrieve_candles_init();
    });

  },
  mounted : function() {
    this.retrieve_candles_init();
  },
  beforeDestroy() {
    eventBus.$off("updateCoinData");
    eventBus.$off("clickCoinList");

    clearInterval(this.interval_current_candle);
  },
  methods : {

    sbx_candle_onchange : function(){

      //현재 캔들 데이터 가져오는거 중지
      clearInterval(this.interval_current_candle);
      this.retrieve_candles_init();
    },

    /**
     * 현재 선택된 코인의 캔들을 가져와 초기 세팅
     */
    retrieve_candles_init : function(){

      var baseUrl = 'http://localhost:8080/upbit/';
      var cnt = null;
      var today = new Date();

      if(this.unit === 'day' || this.unit === 'week' || this.unit === 'month'){
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

            for(var i = result.data.length - 1; i >= 0; i--){
              var data = result.data[i];
              var candle_date_time_kst = data.candle_date_time_kst;
              var opening_price =  Math.floor(data.opening_price);
              var high_price = Math.floor(data.high_price);
              var low_price =  Math.floor(data.low_price);
              var trade_price =  Math.floor(data.trade_price);

              if(this.unit === 'day' || this.unit === 'week' || this.unit === 'month'){
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
            }, 500);
          })


    },

    /**
     * 현재 선택된 코인의 정보를 받아옴 (캔들 갱신)
     */
    retrieve_current_candle : function(){

      var baseUrl = 'http://localhost:8080/upbit/';

      if(this.unit === 'day' || this.unit === 'week' || this.unit === 'month'){
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

            if(this.unit === 'day' || this.unit === 'week' || this.unit === 'month'){
              candle_date_time_kst = candle_date_time_kst.substr(0,10);
            }

            var candle_data = {
              x : candle_date_time_kst,
              y : [opening_price, high_price, low_price, trade_price]
            };

            var lastIdx = this.series[0].data.length - 1;

            if(this.series[0].data[lastIdx].x === candle_date_time_kst){
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
  },
};

</script>


