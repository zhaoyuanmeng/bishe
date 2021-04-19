<template>
  <header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <div class="container">
        <a class="navbar-brand" href="#">
          <i class="ace-icon fa fa-video-camera"></i>&nbsp;来学吧
        </a>
        <button
          class="navbar-toggler"
          type="button"
          data-toggle="collapse"
          data-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
              <router-link class="nav-link" to="/">主页</router-link>
            </li>
            <li class="nav-item active">
              <router-link class="nav-link" to="/list">全部课程</router-link>
            </li>
            <li class="nav-item active">
              <router-link class="nav-link" to="/upload">留言反馈</router-link>
            </li>
            <li class="nav-item active">
              <router-link class="nav-link" to="/teacher">热门教师</router-link>
            </li>
            <li class="nav-item active">
              <router-link class="nav-link" to="/member">个人中心</router-link>
            </li>
            <li class="nav-item dropdown active">
              <a
                class="nav-link dropdown-toggle"
                href="#"
                id="navbarDropdown"
                role="button"
                data-toggle="dropdown"
                aria-haspopup="true"
                aria-expanded="false"
              >更多</a>
              <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                <a class="dropdown-item" href="/about">关于我们</a>
                <a class="dropdown-item" href="/teacher">热门讲师</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#">更多信息</a>
              </div>
            </li>
          </ul>
          <div class="search">
            <input type="text" name="condition" v-model="searchName" placeholder="请输入搜索的课程" />
            <!--<input type="submit" value="搜索" v-on:click="search()">-->
            <button v-on:click="search()">搜索</button>
          </div>
          <span v-show="loginMember.id" class="text-white pr-3">您好：{{loginMember.name}}</span>
          <button
            v-show="loginMember.id"
            v-on:click="logout()"
            class="btn btn-outline-light my-2 my-sm-0"
          >退出登录</button>
          <button
            v-show="!loginMember.id"
            v-on:click="openLoginModal()"
            class="btn btn-outline-light my-2 my-sm-0"
          >登录/注册</button>
        </div>
      </div>
    </nav>

    <the-login ref="loginComponent"></the-login>
  </header>
</template>
<style>
div.search {
  height: 30px;
  width: 300px;
  display: flex;
}
div.search form {
  width: 100%;
  height: 100%;
}
div.search form input:nth-child(2) {
  width: 400px;
  height: 100%;
  font-size: 16px;
  text-indent: 40px;
  border: none;
  float: left;
}
div.search form input:nth-child(3) {
  width: 100px;
  height: 100%;
  font-size: 16px;
  letter-spacing: 5px;
  border: none;
}
div.search form img {
  position: absolute;
  left: 50vw;
  transform: translateX(-250px);
  margin-top: 10px;
  margin-left: 10px;
  height: 20px;
}
</style>
<script>
import TheLogin from "./login";
export default {
  name: "theHeader",
  components: { TheLogin },
  data: function() {
    return {
      loginMember: {},
      searchName: " "
    };
  },
  mounted() {
    let _this = this;
    _this.loginMember = Tool.getLoginMember();
  },
  methods: {
    /**
     * 打开登录注册窗口
     */
    openLoginModal() {
      let _this = this;
      _this.$refs.loginComponent.openLoginModal();
    },

    setLoginMember(loginMember) {
      let _this = this;
      _this.loginMember = loginMember;
    },

    logout() {
      let _this = this;
      _this.$ajax
        .get(
          process.env.VUE_APP_SERVER +
            "/business/web/member/logout/" +
            _this.loginMember.token
        )
        .then(response => {
          let resp = response.data;
          if (resp.success) {
            Tool.setLoginMember(null);
            _this.loginMember = {};
            Toast.success("退出登录成功");
            _this.$router.push("/");
          } else {
            Toast.warning(resp.message);
          }
        });
    },

    // 搜索部分
    search() {
      let _this = this;
      console.log(_this.searchName);
      _this.$ajax
        .get(
          process.env.VUE_APP_SERVER +
            "/business/web/course/finds?searchName=" +
            _this.searchName
        )
        .then(response => {
          let resp = response.data;
          if (resp.success) {
            //   返回ID就行 直接跳转到detail/id
            if (resp.content != "") {
              Toast.success("查询成功");
              console.log(resp.content);
              _this.$router.push("detail?id=" + resp.content);
            } else {
              Toast.warning("查询失败");
            }
          } else {
            Toast.warning("查询失败");
          }
        });
      _this.searchName = "";
    }
  }
};
</script>
