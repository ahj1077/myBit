<template>
  <div id="left">
    <div id="coin_name" style="height: 10%; font-size: 20px">
      <div id="coin_divs">{{selectedCoinData.korean_name}} 종토방</div>
    </div>
    <div class="line"/>
    <div style="width: 100%;">
      <div id="header" style="text-align: center; padding-top: 15px; height:40px; border-bottom: 1px solid #FFFFFF;">
        <ul>
          <li style="width: 65%">제목</li>
          <li style="width: 12%">작성자</li>
          <li style="width: 11%">추천수</li>
          <li style="width: 12%">작성일</li>
        </ul>
      </div>

      <table id="tbl_posts">
        <tr class = "post" v-for="post in posts" v-bind:key = "post.id">
          <td style="width: 70%; padding-left: 3%;">
            <span class="post_title" v-on:click="postTitle_onclick(post.id, $event)">
              <span style="display:none">{{post.id}}</span>{{post.title}}
            </span>
          </td>
          <td style="width: 10%;">{{post.writer}}</td>
          <td style="width: 10%; text-align: center;">{{post.recommendCount}}</td>
          <td style="width: 10%; font-size: 10px">{{post.writeDttm}}</td>
        </tr>
      </table>

      <div style="padding-top: 8px; padding-right: 10px">
          <span style="float: right">
            <router-link id="btn_write" :to="{name : 'newPost', query : {market : this.selectedCoinData.market, korean_name : this.selectedCoinData.korean_name}}">글쓰기</router-link>
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

.post {
  height : 30px;
  text-align: left;
  background-color: #1A2436;
  border-bottom: 1px solid #2B3547;
}

.post:hover{
  background-color: #1F2A3C;
}


</style>


<script>
import { eventBus } from "@/main.js";
import axios from "axios";

var data =  {
  selectedCoinData: {
    market: 'KRW-BTC',
    korean_name: '비트코인',
    currentPrice: 0,
    signed_change_rate: '0',
    signed_change_price: '0',
    high_price: 0,
    low_price: 0,
    acc_trade_price_24h: 0,
    acc_trade_volume_24h: 0,
  },

  posts : [],
}

export default {
  name: "board",
  components: {
  },
  data: function(){return data},
  created: function() {
    eventBus.$on("clickCoinList", (clickedCoinData) => {
      this.selectedCoinData = clickedCoinData;

      //게시글 목록 조회
      this.retrievePosts()
    });

    this.retrievePosts();
  },
  beforeDestroy : function(){
    eventBus.$off("clickCoinList");
  },
  mounted : function() {
  },
  updated : function(){
  },
  methods : {
    postTitle_onclick : function(id){
      var router = this.$router;
      router.push('/post?id=' + id + '&korean_name=' + this.selectedCoinData.korean_name
        + '&market=' + this.selectedCoinData.market); //게시글로 이동
    },

    /**
     * 현재 선택된 코인의 게시글 목록 조회
     */
    retrievePosts : function(){
      const selectedCoinMarket = this.selectedCoinData.market.split('-')[1];

      const url = 'http://localhost:8080/board/posts/' + selectedCoinMarket;

      axios.get(url).then((response) => {
        if(response.status == 200){
          const posts = response.data;
          this.posts = [];

          for(let i=0; i<posts.length; i++){
            this.posts.push(posts[i]);
          }
        }
      });
    }
  }
};

</script>


