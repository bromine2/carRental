//同时发送请求次数
let ajaxTimes=0;
export const request=(params)=>{
  ajaxTimes++;
  wx.showLoading({
    title: '加载ing',
    mask: true
  })
  const baseUrl="http://localhost:7777";
  return new Promise((resolve,reject)=>{
    wx.request({
      method: params.method,   //加上这一行，请求方式
      data: params.data,          //和这一行，参数传递
      url: baseUrl+params.url,
      success: (result)=>{
        resolve(result.data)
      },
      fail:(err)=>{
        reject(err);
      },
      complete:()=>{
        ajaxTimes--;
        if(ajaxTimes===0){
          wx.hideLoading();
        }
      }
    })
  });
}