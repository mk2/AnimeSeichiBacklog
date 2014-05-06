package com.me.h.lycaon.animeseichibacklog.service.crud.impl

import com.google.common.cache.Cache
import com.google.common.cache.CacheBuilder
import com.me.h.lycaon.animeseichibacklog.dao.FeatureDao
import com.me.h.lycaon.animeseichibacklog.domain.Feature
import com.me.h.lycaon.animeseichibacklog.service.crud.FeatureCrudService
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
/**
 * Created by lycaon_h on 2014/03/05.
 */
@CompileStatic
@Slf4j
@Service
final class FeatureCrudServiceGroovyImpl implements FeatureCrudService {

    Cache<Serializable, Collection<Feature>> featureCache


    @Autowired
    FeatureCrudServiceGroovyImpl(
            CacheBuilder crudCacheBuilder
    ) {
        featureCache = crudCacheBuilder.build()
    }

    @Autowired
    FeatureDao featureDao


    @Override
    long create(
            Feature feature
    ) {
        featureDao.insert(feature)
    }


    @Override
    Feature readById(
            final long id
    ) {
        featureCache.get(id, {
            [featureDao.selectById(id)]
        }).toList()[0]
    }


    @Override
    List<Feature> readAll() {
        featureCache.get("all", {
            featureDao.selectAll()
        }).toList()
    }


    @Override
    List<Feature> readByRange(
            long offset,
            long limit
    ) {
        featureCache.get("${offset}-${limit}", {
            featureDao.selectByRange(offset, limit)
        }).toList()
    }


    @Override
    List<Feature> readByBBOX(
            double lngLowLeft,
            double latLowLeft,
            double lngUpRight,
            double latUpRight
    ) {
        featureDao.selectByBBox(lngLowLeft, latLowLeft, lngUpRight, latUpRight)
    }


    @Override
    List<Feature> readByBBOX(
            double[] lngLats
    ) {
        this.readByBBOX(lngLats[0], lngLats[1], lngLats[2], lngLats[3])
    }


    @Override
    List<Feature> readByBBOX(
            List<Double> lngLats
    ) {
        this.readByBBOX(lngLats[0], lngLats[1], lngLats[2], lngLats[3])
    }


    @Override
    List<Feature> readByBBOX(
            double lngLowLeft,
            double latLowLeft,
            double lngUpRight,
            double latUpRight,
            Map<String, String> options
    ) {

        if (options.size() > 0) {
            def noNullOptions = [*: options].withDefault { "%%" }

            String tags = "tags LIKE '" + (noNullOptions["tags"] as String).split(/,/).join("' OR tags LIKE '") + "'"
            String userName = noNullOptions["userName"]

            featureDao.selectByBBoxWithTagsWithUserName(
                    lngLowLeft,
                    latLowLeft,
                    lngUpRight,
                    latUpRight,
                    tags,
                    userName)
        } else {
            this.readByBBOX(lngLowLeft, latLowLeft, lngUpRight, latUpRight)
        }
    }


    @Override
    List<Feature> readByBBOX(
            double[] lngLats,
            Map<String, String> options
    ) {
        this.readByBBOX(lngLats[0], lngLats[1], lngLats[2], lngLats[3], options)
    }


    @Override
    List<Feature> readByBBOX(
            List<Double> lngLats,
            Map<String, String> options
    ) {
        this.readByBBOX(lngLats[0], lngLats[1], lngLats[2], lngLats[3], options)
    }


    @Override
    List<Feature> readByTag(
            String tag
    ) {
        featureCache.get(tag, {
            featureDao.selectByTag(tag)
        }).toList()
    }


    @Override
    List<Feature> readByTags(
            String[] tags
    ) {
        null
    }


    @Override
    List<Feature> readByTags(
            List<String> tags
    ) {
        readByTags(tags as String[])
    }


    @Override
    void update(
            long id,
            Feature entity
    ) {
        featureDao.update(entity, id)
    }


    @Override
    void delete(
            long id
    ) {
        featureDao.delete(id)
    }


    @Override
    long count() {
        featureDao.count()
    }
}
