package com.me.h.lycaon.animeseichibacklog.dao.impl

import com.me.h.lycaon.animeseichibacklog.dao.FeatureDao
import com.me.h.lycaon.animeseichibacklog.domain.Feature
import com.me.h.lycaon.animeseichibacklog.persistence.FeatureMapper
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
class FeatureDaoMyBatisImpl extends DomainDaoMyBatisImpl<Feature> implements FeatureDao {

    @Autowired
    protected FeatureMapper featureMapper


    @Override
    List<Feature> selectByBBoxWithTagsWithUserName(
            double lngLowLeft,
            double latLowLeft,
            double lngUpRight,
            double latUpRight,
            String tags,
            String userName
    ) {
        featureMapper.selectByBBoxWithTagsWithUserName(
                lngLowLeft,
                latLowLeft,
                lngUpRight,
                latUpRight,
                tags,
                userName)
    }


    @Override
    List<Feature> selectByBBox(
            double lngLowLeft,
            double latLowLeft,
            double lngUpRight,
            double latUpRight
    ) {
        featureMapper.selectByBBox(lngLowLeft, latLowLeft, lngUpRight, latUpRight)
    }


    @Override
    long insert(
            Feature entity
    ) {
        featureMapper.insert(entity)
        featureMapper.selectLastSerialId()
    }


    @Override
    void update(
            Feature entity,
            long entityId
    ) {
        entity.featureId = entityId
        featureMapper.update(entity);
    }


    @Override
    List<Feature> selectAll() {
        featureMapper.selectAll()
    }


    @Override
    Feature selectById(
            long entityId
    ) {
        featureMapper.selectById(entityId)
    }


    @Override
    List<Feature> selectByRange(
            long offset,
            long limit
    ) {
        featureMapper.selectByRange(offset, limit)
    }


    @Override
    List<Feature> selectByTag(
            String tag
    ) {
        featureMapper.selectAll()
    }


    @Override
    void delete(
            long entityId
    ) {
        featureMapper.delete(entityId)
    }


    @Override
    long count() {
        featureMapper.count()
    }
}
