package com.example.springbootbook.service;

import com.example.springbootbook.model.Book;

import java.util.List;

/**
 * (Book)表服务接口
 *
 * @author makejava
 * @since 2020-09-25 21:33:24
 */
public interface BookService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Book queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    List<Book> queryAllByLimit();

    /**
     * 新增数据
     *
     * @param book 实例对象
     * @return 实例对象
     */
    Book insert(Book book);

    /**
     * 修改数据
     *
     * @param book 实例对象
     * @return 实例对象
     */
    Book update(Book book);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}