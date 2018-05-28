package com.chenbk.boot.model;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by Kang on 2018/5/9.
 */
@ApiModel(value="user对象",description="user对象")
public class User implements Serializable {


    @ApiModelProperty(value = "用户ID",required = true,notes = "用户ID")
    private Integer id;

    @ApiModelProperty(value = "用户名称",required = true,notes = "用户名称")
    private String name;

    @ApiModelProperty(value = "用户年龄",required = true,notes = "用户年龄")
    private Integer age;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

