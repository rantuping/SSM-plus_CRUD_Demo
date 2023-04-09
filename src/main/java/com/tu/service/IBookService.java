package com.tu.service;
//3。业务层  逻辑接口
//在业务层制作的时候还是要先制作业务层的接口，然后再进行接口的实现
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tu.entity.Book;

//使用mp
//                                            这里写实体类
public interface IBookService extends IService<Book> {
//    这里写的接口，要到BookServiceImpl里面去实现
    boolean save(Book book);  //这个接口就没有在对应的实现类里面去实现
    boolean delete(Integer id);
    boolean modify(Book book);
//    IPage<Book> getPage(int currentPage, int pageSize);
    IPage<Book> getPage(int currentPage, int pageSize, Book book);
}
