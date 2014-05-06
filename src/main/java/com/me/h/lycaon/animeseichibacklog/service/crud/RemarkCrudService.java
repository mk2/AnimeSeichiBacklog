package com.me.h.lycaon.animeseichibacklog.service.crud;

import com.me.h.lycaon.animeseichibacklog.domain.Remark;

import java.util.List;

/**
 * Created by lycaon_h on 2014/03/13.
 */
public interface RemarkCrudService extends CrudService<Remark> {

    public List<Remark> readByFeatureId(long featureId);

}
