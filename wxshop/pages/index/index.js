// index.js
import {request} from "../../request/index.js";
import {config} from "../../request/config.js";
Page({
  data:{
    defaultImageUrl:'../../imgs/default.png',
    goodsInfoCarouseList:[],//轮播图列表
    goodsInfoRecommendList: [],//推荐商品列表
    goodsInfoHotSaleList: [],//热门商品列表
    goodsInfoAllList: [],//获取所有商品
  },
  onLoad:function(){
    this.getGoodsInfoCarouseList();
    this.getGoodsInfoRecommendList();
    this.getGoodsInfoHotSaleList();
    this.getGoodsInfoAllList();
  },

  //获取轮播图
  getGoodsInfoCarouseList(){
    request({url:'/goodsInfo/page/all?pageNum=1&pageSize=4'}).then(res=>{
      if(res.code==='0'){
        let goodsInfoCarouseList=res.data.list;
        if(!goodsInfoCarouseList||goodsInfoCarouseList.length===0){
          goodsInfoCarouseList.push({"name":"哈哈哈","url": this.data.defaultImageUrl});
          goodsInfoCarouseList.push({"name":"哈哈哈哈","url": this.data.defaultImageUrl});
          goodsInfoCarouseList.push({"name":"哈哈哈哈哈","url": this.data.defaultImageUrl});
          goodsInfoCarouseList.push({"name":"哈哈哈哈哈哈","url": this.data.defaultImageUrl});
        }else{
          if(goodsInfoCarouseList.length>5){
            goodsInfoCarouseList=goodsInfoCarouseList.slice(0,5);
          }
          goodsInfoCarouseList.forEach(item=>{
            if(!item.fields||item.fields==='[]'){
              item.url=this.data.defaultImageUrl;
            }else{
              let fileArr=JSON.parse(item.fields);
              item.url= config.baseFileUrl+fileArr[0];
            }
          });
        }
        this.setData({
        goodsInfoCarouseList
      })
      }else{
        wx.showToast({
          title: res.msg,
          icon: 'none'
        })
      }
      
    })
  },
    //获取推荐车辆
    getGoodsInfoRecommendList(){
      request({url:'/goodsInfo/findRecommendGoods'}).then(res=>{
        if(res.code==='0'){
          let goodsInfoRecommendList=res.data.list;
          if(!goodsInfoRecommendList||goodsInfoRecommendList.length===0){
            goodsInfoRecommendList.push({"name":"哈哈哈","url": this.data.defaultImageUrl});
            goodsInfoRecommendList.push({"name":"哈哈哈哈","url": this.data.defaultImageUrl});
            goodsInfoRecommendList.push({"name":"哈哈哈哈哈","url": this.data.defaultImageUrl});
          }else{
            if(goodsInfoRecommendList.length>5){
              goodsInfoRecommendList=goodsInfoRecommendList.slice(0,5);
            }
            goodsInfoRecommendList.forEach(item=>{
              if(!item.fields||item.fields==='[]'){
                item.url=this.data.defaultImageUrl;
              }else{
                let fileArr=JSON.parse(item.fields);
                item.url= config.baseFileUrl+fileArr[0];
              }
            });
          }
          this.setData({
          goodsInfoRecommendList
        })
        }else{
          wx.showToast({
            title: res.msg,
            icon: 'none'
          })
        }
        
      })
    },
    //获取热门车辆
    getGoodsInfoHotSaleList(){
      request({url:'/goodsInfo/findHotSaleGoods'}).then(res=>{
        if(res.code==='0'){
          let goodsInfoHotSaleList=res.data.list;
          if(!goodsInfoHotSaleList||goodsInfoHotSaleList.length===0){
            goodsInfoHotSaleList.push({"name":"哈哈哈","url": this.data.defaultImageUrl});
            goodsInfoHotSaleList.push({"name":"哈哈哈哈","url": this.data.defaultImageUrl});
            goodsInfoHotSaleList.push({"name":"哈哈哈哈哈","url": this.data.defaultImageUrl});
          }else{
            if(goodsInfoHotSaleList.length>5){
              goodsInfoHotSaleList=goodsInfoHotSaleList.slice(0,5);
            }
            goodsInfoHotSaleList.forEach(item=>{
              if(!item.fields||item.fields==='[]'){
                item.url=this.data.defaultImageUrl;
              }else{
                let fileArr=JSON.parse(item.fields);
                item.url= config.baseFileUrl+fileArr[0];
              }
            });
          }
          this.setData({
          goodsInfoHotSaleList
        })
        }else{
          wx.showToast({
            title: res.msg,
            icon: 'none'
          })
        }
      })
    },
    //本站所有商品
  getGoodsInfoAllList(){
    request({url:'/goodsInfo/page/all?pageNum=1&pageSize=200'}).then(res=>{
      if(res.code==='0'){
        let goodsInfoAllList=res.data.list;
        if(!goodsInfoAllList||goodsInfoAllList.length===0){
          goodsInfoAllList.push({"name":"哈哈哈","url": this.data.defaultImageUrl});
          goodsInfoAllList.push({"name":"哈哈哈哈","url": this.data.defaultImageUrl});
          goodsInfoAllList.push({"name":"哈哈哈哈哈","url": this.data.defaultImageUrl});
          goodsInfoAllList.push({"name":"哈哈哈哈哈哈","url": this.data.defaultImageUrl});
        }else{
          if(goodsInfoAllList.length>5){
            goodsInfoAllList=goodsInfoAllList.slice(0,5);
          }
          goodsInfoAllList.forEach(item=>{
            if(!item.fields||item.fields==='[]'){
              item.url=this.data.defaultImageUrl;
            }else{
              let fileArr=JSON.parse(item.fields);
              item.url= config.baseFileUrl+fileArr[0];
            }
          });
        }
        this.setData({
        goodsInfoAllList
      })
      }else{
        wx.showToast({
          title: res.msg,
          icon: 'none'
        })
      }
      
    })
  },
});
