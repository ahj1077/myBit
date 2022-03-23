<template>
  <div id="main">
    <NewPost/>
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
import NewPost from "@/components/layout/NewPost";

var data = {

}
export default {
  components: {NewPost, CoinList},
  data: function(){return data},
  created: function() {

  },
  beforeDestroy : function(){

  },
  methods : {
  }
};
</script>