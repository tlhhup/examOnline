package org.tlh.exam.base.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tlh.exam.base.entity.BaseEntity;
import org.tlh.exam.base.mapper.BaseMapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by 离歌笑tlh/hu ping on 2018/12/8
 * <p>
 * Github: https://github.com/tlhhup
 */
@Service
@Transactional(readOnly = true)
public abstract class BaseService<T extends BaseEntity,ID> {

    @Autowired
    private BaseMapper<T> mapper;

    @Transactional
    public boolean saveEntity(T t){
        return this.mapper.insert(t)>0;
    }

    @Transactional
    public boolean deleteEntity(T t){
       return this.mapper.delete(t)>0;
    }

    @Transactional
    public boolean deleteEntityById(ID id){
        return this.mapper.deleteByPrimaryKey(id)>0;
    }

    @Transactional
    public boolean updateById(T entity) {
        return this.mapper.updateByPrimaryKey(entity)>0;
    }

    @Transactional
    public boolean updateSelectiveById(T entity) {
        return this.mapper.updateByPrimaryKeySelective(entity)>0;
    }

    public T selectOne(T entity) {
        return this.mapper.selectOne(entity);
    }


    public T selectById(ID id) {
        return this.mapper.selectByPrimaryKey(id);
    }


    public List<T> selectList(T entity) {
        return this.mapper.select(entity);
    }


    public List<T> selectListAll() {
        return this.mapper.selectAll();
    }

    public List<T> selectByExample(Example example) {
        return this.mapper.selectByExample(example);
    }

    public int selectCountByExample(Example example) {
        return this.mapper.selectCountByExample(example);
    }

    public PageInfo<T> selectListWithPage(T t,int pageNum,int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<T> data = this.selectList(t);
        return new PageInfo<>(data);
    }

    public PageInfo<T> selectByExampleWithPage(Example example,int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<T> data = this.selectByExample(example);
        return new PageInfo<>(data);
    }
}
