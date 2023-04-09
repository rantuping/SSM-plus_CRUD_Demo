package com.tu.entity;
//1。实体类开发

import lombok.Data;

@Data
//只需要声明变量即可
//同时加入了Getter和Setter，toString，hashCode，equals，但是没有constructor
//加入此注解，可以不用再书写Getter和Setter
//用在实体bean上，不需要写出set和get方法，但是具备实体bean所具备的方法，简化编程提高变成速度。

//如果这里写的是@Constructor的话，就可以使用无参构造方法
public class Book {
    private Integer id;
    private String type;
    private String name;
    private String description;
//    这里自己开发的化使用Getter和Setter
}
