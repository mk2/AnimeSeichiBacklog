package com.me.h.lycaon.animeseichibacklog.dao;

import com.me.h.lycaon.animeseichibacklog.domain.Feature;

import java.util.List;

/**
 * Created by lycaon_h on 2014/03/20.
 */
public interface FeatureDao extends DomainDao<Feature> {

    public List<Feature> selectByBBoxWithTagsWithUserName(
            double lngLowLeft,
            double latLowLeft,
            double lngUpRight,
            double latUpRight,
            String tags,
            String userName
    );

    public List<Feature> selectByBBox(
            double lngLowLeft,
            double latLowLeft,
            double lngUpRight,
            double latUpRight
    );

}
