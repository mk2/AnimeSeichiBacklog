package com.me.h.lycaon.animeseichibacklog.service.converter.impl

import com.me.h.lycaon.animeseichibacklog.domain.Feature
import com.me.h.lycaon.animeseichibacklog.model.FeatureFormModel
import com.me.h.lycaon.animeseichibacklog.service.image.ImageService
import com.me.h.lycaon.animeseichibacklog.service.user.UserInfoService
import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.convert.converter.Converter

import javax.imageio.ImageIO
import java.awt.image.BufferedImage

/**
 * Created by lycaon_h on 2014/03/16.
 */
@CompileStatic
@TypeChecked
class FeatureFormModelToFeature implements Converter<FeatureFormModel, Feature> {

    @Autowired
    UserInfoService userInfoService

    @Autowired
    ImageService imageService


    @Override
    Feature convert(
            final FeatureFormModel featureFormModel
    ) {
        Feature feature = new Feature(featureFormModel)

        BufferedImage bImage = ImageIO.read(featureFormModel
                                                    .getFeatureImageFile()
                                                    .getInputStream());
        feature.featureImagePath = imageService.saveImageWithThumbnail(bImage);

        feature.createUserId = userInfoService.userId
        feature.createUserAlias = userInfoService.userAlias
        feature.createUserImagePath = userInfoService.userImagePath
        feature.createUserTags = userInfoService.userTags

        feature.updateUserId = userInfoService.userId
        feature.updateUserAlias = userInfoService.userAlias
        feature.updateUserImagePath = userInfoService.userImagePath
        feature.updateUserTags = userInfoService.userTags

        return feature
    }

}
