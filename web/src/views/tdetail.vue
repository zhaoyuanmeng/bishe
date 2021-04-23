<template>
  <main role="main">
    <p>{{id}}</p>
  </main>
</template>

<script>
export default {
  name: "tdetail",
  data: function() {
    return {
      id: "",
      course: {},
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
          let resp = response.data;
          console.log("老师信息", response);
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
          let resp = response.data;
          console.log("老师的课程信息", response);
        });
    },
  }
};
</script>

<style scoped>
</style>