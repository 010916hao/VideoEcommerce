<template>
  <div>
    <h1>welcome to admin</h1>
  </div>
</template>

<script>
  export default {
    name: "welcome",
    mounted: function() {
      this.$parent.activeSidebar("welcome-sidebar");
    },
    methods: {
    }
  }
</script>

<style scoped>

</style>