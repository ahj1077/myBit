import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from './views/Home.vue'
import MyCoin from './views/MyCoin.vue'
import DiscussionRoom from './views/DiscussionRoom.vue'


Vue.use(VueRouter);

const router = new VueRouter({  //eslint-disable-line no-unused-vars
    mode: "history",
    routes: [
        { path: "/", component: Home },
        { path: "/myCoin", component: MyCoin },
        { path: "/DiscussionRoom", component: DiscussionRoom }
    ]
})

export default router;
