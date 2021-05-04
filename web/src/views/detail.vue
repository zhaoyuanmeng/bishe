<template>
  <main role="main">
    <div class="album py-5 bg-light">
      <div class="container">
        <div class="row course-head">
          <div class="col-sm-6" id="cover-video-div">
            <img class="img-fluid" v-bind:src="course.image" />
          </div>
          <div class="col-sm-6">
            <h1>{{course.name}}</h1>
            <p class="course-head-item">
              <span>
                <i class="fa fa-clock-o"></i>
                {{(course.time) | formatSecond}}
              </span>
              <span>{{COURSE_LEVEL | optionKV(course.level)}}</span>
              <span>
                <i class="fa fa-user"></i>
                {{course.enroll}}
              </span>
            </p>
            <p class="course-head-desc">{{course.summary}}</p>
            <p class="course-head-price">
              <span class="price-now text-danger">
                <i class="fa fa-yen"></i>
                &nbsp;{{course.price}}&nbsp;&nbsp;
              </span>
            </p>
            <p class="course-head-button-links">
              <a
                v-show="!memberCourse.id"
                v-on:click="enroll1()"
                class="btn btn-lg btn-primary btn-shadow"
                href="javascript:;"
              >立即报名</a>
              <a
                v-show="memberCourse.id"
                href="#"
                class="btn btn-lg btn-success btn-shadow disabled"
              >您已报名</a>
            </p>
          </div>
        </div>

        <div class="row">
          <!-- 课程内容 & 大章小节 & 课程评价-->
          <div class="col-md-9">
            <!-- Nav tabs -->
            <ul class="nav nav-tabs">
              <li class="nav-item">
                <a class="nav-link active" href="#info" data-toggle="tab">课程介绍</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#chapter" data-toggle="tab">章节目录</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#comment" data-toggle="tab">课程评价</a>
              </li>
            </ul>

            <br />

            <!-- Tab panes -->
            <div class="tab-content">
              <div class="tab-pane active" id="info" v-html="course.content"></div>
              <div class="tab-pane" id="chapter">
                <div v-for="(chapter, i) in chapters" class="chapter">
                  <div v-on:click="doFolded(chapter, i)" class="chapter-chapter">
                    <span>{{chapter.name}}</span>
                    <span class="pull-right">
                      <i v-show="chapter.folded" class="fa fa-plus-square" aria-hidden="true"></i>
                      <i v-show="!chapter.folded" class="fa fa-minus-square" aria-hidden="true"></i>
                    </span>
                  </div>
                  <div v-show="!chapter.folded">
                    <table class="table table-striped">
                      <tr v-for="(s, j) in chapter.sections" class="chapter-section-tr">
                        <td class="col-sm-8 col-xs-12">
                          <div v-on:click="play(s)" class="section-title">
                            <i class="fa fa-video-camera d-none d-sm-inline"></i>&nbsp;&nbsp;
                            <span class="d-none d-sm-inline">第{{j+1}}节&nbsp;&nbsp;</span>
                            {{s.title}}
                            <span
                              v-show="s.charge !== SECTION_CHARGE.CHARGE.key"
                              class="badge badge-primary hidden-xs"
                            >免费</span>
                          </div>
                        </td>
                        <td class="col-sm-1 text-right">
                          <span class="badge badge-primary">{{s.time | formatSecond}}</span>
                        </td>
                      </tr>
                    </table>
                  </div>
                </div>
              </div>
              <!--评论信息-->
              <div class="tab-pane" id="comment">
                <div class="comments">
                  <!-- 发布评论 -->
                  <div class="comment-wrap">
                    <div class="photo">
                      <div class="avatar" :style="{'backgroundImage': 'url(' + member.photo + ')'}"></div>
                    </div>
                    <div class="comment-block">
                      <textarea
                        name
                        id
                        cols="30"
                        rows="3"
                        placeholder="Say somthing..."
                        v-model="content"
                      ></textarea>
                      <div class="con">
                        <input type="range" name="star" min="0" max="5" v-model="pf" />
                      </div>
                      <!-- <input type="submit" value="提交" @click="sendComment" /> -->
                      <button @click="sendComment">发送</button>
                    </div>
                  </div>
                  <div v-if=" content_arr.length == 0">暂时没有评论</div>
                  <!-- 评论列表 -->
                  <div class="comment-wrap" v-else v-for="(item,index) in content_arr" :key="index">
                    <div class="photo">
                      <div
                        class="avatar"
                        style="background-image: url('https://img1.baidu.com/it/u=1485012388,2380514454&fm=26&fmt=auto&gp=0.jpg')"
                      ></div>
                    </div>
                    <div class="comment-block">
                      <p class="comment-text">{{item.content}}</p>
                      <div class="bottom-comment">
                        <div class="comment-date">{{item.createTime}}</div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <!-- 讲师信息 -->
          <div class="col-md-3">
            <div class="card" style="width: 18rem;">
              <img v-bind:src="teacher.image" class="card-img-top" />
              <div class="card-body">
                <h5 class="card-title">{{teacher.name}}</h5>
                <p class="card-text">{{teacher.nickname}}</p>
                <p class="card-text">{{teacher.intro}}</p>
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-9"></div>
          <!-- 相关课程信息 -->
          <!-- <div class="col-md-3">
            <div class="card" style="width: 18rem;">
              <h5 class="card-title" style="text-align:center;">相关课程</h5>
              <div class="card-body">
                <p class="card-text">签名：{{teacher.motto}}</p>
                <p class="card-text">介绍：{{teacher.intro}}</p>
              </div>
            </div>
          </div>-->
        </div>
      </div>
    </div>

    <modal-player ref="modalPlayer"></modal-player>

    <!-- 新加的订单支付弹出层 -->
    <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
            <h4 class="modal-title">报名窗口</h4>
          </div>
          <div class="modal-body">确定要报名吗</div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            <button v-on:click="save()" type="button" class="btn btn-primary">确定</button>
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
import ModalPlayer from "../components/modal-player";
export default {
  name: "detail",
  components: { ModalPlayer },
  data: function() {
    return {
      id: "",
      course: {},
      teacher: {},
      chapters: [],
      sections: [],
      conent: "", //评论内容
      content_arr: [], //评论列表
      pf: 0,
      memberCourse: {},
      member: {},
      COURSE_LEVEL: COURSE_LEVEL,
      SECTION_CHARGE: SECTION_CHARGE
    };
  },
  mounted() {
    let _this = this;
    _this.id = _this.$route.query.id; //课程id
    _this.findCourse();
    _this.getComment(); //获取课程评论
  },
  methods: {
    findCourse() {
      let _this = this;
      _this.$ajax
        .get(
          process.env.VUE_APP_SERVER + "/business/web/course/find/" + _this.id
        )
        .then(response => {
          let resp = response.data;
          _this.course = resp.content;
          _this.teacher = _this.course.teacher || {};
          _this.chapters = _this.course.chapters || [];
          _this.sections = _this.course.sections || [];

          // 获取报名信息
          _this.getEnroll();

          // 将所有的节放入对应的章中
          for (let i = 0; i < _this.chapters.length; i++) {
            let c = _this.chapters[i];
            c.sections = [];
            for (let j = 0; j < _this.sections.length; j++) {
              let s = _this.sections[j];
              if (c.id === s.chapterId) {
                c.sections.push(s);
              }
            }

            Tool.sortAsc(c.sections, "sort");
          }
        });
    },

    /**
     * 展开/收缩一个章节
     * @param chapter
     */
    doFolded(chapter, i) {
      let _this = this;
      chapter.folded = !chapter.folded;
      // 在v-for里写v-show，只修改属性不起作用，需要$set
      _this.$set(_this.chapters, i, chapter);
    },

    /**
     * 播放视频
     * @param section
     */
    play(section) {
      let _this = this;
      if (section.charge === _this.SECTION_CHARGE.CHARGE.key) {
        let loginMember = Tool.getLoginMember();
        if (Tool.isEmpty(loginMember)) {
          Toast.warning("请先登录");
          return;
        } else {
          if (Tool.isEmpty(_this.memberCourse)) {
            Toast.warning("请先报名");
            return;
          }
        }
      }
      _this.$refs.modalPlayer.playVod(section.vod);
    },

    /**
     * 报名
     */
    enroll() {
      let _this = this;
      let loginMember = Tool.getLoginMember();
      if (Tool.isEmpty(loginMember)) {
        Toast.warning("请先登录");
        return;
      }
      _this.$ajax
        .post(
          process.env.VUE_APP_SERVER + "/business/web/member-course/enroll",
          {
            courseId: _this.course.id,
            memberId: loginMember.id
          }
        )
        .then(response => {
          let resp = response.data;
          if (resp.success) {
            _this.memberCourse = resp.content;

            // 这里面加个弹出框
            Toast.success("报名成功！");
          } else {
            Toast.warning(resp.message);
          }
        });
    },
    // 点击就触发弹出层
    enroll1() {
      $("#form-modal").modal("show");
    },

    // 新家的订单弹出层
    /**
     * 点击【保存】
     */
    save() {
      $("#form-modal").modal("hide");
      // 调用报名接口
      this.enroll();
    },

    /**
     * 获取报名
     */
    getEnroll() {
      let _this = this;
      let loginMember = Tool.getLoginMember();
      if (Tool.isEmpty(loginMember)) {
        console.log("未登录");
        return;
      }
      _this.$ajax
        .post(
          process.env.VUE_APP_SERVER + "/business/web/member-course/get-enroll",
          {
            courseId: _this.course.id,
            memberId: loginMember.id
          }
        )
        .then(response => {
          let resp = response.data;
          if (resp.success) {
            _this.memberCourse = resp.content || {};
          }
        });
    },

    // 评论相关的

    // 获取评论
    getComment() {
      let _this = this;
      let loginMember = Tool.getLoginMember();
      _this.member = loginMember;
      console.log("登录信息", loginMember);
      if (Tool.isEmpty(loginMember)) {
        console.log("未登录");
        return;
      }
      _this.$ajax
        .get(
          process.env.VUE_APP_SERVER +
            "/business/web/course/comment-find/" +
            _this.id
        )
        .then(response => {
          let resp = response.data;
          _this.content_arr = resp;
          console.log("评论列表----", resp);
        });
    },
    // 添加评论
    sendComment() {
      var _this = this;
      let loginMember = Tool.getLoginMember();
      // 获取评分
      console.log("评分", _this.pf);
      if (Tool.isEmpty(loginMember)) {
        console.log("未登录");
        return;
      }
      console.log("发送的内容----", this.conent);
      _this.$ajax
        .post(process.env.VUE_APP_SERVER + "/business/web/course/comment-add", {
          courseId: _this.course.id,
          memberId: loginMember.id,
          name: loginMember.name,
          content: _this.content,
          pf: _this.pf
        })
        .then(response => {
          let resp = response.data;
          // 重新调用一下
          _this.getComment();
          console.log("发送评论----", resp);
        });
    }
  }
};
</script>

<style>
/* 评分 */
.con {
  /* display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column; */
}
input[type="range"] {
  -webkit-appearance: none;
  width: 100px;
  margin: 0;
  outline: 0;
}
input[type="range" i]::-webkit-slider-runnable-track {
  background: coral;
  height: 20px;
  -webkit-mask: url("data:image/svg+xml,%3Csvg width='12' height='11' viewBox='0 0 12 11' fill='none' xmlns='http://www.w3.org/2000/svg'%3E%3Cpath d='M6 0l1.693 3.67 4.013.476L8.74 6.89l.788 3.964L6 8.88l-3.527 1.974.788-3.964L.294 4.146l4.013-.476L6 0z' fill='%23F67600'/%3E%3C/svg%3E");
  -webkit-mask-size: 20px;
  -webkit-mask-repeat: repeat-x;
}
input[type="range" i]::-webkit-slider-thumb {
  -webkit-appearance: none;
  width: 0;
  height: 100%;
  box-shadow: 999px 0px 0px 999px #e8eaed;
}

/* 课程信息 */
.course-head {
}
.course-head h1 {
  font-size: 2rem;
  margin-bottom: 1.5rem;
}
.course-head-item span {
  margin-right: 1rem;
}
.course-head-desc {
  font-size: 1rem;
  color: #555;
}
.course-head a {
}
.course-head-price {
  font-size: 2rem;
}
@media (max-width: 700px) {
  .course-head h1 {
    font-size: 1.5rem;
  }
}

/* 章节列表 */
.chapter {
  padding-bottom: 1.25rem;
}
.chapter-chapter {
  font-size: 1.25rem;
  padding: 1.25rem;
  background-color: #23527c;
  color: white;
  cursor: pointer;
}
.chapter-section-tr {
  font-size: 1rem;
}
.chapter-section-tr td {
  padding: 1rem 1.25rem;
  vertical-align: middle;
}
/*鼠标手势*/
.chapter-section-tr td .section-title {
  color: #555;
}
.chapter-section-tr td .section-title:hover {
  color: #23527c;
  font-weight: bolder;
  cursor: pointer;
}
/*行头小图标*/
.chapter-section-tr td .section-title i {
  color: #2a6496;
}
@media (max-width: 700px) {
  .chapter-chapter {
    font-size: 1.2rem;
  }
  .chapter-section-tr {
    font-size: 0.9rem;
  }
}

/* 评论相关的 */
input,
textarea {
  outline: none;
  border: none;
  display: block;
  margin: 0;
  padding: 0;
  -webkit-font-smoothing: antialiased;
  font-family: "PT Sans", "Helvetica Neue", "Helvetica", "Roboto", "Arial",
    sans-serif;
  font-size: 1rem;
  color: #555f77;
}
input::-webkit-input-placeholder,
textarea::-webkit-input-placeholder {
  color: #ced2db;
}
input::-moz-placeholder,
textarea::-moz-placeholder {
  color: #ced2db;
}
input:-moz-placeholder,
textarea:-moz-placeholder {
  color: #ced2db;
}
input:-ms-input-placeholder,
textarea:-ms-input-placeholder {
  color: #ced2db;
}
p {
  line-height: 1.3125rem;
}
.comments {
  margin: 2.5rem auto 0;
  max-width: 60.75rem;
  padding: 0 1.25rem;
}
.comment-wrap {
  margin-bottom: 1.25rem;
  display: table;
  width: 100%;
  min-height: 5.3125rem;
}
.photo {
  padding-top: 0.625rem;
  display: table-cell;
  width: 3.5rem;
}
.photo .avatar {
  height: 2.25rem;
  width: 2.25rem;
  border-radius: 50%;
  background-size: contain;
}
.comment-block {
  padding: 1rem;
  background-color: #fff;
  display: table-cell;
  vertical-align: top;
  border-radius: 0.1875rem;
  box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.08);
}
.comment-block textarea {
  width: 100%;
  max-width: 100%;
}
.comment-text {
  margin-bottom: 1.25rem;
}
.bottom-comment {
  color: #acb4c2;
  font-size: 0.875rem;
}
.comment-date {
  float: left;
}
.comment-actions {
  float: right;
}
.comment-actions li {
  display: inline;
}
.comment-actions li.complain {
  padding-right: 0.625rem;
  border-right: 1px solid #e1e5eb;
}
.comment-actions li.reply {
  padding-left: 0.625rem;
}
</style>
