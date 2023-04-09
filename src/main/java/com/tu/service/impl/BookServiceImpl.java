package com.tu.service.impl;
//4。业务层  具体接口方法的实现
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tu.mapper.BookDao;
import com.tu.entity.Book;
import com.tu.service.IBookService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//实现IBookService的接口
@Service
//                                               实现类    模型类           //对应的逻辑层接口
public class BookServiceImpl extends ServiceImpl<BookDao, Book> implements IBookService {
//    这里注入对应的接口来需要用到的东西
    @Autowired
    private BookDao bookDao;

    @Override
    public boolean save(Book book) {
        return bookDao.insert(book) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        return bookDao.deleteById(id) > 0;
    }

    @Override
    public boolean modify(Book book) {
        return bookDao.updateById(book) > 0;
    }

//    分页查询
//    @Override
//    public IPage<Book> getPage(int currentPage, int pageSize) {
//        IPage page = new Page(currentPage,pageSize);
////        null即没传入条件，直接进行分页操作(只有前面定义拦截器，这里的分页查询才有效)
//        bookDao.selectPage(page,null);
//        return page;
//    }

//    条件分页查询
    @Override
    public IPage<Book> getPage(int currentPage, int pageSize, Book book) {
        //Book book是用于接受前端传递将你来的条件查询的数据
        // 条件查询对象lqw
        LambdaQueryWrapper<Book> lqw = new LambdaQueryWrapper<Book>();
        //          如果查询值不为空，则进行查询
        lqw.like(Strings.isNotEmpty(book.getType()),Book::getType,book.getType());
        lqw.like(Strings.isNotEmpty(book.getName()),Book::getName,book.getType());
        lqw.like(Strings.isNotEmpty(book.getDescription()),Book::getDescription,book.getType());
//        查询完成再进行分页操作
        IPage page = new Page(currentPage,pageSize);
//        传入条件lqw，再根据lqw的条件内容，进行查询，再分页显示
//        (只有前面定义拦截器，这里的分页查询才有效)
        bookDao.selectPage(page, lqw);
        return page;
    }
}
