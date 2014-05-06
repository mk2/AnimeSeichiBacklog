package com.me.h.lycaon.animeseichibacklog.domain;

import java.util.Date;

/**
 * {@see /doc/sql/schema.sql}
 * Created by lycaon_h on 2014/03/03.
 */
public class Feature implements Domain {

    private long featureId;

    private long createUserId;

    private String createUserAlias;

    private String createUserImagePath;

    private String createUserTags;

    private long updateUserId;

    private String updateUserAlias;

    private String updateUserImagePath;

    private String updateUserTags;

    private boolean isEditable;

    private boolean isOtherUserEditable;

    private String featureTags;

    private String featureName;

    private String featureDescription;

    private String featureStyle;

    private String featureImagePath;

    private int featureReputation;

    private int featureReputationVotersCount;

    private Date featureCreateDate;

    private Date featureUpdateDate;

    private String featureGeomAsGeoJson;


    public Feature() {
    }


    public Feature(
            Feature feature
    ) {
        this.featureId = feature.featureId;
        this.createUserId = feature.createUserId;
        this.createUserAlias = feature.createUserAlias;
        this.createUserImagePath = feature.createUserImagePath;
        this.createUserTags = feature.createUserTags;
        this.updateUserId = feature.updateUserId;
        this.updateUserAlias = feature.updateUserAlias;
        this.updateUserImagePath = feature.updateUserImagePath;
        this.updateUserTags = feature.updateUserTags;
        this.isEditable = feature.isEditable;
        this.isOtherUserEditable = feature.isOtherUserEditable;
        this.featureTags = feature.featureTags;
        this.featureName = feature.featureName;
        this.featureDescription = feature.featureDescription;
        this.featureStyle = feature.featureStyle;
        this.featureImagePath = feature.featureImagePath;
        this.featureReputation = feature.featureReputation;
        this.featureReputationVotersCount = feature.featureReputationVotersCount;
        this.featureCreateDate = feature.featureCreateDate;
        this.featureUpdateDate = feature.featureUpdateDate;
        this.featureGeomAsGeoJson = feature.featureGeomAsGeoJson;
    }


    public Feature(
            long featureId,
            long createUserId,
            String createUserAlias,
            String createUserImagePath,
            String createUserTags,
            long updateUserId,
            String updateUserAlias,
            String updateUserImagePath,
            String updateUserTags,
            boolean isEditable,
            boolean isOtherUserEditable,
            String featureTags,
            String featureName,
            String featureDescription,
            String featureStyle,
            String featureImagePath,
            int featureReputation,
            int featureReputationVotersCount,
            Date featureCreateDate,
            Date featureUpdateDate,
            String featureGeomAsGeoJson
    ) {
        this.featureId = featureId;
        this.createUserId = createUserId;
        this.createUserAlias = createUserAlias;
        this.createUserImagePath = createUserImagePath;
        this.createUserTags = createUserTags;
        this.updateUserId = updateUserId;
        this.updateUserAlias = updateUserAlias;
        this.updateUserImagePath = updateUserImagePath;
        this.updateUserTags = updateUserTags;
        this.isEditable = isEditable;
        this.isOtherUserEditable = isOtherUserEditable;
        this.featureTags = featureTags;
        this.featureName = featureName;
        this.featureDescription = featureDescription;
        this.featureStyle = featureStyle;
        this.featureImagePath = featureImagePath;
        this.featureReputation = featureReputation;
        this.featureReputationVotersCount = featureReputationVotersCount;
        this.featureCreateDate = featureCreateDate;
        this.featureUpdateDate = featureUpdateDate;
        this.featureGeomAsGeoJson = featureGeomAsGeoJson;
    }


    public long getFeatureId() {
        return featureId;
    }


    public void setFeatureId(long featureId) {
        this.featureId = featureId;
    }


    public long getCreateUserId() {
        return createUserId;
    }


    public void setCreateUserId(long createUserId) {
        this.createUserId = createUserId;
    }


    public String getCreateUserAlias() {
        return createUserAlias;
    }


    public void setCreateUserAlias(String createUserAlias) {
        this.createUserAlias = createUserAlias;
    }


    public String getCreateUserImagePath() {
        return createUserImagePath;
    }


    public void setCreateUserImagePath(String createUserImagePath) {
        this.createUserImagePath = createUserImagePath;
    }


    public String getCreateUserTags() {
        return createUserTags;
    }


    public void setCreateUserTags(String createUserTags) {
        this.createUserTags = createUserTags;
    }


    public long getUpdateUserId() {
        return updateUserId;
    }


    public void setUpdateUserId(long updateUserId) {
        this.updateUserId = updateUserId;
    }


    public String getUpdateUserAlias() {
        return updateUserAlias;
    }


    public void setUpdateUserAlias(String updateUserAlias) {
        this.updateUserAlias = updateUserAlias;
    }


    public String getUpdateUserImagePath() {
        return updateUserImagePath;
    }


    public void setUpdateUserImagePath(String updateUserImagePath) {
        this.updateUserImagePath = updateUserImagePath;
    }


    public String getUpdateUserTags() {
        return updateUserTags;
    }


    public void setUpdateUserTags(String updateUserTags) {
        this.updateUserTags = updateUserTags;
    }


    public boolean isEditable() {
        return isEditable;
    }


    public void setEditable(boolean isEditable) {
        this.isEditable = isEditable;
    }


    public boolean isOtherUserEditable() {
        return isOtherUserEditable;
    }


    public void setOtherUserEditable(boolean isOtherUserEditable) {
        this.isOtherUserEditable = isOtherUserEditable;
    }


    public String getFeatureTags() {
        return featureTags;
    }


    public void setFeatureTags(String featureTags) {
        this.featureTags = featureTags;
    }


    public String getFeatureName() {
        return featureName;
    }


    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }


    public String getFeatureDescription() {
        return featureDescription;
    }


    public void setFeatureDescription(String featureDescription) {
        this.featureDescription = featureDescription;
    }


    public String getFeatureStyle() {
        return featureStyle;
    }


    public void setFeatureStyle(String featureStyle) {
        this.featureStyle = featureStyle;
    }


    public String getFeatureImagePath() {
        return featureImagePath;
    }


    public void setFeatureImagePath(String featureImagePath) {
        this.featureImagePath = featureImagePath;
    }


    public int getFeatureReputation() {
        return featureReputation;
    }


    public void setFeatureReputation(int featureReputation) {
        this.featureReputation = featureReputation;
    }


    public int getFeatureReputationVotersCount() {
        return featureReputationVotersCount;
    }


    public void setFeatureReputationVotersCount(int featureReputationVotersCount) {
        this.featureReputationVotersCount = featureReputationVotersCount;
    }


    public Date getFeatureCreateDate() {
        return featureCreateDate;
    }


    public void setFeatureCreateDate(Date featureCreateDate) {
        this.featureCreateDate = featureCreateDate;
    }


    public Date getFeatureUpdateDate() {
        return featureUpdateDate;
    }


    public void setFeatureUpdateDate(Date featureUpdateDate) {
        this.featureUpdateDate = featureUpdateDate;
    }


    public String getFeatureGeomAsGeoJson() {
        return featureGeomAsGeoJson;
    }


    public void setFeatureGeomAsGeoJson(String featureGeomAsGeoJson) {
        this.featureGeomAsGeoJson = featureGeomAsGeoJson;
    }
}
