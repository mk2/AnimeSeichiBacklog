package com.me.h.lycaon.animeseichibacklog.dao.impl

import com.me.h.lycaon.animeseichibacklog.persistence.RemarkMapper

/**
 * Created by lycaon_h on 2014/03/21.
 */
class DelegatingRemarkMapper implements RemarkMapper {

    @Delegate
    RemarkMapper remarkMapperDelegate

}
