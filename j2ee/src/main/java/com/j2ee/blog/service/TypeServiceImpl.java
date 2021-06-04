package com.j2ee.blog.service;

import com.j2ee.blog.NotFoundException;
import com.j2ee.blog.dao.TypeRepository;
import com.j2ee.blog.po.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import org.springframework.data.domain.Pageable;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    /**
     * @Transactional 是声明式事务管理 编程中使用的注解
     * @Transactional 注解，使用默认配置，抛出异常之后，事务会自动回滚，数据不会插入到数据库。
     * 1 .添加位置
     *
     * 1）接口实现类或接口实现方法上，而不是接口类中。
     * 2）访问权限：public 的方法才起作用。@Transactional 注解应该只被应用到 public 方法
     * @param type
     * @return
     */
    @Transactional
    @Override
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    /**
     * 用以上方法findOne(id);报错，经过网上查询资料最后找到原因。解决办法如下，
     * 1、springboot 版本问题，将 2.0.1 版本换成 1.5.4 版本。
     * 2、将studentRespository.findOne(id); 改为 studentRespository.findById(id).orElse(null);
     * 3、将studentRespository.findOne(id); 改为
     * return studentRespository.findById(id).get();
     * ————————————————
     * @param id
     * @return
     */
    @Transactional
    @Override
    public Type getType(Long id) {
        return typeRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    /**
     * Pageable 是Spring Data库中定义的一个接口，用于构造翻页查询，
     * 是所有分页相关信息的一个抽象，通过该接口，我们可以得到和分页相关所有信息（例如pageNumber、pageSize等），
     * 这样，Jpa就能够通过pageable参数来得到一个带分页信息的Sql语句。
     */
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
        Type t = typeRepository.findById(id).orElse(null);
        if (t == null) {
            throw new NotFoundException("不存在该类型");
        }
        /**
         * BeanUtils.copyProperties(type, t);是一个浅拷贝
         */
        BeanUtils.copyProperties(type, t);
        return typeRepository.save(t);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeRepository.findByName(name);
    }

    @Transactional
    @Override
    public void deleteType(Long id) {
        typeRepository.deleteById(id);
    }
}
