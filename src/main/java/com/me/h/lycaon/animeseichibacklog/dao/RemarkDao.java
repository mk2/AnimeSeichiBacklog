package com.me.h.lycaon.animeseichibacklog.dao;

import com.me.h.lycaon.animeseichibacklog.domain.Remark;

import java.util.List;

/**
 * Created by lycaon_h on 2014/03/20.
 */
public interface RemarkDao extends DomainDao<Remark> {

    public List<Remark> selectByFeatureId(long featureId);

}
