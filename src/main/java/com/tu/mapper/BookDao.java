package com.tu.mapper;
//2。数据层的接口在这里定义，以便后面的业务层进行具体实际逻辑开发时使用

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import com.tu.entity.Book;

//这里是mybatis的书写方式
//public interface BookDao{
////    这里以单个查询为例（需要自己进行增删改查的代码的书写）
//    @select("select * from tbl_book where id = #{id}")
//    Book getById(Integer id);
//}

//这里是myBatisPlus的书写方式
@Mapper
// extends BaseMapper<Book>继承这里，就继承了这里面的增删改查的操作
// 如果有其他需要的功能函数，则可以自己定义或者进行重写
//                                       这里写实体类
public interface BookDao extends BaseMapper<Book> {
//    增删改查接口
}
