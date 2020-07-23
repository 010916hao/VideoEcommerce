<template>
  <div>
    <p>
      <button v-on:click="add()" class="btn btn-primary btn-round">
        <i class="ace-icon fa fa-edit"></i>
        Add
      </button>
      &nbsp;
      <button v-on:click="list()" class="btn btn-success btn-round">
        <i class="ace-icon fa fa-refresh"></i>
        Reload
      </button>
    </p>

    <table id="simple-table" class="table  table-bordered table-hover">
      <thead>
      <tr>
        <th>id</th>
        <th>title</th>
        <th>course_id</th>
        <th>chapter_id</th>
        <th>video</th>
        <th>time</th>
        <th>charge</th>
        <th>sort</th>
        <th>created_at</th>
        <th>updated_at</th>
        <th>Operations</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="section in sections">

        <td>{{section.id}}</td>
        <td>{{section.title}}</td>
        <td>{{section.courseId}}</td>
        <td>{{section.chapterId}}</td>
        <td>{{section.video}}</td>
        <td>{{section.time}}</td>
        <td>{{section.charge}}</td>
        <td>{{section.sort}}</td>
        <td>{{section.createdAt}}</td>
        <td>{{section.updatedAt}}</td>

        <td>
          <div class="hidden-sm hidden-xs btn-group">

            <button class="btn btn-xs btn-success">
              <i class="ace-icon fa fa-check bigger-120"></i>
            </button>

            <button v-on:click="edit(section)" class="btn btn-xs btn-info">
              <i class="ace-icon fa fa-pencil bigger-120"></i>
            </button>

            <button v-on:click="del(section.id)" class="btn btn-xs btn-danger">
              <i class="ace-icon fa fa-trash-o bigger-120"></i>
            </button>

            <button class="btn btn-xs btn-warning">
              <i class="ace-icon fa fa-flag bigger-120"></i>
            </button>
          </div>

          <div class="hidden-md hidden-lg">
            <div class="inline pos-rel">
              <button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown" data-position="auto">
                <i class="ace-icon fa fa-cog icon-only bigger-110"></i>
              </button>

              <ul
                class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
<!--                <li>-->
<!--                  <a href="#" class="tooltip-info" data-rel="tooltip" title="View">-->
<!--                  <span class="blue">-->
<!--                    <i class="ace-icon fa fa-search-plus bigger-120"></i>-->
<!--                  </span>-->
<!--                  </a>-->
<!--                </li>-->

                <li>
                  <a href="#" class="tooltip-success" data-rel="tooltip" title="Edit">
                                  <span class="green">
                                    <i v-on:click="edit(section)" class="ace-icon fa fa-pencil-square-o bigger-120"></i>
                                  </span>
                  </a>
                </li>

                <li>
                  <a href="#" class="tooltip-error" data-rel="tooltip" title="Delete">
                                  <span class="red">
                                    <i v-on:click="del(section.id)" class="ace-icon fa fa-trash-o bigger-120"></i>
                                  </span>
                  </a>
                </li>
              </ul>
            </div>
          </div>
        </td>




      </tr>

      </tbody>
    </table>
    <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="5"></pagination>

    <div id="form_modal" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">Form</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-2 control-label">id</label>
                    <div class="col-sm-10">
                        <input v-model="section.id" class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">title</label>
                    <div class="col-sm-10">
                        <input v-model="section.title" class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">course_id</label>
                    <div class="col-sm-10">
                        <input v-model="section.courseId" class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">chapter_id</label>
                    <div class="col-sm-10">
                        <input v-model="section.chapterId" class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">video</label>
                    <div class="col-sm-10">
                        <input v-model="section.video" class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">time</label>
                    <div class="col-sm-10">
                        <input v-model="section.time" class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">charge</label>
                    <div class="col-sm-10">
                        <input v-model="section.charge" class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">sort</label>
                    <div class="col-sm-10">
                        <input v-model="section.sort" class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">created_at</label>
                    <div class="col-sm-10">
                        <input v-model="section.createdAt" class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">updated_at</label>
                    <div class="col-sm-10">
                        <input v-model="section.updatedAt" class="form-control">
                    </div>
                </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
            <button v-on:click="save()" type="button" class="btn btn-primary" data-dismiss="modal">Save</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
  </div>


</template>

<script>
  import Pagination from "../../components/Pagination";

  export default {
    components: {Pagination},
    name: "section",
    data: function() {
      return {
        section: {},
        sections: []
      }
    },
    mounted: function() {
      // this.$parent.activeSidebar("business-section-sidebar");
      let _this = this;
      this.$refs.pagination.size = 5;
      _this.list(1);
    },
    methods: {
      add() {
        let _this = this;
        _this.section = {};
        $("#form_modal").modal("show");
      },

      edit(section) {
        let _this = this;
        _this.section = $.extend({}, section);
        $("#form_modal").modal("show");
      },

      list(page) {
        let _this = this;
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/section/list', {
          page: page,
          size: _this.$refs.pagination.size
        }).then((response)=>{
          Loading.hide();
          //console.log("Results of querying sections：", response);
          let resp = response.data;
          _this.sections = resp.content.list;
          _this.$refs.pagination.render(page, resp.content.total);
        })
      },

      save() {
        let _this = this;
        //save validation
        if (1 != 1
          || !Validator.require(_this.section.title, "title")
          || !Validator.length(_this.section.title, "title", 1, 50)
          || !Validator.length(_this.section.video, "video", 1, 200)
        ) {
          return;
        }
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/section/save', _this.section).
        then((response)=>{
          Loading.hide();
          //console.log("Save a new section：", response);
          let resp = response.data;
          if (resp.success) {
            $("#form_modal").modal("hide");
            _this.list(1);
          } else {
            Toast.warning(resp.message);
          }

        })
        Toast.success("Save successfully!");
      },

      del(id) {
        let _this = this;
        Confirm.show("You won't be able to revert this!", function () {
          Loading.show();
          _this.$ajax.delete(process.env.VUE_APP_SERVER + '/business/admin/section/delete/' + id).then((response)=>{
            Loading.hide();
            //console.log("Delete a new section：", response);
            let resp = response.data;
            if (resp.success) {
              _this.list(1);
              Swal.fire(
                'Deleted!',
                'Your file has been deleted.',
                'success'
              )
            }
          })
        });
      }
    }
  }
</script>

<style scoped>

</style>