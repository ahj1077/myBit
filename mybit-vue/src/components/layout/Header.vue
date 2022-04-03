<template>
    <div id="navbar">
        <div id="logo">
            <a href="/"><h3>MyBit</h3></a>
        </div>

        <div id="nav_list">
            <ul id="ul" style="width: 100%">
                <li><a href="/">거래소</a></li>
                <li><a href="/myCoin">나의 코인</a></li>
                <li><a href="/discussionRoom">종토방</a></li>
                <li style="float: right; margin-right: 15%" v-bind:class="{invisible : userId != null}"><router-link :to="{name : 'login'}">로그인</router-link></li>
                <li style="float: right;" v-bind:class="{invisible : userId == null}">
                  <span v-on:click="logout_onclick">로그아웃</span>
                </li>
                <li style="float: right;" v-bind:class="{invisible : userId == null}">
                    <span>{{nickName}}</span>
                </li>
            </ul>
        </div>
    </div>
</template>

<style>

    #navbar {       
        display: flex;
        align-items: center; 
        background-color: #093687;
        height: 60px;
        width: 100%;
    }

    #ul {
        list-style-type: none;
        margin: 0;
        padding: 0;
    }

    #logo {
        float: left;
        padding-left: 15%;
    }

    #logo a{
        color: #FFFFFF;
        text-decoration: none;
    }

    #ul li {
        display : inline;
    }

    #ul li *{
        margin-left: 20px;
        font-weight: bold;
        color: #FFFFFF;
        text-decoration: none;
    }

    #ul li a:hover{    
        color: #EEEEEE;
    }

    #nav_list {
        display: flex;
        align-items: center;
        margin-left: 3%;
        height: 100%;
        width: 100%;
    }

    .invisible {
      display: none;
    }

</style>

<script>
import axios from "axios";

var data = {
  userId : null,
  userName : null,
  nickName : null,
}

export default {
  name: "header",
  data : function(){
    return data;
  },
  created: function() {
    //로그인한 유저인지 체크
    this.checkLoginUser();
  },
  methods : {
    checkLoginUser : function(){
      axios.get('http://localhost:8080/login/check', { withCredentials: true })
          .then((result) => {
            if(result.status == 200) {
              this.userId = result.data.userId;
              this.userName = result.data.userName;
              this.nickName = result.data.nickName;
            }
          });
    },

    logout_onclick : function() {
      axios.get('http://localhost:8080/login/logout', { withCredentials: true })
          .then((result) => {
            if(result.status == 200){
              alert("로그아웃 되었습니다.");

              var router = this.$router;
              router.push('/'); //홈으로 redirect
            }
          });
    }
  }
};
</script>
