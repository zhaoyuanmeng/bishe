<template>
  <main role="main">
    <div class="content">
      <p>1、会员中心页面</p>
      <p>1、系统没有绝对没有完美的系统</p>
      <p>2、所以请宽容我们的“错误”</p>
      <p>3、如果你很紧急的错误要提交，请联系QQ：1426593075</p>
      <p>4、或者添加微信wx1233412</p>
      <p>5、下面按钮是提交你的错误给后台，我们会在三个工作日审核·谢谢你的理解</p>
      <button v-on:click="add()" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-edit"></i>
        提交错误
      </button>
    </div>

    <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">错误信息收集</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <div class="form-group">
                <label class="col-sm-2 control-label">姓名</label>
                <div class="col-sm-10">
                  <input v-model="upload.userName" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">描述</label>
                <div class="col-sm-10">
                  <input v-model="upload.content" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">截图</label>
                <div class="col-sm-10">
                  <big-file v-bind:input-id="'image-upload'"
                            v-bind:text="'上传截图'"
                            v-bind:suffixs="['jpg', 'jpeg', 'png']"
                            v-bind:use=" FILE_USE.FANKUI.key"
                            v-bind:after-upload="afterUpload"></big-file>
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
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
  </main>
</template>

<script>
    import Pagination from "../../.././admin/src/components/pagination";
    import File from "../../.././admin/src/components/file";
    import BigFile from ".././components/big-file";
    import "../.././public/static/js/progress"
    export default {
        components: {Pagination, File, BigFile},
        name: "business-teacher",
        data: function() {
            return {
                upload: {},
                FILE_USE: FILE_USE,
                key:"AK"
            }
        },
        mounted: function() {
//            let _this = this;
//            _this.$refs.pagination.size = 5;
//            _this.list(1);
            // sidebar激活样式方法一
            // this.$parent.activeSidebar("business-teacher-sidebar");

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
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/teacher/list', {
                    page: page,
                    size: _this.$refs.pagination.size,
                }).then((response)=>{
                    Loading.hide();
                    let resp = response.data;
                    _this.teachers = resp.content.list;
                    _this.$refs.pagination.render(page, resp.content.total);

                })
            },

            /**
             * 点击【保存】
             */
            save() {
                let _this = this;


//                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/upload/save', _this.upload).then((response)=>{
//                    Loading.hide();
                    let resp = response.data;
                    if (resp.success) {
                        $("#form-modal").modal("hide");
                        Toast.success("上传成功！");
                    } else {
                        Toast.warning(resp.message)
                    }
                })
            },

            afterUpload(resp) {
                let _this = this;
                console.log('sssssssssssss'+ resp.content.path)
                let image = resp.content.path;
                _this.upload.image = image;
                _this.upload.file = resp.content.path
            }
        }
    }
</script>

<style>
  .content{
    width: 800px;
    height: 600px;
    margin: 50px auto;
    /*background-color: #0B61A4;*/
  }
  .modal{
    margin-top: 100px;
  }
  .modal-title{
     margin:  0px;
     position: relative;
     left: -320px;
   }
  .close{
    margin:  0px;
    position: relative;
    left: 130px;
  }
</style>
