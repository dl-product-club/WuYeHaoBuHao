// main/createPost/createPost.js
Page({
  /*** 页面的初始数据*/
  data: {
    imgslist: []
  },
  /*** 生命周期函数--监听页面加载*/
  onLoad: function (options) { },
  /*** 生命周期函数--监听页面初次渲染完成*/
  onReady: function () {},
  /*** 生命周期函数--监听页面显示 */
  onShow: function () {},
  /*** 生命周期函数--监听页面隐藏*/
  onHide: function () {},
  /** 生命周期函数--监听页面卸载*/
  onUnload: function () {},
  /**页面相关事件处理函数--监听用户下拉动作*/
  onPullDownRefresh: function () {},
  /**页面上拉触底事件的处理函数*/
  onReachBottom: function () {},
  /** 用户点击右上角分享*/
  onShareAppMessage: function () {},
  formSubmit: function (e) {
    console.log('form发生了submit事件，携带数据为：', e.detail.value)
  },
  formReset: function () {
    console.log('form发生了reset事件')
  },
  chooseImageFn(){
    wx.chooseImage({
      success: (res) => {
        // 返回选定照片的本地文件路径列表，tempFilePath可以作为img标签的src属性显示图片
        var tempFilePaths = res.tempFilePaths;
        var imgslist = this.data.imgslist;
        tempFilePaths = [...imgslist,...tempFilePaths];
        this.setData({ imgslist: tempFilePaths });
      }
    })
  }

})