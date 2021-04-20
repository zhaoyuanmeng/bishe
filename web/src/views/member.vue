<template>
  <main role="main">
    <!--个人中心  -->
    <div class="membertab">
      <div class="memblist">
        <div class="membhead">
          <div style="text-align:center;">
            <img :src="member.photo" width="80" />
          </div>
          <div style="width:220px;text-align:center;">
            <p class="membUpdate mine">{{member.name}}</p>
            <p class="membUpdate mine zyd">
              <button v-on:click="add()" class="btn btn-white btn-default btn-round">
                <i class="ace-icon fa fa-edit"></i>
                修改个人信息
              </button>
            </p>
            <!--下划线 -->
            <!-- <div class="clearh"></div> -->
          </div>
        </div>
        <!-- 导航栏这里先不要 -->
        <!-- <div class="memb">
          <ul>
            <li class="currnav">
              <a class="mb1" href="mycourse.html">我的课程</a>
            </li>
            <li>
              <a class="mb3" href="myask.html">我的问答</a>
            </li>
            <li>
              <a class="mb4" href="mynote.html">我的笔记</a>
            </li>
            <li>
              <a class="mb12" href="myhomework.html">我的作业</a>
            </li>
            <li>
              <a class="mb2" href="training_list.html" target="_blank">我的题库</a>
            </li>
          </ul>
        </div>-->
      </div>

      <div class="membcont">
        <h3 class="mem-h3">我的课程</h3>
        <div class="box demo2" style="width:820px;">
          <ul class="tab_menu" style="margin-left:30px;">
            <li class="current">学习中</li>
          </ul>
          <div class="tab_box">
            <div v-for="o in courses" class="col-md-4">
              <the-course v-bind:course="o"></the-course>
            </div>
          </div>
        </div>
      </div>

      <div class="clearh"></div>
    </div>

    <!--  -->
    <!-- <div class="content">
      <p>1、用户名</p>
      <p>2、头像</p>
      <button v-on:click="add()" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-edit"></i>
        修改个人信息
      </button>
    </div>-->
    <!-- 存放那些报名过的课程 -->
    <!-- <div class="album py-5 bg-light">
      <div class="container">
        <div>
          <p>报名过的课程</p>
          <div class="row">
            <div v-for="o in courses" class="col-md-4">
              <the-course v-bind:course="o"></the-course>
            </div>
          </div>
          <h3 v-show="courses.length === 0">课程还未上架</h3>
        </div>
      </div>
    </div>-->
    <!-- end -->

    <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
            <h4 class="modal-title">更改个人信息</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <div class="form-group">
                <label class="col-sm-2 control-label">姓名</label>
                <div class="col-sm-10">
                  <input v-model="upload.name" class="form-control" />
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">手机号</label>
                <div class="col-sm-10">
                  <input v-model="upload.mobile" class="form-control" />
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">头像</label>
                <div class="col-sm-10">
                  <big-file
                    v-bind:input-id="'image-upload'"
                    v-bind:text="'上传头像'"
                    v-bind:suffixs="['jpg', 'jpeg', 'png']"
                    v-bind:use=" FILE_USE.FANKUI.key"
                    v-bind:after-upload="afterUpload"
                  ></big-file>
                  <!--<div v-show="upload.image" class="row">-->
                  <!--<div class="col-md-4">-->
                  <!--<img v-bind:src="upload.image" class="img-responsive">-->
                  <!--</div>-->
                  <!--</div>-->
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            <button v-on:click="save()" type="button" class="btn btn-primary">保存</button>
          </div>
        </div>
        <!-- /.modal-content -->
      </div>
      <!-- /.modal-dialog -->
    </div>
    <!-- /.modal -->
  </main>
</template>

<script>
import Pagination from "../../.././admin/src/components/pagination";
import File from "../../.././admin/src/components/file";
import BigFile from ".././components/big-file";
import TheCourse from "../components/the-course";
import "../.././public/static/js/progress";
export default {
  components: { Pagination, File, BigFile, TheCourse },
  name: "business-teacher",
  data: function() {
    return {
      upload: {},
      FILE_USE: FILE_USE,
      key: "AK",
      member: {},
      courses: [
        // {
        //   id: "CJiw6X1S",
        //   image:
        //     "http://zydhy.oss-cn-beijing.aliyuncs.com/course/19xGddgWsYUEImuQSOM6i6.jpg",
        //   name: "123",
        //   level: "",
        //   summary: "",
        //   enroll: ""
        // },
        // {
        //   id: "CJiw6X1S",
        //   image:
        //     "http://zydhy.oss-cn-beijing.aliyuncs.com/course/19xGddgWsYUEImuQSOM6i6.jpg",
        //   name: "123",
        //   level: "",
        //   summary: "",
        //   enroll: ""
        // }
      ]
    };
  },
  mounted: function() {
    //            let _this = this;
    //            _this.$refs.pagination.size = 5;
    //            _this.list(1);
    // sidebar激活样式方法一
    // this.$parent.activeSidebar("business-teacher-sidebar");
    this.getCourseList();
  },
  methods: {
    /**
     * 点击【新增】
     */
    add() {
      let _this = this;
      _this.upload = {};
      $("#form-modal").modal("show");
    },

    /**
     * 点击【编辑】
     */
    edit(teacher) {
      let _this = this;
      _this.teacher = $.extend({}, teacher);
      $("#form-modal").modal("show");
    },

    /**
     * 列表查询
     */
    list(page) {
      let _this = this;
      Loading.show();
      _this.$ajax
        .post(process.env.VUE_APP_SERVER + "/business/admin/teacher/list", {
          page: page,
          size: _this.$refs.pagination.size
        })
        .then(response => {
          Loading.hide();
          let resp = response.data;
          _this.teachers = resp.content.list;
          _this.$refs.pagination.render(page, resp.content.total);
        });
    },

    /**
     * 点击【保存】
     */
    save() {
      let _this = this;
      let loginMember = Tool.getLoginMember();
      _this.upload.id = loginMember.id;
      //                Loading.show();
      _this.$ajax
        .post(
          process.env.VUE_APP_SERVER + "/business/web/member/updateMemberInfo",
          _this.upload
        )
        .then(response => {
          //                    Loading.hide();
          let resp = response.data;
          if (resp.success) {
            $("#form-modal").modal("hide");
            Toast.success("上传成功");
          } else {
            $("#form-modal").modal("hide");
            Toast.success("上传成功");
          }
        });
    },

    afterUpload(resp) {
      let _this = this;
      console.log("sssssssssssss" + resp.content.path);
      let image = resp.content.path;
      _this.upload.image = image;
      _this.upload.photo = image;

      _this.upload.file = resp.content.path;
    },

    // 获取报名参加的课程列表
    // 参数是用户名id
    getCourseList(id) {
      let _this = this;
      let loginMember = Tool.getLoginMember();
      _this.member = loginMember;
      if (Tool.isEmpty(loginMember)) {
        Toast.warning("请先登录");
        return;
      }
      console.log("会员信息", loginMember);
      _this.$ajax
        .get(
          process.env.VUE_APP_SERVER +
            "/business/web/member-course/getMemberCourse/" +
            loginMember.id
        )
        .then(response => {
          let resp = response.data;
          _this.courses = resp.map(item => {
            item.id = item.courseId;
            return item;
          });

          console.log("课程列表---", _this.courses);
        });
    }

    // 获取个人的信息列表
  }
};
</script>

<style>
.mem-h3 {
  line-height: 20px;
}
.membUpdate.mine.zyd {
  height: 70px;
}
.content {
  width: 800px;
  height: 200px;
  margin: 50px auto;
  /*background-color: #0B61A4;*/
}
.modal {
  margin-top: 100px;
}
.modal-title {
  margin: 0px;
  position: relative;
  left: -320px;
}
.close {
  margin: 0px;
  position: relative;
  left: 130px;
}
</style>
