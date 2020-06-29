<template>
  <div class="pagination" role="group" aria-label="pagination">
    <button type="button" class="btn btn-default btn-white btn-round"
            v-bind:disabled="page === 1"
            v-on:click="selectPage(1)">
      1
    </button>
    <button type="button" class="btn btn-default btn-white btn-round"
            v-bind:disabled="page === 1"
            v-on:click="selectPage(page - 1)">
      Last Page
    </button>
    <button v-for="p in pages" v-bind:id="'page-' + p"
            type="button" class="btn btn-default btn-white btn-round"
            v-bind:class="{'btn-primary active':page == p}"
            v-on:click="selectPage(p)">
      {{p}}
    </button>
    <button type="button" class="btn btn-default btn-white btn-round"
            v-bind:disabled="page === pageTotal"
            v-on:click="selectPage(page + 1)">
      Next Page
    </button>
    <button type="button" class="btn btn-default btn-white btn-round"
            v-bind:disabled="page === pageTotal"
            v-on:click="selectPage(pageTotal)">
      {{pageTotal||1}}
    </button>
    &nbsp;
    <span class="m--padding-10">

        <select v-model="size">
            <option value="1">1</option>
            <option value="5">5</option>
            <option value="10">10</option>
            <option value="20">20</option>
            <option value="50">50</option>
            <option value="100">100</option>
        </select>
        chapters per page，total【{{total}}】chapters
    </span>
  </div>
</template>

<script>
  export default {
    name: 'pagination',
    props: {
      list: {
        type: Function,
        default: null
      },
      itemCount: Number // page numbers which are shown on web pages
    },
    data: function () {
      return {
        total: 0, // row number
        size: 10, // size
        page: 0, // current page
        pageTotal: 0, // total page numbers
        pages: [], // page array which is shown
      }
    },
    methods: {
      /**
       * pagination render component
       * @param page
       * @param total
       */
      render(page, total) {
        let _this = this;
        _this.page = page;
        _this.total = total;
        _this.pageTotal = Math.ceil(total / _this.size);
        _this.pages = _this.getPageItems(_this.pageTotal, page, _this.itemCount || 5);
      },

      /**
       * query a page
       * @param page
       */
      selectPage(page) {
        let _this = this;
        if (page < 1) {
          page = 1;
        }
        if (page > _this.pageTotal) {
          page = _this.pageTotal;
        }
        if (this.page !== page) {
          _this.page = page;
          if (_this.list) {
            _this.list(page);
          }
        }
      },

      /**
       * current page number to be shown
       * @param total
       * @param current
       * @param length
       * @returns {Array}
       */
      getPageItems(total, current, length) {
        let items = [];
        if (length >= total) {
          for (let i = 1; i <= total; i++) {
            items.push(i);
          }
        } else {
          let base = 0;
          // prev
          if (current - 0 > Math.floor((length - 1) / 2)) {
            // next
            base = Math.min(total, current - 0 + Math.ceil((length - 1) / 2)) - length;
          }
          for (let i = 1; i <= length; i++) {
            items.push(base + i);
          }
        }
        return items;
      }
    }
  }
</script>

<style scoped>
  .pagination {
    vertical-align: middle !important;
    font-size: 16px;
    margin-top: 0;
    margin-bottom: 10px;
  }

  .pagination button {
    margin-right: 5px;
  }

  .btn-primary.active {
    background-color: #2f7bba !important;
    border-color: #27689d !important;
    color: white !important;
    font-weight: 600;
  }

  /*.pagination select {*/
  /*vertical-align: middle !important;*/
  /*font-size: 16px;*/
  /*margin-top: 0;*/
  /*}*/
</style>
