package com.example.springbootbook.service.impl;

import com.example.springbootbook.mapper.BookMapper;
import com.example.springbootbook.model.Book;
import com.example.springbootbook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * (Book)表服务实现类
 *
 * @author makejava
 * @since 2020-09-25 21:33:26
 */

@Service()
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;


    /**
     * 通过ID查询单条数据
     *
     * Cacheable注解表示先从缓存中通过定义的键值进行查询，如果查询不到则进行数据库查询并将查询结果保存到缓存中，其中：
     * value属性为spring application.properties配置文件中设置的缓存名称
     * key表示缓存的键值名称，其中id说明该方法需要一个名为id的参数
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    @Cacheable(value = "bk",key = "'redis_book_'+#id")
    public Book queryById(Integer id) {
        System.out.println("从数据库中查找book");
        return this.bookMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * 命中率低，所以不采用缓存机制
     * @return 对象列表
     */
    @Override
    //查询：先查缓存是否有，有则直接取缓存中数据，没有则运行方法中的代码并缓存
//    @Cacheable(value = "bk")
    public List<Book> queryAllByLimit() {
        System.out.println("从数据库中查找book列表");
        return this.bookMapper.queryAllByLimit();
    }

    /**
     * 新增数据
     *
     * CachePut注解表示将方法的返回结果存放到缓存中
     * value和key属性与上述意义一样，需要注意的是，key中的使用了result.id的方式
     * 这里的result表示该方法的返回值对象，即为user，id为取该对象的id属性值
     *
     * 这里在插入book时，传入的book参数是不存在id属性的，在mapper.xml文件中insert使用了如下的属性设置：
     * useGeneratedKeys="true" keyProperty="id"
     * 意味着，book的id属性会进行自增，并在book插入成功后会将指定的id属性进行回填，因此如下方法的返回值为带有id属性的完整book对象
     *
     * @param book 实例对象
     * @return 实例对象
     */
    @Override
    @Transactional
    @CachePut(value = "bk", key = "'redis_book_'+#result.id")
    public Book insert(Book book) {
        this.bookMapper.insert(book);
        return book;
    }

    /**
     * 修改数据
     * CachePut注解表示将方法的返回结果存放到缓存中
     * 这里在CachePut注解中使用了condition配置项，它是一个Spring的EL，这个表达式要求返回Boolean类型的值，如果为true
     * 则使用缓存操作，否则不使用。
     *
     * @param book 实例对象
     * @return 实例对象
     */
    @Override
    @Transactional
    @CachePut(value = "bk", condition = "#result != null ", key = "'redis_book_'+#book.id")
    public Book update(Book book) {
        this.bookMapper.update(book);
        return this.queryById(book.getId());
    }

    /**
     * 通过主键删除数据
     *
     * CacheEvict注解通过定义的键移除相应的缓存,beforeInvocation属性表示是在方法执行之前还是之后移除缓存
     * ,默认为false,即为方法之后移除缓存
     * @param id 主键
     * @return 是否成功
     */
    @Override
    @Transactional
    @CacheEvict(value = "bk", key = "'redis_book_'+#id", beforeInvocation = false)
    public boolean deleteById(Integer id) {
        return this.bookMapper.deleteById(id) > 0;
    }
}