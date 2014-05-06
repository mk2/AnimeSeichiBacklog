package com.me.h.lycaon.animeseichibacklog.persistence;

import com.me.h.lycaon.animeseichibacklog.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Interface for MyBatis
 * <p/>
 * Method naming rule:
 * As much as possible, the method names should begin with that executing sql command name.
 * <p/>
 * For instance:
 * a method execute "SELECT ..." -> the method name begin with "select"
 * a method execute "INSERT ..." -> the method name begin with "insert"
 * <p/>
 * Created by lycaon_h on 2014/03/04.
 */
public interface UserMapper {

    public void insert(User user);


    public long selectLastSerialId();


    public void insertUserRole(
            @Param("role_id") long roleId,
            @Param("userId") long userId,
            @Param("userRole") String userRole
    );


    public void update(User user);


    public List<User> selectAll();


    public User selectById(Long userId);


    public User selectByName(String userName);


    public User selectByEmail(String userEmail);


    public List<User> selectByTag(String tag);


    @Transactional
    public void delete(Long userId);


    public long count();

}
