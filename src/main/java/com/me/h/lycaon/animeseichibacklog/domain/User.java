package com.me.h.lycaon.animeseichibacklog.domain;

import java.util.Date;

/**
 * Created by lycaon_h on 2014/03/03.
 */
public class User implements Domain {

    private long userId;

    private String userName;

    private String userAlias;

    private String userEmail;

    private String userPassword;

    private boolean userEnabled;

    private String userTags;

    private String userImagePath;

    private int userReputation;

    private int userReputationVotersCount;

    private Date userCreateDate;

    private Date userUpdateDate;


    public User() {
    }


    public User(
            User user
    ) {
        this.userId = user.userId;
        this.userName = user.userName;
        this.userAlias = user.userAlias;
        this.userEmail = user.userEmail;
        this.userPassword = user.userPassword;
        this.userEnabled = user.userEnabled;
        this.userTags = user.userTags;
        this.userImagePath = user.userImagePath;
        this.userReputation = user.userReputation;
        this.userReputationVotersCount = user.userReputationVotersCount;
        this.userCreateDate = user.userCreateDate;
        this.userUpdateDate = user.userUpdateDate;
    }


    public User(
            long userId,
            String userName,
            String userAlias,
            String userEmail,
            String userPassword,
            boolean userEnabled,
            String userTags,
            String userImagePath,
            int userReputation,
            int userReputationVotersCount,
            Date userCreateDate,
            Date userUpdateDate
    ) {
        this.userId = userId;
        this.userName = userName;
        this.userAlias = userAlias;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userEnabled = userEnabled;
        this.userTags = userTags;
        this.userImagePath = userImagePath;
        this.userReputation = userReputation;
        this.userReputationVotersCount = userReputationVotersCount;
        this.userCreateDate = userCreateDate;
        this.userUpdateDate = userUpdateDate;
    }


    public long getUserId() {
        return userId;
    }


    public void setUserId(long userId) {
        this.userId = userId;
    }


    public String getUserName() {
        return userName;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getUserAlias() {
        return userAlias;
    }


    public void setUserAlias(String userAlias) {
        this.userAlias = userAlias;
    }


    public String getUserEmail() {
        return userEmail;
    }


    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }


    public String getUserPassword() {
        return userPassword;
    }


    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }


    public boolean isUserEnabled() {
        return userEnabled;
    }


    public void setUserEnabled(boolean userEnabled) {
        this.userEnabled = userEnabled;
    }


    public String getUserTags() {
        return userTags;
    }


    public void setUserTags(String userTags) {
        this.userTags = userTags;
    }


    public String getUserImagePath() {
        return userImagePath;
    }


    public void setUserImagePath(String userImagePath) {
        this.userImagePath = userImagePath;
    }


    public int getUserReputation() {
        return userReputation;
    }


    public void setUserReputation(int userReputation) {
        this.userReputation = userReputation;
    }


    public int getUserReputationVotersCount() {
        return userReputationVotersCount;
    }


    public void setUserReputationVotersCount(int userReputationVotersCount) {
        this.userReputationVotersCount = userReputationVotersCount;
    }


    public Date getUserCreateDate() {
        return userCreateDate;
    }


    public void setUserCreateDate(Date userCreateDate) {
        this.userCreateDate = userCreateDate;
    }


    public Date getUserUpdateDate() {
        return userUpdateDate;
    }


    public void setUserUpdateDate(Date userUpdateDate) {
        this.userUpdateDate = userUpdateDate;
    }
}
