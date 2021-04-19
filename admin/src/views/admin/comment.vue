<template>
  <div>
    <p>
      &nbsp;
      <button v-on:click="list()" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-refresh"></i>
        刷新
      </button>
    </p>
    <!-- 搜索 -->
    <div class="nav-search" id="nav-search">
      <form class="form-search">
        <span class="input-icon">
          <input
            type="text"
            placeholder="Search ..."
            class="nav-search-input"
            id="nav-search-input"
            v-model="name"
            autocomplete="off"
          />
          <i class="ace-icon fa fa-search nav-search-icon" @click="search"></i>
        </span>
      </form>
    </div>
    <!-- /.nav-search -->

    <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>

    <table id="simple-table" class="table table-bordered table-hover">
      <thead>
        <tr>
          <th>id</th>
          <th>课程名</th>
          <th>用户名</th>
          <th>评论内容</th>
          <th>操作内容</th>
        </tr>
      </thead>

      <tbody>
        <tr v-for="(comment,index) in comments" :key="index">
          <td>{{comment.id}}</td>
          <td>{{comment.courseId}}</td>
          <td>{{comment.name}}</td>
          <td>{{comment.content}}</td>
          <td>
            <div class="hidden-sm hidden-xs btn-group">
              <button v-on:click="del(comment.id)" class="btn btn-xs btn-danger">
                <i class="ace-icon fa fa-trash-o bigger-120"></i>
              </button>
            </div>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import Pagination from "../../components/pagination";
export default {
  components: { Pagination },
  name: "system-comment",
  data: function() {
    return {
      comments: [],
      name: "" //要搜索的名字
    };
  },
  mounted: function() {
    let _this = this;
    _this.list();
    // sidebar激活样式方法一
    // this.$parent.activeSidebar("system-role-sidebar");
  },
  methods: {
    /**
     * 列表查询
     */
    list() {
      let _this = this;
      Loading.show();
      _this.$ajax
        .get(
          process.env.VUE_APP_SERVER + "/business/web/course/comment/findall"
        )
        .then(response => {
          Loading.hide();
          let resp = response.data;
          this.comments = resp;
          console.log("评论列表", resp);
        });
    },

    /**
     * 点击【删除】
     */
    del(id) {
      let _this = this;
      Confirm.show("删除评论不可恢复，确认删除？", function() {
        Loading.show();
        _this.$ajax
          .get(
            process.env.VUE_APP_SERVER +
              "/business/web/course/comment/delete/" +
              id
          )
          .then(response => {
            Loading.hide();
            let resp = response.data;
            console.log("删除的结果", resp);
            if (resp.success) {
              _this.list();
              Toast.success("删除成功！");
            }
          });
      });
    },

    // 搜索
    search(name) {
      let _this = this;
      console.log(this.name);
      Loading.show();
      _this.$ajax
        .get(
          process.env.VUE_APP_SERVER +
            "/business/web/course/comment/findbyname/" +
            _this.name
        )
        .then(response => {
          Loading.hide();
          let resp = response.data;
          this.comments = resp;
          console.log("搜索列表", response);
        });
      _this.name = "";
    }
  }
};
</script>