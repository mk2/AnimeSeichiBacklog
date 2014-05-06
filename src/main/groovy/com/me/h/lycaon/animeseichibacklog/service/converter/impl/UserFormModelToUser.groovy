package com.me.h.lycaon.animeseichibacklog.service.converter.impl

import com.me.h.lycaon.animeseichibacklog.domain.User
import com.me.h.lycaon.animeseichibacklog.model.UserFormModel
import com.me.h.lycaon.animeseichibacklog.service.image.ImageService
import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.convert.converter.Converter

import javax.imageio.ImageIO
import java.awt.image.BufferedImage

/**
 * Created by lycaon_h on 2014/03/17.
 */
@CompileStatic
@TypeChecked
class UserFormModelToUser implements Converter<UserFormModel, User> {

    @Autowired
    ImageService imageService


    @Override
    User convert(
            final UserFormModel userFormModel
    ) {
        User user = new User(userFormModel)

        BufferedImage bImage = ImageIO.read(userFormModel
                                                    .getUserImageFile()
                                                    .getInputStream());
        user.userImagePath = imageService.saveImageWithThumbnail(bImage);

        return user
    }

}
