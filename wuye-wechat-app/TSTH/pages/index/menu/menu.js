// pages/index/menu/menu.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {

  },

  /**
   * 组件的初始数据
   */
  data: {
    tabs:[
      { name: 'xx物业1', id: 0,},
      { name: 'xx物业2', id: 1,},
      { name: 'xx物业2', id: 2,}
    ]
  },

  /**
   * 组件的方法列表
   */
  methods: {
    selectTab:function(tab){
      // console.log(tab.target.dataset.id)
      var myEventDetail = { id: tab.target.dataset.id} // detail对象，提供给事件监听函数
      var myEventOption = {} // 触发事件的选项
      this.triggerEvent('changetab', myEventDetail, myEventOption)
    },
  }
})
