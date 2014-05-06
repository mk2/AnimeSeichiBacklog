package com.me.h.lycaon.animeseichibacklog.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.me.h.lycaon.animeseichibacklog.domain.*;
import com.me.h.lycaon.animeseichibacklog.service.crud.FeatureCrudService;
import com.me.h.lycaon.animeseichibacklog.service.crud.RemarkCrudService;
import com.me.h.lycaon.animeseichibacklog.service.crud.UserCrudService;
import com.me.h.lycaon.animeseichibacklog.service.image.ImageService;
import com.me.h.lycaon.animeseichibacklog.service.user.UserInfoService;
import org.geojson.GeoJsonObject;
import org.geojson.Point;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by lycaon_h on 2014/03/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {
        "/spring/test-controller-context.xml"
})
public abstract class AbstractControllerTest {

    @Autowired
    protected WebApplicationContext wac;

    protected MockMvc mockMvc;

    @Autowired
    protected FeatureCrudService featureCrudService;

    @Autowired
    protected UserCrudService userCrudService;

    @Autowired
    protected UserInfoService userInfoService;

    @Autowired
    protected RemarkCrudService remarkCrudService;

    @Autowired
    protected ImageService imageService;

    protected ObjectMapper objMap = new ObjectMapper();


    @Before
    public void setup() {
        setupContext();
        setupBeans();
    }


    protected void setupContext() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                                 .build();
    }


    /**
     * 各モックのスタブを設定する
     */
    protected abstract void setupBeans();


    protected String getGeoJsonObject() {
        GeoJsonObject geoJsonObject = new Point(140, 36);
        try {
            return objMap.writeValueAsString(geoJsonObject);
        } catch (JsonProcessingException e) {
            return "";
        }
    }


    protected User getUser() {
        UserBuilder userBuilder = new UserBuilder()
                .setUserId(1L)
                .setUserName("test")
                .setUserAlias("テスト")
                .setUserPassword("abcd")
                .setUserEmail("test@example.com");
        return userBuilder.createUser();
    }


    protected Feature getFeature(User user) {
        FeatureBuilder featureBuilder = new FeatureBuilder()
                .setFeatureId(1L)
                .setFeatureName("テスト")
                .setFeatureDescription("テストテストテスト");
        return featureBuilder.createFeature();
    }


    protected Remark getRemark(
            User user,
            Feature feature
    ) {
        RemarkBuilder remarkBuilder = new RemarkBuilder()
                .setFeatureId(feature.getFeatureId())
                .setRemarkId(1L);
        return remarkBuilder.createRemark();
    }

}
