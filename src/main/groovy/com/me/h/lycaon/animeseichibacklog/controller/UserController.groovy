package com.me.h.lycaon.animeseichibacklog.controller

import com.me.h.lycaon.animeseichibacklog.controller.exception.RequestException
import com.me.h.lycaon.animeseichibacklog.domain.User
import com.me.h.lycaon.animeseichibacklog.model.UserFormModel
import com.me.h.lycaon.animeseichibacklog.service.crud.UserCrudService
import com.me.h.lycaon.animeseichibacklog.service.user.UserInfoService
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.core.convert.ConversionService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.SessionAttributes

import javax.validation.Valid

/**
 * Created by lycaon_h on 2014/03/05.
 */
@Slf4j
@SessionAttributes(value = ["userRoles", "userAlias"])
@Controller
@RequestMapping("/u")
public class UserController {

    @Autowired
    private UserCrudService userCrudService;

    @Autowired
    private UserInfoService userInfoService;


    @Autowired
    @Qualifier("conversionService")
    private ConversionService conversionService;

    /**
     * Login process
     *
     * @param userFormModel Required fields: userEmail and userPassword
     * @param model Store user auth information to be bound to the key "userRole".
     * @return Login success -> redirect to "/map", login failure -> redirect to "/"
     */
    @RequestMapping(value = "/l", method = RequestMethod.POST)
    public String login(
            @Valid @ModelAttribute("userFormModel")
            final UserFormModel userFormModel,
            final BindingResult result,
            ModelMap model
    ) throws RequestException {
        final String userEmail = userFormModel.getUserEmail();
        final String userPassword = userFormModel.getUserPassword();

        User user = userCrudService.readByEmail(userEmail);

        if (userInfoService.login(user.getUserName(), userPassword)) {
            model.addAttribute("userRoles", userInfoService.getUserRoles());
            model.addAttribute("userAlias", userInfoService.getUserAlias());
            return "redirect:/map";
        } else {
            return "redirect:/";
        }
    }

    /**
     * Logout process
     *
     * @return Logout success -> redirect to "/"
     */
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @RequestMapping(value = "/o", method = RequestMethod.GET)
    public String logout(
            ModelMap model
    ) {
        SecurityContextHolder.clearContext();
        model.addAttribute("userRoles", "");
        model.addAttribute("userAlias", "");
        return "redirect:/";
    }

    /**
     * User creation process
     *
     * @param userFormModel Required fields: userAlias, userEmail and userPassword
     * @param model Store user auth information to be bound to the key "userRole".
     * @return Login success -> redirect to "/map", login failure -> redirect to "/"
     */
    @RequestMapping(value = "/c", method = RequestMethod.POST)
    public String create(
            @Valid @ModelAttribute("userFormModel")
            final UserFormModel userFormModel,
            final BindingResult result,
            ModelMap model
    ) throws Exception {
        User user = conversionService.convert(userFormModel, User.class);
        long userId = userCrudService.create(user);

        User createdUser = userCrudService.readById(userId);

        if (userInfoService.login(createdUser.getUserName(), user.getUserPassword())) {
            model.addAttribute("userRoles", userInfoService.getUserRoles());
            model.addAttribute("userAlias", userInfoService.getUserAlias());
            return "redirect:/map";
        } else {
            return "redirect:/";
        }
    }

    /**
     * @param userFormModel
     * @param result
     * @param model
     */
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @RequestMapping(value = "/u", method = RequestMethod.POST)
    public void update(
            @Valid @ModelAttribute("userFormModel") UserFormModel userFormModel,
            BindingResult result,
            ModelMap model
    ) {
    }

    /**
     * @param model
     * @return
     */
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @RequestMapping(value = "/d", method = RequestMethod.DELETE)
    public String delete(
            ModelMap model
    ) {
        long userId = userInfoService.getUserId();

        userCrudService.delete(userId);

        if (userCrudService.readById(userId) == null) {
            model.clear();
            return "redirect:/withdraw-complete";
        } else {
            return "redirect:/";
        }
    }

}
