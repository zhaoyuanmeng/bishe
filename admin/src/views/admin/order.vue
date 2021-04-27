<template>
  <div>
    <p>
      <button v-on:click="list(1)" class="btn btn-white btn-default btn-round">
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
    <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>

    <table id="simple-table" class="table table-bordered table-hover">
      <thead>
        <tr>
          <th>订单id</th>
          <th>会员名</th>
          <th>课程名</th>
          <th>金额</th>
          <th>购买时间</th>
        </tr>
      </thead>

      <tbody v-if="orders.length>0">
        <tr v-for="order in orders">
          <td>{{order.id}}</td>
          <td>{{order.memberDto.name}}</td>
          <td>{{order.courseDto.name}}</td>
          <td>{{order.courseDto.price}}</td>
          <td>{{order.courseDto.updatedAt}}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import Pagination from "../../components/pagination";
export default {
  components: { Pagination },
  name: "business-order",
  data: function() {
    return {
      member: {},
      members: [],
      orders: [], //订单列表
      name: ""
    };
  },
  mounted: function() {
    let _this = this;
    _this.$refs.pagination.size = 5;
    _this.list(1);
    // sidebar激活样式方法一
    // this.$parent.activeSidebar("business-member-sidebar");
  },
  methods: {
    /**
     * 列表查询
     */
    list(page) {
      let _this = this;
      Loading.show();
      _this.$ajax
        .post(
          process.env.VUE_APP_SERVER + "/business/admin/memberCourse/lista",
          {
            page: page,
            size: _this.$refs.pagination.size
          }
        )
        .then(response => {
          Loading.hide();
          let resp = response.data;
          _this.orders = resp.content.list;
          console.log(_this.orders);
          _this.$refs.pagination.render(page, resp.content.total);
        });
    },
    /**
     * 会员查询
     */
    search() {
      let _this = this;
      Loading.show();
      let res = [];
      for (let i = 0; i < _this.orders.length; i++) {
        if (_this.orders[i].memberDto.name == _this.name) {
          res.push(_this.orders[i]);
        }
      }
      _this.orders = res;
      console.log("名字", this.name);
      console.log("搜索的结果", _this.orders);
      _this.name = "";
      Loading.hide();
    }
  }
};
</script>
