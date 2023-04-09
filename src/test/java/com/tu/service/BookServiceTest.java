package com.tu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tu.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class BookServiceTest {

    @Autowired
    private IBookService iBookService;

    @Test
    void testGetById(){
        //这里需要打印，因为这里业务层，不是看的数据层的id
        System.out.println(iBookService.getById(6));
    }

    @Test
    void testSave(){
        Book book = new Book();
        book.setType("test123");
        book.setName("112233");
        book.setDescription("this is a test...");
//        插入默认生成id策略是雪花算法，这里只需要使用数据库的自增策略
        System.out.println(iBookService.save(book));
    }

    @Test
    void testUpdate(){
        Book book = new Book();
//        修改数据库id为5的数据内容
        book.setId(5);
        book.setType("99999");
        book.setName("bookTest");
        book.setDescription("this is a updateTest...");
//        插入默认生成id策略是雪花算法，这里只需要使用数据库的自增策略
        System.out.println(iBookService.updateById(book));
    }

    @Test
    void testDelete(){
//        删除id为14的数据
        iBookService.removeById(14);
    }

    //    查询全部元素
    @Test
    void testGetAll(){
        iBookService.list();
    }

    //    分页查询
    @Test
    void testPage(){
        IPage<Book> page = new Page<Book>(2,5);
        IPage<Book> page1 = iBookService.page(page);
        System.out.println(page1.getCurrent());   //第几页
        System.out.println(page1.getPages());     //页数
        System.out.println(page1.getSize());      //每页的大小
        System.out.println(page1.getTotal());     //数据总数
        System.out.println(page1.getRecords());   //筛选出来的数据的信息
    }
}
