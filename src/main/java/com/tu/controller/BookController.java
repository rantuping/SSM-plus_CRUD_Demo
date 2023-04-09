package com.tu.controller;
//5。表现层的开发
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tu.controller.utils.R;
import com.tu.entity.Book;
import com.tu.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
//使用@Controller 注解，在对应的方法上，视图解析器可以解析return 的jsp,html页面，并且跳转到相应页面，若返回json等内容到页面，则需要加@ResponseBody注解
//@RestController包含了@Controller和@ResponseBody注解
@RestController
//定义在类上，则前端访问的路径需要添加 /books
@RequestMapping("/books")
public class BookController {
    //   这里要调用业务层，所以需要注入业务层
    @Autowired
    private IBookService bookService;

    // 查全部
    @GetMapping
    public R getAll(){
        return new R(true,bookService.list());
    }

    // 查单个元素
    // 使用路径变量来进行传参
    // http://localhost/books/2
    @GetMapping("{id}")
    public R getById(@PathVariable Integer id){
        return new R(true,bookService.getById(id));
    }

    // 增
    @PostMapping
    //                  使用的是：请求体参数
    public R save(@RequestBody Book book) throws IOException {
        if(book.getName().equals("123")) throw new IOException();
        boolean save = bookService.save(book);
        //走后台这里返回添加是否成功的信息
        return new R(save, save? "添加成功" : "添加失败");
    }

    // 改update
    @PutMapping
    public R update(@RequestBody Book book) {
        return new R(bookService.modify(book));
    }

    // 删delete
    @DeleteMapping("{id}")
    public R delete(@PathVariable Integer id) {
        return new R(bookService.delete(id));
    }

//    // 分页请求
//    @GetMapping("{currentPage}/{pageSize}")
//    //                         使用路径变量，将参数传入到getPage中去
//    public R getPage(@PathVariable int currentPage,@PathVariable int pageSize){
//        IPage<Book> page = bookService.getPage(currentPage, pageSize);
//        //如果当前页码值大于总页码值，那么重新执行查询操作，使用最大页码值作为当前页码值
//        if (currentPage > page.getPages()){
//            // 让查询重新执行一次
//            // 将page.getPages()对应的long类型转换为int类型。
//            //                          重新查询的时候的第一个参数为查询页数了
//            page = bookService.getPage((int)page.getPages(), pageSize);
//        }
//        return new R(true,page);
//    }

//    分页
    @GetMapping("{currentPage}/{pageSize}")
    //                         使用路径变量，将参数传入到getPage中去
    public R getPage(@PathVariable int currentPage,@PathVariable int pageSize, Book book){
//        System.out.println(pageSize);
        IPage<Book> page = bookService.getPage(currentPage, pageSize, book);
//        long total = page.getTotal();
//        System.out.println(total);
        //如果当前页码值大于总页码值，那么重新执行查询操作，使用最大页码值作为当前页码值
        if (currentPage > page.getPages()){   //这是为了前端的页码显示无bug。
            // 让查询重新执行一次
            // 将page.getPages()对应的long类型转换为int类型。
            //                          重新查询的时候的第一个参数为查询页数了
            page = bookService.getPage((int)page.getPages(),pageSize,book);
        }
        return new R(true,page);
    }
}
