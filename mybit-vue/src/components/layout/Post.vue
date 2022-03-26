<template>
  <div id="left">
    <div id="header" style="height: 10%; font-size: 20px">
      <div id="title"><span ref="coinKoreanName">{{$route.query.korean_name}}</span> 종토방</div>
      <div style="display: none" ref="coinMarket">{{$route.query.market}}</div>
    </div>
    <div>
      <div style="width: 100%; padding: 5% 5% 2% 5%; border-bottom: 1px solid #333333;">
        <input type="text" v-model="title" style="width: 100%;" readonly/>
      </div>
      <div style="width: 100%; height: 500px; padding: 2% 5% 2% 5%;">
        <textarea v-model="content" style="width: 100%; height: 100%" readonly></textarea>
      </div>
      <div style="display:flex; align-items: center; justify-content:center; height: 60px;">
        <button id="btn_recommend" v-on:click="btn_recommend_onclick">추천({{recommendCount}})</button>
      </div>
      <div style="padding-right: 5%">
        <span style="float: right">
          <span style="margin-right: 10px">
            <input type="button" id="btn_revise" value="수정하기" v-on:click="btn_revise_onclick"/>
          </span>
          <span style="margin-right: 10px">
            <input type="button" id="btn_remove" value="삭제하기" v-on:click="btn_remove_onclick"/>
          </span>
          <span style="">
            <input type="submit" id="btn_back" value="뒤로가기" v-on:click="btn_back_onclick"/>
          </span>
        </span>
      </div>
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

#header {
  display:flex;
  align-items: center;
  position:relative;
  height: 6%;
  border-bottom: 1px solid #333333;
}

#title {
  display: table;
  text-align: center;
  vertical-align: middle;
  padding-left: 4%;
  font-weight: bold;
  font-size: 130%;
}

#btn_back{
  background-color: #FFFFFF;
  font-size: 15px;
  color: #555555;
  text-decoration-line: none;
  border: 1px solid #FFFFFF;
  border-radius: 1px;
}

#btn_revise{
  background-color: #0C66C6;
  font-size: 15px;
  color: #FFFFFF;
  text-decoration-line: none;
  border: 1px solid #0C66C6;
  border-radius: 1px;
}

#btn_remove{
  background-color: #B84042;
  font-size: 15px;
  color: #FFFFFF;
  text-decoration-line: none;
  border: 1px solid #0C66C6;
  border-radius: 1px;
}

#btn_recommend{
  height: 60px;
  width: 60px;
  background-color: #000000;
  font-size: 15px;
  color: #FFFFFF;
  border: 3px solid #000000;
  border-radius: 3px;
}


</style>


<script>
import axios from "axios";

var data= {

  title : '',
  content : '',
  coinSymbol : '',
  writer : '',
  coinData : {
  },
  errors : [],
}

export default {
  name: "post",
  components: {
  },
  data: function(){return data},
  created: function() {
  },
  mounted : function() {
    this.retrievePost();
  },
  beforeDestroy() {

  },
  methods : {

    retrievePost : function(){
      const postId = this.$route.query.id
      var baseUrl = 'http://localhost:8080/board/post/' + postId;

      axios.get(baseUrl)
      .then((response) => {
        if(response.status == 200) {
          const data = response.data;
          this.title = data.title;
          this.content = data.content;
          this.recommendCount = data.recommendCount;
        }
      });
    },

    btn_back_onclick : function(){
      const router = this.$router;
      router.go(-1); //종토방으로
    },

    btn_recommend_onclick : function(){
      alert("추천기능 추가예정");
    },

    btn_revise_onclick : function(){
      /*const postId = this.$route.query.id
      var baseUrl = 'http://localhost:8080/board/post/' + postId;

      axios.patch(baseUrl)
          .then((response) => {
            if(response.status == 200) {
              const router = this.$router;
              router.go(-1); //종토방으로
            }
          });
          */
    },

    btn_remove_onclick : function(){
      const postId = this.$route.query.id
      var baseUrl = 'http://localhost:8080/board/post/' + postId;

      axios.delete(baseUrl)
          .then((response) => {
            if(response.status == 200) {
              const router = this.$router;
              router.go(-1); //종토방으로
            }
          });
    },
  }
};

</script>


