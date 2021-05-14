<template>
  <div>
    <div class="space-12"></div>
    <div class="space-12"></div>
    <div class="row">
      <div class="col-xs-12">
        <div class="center">
          <span class="btn btn-app btn-sm btn-light no-hover">
            <span class="line-height-1 bigger-170 blue">1,411</span>

            <br />
            <span class="line-height-1 smaller-90">课程</span>
          </span>

          <span class="btn btn-app btn-sm btn-yellow no-hover">
            <span class="line-height-1 bigger-170">32</span>

            <br />
            <span class="line-height-1 smaller-90">大章</span>
          </span>

          <span class="btn btn-app btn-sm btn-pink no-hover">
            <span class="line-height-1 bigger-170">4</span>

            <br />
            <span class="line-height-1 smaller-90">小节</span>
          </span>

          <span class="btn btn-app btn-sm btn-grey no-hover">
            <span class="line-height-1 bigger-170">23</span>

            <br />
            <span class="line-height-1 smaller-90">会员</span>
          </span>

          <span class="btn btn-app btn-sm btn-success no-hover">
            <span class="line-height-1 bigger-170">7</span>

            <br />
            <span class="line-height-1 smaller-90">报名</span>
          </span>

          <span class="btn btn-app btn-sm btn-primary no-hover">
            <span class="line-height-1 bigger-170">55</span>

            <br />
            <span class="line-height-1 smaller-90">评论</span>
          </span>
        </div>
      </div>
      <div class="space-12"></div>
      <div class="hr hr32 hr-dotted"></div>
      <div class="space-12"></div>
    </div>

    <div class="row">
      <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->

        <div class="row">
          <div class="space-6"></div>

          <div class="col-sm-7 infobox-container">
            <div class="infobox infobox-green">
              <div class="infobox-icon">
                <i class="ace-icon fa fa-comments"></i>
              </div>

              <div class="infobox-data">
                <span class="infobox-data-number">32</span>
                <div class="infobox-content">新增评论</div>
              </div>

              <div class="stat stat-success">8%</div>
            </div>

            <div class="infobox infobox-blue">
              <div class="infobox-icon">
                <i class="ace-icon fa fa-twitter"></i>
              </div>

              <div class="infobox-data">
                <span class="infobox-data-number">11</span>
                <div class="infobox-content">新增会员</div>
              </div>

              <div class="badge badge-success">
                +32%
                <i class="ace-icon fa fa-arrow-up"></i>
              </div>
            </div>

            <div class="infobox infobox-pink">
              <div class="infobox-icon">
                <i class="ace-icon fa fa-shopping-cart"></i>
              </div>

              <div class="infobox-data">
                <span class="infobox-data-number">8</span>
                <div class="infobox-content">新增报名</div>
              </div>
              <div class="stat stat-important">4%</div>
            </div>

            <div class="infobox infobox-red">
              <div class="infobox-icon">
                <i class="ace-icon fa fa-flask"></i>
              </div>

              <div class="infobox-data">
                <span class="infobox-data-number">7</span>
                <div class="infobox-content">experiments</div>
              </div>
            </div>

            <div class="infobox infobox-orange2">
              <div class="infobox-chart">
                <span class="sparkline" data-values="196,128,202,177,154,94,100,170,224"></span>
              </div>

              <div class="infobox-data">
                <span class="infobox-data-number">6,251</span>
                <div class="infobox-content">pageviews</div>
              </div>

              <div class="badge badge-success">
                7.2%
                <i class="ace-icon fa fa-arrow-up"></i>
              </div>
            </div>

            <div class="infobox infobox-blue2">
              <div class="infobox-progress">
                <div class="easy-pie-chart percentage" data-percent="42" data-size="46">
                  <span class="percent">42</span>%
                </div>
              </div>

              <div class="infobox-data">
                <span class="infobox-text">traffic used</span>

                <div class="infobox-content">
                  <span class="bigger-110">~</span>
                  58GB remaining
                </div>
              </div>
            </div>
          </div>

          <div class="vspace-12-sm"></div>

          <div class="col-sm-5">
            <div class="widget-box">
              <div class="widget-header widget-header-flat widget-header-small">
                <h5 class="widget-title">
                  <i class="ace-icon fa fa-signal"></i>
                  分类播放排名
                </h5>

                <div class="widget-toolbar no-border">
                  <div class="inline dropdown-hover">
                    <button class="btn btn-minier btn-primary">
                      This Week
                      <i class="ace-icon fa fa-angle-down icon-on-right bigger-110"></i>
                    </button>

                    <ul
                      class="dropdown-menu dropdown-menu-right dropdown-125 dropdown-lighter dropdown-close dropdown-caret"
                    >
                      <li class="active">
                        <a href="#" class="blue">
                          <i class="ace-icon fa fa-caret-right bigger-110">&nbsp;</i>
                          This Week
                        </a>
                      </li>

                      <li>
                        <a href="#">
                          <i class="ace-icon fa fa-caret-right bigger-110 invisible">&nbsp;</i>
                          Last Week
                        </a>
                      </li>

                      <li>
                        <a href="#">
                          <i class="ace-icon fa fa-caret-right bigger-110 invisible">&nbsp;</i>
                          This Month
                        </a>
                      </li>

                      <li>
                        <a href="#">
                          <i class="ace-icon fa fa-caret-right bigger-110 invisible">&nbsp;</i>
                          Last Month
                        </a>
                      </li>
                    </ul>
                  </div>
                </div>
              </div>

              <div class="widget-body">
                <div class="widget-main">
                  <div id="piechart-placeholder"></div>

                  <div class="hr hr8 hr-double"></div>

                  <div class="clearfix">
                    <div class="grid3">
                      <span class="grey">
                        <i class="ace-icon fa fa-facebook-square fa-2x blue"></i>
                        &nbsp; likes
                      </span>
                      <h4 class="bigger pull-right">1,255</h4>
                    </div>

                    <div class="grid3">
                      <span class="grey">
                        <i class="ace-icon fa fa-twitter-square fa-2x purple"></i>
                        &nbsp; tweets
                      </span>
                      <h4 class="bigger pull-right">941</h4>
                    </div>

                    <div class="grid3">
                      <span class="grey">
                        <i class="ace-icon fa fa-pinterest-square fa-2x red"></i>
                        &nbsp; pins
                      </span>
                      <h4 class="bigger pull-right">1,050</h4>
                    </div>
                  </div>
                </div>
                <!-- /.widget-main -->
              </div>
              <!-- /.widget-body -->
            </div>
            <!-- /.widget-box -->
          </div>
          <!-- /.col -->
        </div>
        <!-- /.row -->

        <div class="hr hr32 hr-dotted"></div>

        <div class="row">
          <div class="col-sm-5">
            <div class="widget-box transparent">
              <div class="widget-header widget-header-flat">
                <h4 class="widget-title lighter">
                  <i class="ace-icon fa fa-star orange"></i>
                  课程报名
                </h4>

                <div class="widget-toolbar">
                  <a href="#" data-action="collapse">
                    <i class="ace-icon fa fa-chevron-up"></i>
                  </a>
                </div>
              </div>

              <div class="widget-body">
                <div class="widget-main no-padding">
                  <table class="table table-bordered table-striped">
                    <thead class="thin-border-bottom">
                      <tr>
                        <th>
                          <i class="ace-icon fa fa-caret-right blue"></i>课程名
                        </th>

                        <th>
                          <i class="ace-icon fa fa-caret-right blue"></i>课程价格
                        </th>

                        <th class="hidden-480">
                          <i class="ace-icon fa fa-caret-right blue"></i>报名人数
                        </th>
                      </tr>
                    </thead>

                    <tbody>
                      <tr v-for="course in courses">
                        <td>{{course.name}}</td>
                        <td>{{course.price}}</td>

                        <td class="hidden-480">
                          <span class="label label-info arrowed-right arrowed-in">{{course.enroll}}</span>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                </div>
                <!-- /.widget-main -->
              </div>
              <!-- /.widget-body -->
            </div>
            <!-- /.widget-box -->
          </div>
          <!-- /.col -->

          <div class="col-sm-7">
            <div class="widget-box transparent">
              <div class="widget-header widget-header-flat">
                <h4 class="widget-title lighter">
                  <i class="ace-icon fa fa-signal"></i>
                  播放量
                </h4>

                <div class="widget-toolbar">
                  <a href="#" data-action="collapse">
                    <i class="ace-icon fa fa-chevron-up"></i>
                  </a>
                </div>
              </div>

              <div class="widget-body">
                <div class="widget-main padding-4">
                  <div id="sales-charts"></div>
                </div>
                <!-- /.widget-main -->
              </div>
              <!-- /.widget-body -->
            </div>
            <!-- /.widget-box -->
          </div>
          <!-- /.col -->
        </div>
        <!-- /.row -->

        <div class="hr hr32 hr-dotted"></div>

        <!-- /.row -->

        <!-- PAGE CONTENT ENDS -->
      </div>
      <!-- /.col -->
    </div>
    <!-- /.row -->
  </div>
</template>
<script src="jquery-2.1.4.min.js"></script>

<script>
export default {
  name: "welcome",
  mounted: function() {
    // sidebar激活样式方法一
    // this.$parent.activeSidebar("welcome-sidebar");
    this.drawSaleChart();
    this.drawZhuChart();
    this.list(1);
  },
  data() {
    return {
      courses: []
    };
  },
  methods: {
    // 播放量的图表
    drawSaleChart() {
      // 生成随机数据
      let d1 = [];
      for (let i = 0; i < 30; i++) {
        d1.push([i + 1, 10 + Math.floor(Math.random() * 100 + 1)]);
      }
      let d2 = [];
      for (let i = 0; i < 30; i++) {
        d2.push([i + 1, 10 + Math.floor(Math.random() * 100 + 1)]);
      }
      let sales_charts = $("#sales-charts").css({
        width: "100%",
        height: "220px"
      });
      $.plot(
        "#sales-charts",
        [
          { label: "过去一月", data: d1 },
          { label: "现在这月", data: d2 }
        ],
        {
          hoverable: true,
          shadowSize: 0,
          series: {
            lines: { show: true },
            points: { show: true }
          },
          xaxis: {
            tickLength: 0
          },
          yaxis: {},
          grid: {
            backgroundColor: { colors: ["#fff", "#fff"] },
            borderWidth: 1,
            borderColor: "#555"
          }
        }
      );
    },
    // 柱状图的图表
    drawZhuChart() {
      let placeholder = $("#piechart-placeholder").css({
        width: "90%",
        "min-height": "150px"
      });
      let data = [
        { label: "java", data: 38.7, color: "#68BC31" },
        { label: "php", data: 24.5, color: "#2091CF" },
        { label: "go", data: 8.2, color: "#AF4E96" },
        { label: "javascript", data: 18.6, color: "#DA5430" },
        { label: "linux", data: 10, color: "#FEE074" }
      ];
      $.plot(placeholder, data, {
        series: {
          pie: {
            show: true,
            tilt: 0.8,
            highlight: {
              opacity: 0.25
            },
            stroke: {
              color: "#fff",
              width: 2
            },
            startAngle: 2
          }
        },
        legend: {
          show: true,
          position: "ne",
          labelBoxBorderColor: null,
          margin: [-30, 15]
        },
        grid: {
          hoverable: true,
          clickable: true
        }
      });
      var $tooltip = $(
        "<div class='tooltip top in'><div class='tooltip-inner'></div></div>"
      )
        .hide()
        .appendTo("body");
      var previousPoint = null;

      placeholder.on("plothover", function(event, pos, item) {
        if (item) {
          if (previousPoint != item.seriesIndex) {
            previousPoint = item.seriesIndex;
            var tip =
              item.series["label"] + " : " + item.series["percent"] + "%";
            $tooltip
              .show()
              .children(0)
              .text(tip);
          }
          $tooltip.css({ top: pos.pageY + 10, left: pos.pageX + 10 });
        } else {
          $tooltip.hide();
          previousPoint = null;
        }
      });
    },

    // 获取课程的信息
    /**
     * 列表查询
     */
    list(page) {
      let _this = this;
      Loading.show();
      _this.$ajax
        .post(process.env.VUE_APP_SERVER + "/business/admin/course/list", {
          page: page,
          size: 100
        })
        .then(response => {
          Loading.hide();
          let resp = response.data;
          _this.courses = resp.content.list;
          this.courseP(); //获取排行榜数据
          console.log(_this.courses);
        });
    },

    // 根据报名数排序
    courseP() {
      this.courses.sort(function(a, b) {
        return (a.enroll - b.enroll) * -1; //时间正序  return的值  *-1 则为倒序
      });
      console.log("课程排行帮", this.courses);
    }
  }
};
</script>
