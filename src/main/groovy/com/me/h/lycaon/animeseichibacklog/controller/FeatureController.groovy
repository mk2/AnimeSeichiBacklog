package com.me.h.lycaon.animeseichibacklog.controller

import com.me.h.lycaon.animeseichibacklog.domain.Feature
import com.me.h.lycaon.animeseichibacklog.model.FeatureFormModel
import com.me.h.lycaon.animeseichibacklog.service.crud.FeatureCrudService
import groovy.util.logging.Slf4j
import org.geojson.FeatureCollection
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.core.convert.ConversionService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView

import javax.validation.Valid

/**
 * A Controller for feature crud manipulations.
 * <p/>
 * Basically, create, update, and delete request bring a redirection to a user.
 * But read request does not.
 * <p/>
 * Created by lycaon_h on 2014/03/05.
 */
@Slf4j
@SessionAttributes(value = ["bbox", "baseUrls"])
@Controller
@RequestMapping("/f")
public class FeatureController {

    @Autowired
    private FeatureCrudService featureCrudService

    @Autowired
    @Qualifier("conversionService")
    private ConversionService conversionService

    /**
     * Make a feature.
     * Make a feature request has a side effect - redirecting -, so we have to hold the user
     * viewing area lat/lng by using model attribute "bbox".
     * TODO we have to save "bbox" attribute to the cookie in future.
     *
     * @param featureFormModel Grouped attributes posted from client.
     * @return
     */
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @RequestMapping(
            value = "/c",
            method = RequestMethod.POST)
    def createFeature(
            @Valid @ModelAttribute("featureFormModel")
            final FeatureFormModel featureFormModel,
            final BindingResult result
    ) {
        { ->
            log.info("### RequestParams:  ${featureFormModel.toString()}")

            Feature feature = conversionService.convert(featureFormModel, Feature.class)
            featureCrudService.create(feature)

            ModelAndView modelAndView = new ModelAndView("redirect:/map")
            modelAndView.addObject("bbox", featureFormModel.getBbox())
        }
    }

    /**
     * Read a certain feature by the given id.
     *
     * @param featureId An id corresponds to "feature_id" column in the database.
     * @return GeoJson format string.
     */
    @ResponseBody
    @RequestMapping(value = "/xr/{id}/",
            method = RequestMethod.GET)
    def readById(
            @PathVariable("id") final long featureId
    ) {
        conversionService.convert(
                featureCrudService.readById(featureId),
                org.geojson.Feature.class
        )
    }

    /**
     * Read some features in the given bounding box.
     *
     * @param lngLowLeft
     * @param latLowLeft
     * @param lngUpRight
     * @param latUpRight
     * @param mVars
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/xr/{lngLowLeft}/{latLowLeft}/{lngUpRight}/{latUpRight}/",
            method = RequestMethod.GET)
    def readByBBOX(
            @PathVariable final double lngLowLeft,
            @PathVariable final double latLowLeft,
            @PathVariable final double lngUpRight,
            @PathVariable final double latUpRight,
            @MatrixVariable final Map<String, String> mVars
    ) {
        { ->
            List<Feature> features = featureCrudService.readByBBOX(
                    lngLowLeft,
                    latLowLeft,
                    lngUpRight,
                    latUpRight,
                    mVars
            )

            FeatureCollection geoJsonFeatureCollections = new FeatureCollection()

            for (Feature feature : features) {
                geoJsonFeatureCollections.add(
                        conversionService.convert(
                                feature,
                                org.geojson.Feature.class
                        )
                )
            }

            geoJsonFeatureCollections
        }
    }

    /**
     * Read a number of features.
     * !! CAUTION: this request may cause very very overloading. !!
     *
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/xs",
            method = RequestMethod.GET)
    def readSize(
    ) {
        featureCrudService.count()
    }

    /**
     * Update a feature.
     *
     * @param featureFormModel
     * @param result
     * @param featureId
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @RequestMapping(value = "/u",
            method = RequestMethod.POST)
    def updateFeature(
            @Valid @ModelAttribute("featureFormModel")
            final FeatureFormModel featureFormModel,
            final BindingResult result,
            @RequestParam("featureId")
            final long featureId
    ) {
        { ->
            Feature feature = conversionService.convert(featureFormModel, Feature.class)
            featureCrudService.update(featureId, feature)

            ModelAndView modelAndView = new ModelAndView("redirect:/map")
            modelAndView.addObject("bbox", featureFormModel.getBbox())
        }
    }

    /**
     * Delete a feature.
     *
     * @param featureId
     */
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @RequestMapping(value = "/d/{id}/",
            method = RequestMethod.DELETE)
    def deleteFeature(
            @PathVariable("id") long featureId
    ) {
        featureCrudService.delete(featureId)
    }

}
