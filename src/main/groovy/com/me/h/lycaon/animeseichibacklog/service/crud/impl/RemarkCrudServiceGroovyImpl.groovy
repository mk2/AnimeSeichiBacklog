package com.me.h.lycaon.animeseichibacklog.service.crud.impl

import com.google.common.cache.Cache
import com.google.common.cache.CacheBuilder
import com.me.h.lycaon.animeseichibacklog.dao.RemarkDao
import com.me.h.lycaon.animeseichibacklog.domain.Remark
import com.me.h.lycaon.animeseichibacklog.service.crud.RemarkCrudService
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
/**
 * Created by lycaon_h on 2014/03/22.
 */
@CompileStatic
@Slf4j
@Service
final class RemarkCrudServiceGroovyImpl implements RemarkCrudService {

    Cache<Serializable, Collection<Remark>> remarkCache


    @Autowired
    RemarkCrudServiceGroovyImpl(
            CacheBuilder crudCacheBuilder
    ) {
        remarkCache = crudCacheBuilder.build()
    }

    @Autowired
    RemarkDao remarkDao


    @Override
    List<Remark> readByFeatureId(
            long featureId
    ) {
        remarkCache.get("f${featureId}", {
            remarkDao.selectByFeatureId(featureId)
        }).toList()
    }


    @Override
    long create(
            Remark entity
    ) {
        return remarkDao.insert(entity)
    }


    @Override
    Remark readById(
            long id
    ) {
        remarkCache.get("r${id}", {
            [remarkDao.selectById(id)]
        }).toList()[0]
    }


    @Override
    List<Remark> readByRange(
            long offset,
            long limit
    ) {
        remarkCache.get("${offset}-${limit}", {
            remarkDao.selectByRange(offset, limit)
        }).toList()
    }


    @Override
    List<Remark> readAll() {
        remarkCache.get("all", {
            remarkDao.selectAll()
        }).toList()
    }


    @Override
    List<Remark> readByTag(
            String tag
    ) {
        remarkCache.get("t{$tag}", {
            remarkDao.selectByTag(tag)
        }).toList()
    }


    @Override
    List<Remark> readByTags(
            String[] tags
    ) {
        null
    }


    @Override
    List<Remark> readByTags(
            List<String> tags
    ) {
        readByTags(tags as String[])
    }


    @Override
    void update(
            long id,
            Remark entity
    ) {
        remarkDao.update(entity, id)
    }


    @Override
    void delete(
            long id
    ) {
        remarkDao.delete(id)
    }


    @Override
    long count() {
        remarkDao.count()
    }
}
