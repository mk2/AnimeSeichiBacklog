package com.me.h.lycaon.animeseichibacklog.domain;

import java.util.Date;

public class RemarkBuilder {
    private long   remarkId;
    private long   featureId;
    private long   createUserId;
    private String createUserAlias;
    private String createUserImagePath;
    private String createUserTags;
    private String remarkMessage;
    private String remarkImagePath;
    private int    remarkReputaion;
    private int    remarkReputaionVotersCount;
    private Date   remarkCreateDate;


    public RemarkBuilder setRemarkId(long remarkId) {
        this.remarkId = remarkId;
        return this;
    }


    public RemarkBuilder setFeatureId(long featureId) {
        this.featureId = featureId;
        return this;
    }


    public RemarkBuilder setCreateUserId(long createUserId) {
        this.createUserId = createUserId;
        return this;
    }


    public RemarkBuilder setCreateUserAlias(String createUserAlias) {
        this.createUserAlias = createUserAlias;
        return this;
    }


    public RemarkBuilder setCreateUserImagePath(String createUserImagePath) {
        this.createUserImagePath = createUserImagePath;
        return this;
    }


    public RemarkBuilder setCreateUserTags(String createUserTags) {
        this.createUserTags = createUserTags;
        return this;
    }


    public RemarkBuilder setRemarkMessage(String remarkMessage) {
        this.remarkMessage = remarkMessage;
        return this;
    }


    public RemarkBuilder setRemarkImagePath(String remarkImagePath) {
        this.remarkImagePath = remarkImagePath;
        return this;
    }


    public RemarkBuilder setRemarkReputaion(int remarkReputaion) {
        this.remarkReputaion = remarkReputaion;
        return this;
    }


    public RemarkBuilder setRemarkReputaionVotersCount(int remarkReputaionVotersCount) {
        this.remarkReputaionVotersCount = remarkReputaionVotersCount;
        return this;
    }


    public RemarkBuilder setRemarkCreateDate(Date remarkCreateDate) {
        this.remarkCreateDate = remarkCreateDate;
        return this;
    }


    public Remark createRemark() {
        return new Remark(remarkId, featureId, createUserId, createUserAlias, createUserImagePath, createUserTags,
                          remarkMessage, remarkImagePath, remarkReputaion, remarkReputaionVotersCount,
                          remarkCreateDate);
    }
}