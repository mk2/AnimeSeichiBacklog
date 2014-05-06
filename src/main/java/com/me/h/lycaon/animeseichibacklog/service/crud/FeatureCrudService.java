package com.me.h.lycaon.animeseichibacklog.service.crud;

import com.me.h.lycaon.animeseichibacklog.domain.Feature;

import java.util.List;
import java.util.Map;

/**
 * Created by lycaon_h on 2014/03/04.
 */
public interface FeatureCrudService extends CrudService<Feature> {

    public List<Feature> readByBBOX(
            double lngLowLeft,
            double latLowLeft,
            double lngUpRight,
            double latUpRight
    );

    public List<Feature> readByBBOX(double[] lngLats);

    public List<Feature> readByBBOX(List<Double> lngLats);

    public List<Feature> readByBBOX(
            double lngLowLeft,
            double latLowLeft,
            double lngUpRight,
            double latUpRight,
            Map<String, String> options
    );

    public List<Feature> readByBBOX(
            double[] lngLats,
            Map<String, String> options
    );

    public List<Feature> readByBBOX(
            List<Double> lngLats,
            Map<String, String> options
    );

}
