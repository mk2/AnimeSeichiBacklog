package com.me.h.lycaon.animeseichibacklog.controller

import com.google.common.collect.Lists
import com.me.h.lycaon.animeseichibacklog.domain.Remark
import com.me.h.lycaon.animeseichibacklog.model.RemarkFormModel
import com.me.h.lycaon.animeseichibacklog.model.RemarkViewModel
import com.me.h.lycaon.animeseichibacklog.service.crud.RemarkCrudService
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.core.convert.ConversionService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView

import javax.validation.Valid

/**
 * Created by lycaon_h on 2014/03/05.
 */
@Slf4j
@Controller
@RequestMapping("/r")
@SessionAttributes(value = ["bbox", "baseUrls"])
public class RemarkController {

    @Autowired
    private RemarkCrudService remarkCrudService

    @Autowired
    @Qualifier("conversionService")
    private ConversionService conversionService


    @RequestMapping(value = "/c", method = RequestMethod.POST)
    def createRemark(
            @Valid @ModelAttribute("remarkFormModel")
            final RemarkFormModel remarkFormModel,
            final BindingResult result
    ) {
        { ->
            Remark remark = conversionService.convert(remarkFormModel, Remark.class)
            remarkCrudService.create(remark)

            ModelAndView modelAndView = new ModelAndView("redirect:/map")
            modelAndView.addObject("bbox", remarkFormModel.getBbox())
        }
    }


    @ResponseBody
    @RequestMapping(value = "/xr/{featureId}/", method = RequestMethod.GET)
    def readByFeatureId(
            @PathVariable("featureId") long featureId
    ) {
        List<Remark> remarks = remarkCrudService.readByFeatureId(featureId)

        List<RemarkViewModel> remarkViewModels = Lists.newArrayList()

        for (Remark remark : remarks) {
            remarkViewModels.add(conversionService.convert(remark, RemarkViewModel.class))
        }

        return new ResponseEntity<>(remarkViewModels, HttpStatus.OK)
    }


    @ResponseBody
    @RequestMapping(value = "/xd/remark/{id}", method = RequestMethod.DELETE)
    def deleteRemark(
            @RequestBody String rawJsonString,
            @PathVariable("id") long id
    ) throws IOException {
    }

}
