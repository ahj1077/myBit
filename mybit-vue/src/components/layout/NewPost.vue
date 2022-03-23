<template>
  <div id="left">
    <div id="header" style="height: 10%; font-size: 20px">
      <div id="title">글쓰기</div>
    </div>
    <form @submit="checkForm" method="post">
      <div style="width: 100%; padding: 5% 5% 2% 5%;">
        <input type="text" v-model="title"  style="width: 100%;" placeholder="제목을 입력해주세요."/>
      </div>
      <div style="width: 100%; height: 500px; padding: 2% 5% 2% 5%;">
        <textarea style="width: 100%; height: 100%" v-model="content" placeholder="본문을 입력해주세요."></textarea>
      </div>
      <div style="padding-right: 5%">
        <span style="float: right">
          <span style="margin-right: 10px">
            <input type="button" id="btn_cancel" value="취소"/>
          </span>
          <span style="">
            <input type="submit" id="btn_save" value="등록"/>
          </span>
        </span>
      </div>
    </form>
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

#btn_cancel{
  background-color: #FFFFFF;
  font-size: 15px;
  color: #555555;
  text-decoration-line: none;
  border: 1px solid #FFFFFF;
  border-radius: 1px;
}

#btn_save{
  background-color: #0C66C6;
  font-size: 15px;
  color: #FFFFFF;
  text-decoration-line: none;
  border: 1px solid #0C66C6;
  border-radius: 1px;
}


</style>


<script>
import axios from "axios";

var data= {
  title : '',
  content : '',
}

export default {
  name: "newPost",
  components: {
  },
  data: function(){return data},
  created: function() {

  },
  mounted : function() {

  },
  beforeDestroy() {

  },
  methods : {
    checkForm : function(e){

      if(this.title.length === 0){
        alert("제목을 입력해 주세요.");
        e.preventDefault();
      }
      else if(this.content.length === 0){
        alert("내용을 입력해 주세요.");
        e.preventDefault();
      }

      this.createNewPost();
    },

    createNewPost : function(){
      var baseUrl = 'http://localhost:8080/board/newPost';
      let form = new FormData();
      form.append('title', this.title);
      form.append('content',this.content);

      axios.post(baseUrl, form)
      .then((response) => {
        console.log(response);
      });
    }
  }
};

</script>


