package com.example.springbootbook.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * (Book)实体类
 *
 * @author makejava
 * @since 2020-09-25 21:33:23
 */
@ApiModel(description = "(Book)实体类")
public class Book implements Serializable {
    private static final long serialVersionUID = -57040448457164324L;

    private Integer id;
    //Api模型属性
    @ApiModelProperty("书名")
    private String name;

    private String author;

    private String publish;

    private Integer pages;

    private Float price;

    private Integer bookcaseid;

    private Integer abled;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getBookcaseid() {
        return bookcaseid;
    }

    public void setBookcaseid(Integer bookcaseid) {
        this.bookcaseid = bookcaseid;
    }

    public Integer getAbled() {
        return abled;
    }

    public void setAbled(Integer abled) {
        this.abled = abled;
    }

}