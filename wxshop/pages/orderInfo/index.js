// pages/orderInfo/index.js
import {request} from "../../request/index.js";
import {config} from "../../request/config.js";
Page({

  /**
   * 页面的初始数据
   */
  data: {
    state: "",
    dataList:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    const state=options.state;
    this.setData({
      state: state
    })
    this.getOrderData(state);

  },

  getOrderData(state){
    let user=wx.getStorageSync('user');
    let url="/orderInfo/page/front?pageNum=1&pageSize=50&userId="+user.id;
    if(state!="all"){
      url=url+"&state="+state;
    }
    console.log(url);  //添加这一行
    request({url:url}).then(res=>{
      if(res.code==='0'){
        let list=res.data.list;
        console.log(list);
        list.forEach((item,index)=>{
          console.log(item)
          let goodsInfo=item.goodsList[0];
          let imgSrc="../../imgs/default.png";
          if(goodsInfo.fields){
            let fields=JSON.parse(goodsInfo.fields);
            if(fields.length){
              imgSrc=config.baseFileUrl+fields[0];
            }
          }
          item.url=imgSrc;
          item.count=item.goodsList.length;
          item.description=goodsInfo.description;
        })
        this.setData({
          dataList:list
        })
      }
    })
  },

  payGoods(e){
    let id=e.currentTarget.dataset.id;
    let state=e.currentTarget.dataset.state;
    request({url:"/orderInfo/state/"+id+"/"+state,method:"POST"}).then(res=>{
      if(res.code==='0'){
        wx.showToast({
          title: '操作成功'
        })
        this.getOrderData(this.data.state);
      }else{
        wx.showToast({
          title: res.msg,
          icon:"none"
        })
      }
    })
  }
})