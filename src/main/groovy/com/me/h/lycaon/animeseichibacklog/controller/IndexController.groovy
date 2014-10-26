package com.me.h.lycaon.animeseichibacklog.controller

import com.google.common.collect.Lists
import com.me.h.lycaon.animeseichibacklog.model.FeatureFormModel
import com.me.h.lycaon.animeseichibacklog.model.RemarkFormModel
import com.me.h.lycaon.animeseichibacklog.model.UserFormModel
import com.me.h.lycaon.animeseichibacklog.service.user.UserInfoService
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.*

/**
 * A controller handles the almost all mappings to jsp files.
 * "/"(root, index) is handled in this contoller, so it named "IndexController".
 * <p>
 * Details of session attributes:
 * <p>
 * (1) bbox Stored user viewing area's bounding box lat/lng. Client side program determine the
 * value and fit user viewing area.
 *{* 0: South West Lat
 * 1: South West Lng
 * 2: North East Lat
 * 3: North East Lng
 *}* <p>
 * (2) baseUrls Base URLs for client requests to server.
 *{* rsrcBaseUrl: for accessing javascript, css files..
 * photoBaseUrl: for accessing user posted photos.
 *}* <p>
 * (3) userRoles Role name of user, client change the user interface as to user role.
 *{* ROLE_USER, ANONYMOUS, etc
 *}* <p>
 * Created by lycaon_h on 2014/03/05.
 */
@Slf4j
@SessionAttributes(value = ["bbox", "baseUrls", "userRoles", "i18n_lang", "userAlias"])
@Controller
@RequestMapping("/")
public class IndexController {

    /**
     * The value for vidageek i18n library, it effects user interface locale.
     * It is "default" -> selecting japanese
     * It is "en" -> selecting english (but many texts have not yet been translated...)
     */
    @Value('${server.i18n_lang}')
    private String i18n_lang;

    @Value('${server.initBbox.sw.lat}')
    private Double initBboxSwLat;

    @Value('${server.initBbox.sw.lng}')
    private Double initBboxSwLng;

    @Value('${server.initBbox.ne.lat}')
    private Double initBboxNeLat;

    @Value('${server.initBbox.ne.lng}')
    private Double initBboxNeLng;

    @Value('${server.photoBaseUrl}')
    private String photoBaseUrl;

    @Value('${server.rsrcBaseUrl}')
    private String rsrcBaseUrl;

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "",
            method = RequestMethod.GET)
    public String index(ModelMap model) {
        model.addAttribute("bbox",
                Lists.newArrayList(
                        initBboxSwLat, initBboxSwLng, initBboxNeLat, initBboxNeLng)
        );

        Map<String, String> rsrcBaseUrlMap = new LinkedHashMap<>(2);
        rsrcBaseUrlMap.put("rsrcBaseUrl", rsrcBaseUrl);
        rsrcBaseUrlMap.put("photoBaseUrl", photoBaseUrl);

        model.addAttribute("baseUrls", rsrcBaseUrlMap);

        model.addAttribute("userRoles", userInfoService.getUserRoles());

        model.addAttribute("userAlias", userInfoService.getUserAlias());

        model.addAttribute("i18n_lang", i18n_lang);

        return "index";
    }


    @RequestMapping(value = "login",
            method = RequestMethod.GET)
    public String login(
            @ModelAttribute("userFormModel") UserFormModel userFormModel
    ) {
        if (userInfoService.isUserLogin()) {
            return "map";
        } else {
            return "account-login";
        }
    }


    @RequestMapping(value = "map/{bboxSwLat}/{bboxSwLng}/{bboxNeLat}/{bboxNeLng}/",
            method = RequestMethod.GET)
    public String mapWithLatLng(
            @PathVariable Double bboxSwLat,
            @PathVariable Double bboxSwLng,
            @PathVariable Double bboxNeLat,
            @PathVariable Double bboxNeLng,
            @ModelAttribute("featureFormModel") FeatureFormModel featureFormModel,
            @ModelAttribute("remarkFormModel") RemarkFormModel remarkFormModel,
            ModelMap model
    ) {
        model.put("bbox", Lists.newArrayList(bboxSwLat, bboxSwLng, bboxNeLat, bboxNeLng));
        return "map";
    }


    @RequestMapping(value = "map",
            method = RequestMethod.GET)
    public String map(
            @ModelAttribute("featureFormModel") FeatureFormModel featureFormModel,
            @ModelAttribute("remarkFormModel") RemarkFormModel remarkFormModel
    ) {
        return "map";
    }


    @RequestMapping(value = "about",
            method = RequestMethod.GET)
    public String about() {
        return "about";
    }


    @RequestMapping(value = "usage",
            method = RequestMethod.GET)
    public String usage() {
        return "usage";
    }


    @RequestMapping(value = "register",
            method = RequestMethod.GET)
    public String register(
            @ModelAttribute("userFormModel") UserFormModel userFormModel
    ) {
        return "account-register";
    }


    @RequestMapping(value = "withdraw",
            method = RequestMethod.GET)
    public String withdraw() {
        return "account-withdraw";
    }


    @RequestMapping(value = "withdraw-complete",
            method = RequestMethod.GET)
    public String withdrawComplete() {
        return "account-withdraw-complete";
    }


    @RequestMapping(value = "withdraw-failed",
            method = RequestMethod.GET)
    public String withdrawFailed() {
        return "account-withdraw-failed";
    }


    @RequestMapping(value = "update",
            method = RequestMethod.GET)
    public String update() {
        return "account-update";
    }


    @RequestMapping(value = "update-complete",
            method = RequestMethod.GET)
    public String updateComplete() {
        return "account-update-complete";
    }


    @RequestMapping(value = "update-failed",
            method = RequestMethod.GET)
    public String updateFailed() {
        return "account-update-failed";
    }
}
