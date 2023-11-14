package com.moveon.boot.dao;

import java.util.List;

/**
 * @ClassName BaseDao
 * @Description TODO
 * @Author Moveon
 * @Date 2023/10/21 21:35
 * @Version 1.0
 **/
public interface BaseDao<T> {

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
     * 根据id查询记录
     * @param id
     * @return
     */
    T get(long id);

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
