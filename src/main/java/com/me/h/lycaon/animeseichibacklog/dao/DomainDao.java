package com.me.h.lycaon.animeseichibacklog.dao;

import com.me.h.lycaon.animeseichibacklog.domain.Domain;

import java.util.List;

/**
 * Created by lycaon_h on 2014/03/20.
 */
public interface DomainDao<T extends Domain> {

    /**
     * Query Executor Methods
     */

    public long insert(T entity);

    public void update(
            T entity,
            long entityId
    );

    public List<T> selectAll();

    public T selectById(long entityId);

    public List<T> selectByRange(
            long offset,
            long limit
    );

    public List<T> selectByTag(String tag);

    public void delete(long entityId);

    public long count();

}
