<template>
  <main role="main">
    <!-- Preloader -->
    <!-- <section class="loading-overlay">
        <div class="Loading-Page">
            <h2 class="loader">Loading</h2>
        </div>
    </section>-->
    <section class="flat-row bg-theme pd-top-100 wrap-our-teacher">
      <div class="container">
        <div class="row">
          <!-- 模板 -->
          <div class="col-md-3 col-sm-6" v-for="(teacher,index) in teachers" :key="index">
            <div class="flat-teammember">
              <div class="team-image">
                <img alt="image" :src="teacher.image" />
              </div>
              <div class="profile">
                <h6 class="name">
                  <!-- <a href="#">{{teacher.name}}</a> -->
                  <router-link v-bind:to="'/tdetail?id=' + teacher.id" class="btn btn-outline-secondary">{{teacher.name}}</router-link>
                </h6>
                <p class="position">{{teacher.position}}</p>
              </div>
              <ul class="flat-socials">
                <li>
                  <a href="#">
                    <i class="fa fa-facebook"></i>
                  </a>
                </li>
                <li>
                  <a href="#">
                    <i class="fa fa-twitter"></i>
                  </a>
                </li>
                <li>
                  <a href="#">
                    <i class="fa fa-google-plus"></i>
                  </a>
                </li>
                <li>
                  <a href="#">
                    <i class="fa fa-pinterest"></i>
                  </a>
                </li>
              </ul>
            </div>
            <!-- flat-teammember -->
          </div>
          <!-- col.md-3 -->
        </div>
        <div class="row">
          <div class="dividers h50"></div>
        </div>
        <div class="row">
          <div class="col-md-12">
            <div class="blog-pagination">
              <ul class="flat-pagination">
                <li>
                  <a href="#">
                    <i class="fa fa-long-arrow-left"></i>
                  </a>
                </li>
                <li class="active">
                  <a href="#">1</a>
                </li>
                <li>
                  <a href="#">2</a>
                </li>
                <li>
                  <a href="#">3</a>
                </li>
                <li>
                  <a href="#">4</a>
                </li>
                <li>
                  <a href="#">5</a>
                </li>
                <li>
                  <a href="#">
                    <i class="fa fa-long-arrow-right"></i>
                  </a>
                </li>
              </ul>
            </div>
            <!-- /.blog-pagination -->
          </div>
        </div>
      </div>
    </section>
  </main>
</template>

<script>
export default {
  data() {
    return {
      teachers: [] //老师列表
    };
  },
  methods: {
    // 获取老师列表
    list() {
      let _this = this;
      _this.$ajax
        .post(process.env.VUE_APP_SERVER + "/business/admin/teacher/all")
        .then(response => {
          // Loading.hide();
          let resp = response.data.content;
          _this.teachers = resp;
          console.log(resp);
        });
    }
  },
  mounted() {
    // 获取到老师列表
    this.list();
  }
};
</script>

<style scoped>
</style>