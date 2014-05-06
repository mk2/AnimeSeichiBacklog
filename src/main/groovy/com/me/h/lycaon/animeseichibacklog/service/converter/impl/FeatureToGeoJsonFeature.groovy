package com.me.h.lycaon.animeseichibacklog.service.converter.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.common.cache.Cache
import com.google.common.cache.CacheBuilder
import com.me.h.lycaon.animeseichibacklog.domain.Feature
import com.me.h.lycaon.animeseichibacklog.service.user.UserInfoService
import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import org.geojson.Feature as GeoJsonFeature
import org.geojson.GeoJsonObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.convert.converter.Converter

/**
 * Created by lycaon_h on 2014/03/07.
 */
@CompileStatic
@TypeChecked
class FeatureToGeoJsonFeature implements Converter<Feature, GeoJsonFeature> {

    @Autowired
    UserInfoService userInfoService

    Cache<String, GeoJsonFeature> geoJsonFeatureCache

    final ObjectMapper objectMapper = new ObjectMapper()


    @Autowired
    FeatureToGeoJsonFeature(
            CacheBuilder conversionCacheBuilder
    ) {
        geoJsonFeatureCache = conversionCacheBuilder.build()
    }


    @Override
    GeoJsonFeature convert(
            final Feature feature
    ) {
        String key = "" + feature.featureId + feature.featureUpdateDate.time
        geoJsonFeatureCache.get(key, {
            final long userId = userInfoService.userId

            GeoJsonFeature geoJsonFeature = new GeoJsonFeature()

            geoJsonFeature.properties["featureId"] = feature.featureId
            geoJsonFeature.properties["featureName"] = feature.featureName
            geoJsonFeature.properties["featureDescription"] = feature.featureDescription
            geoJsonFeature.properties["featureTags"] = feature.featureTags
            geoJsonFeature.properties["featureImagePath"] = feature.featureImagePath

            geoJsonFeature.properties["isEditable"] =
                    (feature.isEditable() && feature.createUserId == userId)
            geoJsonFeature.properties["isOtherUserEditable"] =
                    (feature.isOtherUserEditable())

            geoJsonFeature.properties["createUserId"] = feature.createUserId
            geoJsonFeature.properties["createUserAlias"] = feature.createUserAlias
            geoJsonFeature.properties["createUserImagePath"] = feature.createUserImagePath
            geoJsonFeature.properties["createUserTags"] = feature.createUserTags

            geoJsonFeature.properties["updateUserId"] = feature.updateUserId
            geoJsonFeature.properties["updateUserAlias"] = feature.updateUserAlias
            geoJsonFeature.properties["updateUserImagePath"] = feature.updateUserImagePath
            geoJsonFeature.properties["updateUserTags"] = feature.updateUserTags

            geoJsonFeature.properties["featureReputation"] = feature.featureReputation
            geoJsonFeature.properties["featureCreateDate"] = feature.featureCreateDate
            geoJsonFeature.properties["featureUpdateDate"] = feature.featureUpdateDate

            geoJsonFeature.geometry = objectMapper.readValue(feature.featureGeomAsGeoJson, GeoJsonObject)

            return geoJsonFeature
        });
    }
}
