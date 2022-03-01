package com.imoc.order.redisUser.dao;

import com.imoc.order.redisUser.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户表(TUser表数据库访问层)
 *
 * @author liurui
 * @since 2022-03-01 17:04:24
 */
public interface TUserMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User selectByPrimaryKey(Integer id);

    /**
     * 通过IDS查询
     *
     * @param ids List<String> ids
     * @return List<TUser>
     */
    List<User> selectByids(@Param("ids") List<String> ids);

    /**
     * 通过各个字段查询
     *
     * @param user 查询条件
     * @return List<TUser>
     */
    List<User> selectByFilter(User user);

    /**
     * 新增
     *
     * @param user
     * @return
     */
    int insertSelective(User user);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<TUser> 实例对象列表
     * @return
     */
    int insertBatch(@Param("entities") List<User> entities);

    /**
     * 修改
     *
     * @param user
     * @return
     */
    int updateByPrimaryKeySelective(User user);

    /**
     * ͨ删除
     *
     * @param id 主键
     * @return
     */
    int deleteById(Integer id);

}
