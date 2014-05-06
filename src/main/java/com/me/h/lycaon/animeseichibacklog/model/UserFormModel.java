package com.me.h.lycaon.animeseichibacklog.model;

import com.me.h.lycaon.animeseichibacklog.domain.User;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by lycaon_h on 2014/03/13.
 */
public class UserFormModel extends User {

    private MultipartFile userImageFile;


    public UserFormModel() {}


    public UserFormModel(User user) {
        super(user);
    }


    public MultipartFile getUserImageFile() {
        return userImageFile;
    }


    public void setUserImageFile(MultipartFile userImageFile) {
        this.userImageFile = userImageFile;
    }
}
