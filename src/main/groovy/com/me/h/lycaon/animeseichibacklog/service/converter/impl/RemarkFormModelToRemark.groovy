package com.me.h.lycaon.animeseichibacklog.service.converter.impl

import com.me.h.lycaon.animeseichibacklog.domain.Remark
import com.me.h.lycaon.animeseichibacklog.model.RemarkFormModel
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
class RemarkFormModelToRemark implements Converter<RemarkFormModel, Remark> {

    @Autowired
    UserInfoService userInfoService

    @Autowired
    ImageService imageService


    @Override
    Remark convert(
            final RemarkFormModel remarkFormModel
    ) {
        Remark remark = new Remark(remarkFormModel)

        BufferedImage bImage = ImageIO.read(
                remarkFormModel
                        .getRemarkImageFile()
                        .getInputStream());
        remark.remarkImagePath = imageService.saveImageWithThumbnail(bImage);

        remark.createUserId = userInfoService.userId
        remark.createUserAlias = userInfoService.userAlias
        remark.createUserImagePath = userInfoService.userImagePath
        remark.createUserTags = userInfoService.userTags

        return remark
    }

}
