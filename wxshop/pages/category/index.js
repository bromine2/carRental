// index.js
import {request} from "../../request/index.js";
import {config} from "../../request/config.js";
Page({
  data:{
    defaultImageUrl:'../../imgs/default.png',
    goodsInfoCarouseList:[],//轮播图列表
    leftMenuList: [],//左侧类型列表--菜单
    rightContent: [],//右侧商品列表
    currentIndex:1
  },
  onLoad:function(){
    this.getGoodsInfoCarouseList();
    this.getLeftMenuList();
    this.getGoodsList(2);
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
    //获取左侧菜单数据
    getLeftMenuList(){
      request({url:'/typeInfo/page/all'}).then(res=>{
        if(res.code==='0'){
          this.setData({
            leftMenuList:res.data.list
          })
        }
      })
    },

    getGoodsList(typeId){
      request({url:'/goodsInfo/findByType/'+typeId}).then(res=>{
        if(res.code==='0'){
            let list=res.data;
            list.forEach(item=>{
              if(!item.fields||item.fields==='[]'){
                item.url=this.data.defaultImageUrl;
              }else{
                let fileArr=JSON.parse(item.fields);
                item.url= config.baseFileUrl+fileArr[0];
              }
            });
            this.setData({
              rightContent:list
            })

        }
      })

    },

    handleItemTap(e){
      let {id}=e.currentTarget.dataset;
      let rightContent =this.getGoodsList(id);
      this.setData({
        currentIndex:id,
        rightContent:rightContent
      })
    }
});
