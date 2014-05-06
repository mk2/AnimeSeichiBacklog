package com.me.h.lycaon.animeseichibacklog.model;

import com.me.h.lycaon.animeseichibacklog.domain.Remark;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by lycaon_h on 2014/03/16.
 */
public class RemarkFormModel extends Remark {

    private MultipartFile remarkImageFile;

    private List<String> bbox;


    public RemarkFormModel() {}


    public RemarkFormModel(Remark remark) {
        super(remark);
    }


    public MultipartFile getRemarkImageFile() {
        return remarkImageFile;
    }


    public void setRemarkImageFile(MultipartFile remarkImageFile) {
        this.remarkImageFile = remarkImageFile;
    }


    public List<String> getBbox() {
        return bbox;
    }


    public void setBbox(List<String> bbox) {
        this.bbox = bbox;
    }
}
