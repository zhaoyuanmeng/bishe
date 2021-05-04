<template>
  <main role="main">
    <div class="header-nav">
      <div class="clearfix">
        <div class="container">
          <div class="row">
            <div class="col-12">
              <a
                v-on:click="onClickLevel1('00000000')"
                id="category-00000000"
                href="javascript:;"
                class="cur"
              >全部</a>
              <a
                v-for="o in level1"
                v-on:click="onClickLevel1(o.id)"
                v-bind:id="'category-' + o.id"
                href="javascript:;"
              >{{o.name}}</a>
            </div>
          </div>
        </div>
      </div>
      <!-- 搜索框 -->
      <div class="search">
        <input type="text" v-model="searchName" placeholder="请输入搜索的课程" />
        <div v-on:click="search()" class="search-btn">搜索</div>
      </div>
    </div>
    <div class="skill clearfix">
      <div class="container">
        <div class="row">
          <div class="col-12">
            <a
              v-on:click="onClickLevel2('11111111')"
              id="category-11111111"
              href="javascript:;"
              class="on"
            >不限</a>
            <a
              v-for="o in level2"
              v-on:click="onClickLevel2(o.id)"
              v-bind:id="'category-' + o.id"
              href="javascript:;"
            >{{o.name}}</a>

            <div style="clear:both"></div>
          </div>
        </div>
      </div>
    </div>
    <div class="album py-5 bg-light">
      <div class="btns">
        <button @click="DescTime">时间降序</button>
        <button @click="AscTime">时间升序</button>
        <button @click="AscPrice">价格降序</button>
        <button @click="DescPrice">价格升序</button>
      </div>
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <div class="fff">
              <pagination ref="pagination" v-bind:list="listCourse"></pagination>
            </div>
          </div>
        </div>
        <br />
        <div class="row">
          <div v-for="o in courses" class="col-md-4">
            <the-course v-bind:course="o"></the-course>
          </div>
          <h3 v-show="courses.length === 0">课程还未上架</h3>
        </div>
      </div>
    </div>
  </main>
</template>

<script>
import TheCourse from "../components/the-course";
import Pagination from "../components/pagination";

export default {
  components: { Pagination, TheCourse },
  name: "list",
  data: function() {
    return {
      courses: [], //课程列表
      level1: [], //一级分类
      level2: [], //二级分类
      categorys: [], //分类信息
      level1Id: "", //一级分类id
      level2Id: "", //二级分类id
      searchName: "" //查询的关键字
    };
  },
  mounted() {
    let _this = this;
    _this.$refs.pagination.size = 5;
    _this.listCourse(1);
    _this.allCategory();
  },
  methods: {
    /**
     * 查询课程列表
     */
    listCourse(page) {
      let _this = this;
      _this.$ajax
        .post(process.env.VUE_APP_SERVER + "/business/web/course/list", {
          page: page,
          size: _this.$refs.pagination.size,
          categoryId: _this.level2Id || _this.level1Id || "" // 优先取level2Id
        })
        .then(response => {
          let resp = response.data;
          if (resp.success) {
            _this.courses = resp.content.list;
            _this.$refs.pagination.render(page, resp.content.total);
          }
        })
        .catch(response => {
          console.log("error：", response);
        });
    },

    /**
     * 所有分类查询
     */
    allCategory() {
      let _this = this;
      _this.$ajax
        .post(process.env.VUE_APP_SERVER + "/business/web/category/all")
        .then(response => {
          let resp = response.data;
          let categorys = resp.content;
          _this.categorys = categorys;

          // 将所有记录格式化成树形结构
          _this.level1 = [];
          for (let i = 0; i < categorys.length; i++) {
            let c = categorys[i];
            if (c.parent === "00000000") {
              _this.level1.push(c);
            } else {
              _this.level2.push(c);
            }
          }
        });
    },

    /**
     * 点击一级分类时
     * @param level1Id
     */
    onClickLevel1(level1Id) {
      let _this = this;

      // 点击一级分类时，设置变量，用于课程筛选
      // 二级分类id为空，
      // 如果点击的是【全部】，则一级分类id为空
      _this.level2Id = null;
      _this.level1Id = level1Id;
      if (level1Id === "00000000") {
        _this.level1Id = null;
      }

      // 点击一级分类时，显示激活状态
      $("#category-" + level1Id)
        .siblings("a")
        .removeClass("cur");
      $("#category-" + level1Id).addClass("cur");

      // 点击一级分类时，二级分类【无限】按钮要设置激活状态
      $("#category-11111111")
        .siblings("a")
        .removeClass("on");
      $("#category-11111111").addClass("on");

      // 注意：要先把level2中所有的值清空，再往里放
      _this.level2 = [];
      let categorys = _this.categorys;
      // 如果点击的是【全部】，则显示所有的二级分类
      if (level1Id === "00000000") {
        for (let i = 0; i < categorys.length; i++) {
          let c = categorys[i];
          if (c.parent !== "00000000") {
            _this.level2.push(c);
          }
        }
      }
      // 如果点击的是某个一级分类，则显示该一级分类下的二级分类
      if (level1Id !== "00000000") {
        for (let i = 0; i < categorys.length; i++) {
          let c = categorys[i];
          if (c.parent === level1Id) {
            _this.level2.push(c);
          }
        }
      }

      // 重新加载课程列表
      _this.listCourse(1);
    },

    /**
     * 点击二级分类时
     * @param level1Id
     */
    onClickLevel2(level2Id) {
      let _this = this;
      $("#category-" + level2Id)
        .siblings("a")
        .removeClass("on");
      $("#category-" + level2Id).addClass("on");

      // 点击二级分类时，设置变量，用于课程筛选
      // 如果点击的是【无限】，则二级分类id为空
      if (level2Id === "11111111") {
        _this.level2Id = null;
      } else {
        _this.level2Id = level2Id;
      }

      // 重新加载课程列表
      _this.listCourse(1);
    },

    // 根据时间排序降序
    DescTime() {
      console.log(this.courses);
      this.courses.sort(function(a, b) {
        return (Date.parse(a.createdAt) - Date.parse(b.createdAt)) * -1; //时间正序  return的值  *-1 则为倒序
      });
      console.log(this.courses);
    },
    //
    // 根据时间升序升序
    AscTime() {
      console.log(this.courses);
      this.courses.sort(function(a, b) {
        return Date.parse(a.createdAt) - Date.parse(b.createdAt); //时间正序  return的值  *-1 则为倒序
      });
      console.log(this.courses);
    },

    // 根据价格升序
    AscPrice() {
      console.log(this.courses);
      this.courses.sort(function(a, b) {
        return a.price - b.price; //时间正序  return的值  *-1 则为倒序
      });
      console.log(this.courses);
    },
    // 根据价格降序
    DescPrice() {
      console.log(this.courses);
      this.courses.sort(function(a, b) {
        return (a.price - b.price) * -1; //时间正序  return的值  *-1 则为倒序
      });
      console.log(this.courses);
    },
    /**
     * 使用indexof方法实现模糊查询
     * @param  {Array}  list     进行查询的数组
     * @param  {String} keyWord  查询的关键词
     * @return {Array}           查询的结果
     */
    fuzzyQuery(list, keyWord) {
      var arr = [];
      for (var i = 0; i < list.length; i++) {
        if (list[i].name.indexOf(keyWord) >= 0) {
          arr.push(list[i]);
        }
      }
      return arr;
    },
    // 模糊搜索
    search() {
      let list = this.courses;
      let keyWord = this.searchName;
      this.courses = this.fuzzyQuery(list, keyWord);
      this.searchName = "";
      console.log("搜索到的结果", this.courses);
    }
  }
};
</script>
<style>
.btns {
  position: relative;
  left: 200px;
  padding-right: 10px;
  top: -30px;
}
.fff {
  float: left;
}
/* 头部 一级分类 */
.header-nav {
  height: auto;
  background: #fff;
  box-shadow: 0 8px 16px 0 rgba(28, 31, 33, 0.1);
  padding: 16px 0;
  box-sizing: border-box;
  position: relative;
  z-index: 1;
  /*background-color: #d6e9c6;*/
}
.header-nav > div {
  width: 100%;
  padding-left: 12px;
  box-sizing: border-box;
  margin-left: auto;
  margin-right: auto;
  /*background-color: #B4D5AC;*/
}
.header-nav a {
  float: left;
  font-size: 16px;
  color: #07111b;
  line-height: 50px;
  height: 45px;
  position: relative;
  margin-right: 46px;
  font-weight: 700;
}
.header-nav a:hover {
  color: #c80;
}
.header-nav a.cur {
  color: #c80;
}
.header-nav a.cur:before {
  display: block;
}
.header-nav a:before {
  display: none;
  content: " ";
  position: absolute;
  bottom: 0;
  background: #c80;
  width: 16px;
  height: 3px;
  left: 50%;
  margin-left: -8px;
}
/* 二级分类 */
.skill {
  width: 100%;
  padding: 24px 0 0;
  position: relative;
  margin: 0 auto;
}
.skill a.on {
  color: #c80;
  background: rgba(204, 136, 0, 0.1);
}
.skill a {
  float: left;
  margin-right: 20px;
  padding: 0 12px;
  font-size: 14px;
  color: #4d555d;
  line-height: 32px;
  border-radius: 6px;
  margin-bottom: 12px;
}
.skill a:hover {
  background: #d9dde1;
}

/* 搜索框 */
div.search {
  height: 30px;
  width: 300px;
  font-size: 15px;
  display: flex;
  position: relative;
  left: 404px;
  top: -39px;
  border-radius: 15px;
}
div.search input {
  height: 30px;
  width: 300px;
  padding: 5px;
  border-radius: 15px;
}
.search-btn {
  height: 30px;
  width: 60px;
  position: relative;
  text-align: center;
  left: -20px;
  margin: 0 auto;
  background-color: burlywood;
}

/* end */
</style>
