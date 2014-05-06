package com.me.h.lycaon.animeseichibacklog.domain;

import java.util.Date;

public class UserBuilder {
    private long    userId;
    private String  userName;
    private String  userAlias;
    private String  userEmail;
    private String  userPassword;
    private boolean userEnabled;
    private String  userTags;
    private String  userImagePath;
    private int     userReputation;
    private int     userReputationVotersCount;
    private Date    userCreateDate;
    private Date    userUpdateDate;


    public UserBuilder setUserId(long userId) {
        this.userId = userId;
        return this;
    }


    public UserBuilder setUserName(String userName) {
        this.userName = userName;
        return this;
    }


    public UserBuilder setUserAlias(String userAlias) {
        this.userAlias = userAlias;
        return this;
    }


    public UserBuilder setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }


    public UserBuilder setUserPassword(String userPassword) {
        this.userPassword = userPassword;
        return this;
    }


    public UserBuilder setUserEnabled(boolean userEnabled) {
        this.userEnabled = userEnabled;
        return this;
    }


    public UserBuilder setUserTags(String userTags) {
        this.userTags = userTags;
        return this;
    }


    public UserBuilder setUserImagePath(String userImagePath) {
        this.userImagePath = userImagePath;
        return this;
    }


    public UserBuilder setUserReputation(int userReputation) {
        this.userReputation = userReputation;
        return this;
    }


    public UserBuilder setUserReputationVotersCount(int userReputationVotersCount) {
        this.userReputationVotersCount = userReputationVotersCount;
        return this;
    }


    public UserBuilder setUserCreateDate(Date userCreateDate) {
        this.userCreateDate = userCreateDate;
        return this;
    }


    public UserBuilder setUserUpdateDate(Date userUpdateDate) {
        this.userUpdateDate = userUpdateDate;
        return this;
    }


    public User createUser() {
        return new User(userId, userName, userAlias, userEmail, userPassword, userEnabled, userTags, userImagePath,
                        userReputation, userReputationVotersCount, userCreateDate, userUpdateDate);
    }
}