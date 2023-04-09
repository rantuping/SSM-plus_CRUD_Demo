package com.tu;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import  com.tu.mapper.BookDao;
import com.tu.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//在前面注入依赖的前提下，这里可以使用此注解进行单元测试
@SpringBootTest
class BookDaoTestCase {
    //    注入BookDao
    @Autowired
    private BookDao bookDao;

//    自定义接口的测试
//    @Test
//    void findid(){
//        System.out.println("***************");
//        bookDao.findId(2);
//    }

    @Test
//    查询单个元素
    void testGetById(){
        System.out.println(bookDao.selectById(1));
    }

    @Test
    void testSave(){
        Book book = new Book();
        book.setType("test123");
        book.setName("112233");
        book.setDescription("this is a test...") ;
//        插入默认生成id策略是雪花算法，这里只需要使用数据库的自增策略
        System.out.println(bookDao.insert(book));
    }

    @Test
    void testUpdate(){
        Book book = new Book();
//        修改数据库id为5的数据内容
        book.setId(5);
        book.setType("updateTest");
        book.setName("9999");
        book.setDescription("this is a updateTest...");
//        插入默认生成id策略是雪花算法，这里只需要使用数据库的自增策略
        System.out.println(bookDao.updateById(book));
    }

    @Test
    void testDelete(){
//        删除id为14的数据
        bookDao.deleteById(14);
    }

//    查询全部元素
    @Test
    void testGetAll(){
        bookDao.selectList(null);
    }

//    分页查询
    @Test
    void testPage(){
//        这里分页需要自己手工在一个配置类(config)中配置拦截器
        Page page = new Page(2, 5);
        bookDao.selectPage(page,null);
        System.out.println(page.getCurrent());   //第几页
        System.out.println(page.getPages());     //页数
        System.out.println(page.getSize());      //每页的大小
        System.out.println(page.getTotal());     //数据总数
        System.out.println(page.getRecords());   //筛选出来的数据的信息
    }

//    按条件查询
    @Test
    void testGetBy(){
//        String name = "Spring";
//        这里书写条件，以字符串的形式
        String name1 = null;  //定义的筛选条件
        LambdaQueryWrapper<Book> lqw = new LambdaQueryWrapper<>();
//        查找包含name变量的元素
//        lqw.like(Book::getName,name);
//        如果为null，则不链接条件，则进行全部查询
//        除了like还有其他的eq等可对数据进行筛选
//        if (name1!=null)lqw.like(Book::getName,name1);
//        里面没有传值，即条件为空，则查询时不连接条件。  如果传了值，即条件不为空，则查询时连接相关条件
        lqw.like(name1!=null,Book::getName,name1);
        bookDao.selectList(lqw);
    }
}
