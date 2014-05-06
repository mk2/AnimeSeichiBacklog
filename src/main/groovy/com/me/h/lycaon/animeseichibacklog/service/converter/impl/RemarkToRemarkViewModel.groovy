package com.me.h.lycaon.animeseichibacklog.service.converter.impl

import com.google.common.cache.Cache
import com.google.common.cache.CacheBuilder
import com.me.h.lycaon.animeseichibacklog.domain.Remark
import com.me.h.lycaon.animeseichibacklog.model.RemarkViewModel
import com.me.h.lycaon.animeseichibacklog.service.user.UserInfoService
import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.convert.converter.Converter

/**
 * Created by lycaon_h on 2014/03/23.
 */
@CompileStatic
@TypeChecked
class RemarkToRemarkViewModel implements Converter<Remark, RemarkViewModel> {

    @Autowired
    UserInfoService userInfoService

    Cache<String, RemarkViewModel> remarkViewModelCache


    @Autowired
    RemarkToRemarkViewModel(
            CacheBuilder conversionCacheBuilder
    ) {
        remarkViewModelCache = conversionCacheBuilder.build()
    }


    @Override
    RemarkViewModel convert(
            final Remark remark
    ) {
        String key = "" + remark.remarkId + remark.remarkCreateDate.time
        remarkViewModelCache.get(key, {
            new RemarkViewModel(remark)
        });
    }
}
