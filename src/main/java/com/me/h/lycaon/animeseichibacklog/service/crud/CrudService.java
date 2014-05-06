package com.me.h.lycaon.animeseichibacklog.service.crud;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lycaon_h on 2014/03/04.
 */
public interface CrudService<T> {

    @Transactional
    public long create(T entity);


    public T readById(long id);


    public List<T> readByRange(
            long offset,
            long limit
    );


    public List<T> readAll();


    public List<T> readByTag(String tag);


    public List<T> readByTags(String[] tags);


    public List<T> readByTags(List<String> tags);


    @Transactional
    public void update(
            long id,
            T entity
    );


    @Transactional
    public void delete(long id);


    public long count();
}
