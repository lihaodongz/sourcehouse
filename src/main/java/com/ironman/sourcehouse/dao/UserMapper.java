package com.ironman.sourcehouse.dao;

import com.ironman.common.base.BaseMapper;
import com.ironman.sourcehouse.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户Mapper
 *
 * @author dazzlzy
 * @date 2018/5/19
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据条件查询用户集合
     *
     * @param user 查询条件
     * @return 用户集合
     */
    List<User> selectUser(@Param("user") User user);
    /**
     * 根据用户ID集合查询用户集合
     *
     * @param ids 用户ID集合
     * @return 用户集合
     */
    List<User> selectUserByIds(@Param("ids") List<Long> ids);


    Integer insertUser(@Param("user") User user);


    void batchInsert(@Param("userList") List<User> userList);



}