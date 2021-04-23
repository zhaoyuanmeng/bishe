<template>
  <main role="main">
    <div class="banner">
      <div class="item">
        <img :src="dataList[currentIndex]" />
      </div>
      <div class="page" v-if="this.dataList.length > 1">
        <ul>
          <li @click="gotoPage(prevIndex)">&lt;</li>
          <li
            v-for="(item,index) in dataList"
            @click="gotoPage(index)"
            :class="{'current':currentIndex == index}"
          >{{index+1}}</li>
          <li @click="gotoPage(nextIndex)">&gt;</li>
        </ul>
      </div>
    </div>
  </main>
</template>

<script>
export default {
  name: "the-lbt",
  data: function() {
    return {
      dataList: [
        "https://static.fotor.com.cn/assets/projects/pages/76ef8c70-f148-11e8-82bf-bdb9777d9961_503b27a5-d9f5-4982-9e31-6a53a78693ab_thumb.jpg",
        "https://static.fotor.com.cn/assets/projects/pages/41eb83be1fd94ffea2cee429bc371f12_704d27d7ccc24366b1a4158c78cf1efe_thumb.jpg",
        "https://static.fotor.com.cn/assets/projects/pages/43ab44b0-e3df-11e8-8daa-cf5232774662_f630e48b-82db-4aea-b98f-a2ff9ae2aaee_thumb.jpg"
      ],
      currentIndex: 0, //默认显示图片
      timer: null //定时器
    };
  },
  computed: {
    //上一张
    prevIndex() {
      if (this.currentIndex == 0) {
        return this.dataList.length - 1;
      } else {
        return this.currentIndex - 1;
      }
    },
    //下一张
    nextIndex() {
      if (this.currentIndex == this.dataList.length - 1) {
        return 0;
      } else {
        return this.currentIndex + 1;
      }
    }
  },
  methods: {
    // 跳转到指定的图片
    gotoPage(index) {
      this.currentIndex = index;
    },
    //定时器
    runInv() {
      this.timer = setInterval(() => {
        this.gotoPage(this.nextIndex);
      }, 1500);
    }
  },
  mounted: function() {
    this.runInv();
  }
};
</script>
<style scoped>
* {
  margin: 0;
  padding: 0;
}

ul li {
  list-style: none;
  float: left;
  width: 30px;
  height: 40px;
  line-height: 40px;
  text-align: center;
  cursor: pointer;
  color: rgba(255, 255, 255, 0.8);
  font-size: 14px;
}

.banner {
  width: 100%;
  /* max-width: 1200px; */
  margin: 0 auto;
  position: relative;
  /* margin-top: 60px; */
}

.banner img {
  width: 100%;
  height: 570px;
  display: block;
}

.banner .page {
  background: rgba(0, 0, 0, 0.5);
  position: absolute;
  right: 0;
  bottom: 0;
  width: 100%;
}

.banner .page ul {
  float: right;
}

.current {
  color: #ff6700;
}
</style>
