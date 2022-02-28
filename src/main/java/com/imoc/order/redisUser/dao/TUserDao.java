package com.imoc.order.redisUser.dao;

import com.imoc.order.redisUser.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 用户表(TUser)表数据库访问层
 *
 * @author makejava
 * @since 2022-02-28 22:51:21
 */
public interface TUserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param tUser    查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<User> queryAllByLimit(User tUser, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param tUser 查询条件
     * @return 总行数
     */
    long count(User tUser);

    /**
     * 新增数据
     *
     * @param tUser 实例对象
     * @return 影响行数
     */
    int insert(User tUser);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<TUser> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<User> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<TUser> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<User> entities);

    /**
     * 修改数据
     *
     * @param tUser 实例对象
     * @return 影响行数
     */
    int update(User tUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

