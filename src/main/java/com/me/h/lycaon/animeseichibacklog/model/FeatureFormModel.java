package com.me.h.lycaon.animeseichibacklog.model;

import com.me.h.lycaon.animeseichibacklog.domain.Feature;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by lycaon_h on 2014/03/11.
 */
public class FeatureFormModel extends Feature {

    private MultipartFile featureImageFile;

    private List<String> bbox;


    public FeatureFormModel() {
    }


    public FeatureFormModel(
            Feature feature
    ) {
        super(feature);
    }


    public MultipartFile getFeatureImageFile() {
        return featureImageFile;
    }


    public void setFeatureImageFile(MultipartFile featureImageFile) {
        this.featureImageFile = featureImageFile;
    }


    public List<String> getBbox() {
        return bbox;
    }


    public void setBbox(List<String> bbox) {
        this.bbox = bbox;
    }
}
