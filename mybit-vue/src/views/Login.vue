<template>
  <div id="main">
    <div id="top" style="height : 30%"></div>
    <div style="height : 50%">

      <div id="tbx_login" style="text-align: center; margin-bottom: 20px">로그인</div>

      <div><input type="text" v-model="userId" style="margin-bottom: 10px" placeholder="아이디"></div>
      <div><input type="password" v-model="password" id="ibx_password" placeholder="비밀번호" style="margin-bottom: 10px"></div>

      <button class="btn" v-on:click="btn_login_onclick">로그인</button>
      <button class="btn" v-on:click="btn_signUp_onclick">회원가입</button>
    </div>
    <div style="height : 20%"></div>
  </div>
</template>

<style scoped>

#main {
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #FFFFFF;
  height: 600px;
}

#tbx_login{
  color: #000000;
  font-size: 35px;
}

.btn{
  width: 100%;
  color: #FFFFFF;
  background-color: #0C66C6;
  border: 1px solid #0C66C6;
  margin-top: 10px;
}

</style>

<script>

import axios from "axios";

var data = {
  userId : '',
  password : '',

  errors : [],
}
export default {
  name: 'login',
  components: {},
  data: function(){return data},
  created: function() {

  },
  beforeDestroy : function(){

  },

  methods : {
    btn_login_onclick : function(){

      if(this.userId == '' || this.userId == null){
        this.errors.push("아이디를 입력해 주세요.");
      }

      if(this.password == '' || this.password == null){
        this.errors.push("비밀번호를 입력해 주세요.");
      }

      if(this.errors.length > 0){
          alert(this.errors[0]);
          this.errors = [];
          return;
      }

      this.doLogin();
    },

    doLogin : function(){
      var baseUrl = 'http://localhost:8080/login';

      let form = new FormData();
      form.append('userId', this.userId);
      form.append('password', this.password);

      axios.post(baseUrl, form, { withCredentials: true })
          .then((response) => {
            if(response.status == 200) {
              //console.log(response);
              const router = this.$router;
              router.push('/home'); //차트화면으로
            }
          })
          .catch((error) => {
            if(error.response){
              alert(error.response.data);
            }
          });

    },

    btn_signUp_onclick : function(){
      alert("sign up");
    },
  }
};
</script>