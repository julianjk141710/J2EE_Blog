package com.j2ee.blog.po;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "t_type")
public class Type {

    @Id
    @GeneratedValue
    private Long id;
    private String name;


    //说明是一对多关系中的一，并且是被维护关系的一方
    @OneToMany(mappedBy = "type")
    private List<Blog> blogs = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }
    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
