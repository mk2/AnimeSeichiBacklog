package com.me.h.lycaon.animeseichibacklog.dao;

import com.me.h.lycaon.animeseichibacklog.domain.User;

/**
 * Created by lycaon_h on 2014/03/20.
 */
public interface UserDao extends DomainDao<User> {

    public User selectByName(String userName);

    public User selectByEmail(String userEmail);

    public void insertUserRole(
            long roleId,
            long userId,
            String userRole
    );

}
