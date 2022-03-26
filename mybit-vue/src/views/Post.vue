<template>
  <div id="main">
    <Post/>
    <CoinList/>
  </div>
</template>

<style scoped>
#main {
  display: flex;
  align-items:flex-start;
  height: 70%;
}

</style>

<script>
import CoinList from "@/components/layout/CoinList.vue";
import Post from "@/components/layout/Post";

var data = {

}
export default {
  name: 'post',
  components: {Post, CoinList},
  data: function(){return data},
  created: function() {

  },
  beforeDestroy : function(){

  },
  methods : {
  }
};
</script>