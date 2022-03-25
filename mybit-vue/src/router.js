import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from './views/Home.vue'
import MyCoin from './views/MyCoin.vue'
import DiscussionRoom from './views/DiscussionRoom.vue'
import NewPost from './views/NewPost.vue'


Vue.use(VueRouter);

const router = new VueRouter({  //eslint-disable-line no-unused-vars
    mode: "history",
    routes: [
        { path: "/", redirect : "/home"},
        { path: "/home", component : Home},
        { path: "/myCoin", component: MyCoin },
        { path: "/discussionRoom", component: DiscussionRoom },
        { path: "/newPost", name: 'newPost', component: NewPost }
    ]
})

export default router;
