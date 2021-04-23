<template>
  <main role="main">
    <div class="coursecont">
      <div class="coursepic tecti">
        <div class="teaimg">
          <img :src="teacher.image" width="150" />
        </div>
        <div class="teachtext">
          <h3>
            {{teacher.name}}&nbsp;&nbsp;
            <strong>{{teacher.position}}</strong>
          </h3>
          <h4>个人简介</h4>
          <p>{{teacher.intro}}</p>
        </div>
        <div class="clearh"></div>
      </div>

      <div class="clearh"></div>

      <div class="tcourselist">
        <h3 class="righttit" style="padding-left:50px;">在教课程</h3>
        <ul class="tcourseul">
          <li v-for="(course,index) in courses" :key="index">
            <span class="courseimg tcourseimg">
              <a href="#" target="_blank">
                <img width="230" :src="course.image" />
              </a>
            </span>
            <span class="tcoursetext">
              <h4>
                <a href="#" target="_blank" class="teatt">{{course.summary}}</a>
              </h4>
              <p class="teadec">{{course.name}}</p>
              <div class="btn-group">
          <router-link v-bind:to="'/detail?id=' + course.id" class="btn btn-outline-secondary">课程详情</router-link>
        </div>
            </span>
            <div style="height:0" class="clearh"></div>
          </li>
          <div class="clearh"></div>
        </ul>
      </div>

      <div class="clearh"></div>
    </div>
  </main>
</template>

<script>
export default {
  name: "tdetail",
  data: function() {
    return {
      id: "",
      courses: [],
      teacher: {}
    };
  },
  mounted() {
    let _this = this;
    _this.id = _this.$route.query.id; //老师id
    this.findTeacher(); //查询老师的内容
    this.findTeacherCourse(); //查询老师的课程
  },
  methods: {
    // 查询老师的详细内容
    findTeacher() {
      let _this = this;
      _this.$ajax
        .get(
          process.env.VUE_APP_SERVER +
            "/business/admin/teacher/findbyid/" +
            _this.id
        )
        .then(response => {
          let resp = response.data.content;
          this.teacher = resp;
          console.log("老师信息", this.teacher);
        });
    },

    // 查询老师的其他的课程
    findTeacherCourse() {
      let _this = this;
      _this.$ajax
        .get(
          process.env.VUE_APP_SERVER +
            "/business/admin/course/findbyteacher/" +
            _this.id
        )
        .then(response => {
          let resp = response.data.content;
          this.courses = resp;
          console.log("老师的课程信息", this.courses);
        });
    }
  }
};
</script>

<style scoped>
.coursepic.tecti {
  height: 200px;
}
</style>