// pages/goodsInfo/index.js
import {request} from "../../request/index.js";
import {config} from "../../request/config.js";
Page({

  /**
   * 页面的初始数据
   */
  data: {
    goodsId:0,//商品id
    obj: {},//当前
    swiperList:[]//当前商品图片数组
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad:function(options) {
    const id=options.id;
    this.setData({
      goodsId:id
    })
    this.getDetail(id);
  },

  getDetail(id){
    request({url:'/goodsInfo/'+id}).then(res=>{
    if(res.code==='0'){
      let obj=res.data;
      let swiperList=[];
      if(obj.fields){
        let list=JSON.parse(obj.fields);
        list.forEach(item=>{
          let imgObj={};
          imgObj.fields=item;
          imgObj.imgSrc=config.baseFileUrl+item;
          swiperList.push(imgObj);
        })
      }
      if(swiperList.length===0){
        swiperList.push({imgSrc:"../../img/default.png"});
        swiperList.push({imgSrc:"../../img/default.png"});
      }
      this.setData({
        obj,
        swiperList
      })
    }else{
      wx.showToast({
        title: res.msg,
        icon:'none'
      })
    }
    })
  },
  //将商品加入购物车
  handleCartAdd(){
    let user=wx.getStorageSync('user');
    //判断用户是否登录，未登录则转跳登录界面
    if(!user){
      wx.navigateTo({
        url: '/pages/login/index?isTabBar=0&url=/pages/goodsInfo/index$id-'+this.data.goodsId
      })
      return;
    }
    let data= {userid:user.id,level:user.level,goodsid:this.data.goodsId,count:1};
    request({url:'/cartInfo',data:data,method:"POST"}).then(res=>{
      if(res.code==='0'){
        wx.showToast({
          title: '成功加入购物车',
        })
      }else{
        wx.showToast({
          title: res.msg,
          icon: "error"
        })
      }

    })
  }
})