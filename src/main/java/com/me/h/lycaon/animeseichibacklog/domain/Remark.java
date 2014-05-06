package com.me.h.lycaon.animeseichibacklog.domain;

import java.util.Date;

/**
 * Created by lycaon_h on 2014/03/03.
 */
public class Remark implements Domain {

    private long remarkId;

    private long featureId;

    private long createUserId;

    private String createUserAlias;

    private String createUserImagePath;

    private String createUserTags;

    private String remarkMessage;

    private String remarkImagePath;

    private int remarkReputaion;

    private int remarkReputaionVotersCount;

    private Date remarkCreateDate;


    public Remark() {
    }


    public Remark(
            Remark remark
    ) {
        this.remarkId = remark.remarkId;
        this.featureId = remark.featureId;
        this.createUserId = remark.createUserId;
        this.createUserAlias = remark.createUserAlias;
        this.createUserImagePath = remark.createUserImagePath;
        this.createUserTags = remark.createUserTags;
        this.remarkMessage = remark.remarkMessage;
        this.remarkImagePath = remark.remarkImagePath;
        this.remarkReputaion = remark.remarkReputaion;
        this.remarkReputaionVotersCount = remark.remarkReputaionVotersCount;
        this.remarkCreateDate = remark.remarkCreateDate;
    }


    public Remark(
            long remarkId,
            long featureId,
            long createUserId,
            String createUserAlias,
            String createUserImagePath,
            String createUserTags,
            String remarkMessage,
            String remarkImagePath,
            int remarkReputaion,
            int remarkReputaionVotersCount,
            Date remarkCreateDate
    ) {
        this.remarkId = remarkId;
        this.featureId = featureId;
        this.createUserId = createUserId;
        this.createUserAlias = createUserAlias;
        this.createUserImagePath = createUserImagePath;
        this.createUserTags = createUserTags;
        this.remarkMessage = remarkMessage;
        this.remarkImagePath = remarkImagePath;
        this.remarkReputaion = remarkReputaion;
        this.remarkReputaionVotersCount = remarkReputaionVotersCount;
        this.remarkCreateDate = remarkCreateDate;
    }


    public long getRemarkId() {
        return remarkId;
    }


    public void setRemarkId(long remarkId) {
        this.remarkId = remarkId;
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


    public String getRemarkMessage() {
        return remarkMessage;
    }


    public void setRemarkMessage(String remarkMessage) {
        this.remarkMessage = remarkMessage;
    }


    public String getRemarkImagePath() {
        return remarkImagePath;
    }


    public void setRemarkImagePath(String remarkImagePath) {
        this.remarkImagePath = remarkImagePath;
    }


    public int getRemarkReputaion() {
        return remarkReputaion;
    }


    public void setRemarkReputaion(int remarkReputaion) {
        this.remarkReputaion = remarkReputaion;
    }


    public int getRemarkReputaionVotersCount() {
        return remarkReputaionVotersCount;
    }


    public void setRemarkReputaionVotersCount(int remarkReputaionVotersCount) {
        this.remarkReputaionVotersCount = remarkReputaionVotersCount;
    }


    public Date getRemarkCreateDate() {
        return remarkCreateDate;
    }


    public void setRemarkCreateDate(Date remarkCreateDate) {
        this.remarkCreateDate = remarkCreateDate;
    }
}
