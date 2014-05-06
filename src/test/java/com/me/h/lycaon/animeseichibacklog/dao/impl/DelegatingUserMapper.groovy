package com.me.h.lycaon.animeseichibacklog.dao.impl

import com.me.h.lycaon.animeseichibacklog.persistence.UserMapper

/**
 * Created by lycaon_h on 2014/03/21.
 */
class DelegatingUserMapper implements UserMapper {

    @Delegate
    UserMapper userMapperDelegate

}
