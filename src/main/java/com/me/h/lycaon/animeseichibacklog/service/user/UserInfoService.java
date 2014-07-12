package com.me.h.lycaon.animeseichibacklog.service.user;

import java.util.Set;

/**
 * Created by lycaon_h on 2014/03/10.
 */
public interface UserInfoService {

    public boolean login(
            String userName,
            String userPassword
    );


    /**
     * Get user id via session auth information
     *
     * @return If login as Anonymous, return -1L.
     */
    public long getUserId();


    public String getUserName();


    public Set<String> getUserRoles();


    public String getUserAlias();


    public String getUserEmail();


    public boolean isUserLogin();


    public String getUserTags();


    public int getUserReputation();


    public String getUserImagePath();
}
