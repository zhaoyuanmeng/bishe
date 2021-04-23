import Vue from "vue"
import Router from "vue-router"
import Index from "./views/index.vue"
import List from "./views/list.vue"
import Detail from "./views/detail.vue"
import Upload from "./views/upload.vue"
import Member from "./views/member.vue"
import About from "./views/about.vue"
import Teacher from "./views/teacher.vue"
import Tdetail from "./views/tdetail.vue"


Vue.use(Router);

export default new Router({
    mode: "history",
    base: process.env.BASE_URL,
    routes: [{
        path: "*",
        redirect: "/index",
    }, {
        path: "/index",
        component: Index
    }, {
        path: "/list",
        component: List
    }, {
        path: "/detail",
        component: Detail
    },
    {
        path: "/upload",
        component: Upload
    },
    {
        path: "/member",
        component: Member
    },
    {
        path: "/about",
        component: About
    },
    {
        path: "/teacher",
        component: Teacher
    },
    {
        path: "/tdetail",
        component: Tdetail
    }]
})
