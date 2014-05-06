package com.me.h.lycaon.animeseichibacklog.service.converter.impl

import com.google.common.cache.Cache
import com.google.common.cache.CacheBuilder
import com.me.h.lycaon.animeseichibacklog.domain.User
import com.me.h.lycaon.animeseichibacklog.model.UserViewModel
import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.convert.converter.Converter

/**
 * Created by lycaon_h on 2014/03/24.
 */
@CompileStatic
@TypeChecked
class UserToUserViewModel implements Converter<User, UserViewModel> {

    Cache<String, UserViewModel> userViewModelCache


    @Autowired
    UserToUserViewModel(
            CacheBuilder conversionCacheBuilder
    ) {
        userViewModelCache = conversionCacheBuilder.build()
    }


    @Override
    UserViewModel convert(
            User user
    ) {
        String key = "" + user.userId + user.userUpdateDate.time
        userViewModelCache.get(key, {
            UserViewModel userViewModel = new UserViewModel(user);

            userViewModel.userName = ""
            userViewModel.userPassword = ""
            userViewModel.userEmail = ""
            userViewModel.userReputationVotersCount = 0

            return userViewModel
        });
    }
}
