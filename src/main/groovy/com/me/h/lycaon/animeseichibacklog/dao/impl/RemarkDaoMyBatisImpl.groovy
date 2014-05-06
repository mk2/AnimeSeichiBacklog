package com.me.h.lycaon.animeseichibacklog.dao.impl

import com.me.h.lycaon.animeseichibacklog.dao.RemarkDao
import com.me.h.lycaon.animeseichibacklog.dao.exception.DisallowedRequestDaoException
import com.me.h.lycaon.animeseichibacklog.domain.Remark
import com.me.h.lycaon.animeseichibacklog.persistence.RemarkMapper
import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

/**
 * Created by lycaon_h on 2014/03/21.
 */
@CompileStatic
@TypeChecked
@Slf4j
@Repository
class RemarkDaoMyBatisImpl extends DomainDaoMyBatisImpl<Remark> implements RemarkDao {

    @Autowired
    protected RemarkMapper remarkMapper


    @Override
    List<Remark> selectByFeatureId(
            long featureId
    ) {
        remarkMapper.selectByFeatureId(featureId)
    }


    @Override
    long insert(
            Remark entity
    ) {
        remarkMapper.insert(entity)
        remarkMapper.selectLastSerialId()
    }


    @Override
    @Deprecated
    void update(
            Remark entity,
            long entityId
    ) {
        throw new DisallowedRequestDaoException("This request for remark is NOT allowed.")
    }


    @Override
    List<Remark> selectAll() {
        remarkMapper.selectAll()
    }


    @Override
    Remark selectById(
            long entityId
    ) {
        remarkMapper.selectById(entityId)
    }


    @Override
    @Deprecated
    List<Remark> selectByRange(
            long offset,
            long limit
    ) {
        throw new DisallowedRequestDaoException("This request for remark is NOT allowed.")
    }


    @Override
    @Deprecated
    List<Remark> selectByTag(
            String tag
    ) {
        throw new DisallowedRequestDaoException("This request for remark is NOT allowed.")
    }


    @Override
    void delete(
            long entityId
    ) {
        remarkMapper.delete(entityId)
    }


    @Override
    long count() {
        remarkMapper.count()
    }
}
