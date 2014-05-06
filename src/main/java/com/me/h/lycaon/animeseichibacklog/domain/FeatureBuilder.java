package com.me.h.lycaon.animeseichibacklog.domain;

import java.util.Date;

public class FeatureBuilder {
    private long    featureId;
    private long    createUserId;
    private String  createUserAlias;
    private String  createUserImagePath;
    private String  createUserTags;
    private long    updateUserId;
    private String  updateUserAlias;
    private String  updateUserImagePath;
    private String  updateUserTags;
    private boolean isEditable;
    private boolean isOtherUserEditable;
    private String  featureTags;
    private String  featureName;
    private String  featureDescription;
    private String  featureStyle;
    private String  featureImagePath;
    private int     featureReputation;
    private int     featureReputationVotersCount;
    private Date    featureCreateDate;
    private Date    featureUpdateDate;
    private String  featureGeomAsGeoJson;


    public FeatureBuilder setFeatureId(long featureId) {
        this.featureId = featureId;
        return this;
    }


    public FeatureBuilder setCreateUserId(long createUserId) {
        this.createUserId = createUserId;
        return this;
    }


    public FeatureBuilder setCreateUserAlias(String createUserAlias) {
        this.createUserAlias = createUserAlias;
        return this;
    }


    public FeatureBuilder setCreateUserImagePath(String createUserImagePath) {
        this.createUserImagePath = createUserImagePath;
        return this;
    }


    public FeatureBuilder setCreateUserTags(String createUserTags) {
        this.createUserTags = createUserTags;
        return this;
    }


    public FeatureBuilder setUpdateUserId(long updateUserId) {
        this.updateUserId = updateUserId;
        return this;
    }


    public FeatureBuilder setUpdateUserAlias(String updateUserAlias) {
        this.updateUserAlias = updateUserAlias;
        return this;
    }


    public FeatureBuilder setUpdateUserImagePath(String updateUserImagePath) {
        this.updateUserImagePath = updateUserImagePath;
        return this;
    }


    public FeatureBuilder setUpdateUserTags(String updateUserTags) {
        this.updateUserTags = updateUserTags;
        return this;
    }


    public FeatureBuilder setIsEditable(boolean isEditable) {
        this.isEditable = isEditable;
        return this;
    }


    public FeatureBuilder setIsOtherUserEditable(boolean isOtherUserEditable) {
        this.isOtherUserEditable = isOtherUserEditable;
        return this;
    }


    public FeatureBuilder setFeatureTags(String featureTags) {
        this.featureTags = featureTags;
        return this;
    }


    public FeatureBuilder setFeatureName(String featureName) {
        this.featureName = featureName;
        return this;
    }


    public FeatureBuilder setFeatureDescription(String featureDescription) {
        this.featureDescription = featureDescription;
        return this;
    }


    public FeatureBuilder setFeatureStyle(String featureStyle) {
        this.featureStyle = featureStyle;
        return this;
    }


    public FeatureBuilder setFeatureImagePath(String featureImagePath) {
        this.featureImagePath = featureImagePath;
        return this;
    }


    public FeatureBuilder setFeatureReputation(int featureReputation) {
        this.featureReputation = featureReputation;
        return this;
    }


    public FeatureBuilder setFeatureReputationVotersCount(int featureReputationVotersCount) {
        this.featureReputationVotersCount = featureReputationVotersCount;
        return this;
    }


    public FeatureBuilder setFeatureCreateDate(Date featureCreateDate) {
        this.featureCreateDate = featureCreateDate;
        return this;
    }


    public FeatureBuilder setFeatureUpdateDate(Date featureUpdateDate) {
        this.featureUpdateDate = featureUpdateDate;
        return this;
    }


    public FeatureBuilder setFeatureGeomAsGeoJson(String featureGeomAsGeoJson) {
        this.featureGeomAsGeoJson = featureGeomAsGeoJson;
        return this;
    }


    public Feature createFeature() {
        return new Feature(featureId, createUserId, createUserAlias, createUserImagePath, createUserTags, updateUserId,
                           updateUserAlias, updateUserImagePath, updateUserTags, isEditable, isOtherUserEditable,
                           featureTags, featureName, featureDescription, featureStyle, featureImagePath,
                           featureReputation, featureReputationVotersCount, featureCreateDate, featureUpdateDate,
                           featureGeomAsGeoJson);
    }
}