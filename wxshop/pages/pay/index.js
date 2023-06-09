// pages/pay/index.js
import {request} from "../../request/index.js";
import {config} from "../../request/config.js";
Page({

  /**
   * 页面的初始数据
   */
  data: {
    recharge:"",
    price:0
  },

  onShow() {
    let user=wx.getStorageSync('user');
    if(!user){
      wx.showToast({
        title: '请先登录',
        icon:'none'
      })}else{
        request({url:"/userInfo/"+user.id}).then(res=>{
      if(res.code==='0'){
        this.setData({
          price:res.data.account
        })
      }
    })
  }
  },

  onPrice(e){
    this.setData({
      recharge:e.detail.value
    })
  },
  recharge(e){
    let user=wx.getStorageSync('user');
    let data={id:user.id,account:parseFloat(this.data.price)+parseFloat(this.data.recharge)};
    request({url:"/userInfo",data:data,method:"PUT"})
      .then(res=>{
        if(res.code==='0'){
          wx.showToast({
            title: '充值成功'
          })
          wx.switchTab({
            url: '/pages/user/index'
          })
        }else{
          wx.showToast({
            title: res.msg,
            icon:'none'
          })
        }
      })
  }


 
})