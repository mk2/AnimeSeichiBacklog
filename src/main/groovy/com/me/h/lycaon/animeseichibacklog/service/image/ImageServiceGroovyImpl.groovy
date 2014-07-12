package com.me.h.lycaon.animeseichibacklog.service.image

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.apache.commons.lang.RandomStringUtils
import org.imgscalr.Scalr
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

import javax.imageio.ImageIO
import javax.imageio.stream.FileImageOutputStream
import javax.imageio.stream.ImageOutputStream
import java.awt.image.BufferedImage

/**
 * Created by lycaon_h on 2014/03/11.
 */
@CompileStatic
@Slf4j
@Service
final class ImageServiceGroovyImpl implements ImageService {

    @Value('${server.thumbnailWidth}')
    int thumnailWidth

    @Value('${server.thumbnailHeight}')
    int thumnailHeight

    @Value('${server.photoStoragePath}')
    String photoStoragePath

    @Value('${server.thumbnailStoragePath}')
    String thumbnailStoragePath

    @Value('${server.defaultImage}')
    String defaultImagePath;


    @Override
    String saveImageWithThumbnail(
            final BufferedImage bImage
    ) {
        if (bImage == null) {
            return defaultImagePath
        }

        final BufferedImage photo = bImage
        final BufferedImage thumbnail = Scalr.resize(
                photo, Scalr.Method.SPEED, thumnailWidth, thumnailHeight, Scalr.OP_ANTIALIAS)

        // A clojure for generate random png filename for each calling.
        def makeFilename = {
            System.currentTimeMillis() + "_" + RandomStringUtils.randomAlphanumeric(20) + ".png"
        }

        File photoFile = new File("")
        while (true) {
            def filename = makeFilename()
            photoFile = new File("${photoStoragePath}/${filename}")
            if (!photoFile.exists()) {
                break
            }
        }
        if (photoFile.name.size() > 0) {
            log.info "filename: " + photoFile.absolutePath
            def thumbnailFile = new File(photoFile.absolutePath + "_thumbnail.png")

            ImageOutputStream imageOutputStream = new FileImageOutputStream(photoFile)
            ImageIO.write(photo, "png", imageOutputStream)

            imageOutputStream = new FileImageOutputStream(thumbnailFile)
            ImageIO.write(thumbnail, "png", imageOutputStream)

            return photoFile.name
        } else {
            return defaultImagePath
        }
    }
}
