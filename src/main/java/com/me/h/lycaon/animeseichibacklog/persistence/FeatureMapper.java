package com.me.h.lycaon.animeseichibacklog.persistence;

import com.me.h.lycaon.animeseichibacklog.domain.Feature;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Interface for MyBatis
 * <p/>
 * Method naming rule:
 * As much as possible, the method names should begin with that executing sql command name.
 * <p/>
 * For instance:
 * a method execute "SELECT ..." -> the method name begin with "select"
 * a method execute "INSERT ..." -> the method name begin with "insert"
 * <p/>
 * Created by lycaon_h on 2014/03/04.
 */
public interface FeatureMapper {

    public void insert(Feature feature);


    public long selectLastSerialId();


    public void update(Feature feature);


    public List<Feature> selectAll();


    public Feature selectById(long featureId);


    public List<Feature> selectByRange(
            @Param("offset") long offset,
            @Param("limit") long limit
    );


    public List<Feature> selectByBBox(
            @Param("lngLowLeft") double lngLowLeft,
            @Param("latLowLeft") double latLowLeft,
            @Param("lngUpRight") double lngUpRight,
            @Param("latUpRight") double latUpRight
    );


    public List<Feature> selectByBBoxWithTagsWithUserName(
            @Param("lngLowLeft") double lngLowLeft,
            @Param("latLowLeft") double latLowLeft,
            @Param("lngUpRight") double lngUpRight,
            @Param("latUpRight") double latUpRight,
            @Param("tags") String tags,
            @Param("userName") String userName
    );


    @Transactional
    public void delete(long featureid);


    public long count();


}
