package com.moveon.boot.service.entity;

import com.moveon.boot.entity.BaseEntity;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.util.List;

/**
 * @ClassName BaseService
 * @Description TODO
 * @Author Moveon
 * @Date 2023/10/21 21:43
 * @Version 1.0
 **/
public interface BaseService<T extends BaseEntity> {

    /**
     * 插入数据
     * @param entity
     * @return
     */
    int insert(T entity);

    /**
     * 批量插入数据
     * @param list
     * @return
     */
    int batchInsert(List<T> list);

    /**
     * 根据id删除数据
     * @param id
     * @return
     */
    int delete(long id);

    /**
     * 根据id修改数据（传入的对象需要带id）
     * @param entity
     * @return
     */
    int update(T entity);

    /**
     * 将map传入的参数作为条件修改成entity的内容
     * @param entity
     * @param map where条件
     * @return
     */
    int update(T entity, MapSqlParameterSource map);

    /**
     * 根据id查询记录
     * @param id
     * @return
     */
    T get(long id);

    /**
     * 根据多个条件查询数据
     * @param entity
     * @return
     */
    T get(T entity);

    /**
     * 根据多个id查询多条数据
     * @param ids
     * @return
     */
    List<T> list(long[] ids);

    /**
     * 按条件查询数据
     * @param entity
     * @return
     */
    List<T> list(T entity);
}
