package com.example.springbootbook.controller;

import com.example.springbootbook.model.Book;
import com.example.springbootbook.service.BookService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Book)表控制层
 *
 * @author makejava
 * @since 2020-09-25 21:33:26
 */
//实现跨域注解
//origin="*"代表所有域名都可访问
//maxAge飞行前响应的缓存持续时间的最大年龄，简单来说就是Cookie的有效期 单位为秒
//若maxAge是负数,则代表为临时Cookie,不会被持久化,Cookie信息保存在浏览器内存中,浏览器关闭Cookie就消失
//@CrossOrigin
@RestController
@RequestMapping("book")
@Api(tags = {"Book表控制器"}) // tags是对Controller的接口重新分类
public class BookController {
    /**
     * 服务对象
     */
    @Resource
    private BookService bookService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation("通过主键查询单条数据方法")
    @GetMapping("/findById/{id}")
    public Book findById(@ApiParam("book id") @PathVariable("id") Integer id) {
        //rabbitTemp
        return this.bookService.queryById(id);
    }


    /**
     * 分页查询
     * @param page 当前页
     * @param size 每页大小
     * @return book分页列表
     */
    @GetMapping("/findAll/{page}/{size}")
    @ApiOperation("分页查询方法")
    public PageInfo<Book> findAll(@ApiParam("当前页") @PathVariable int page,@ApiParam("每页大小") @PathVariable int size){
        PageHelper.startPage(page,size);
        System.out.println("controller层分页查询方法");
        List<Book> bookList=bookService.queryAllByLimit();
        PageInfo<Book> bookinfo = new PageInfo<>(bookList);//封装为PageInfo
//        System.out.println(bookinfo);
        return bookinfo;
    }


    /**
     * 添加book
     * @param book 对象
     * @return 操作结果
     */
    @PostMapping("/save")
    @ApiOperation("添加book方法")
    public String save(@ApiParam("book对象") @RequestBody Book book){
        Book book1= bookService.insert(book);
        if (book1 != null){
            return "success";
        }else{
            return "error";
        }
    }

    /**
     * 更新
     * @param book book对象
     * @return 操作结果
     */
    @PutMapping("/update")
    @ApiOperation("更新方法")
    public String update(@ApiParam("book对象") @RequestBody Book book){
        Book result = bookService.update(book);
        if(result != null){
            return "success";
        }else{
            return "error";
        }
    }

    /**
     * 删除
     * @param id id
     */
    @DeleteMapping("/deleteById/{id}")
    @ApiOperation("删除方法")
    public void deleteById(@ApiParam("book id") @PathVariable("id") Integer id){
        bookService.deleteById(id);
    }
}