package com.moveon.boot.service.entity.impl;

import com.moveon.boot.annotation.entity.TableEntity;
import com.moveon.boot.annotation.entity.TableField;
import com.moveon.boot.constant.DatabaseConstants;
import com.moveon.boot.entity.BaseEntity;
import com.moveon.boot.service.entity.BaseService;
import com.moveon.boot.util.StringUtils;
import com.moveon.boot.util.generator.id.IDGenerator;
import com.moveon.boot.util.reflect.ReflectUtils;
import com.moveon.boot.util.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.*;

/**
 * @ClassName AutoBaseServiceImpl
 * @Description TODO
 * @Author Moveon
 * @Date 2023/10/22 08:38
 * @Version 1.0
 **/
public class AutoBaseServiceImpl<T extends BaseEntity> implements BaseService<T> {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    // 对应实体类型
    private Class<T> aClass;
    // 实体的类型的字段
    private List<Field> entityFields;
    // BaseEntity的基础字段
    private final List<Field> baseFields = Arrays.asList(BaseEntity.class.getDeclaredFields());
    // 所有字段
    private List<Field> allFields;
    // 表的字段
    private List<Field> tableFields;
    // 查询全字段
    private StringBuilder selectSql = new StringBuilder("SELECT ");
    // 对应表名
    private String tableName;

    public AutoBaseServiceImpl() {
        this.aClass = getEntityClass();
        this.entityFields = Arrays.asList(aClass.getDeclaredFields());
        this.tableName = aClass.getAnnotation(TableEntity.class).tableName();
        this.allFields = new ArrayList<>();
        allFields.addAll(entityFields);
        allFields.addAll(baseFields);
        this.tableFields = new ArrayList<>();
        for (Field entityField : allFields) {
            if (entityField.getAnnotation(TableField.class) != null) {
                selectSql.append(StringUtils.toUnderScoreCase(entityField.getName()) + ", ");
                tableFields.add(entityField);
            }
        }
        selectSql.deleteCharAt(selectSql.length() - 2);
        selectSql.append(" FROM " + tableName);
    }

    protected Class<T> getEntityClass() {
        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<T>) parameterizedType.getActualTypeArguments()[0];
    }

    @Override
    public int insert(T entity) {
        preInsert(entity);
        StringBuilder sql = new StringBuilder("INSERT INTO");
        StringBuilder values = new StringBuilder();
        String tableName = aClass.getAnnotation(TableEntity.class).tableName();
        Map<String, Object> queryMap = new HashMap<>();
        sql.append(" " + tableName + "(");
        for (Field field : allFields) {
            if (field.getAnnotation(TableField.class) == null) {
                continue;
            }
            String propertyName = field.getName();
            String tableField = StringUtils.toUnderScoreCase(propertyName);
            sql.append(tableField + ", ");
            values.append(":" + propertyName + ", ");
            queryMap.put(propertyName, ReflectUtils.invokeGetter(entity, propertyName));
        }
        sql.deleteCharAt(sql.length() - 2);
        values.deleteCharAt(values.length() - 2);
        sql.append(")VALUES(" + values + ")");
        return jdbcTemplate.update(sql.toString(), queryMap);
    }

    @Override
    public int batchInsert(List<T> list) {
        int insertedRecords = 0;
        int totalRecords = list.size();
        while (insertedRecords < totalRecords) {
            int endIndex = Math.min(insertedRecords + totalRecords, totalRecords);
            insertedRecords += DatabaseConstants.BATCH_INSERT_SIZE;
            List<T> ts = list.subList(insertedRecords, endIndex);
            StringBuilder sql = new StringBuilder("INSERT INTO");
            StringBuilder values = new StringBuilder();
            MapSqlParameterSource queryMap = new MapSqlParameterSource();
            sql.append(" " + tableName + "(");
            for (int i = 0; i < ts.size(); i++) {
                T entity = list.get(i);
                values.append("(");
                for (Field field : tableFields) {
                    String propertyName = field.getName();
                    String tableField = StringUtils.toUnderScoreCase(propertyName);
                    if (i == 0) {
                        sql.append(tableField + ", ");
                    }
                    values.append(":" + propertyName + i + ", ");
                    queryMap.addValue(propertyName + i, ReflectUtils.invokeGetter(entity, propertyName));
                }
                values.insert(values.length() - 2, ")");
            }
            values.deleteCharAt(values.length() - 2);
            sql.deleteCharAt(sql.length() - 2);
            sql.append(")VALUES" + values);
            jdbcTemplate.update(sql.toString(), queryMap);
        }
        return totalRecords;
    }

    @Override
    public int delete(long id) {
        String sb = "DELETE FROM " + tableName + " WHERE id = :id";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        return jdbcTemplate.update(sb, map);
    }

    @Override
    public int update(T entity) {
        preUpdate(entity);
        StringBuilder sb = updateSql(entity);
        sb.append(" WHERE del_flag = '0' AND id = :id");
        return 0;
    }

    @Override
    public int update(T entity, MapSqlParameterSource map) {
        preUpdate(entity);
        StringBuilder sb = updateSql(entity);
        sb.append(" WHERE del_flag = '0'");
        for (Map.Entry<String, Object> entry : map.getValues().entrySet()) {
            String propertyName = entry.getKey();
            sb.append(" AND " + StringUtils.toUnderScoreCase(propertyName) + " = :" + propertyName);
        }
        return jdbcTemplate.update(sb.toString(), map);
    }

    @Override
    public T get(long id) {
        StringBuilder sb = new StringBuilder(selectSql);
        sb.append(" WHERE del_flag = '0' AND id = :id");
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        return getT(sb, map);
    }

    @Override
    public T get(T entity) {
        StringBuilder sb = new StringBuilder(selectSql);
        MapSqlParameterSource queryMap = new MapSqlParameterSource();
        StringBuilder querySQL = getQuerySQL(entity, sb, queryMap);
        return getT(querySQL, queryMap);
    }

    @Override
    public List<T> list(long[] ids) {
        StringBuilder sb = new StringBuilder("SELECT ");
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("ids", ids);
        for (Field tableField : tableFields) {
            sb.append(StringUtils.toUnderScoreCase(tableField.getName()) + ", ");
        }
        sb.deleteCharAt(sb.length() - 2);
        sb.append(" FROM " + tableName + " WHERE del_flag = '0' AND id IN(:ids)");
        return jdbcTemplate.query(sb.toString(), map, new BeanPropertyRowMapper<>(aClass));
    }

    @Override
    public List<T> list(T entity) {
        StringBuilder sb = new StringBuilder(selectSql);
        MapSqlParameterSource queryMap = new MapSqlParameterSource();
        StringBuilder querySQL = getQuerySQL(entity, sb, queryMap);
        return jdbcTemplate.query(querySQL.toString(), queryMap, new BeanPropertyRowMapper<>(aClass));
    }

    private StringBuilder updateSql(T entity) {
        StringBuilder sb = new StringBuilder("UPDATE " + tableName + " SET");
        for (Field tableField : tableFields) {
            String propertyName = tableField.getName();
            if (ReflectUtils.invokeGetter(entity, propertyName) != null) {
                sb.append(" " + StringUtils.toUnderScoreCase(propertyName) + " = :" + propertyName + ", ");
            }
        }
        sb.deleteCharAt(sb.length() - 2);
        return sb;
    }

    private void preInsert(T entity) {
        entity.setId(IDGenerator.createId());
        entity.setCreateTime(new Date());
        entity.setCreateBy(SecurityUtils.getUserId());
        entity.setDelFlag("0");
    }

    private void preUpdate(T entity) {
        entity.setUpdateTime(new Date());
        entity.setUpdateBy(SecurityUtils.getUserId());
    }

    private T getT(StringBuilder sb, MapSqlParameterSource map) {
        try {
            List<T> list = jdbcTemplate.query(sb.toString(), map, new BeanPropertyRowMapper<>(aClass));
            if (list != null && list.size() == 1) {
                return list.get(0);
            } else if (list.size() > 1) {
                throw new RuntimeException("查询到了多条记录，请确保条件无误");
            } else {
                return null;
            }
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private StringBuilder getQuerySQL(T entity, StringBuilder sb, MapSqlParameterSource queryMap) {
        StringBuilder query = new StringBuilder(" WHERE del_flag = '0'");
        for (Field tableField : tableFields) {
            String fieldName = tableField.getName();
            Object o = ReflectUtils.invokeGetter(entity, fieldName);
            if(o != null) {
                query.append(" AND " + StringUtils.toUnderScoreCase(fieldName) + "= :" + fieldName);
                queryMap.addValue(fieldName, o);
            }
        }
        sb.append(query);
        return sb;
    }

}
