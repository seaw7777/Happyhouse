import Vue from "vue";
import Router from "vue-router";
import Home from "../views/Home.vue";

import Store from "../views/Store.vue";
import TodayNews from "../views/TodayNews.vue";

import Notice from "../views/notice/NoticeList.vue";
import NoticeDetail from "../views/notice/NoticeDetail.vue";
import NoticeWrite from "../views/notice/NoticeWrite.vue";
import NoticeModify from "../views/notice/NoticeModify.vue";

import CoronaClinic from "../views/corona/Clinic.vue";
import CoronaHospital from "../views/corona/Hospital.vue";

import AreaView from "../views/tradeinfo/AreaView.vue";
import HouseView from "../views/tradeinfo/HouseView.vue";

import MyRegion from "../views/myregion/Choose.vue";
import RegionView from "../views/myregion/RegionView.vue";

import MyPage from "../views/mypage/UserInfo.vue";
import UserInfoModify from "../views/mypage/UserInfoModify.vue";
import MemberList from "../views/mypage/MemberList.vue";

Vue.use(Router);

export default new Router({
  mode: "history",
  base: process.env.BASE_URL,
  routes: [
    {
      path: "/",
      name: "home",
      component: Home
    },
    {
      path: "/notice",
      name: "notice",
      component: Notice
    },
    {
      path: "/notice/detail",
      name: "NoticeDetail",
      component: NoticeDetail
    },
    {
      path: "/notice/write",
      name: "NoticeWrite",
      component: NoticeWrite
    },
    {
      path: "/notice/modify",
      name: "NoticeModify",
      component: NoticeModify
    },
    {
      path: "/todaynews",
      name: "news",
      component: TodayNews
    },
    {
      path: "/corona/clinic",
      name: "coronaclinic",
      component: CoronaClinic
    },
    {
      path: "/corona/hospital",
      name: "coronahospital",
      component: CoronaHospital
    },
    {
      path: "/apt",
      name: "AreaView",
      component: AreaView
    },
    {
      path: "/house",
      name: "HouseView",
      component: HouseView
    },
    {
      path: "/store",
      name: "StoreInfo",
      component: Store
    },
    {
      path: "/region",
      name: "MyRegion",
      component: MyRegion
    },
    {
      path: "/region/view/:type",
      name: "RegionView",
      component: RegionView
    },
    {
      path: "/admin/list",
      name: "MemberList",
      component: MemberList
    },
    {
      path: "/mypage/userinfo",
      name: "MyPage",
      component: MyPage
    },
    {
      path: "/mypage/userinfomodify",
      name: "UserInfoModify",
      component: UserInfoModify
    }
  ]
});
