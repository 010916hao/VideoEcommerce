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
<#--      <tr>-->
<#--        <th>ID</th>-->
<#--        <th>Name</th>-->
<#--        <th>Course Id</th>-->
<#--        <th>Operations</th>-->
<#--      </tr>-->
      <tr><#list fieldList as field>
        <th>${field.name}</th></#list>
        <th>Operations</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="${domain} in ${domain}s">

<#--        <td>{{${domain}.id}}</td>-->
<#--        <td>{{${domain}.name}}</td>-->
<#--        <td>{{${domain}.courseId}}</td>-->
        <#list fieldList as field>
        <td>{{${domain}.${field.nameHump}}}</td>
        </#list>

        <td>
          <div class="hidden-sm hidden-xs btn-group">

            <button class="btn btn-xs btn-success">
              <i class="ace-icon fa fa-check bigger-120"></i>
            </button>

            <button v-on:click="edit(${domain})" class="btn btn-xs btn-info">
              <i class="ace-icon fa fa-pencil bigger-120"></i>
            </button>

            <button v-on:click="del(${domain}.id)" class="btn btn-xs btn-danger">
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
                                    <i v-on:click="edit(${domain})" class="ace-icon fa fa-pencil-square-o bigger-120"></i>
                                  </span>
                  </a>
                </li>

                <li>
                  <a href="#" class="tooltip-error" data-rel="tooltip" title="Delete">
                                  <span class="red">
                                    <i v-on:click="del(${domain}.id)" class="ace-icon fa fa-trash-o bigger-120"></i>
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
<#--              <div class="form-group">-->
<#--                <label class="col-sm-2 control-label">courseid</label>-->
<#--                <div class="col-sm-10">-->
<#--                  <input v-model="${domain}.courseId" class="form-control" placeholder="courseid">-->
<#--                </div>-->
<#--              </div>-->
<#--              <div class="form-group">-->
<#--                <label class="col-sm-2 control-label">coursename</label>-->
<#--                <div class="col-sm-10">-->
<#--                  <input v-model="${domain}.name" class="form-control" placeholder="coursename">-->
<#--                </div>-->
<#--              </div>-->
              <#list fieldList as field>
                <div class="form-group">
                    <label class="col-sm-2 control-label">${field.name}</label>
                    <div class="col-sm-10">
                        <input v-model="${domain}.${field.nameHump}" class="form-control">
                    </div>
                </div>
              </#list>
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
    name: "${domain}",
    data: function() {
      return {
        ${domain}: {},
        ${domain}s: []
      }
    },
    mounted: function() {
      // this.$parent.activeSidebar("${module}-${domain}-sidebar");
      let _this = this;
      this.$refs.pagination.size = 5;
      _this.list(1);
    },
    methods: {
      add() {
        let _this = this;
        _this.${domain} = {};
        $("#form_modal").modal("show");
      },

      edit(${domain}) {
        let _this = this;
        _this.${domain} = $.extend({}, ${domain});
        $("#form_modal").modal("show");
      },

      list(page) {
        let _this = this;
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/${module}/admin/${domain}/list', {
          page: page,
          size: _this.$refs.pagination.size
        }).then((response)=>{
          Loading.hide();
          //console.log("Results of querying ${domain}s：", response);
          let resp = response.data;
          _this.${domain}s = resp.content.list;
          _this.$refs.pagination.render(page, resp.content.total);
        })
      },

      save() {
        let _this = this;
        //save validation
        if (1 != 1
          <#list fieldList as field>
          <#if !field.nullAble>
          || !Validator.require(_this.${domain}.${field.nameHump}, "${field.name}")
          </#if>
          <#if (field.length > 0)>
          || !Validator.length(_this.${domain}.${field.nameHump}, "${field.name}", 1, ${field.length})
          </#if>
          </#list>
        ) {
          return;
        }
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/${module}/admin/${domain}/save', _this.${domain}).
        then((response)=>{
          Loading.hide();
          //console.log("Save a new ${domain}：", response);
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
          _this.$ajax.delete(process.env.VUE_APP_SERVER + '/${module}/admin/${domain}/delete/' + id).then((response)=>{
            Loading.hide();
            //console.log("Delete a new ${domain}：", response);
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