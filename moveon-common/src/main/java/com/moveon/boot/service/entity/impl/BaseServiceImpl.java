package com.moveon.boot.service.entity.impl;

import com.moveon.boot.dao.BaseDao;
import com.moveon.boot.entity.BaseEntity;
import com.moveon.boot.service.entity.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.util.List;

/**
 * @ClassName BaseServiceImpl
 * @Description TODO
 * @Author Moveon
 * @Date 2023/10/21 21:45
 * @Version 1.0
 **/
public abstract class BaseServiceImpl<D extends BaseDao, T extends BaseEntity> implements BaseService<T> {

    @Autowired
    private D dao;

    @Override
    public int insert(T entity) {
        return this.dao.insert(entity);
    }

    @Override
    public int batchInsert(List<T> list) {
        return this.dao.batchInsert(list);
    }

    @Override
    public int delete(long id) {
        return this.dao.delete(id);
    }

    @Override
    public int update(T entity) {
        return this.dao.update(entity);
    }

    @Override
    @Deprecated
    public int update(T entity, MapSqlParameterSource map) {
        return 0;
    }

    @Override
    public T get(long id) {
        return (T) this.dao.get(id);
    }

    @Override
    public List<T> list(long[] ids) {
        return this.dao.list(ids);
    }

    @Override
    public List<T> list(T entity) {
        return this.dao.list(entity);
    }

}
