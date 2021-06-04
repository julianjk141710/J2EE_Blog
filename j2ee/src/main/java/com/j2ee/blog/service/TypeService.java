package com.j2ee.blog.service;

import com.j2ee.blog.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TypeService {

    Type saveType(Type type);

    Type getType(Long id);

    Page<Type> listType(Pageable pageable);

    Type updateType(Long id, Type type);

    Type getTypeByName(String name);

    void deleteType(Long id);
}
