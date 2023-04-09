package com.tu.controller.utils;

import lombok.Data;

@Data
//写了@Data就不用再书写Getter 和Setter方法
public class R {
    //    数据是否操作成功的标志
    private Boolean flag;
    // 写Object最合适，因为这里面可能还有其他的各种格式的内容
    private Object data;
    private String msg;
//    public R(){}
//    只看增加、删除改是否完成的情况
    public R(Boolean flag){
        this.flag = flag;
    }
//    查询是否完成，并返回数据
    public R(Boolean flag,Object data){
        this.flag = flag;
        this.data = data;
    }
    //    用于服务器出错的情况
    public R(String msg){
        this.flag = false;
        this.msg = msg;
    }
}
