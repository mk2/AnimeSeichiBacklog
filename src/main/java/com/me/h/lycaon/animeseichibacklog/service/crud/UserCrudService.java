package com.me.h.lycaon.animeseichibacklog.service.crud;

import com.me.h.lycaon.animeseichibacklog.domain.User;

/**
 * Created by lycaon_h on 2014/03/07.
 */
public interface UserCrudService extends CrudService<User> {

    public User readByEmail(String userEmail);


    public User readByName(String name);

}
