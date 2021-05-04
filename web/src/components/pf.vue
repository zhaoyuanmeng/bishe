<template>
  <main role="main">
    <div class="con">
      <input type="range" name="star" min="0" max="5" :value="pfval" />
    </div>
  </main>
</template>

<script>
export default {
  name: "pf",
  props: {
    pfval: 0
  },
  data() {},
  methods: {
    getPf() {
      return this.pfval;
    }
  }
};
</script>

<style scoped>
.con {
  /* display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column; */
}
input[type="range"] {
  -webkit-appearance: none;
  width: 100px;
  margin: 0;
  outline: 0;
}
input[type="range" i]::-webkit-slider-runnable-track {
  background: coral;
  height: 20px;
  -webkit-mask: url("data:image/svg+xml,%3Csvg width='12' height='11' viewBox='0 0 12 11' fill='none' xmlns='http://www.w3.org/2000/svg'%3E%3Cpath d='M6 0l1.693 3.67 4.013.476L8.74 6.89l.788 3.964L6 8.88l-3.527 1.974.788-3.964L.294 4.146l4.013-.476L6 0z' fill='%23F67600'/%3E%3C/svg%3E");
  -webkit-mask-size: 20px;
  -webkit-mask-repeat: repeat-x;
}
input[type="range" i]::-webkit-slider-thumb {
  -webkit-appearance: none;
  width: 0;
  height: 100%;
  box-shadow: 999px 0px 0px 999px #e8eaed;
}
</style>