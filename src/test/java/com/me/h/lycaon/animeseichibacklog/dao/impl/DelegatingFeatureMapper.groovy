package com.me.h.lycaon.animeseichibacklog.dao.impl

import com.me.h.lycaon.animeseichibacklog.persistence.FeatureMapper

/**
 * Created by lycaon_h on 2014/03/21.
 */
class DelegatingFeatureMapper implements FeatureMapper {

    @Delegate
    FeatureMapper featureMapperDelegate

}
