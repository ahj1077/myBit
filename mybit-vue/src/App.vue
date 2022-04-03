<template>
  <div id="app">
    <Header ref="header"/>
    <div id='content' class='content'>
     <!-- <router-link to='/'>Home</router-link>
      <router-link to='/about'> About</router-link>  -->
      <router-view></router-view> 
    </div>   
  </div>  
</template>

<script>
import Header from '@/components/layout/Header.vue';

export default {
  name: 'App',
  components: {
    Header,
  },
  methods : {
    updateLoginInfo : function(){
        this.$refs.header.checkLoginUser();
    },
  }
}
</script>

<style>
  #app{
    height: 200%;
    background-color: #151E2E;
  }
</style>
