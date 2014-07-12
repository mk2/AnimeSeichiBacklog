package com.me.h.lycaon.animeseichibacklog.service.user

import com.me.h.lycaon.animeseichibacklog.service.crud.UserCrudService
import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User as UserDetailsUser
import org.springframework.stereotype.Service

/**
 * User information retain service.
 * Created by lycaon_h on 2014/03/10.
 */
@CompileStatic
@TypeChecked
@Slf4j
@Service
final class UserInfoServiceGroovyImpl implements UserInfoService {

    @Autowired
    AuthenticationManager authenticationManager

    @Autowired
    UserCrudService userCrudService

    /**
     * Login system with userName and userPassword.
     * It assumed that user auth info is already loaded into auth manager.
     * In this system, user auth info load at "resources/spring/securityContext.xml".
     * @param userName
     * @param userPassword
     * @return
     */
    @Override
    boolean login(
            String userName,
            String userPassword
    ) {
        try {
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(userName, userPassword)
            def auth = authenticationManager.authenticate(token)
            SecurityContextHolder.context.authentication = auth
            log.info "Login SUCCESS with user[${userName}] password[${userPassword}]"
            return true
        } catch (ignored) {
            log.info "Login FAILED with user[${userName}] password[${userPassword}]"
            return false
        }
    }


    @Override
    long getUserId() {
        Authentication auth = SecurityContextHolder.context.authentication
        if (auth.principal instanceof String) {
            return -1L
        } else if (auth.principal instanceof UserDetailsUser) {
            return userCrudService.readByName(
                    (auth.principal as UserDetailsUser).username).userId
        } else {
            return -1L
        }
    }


    @Override
    String getUserName() {
        Authentication auth = SecurityContextHolder.context.authentication
        if (auth.principal instanceof String) {
            return auth.principal as String
        } else if (auth.principal instanceof UserDetailsUser) {
            return (auth.principal as UserDetailsUser).username
        }
    }


    @Override
    Set<String> getUserRoles() {
        Authentication auth = SecurityContextHolder.context.authentication
        return auth.authorities as Set<String>
    }

    /**
     * Get user alias (the name revealed to everyone.)
     * If user is anonymous, return "NO NAME".
     * @return
     */
    @Override
    String getUserAlias() {
        long id = getUserId()

        if (id == -1L) {
            return "NO NAME"
        } else {
            return userCrudService.readById(id).userAlias
        }
    }

    /**
     * Get user image path (the image revealed to everyone.)
     * If user is anonymous, return "nophoto.png".
     * @return
     */
    @Override
    String getUserImagePath() {
        long id = getUserId()

        if (id == -1L) {
            return "nophoto.png"
        } else {
            return userCrudService.readById(id).userImagePath
        }
    }

    /**
     * Get user email address (the email address not revealed to anyone.)
     * If user is anonymous, return ""(empty string).
     * @return
     */
    @Override
    String getUserEmail() {
        long id = getUserId()

        if (id == -1L) {
            return ""
        } else {
            return userCrudService.readById(id).userEmail
        }
    }


    @Override
    boolean isUserLogin() {
        boolean isLogin = -1L != getUserId()
        log.info "### user login is " + isLogin
        return isLogin
    }


    @Override
    String getUserTags() {
        long id = getUserId()

        if (id == -1L) {
            return ""
        } else {
            return userCrudService.readById(id).userTags
        }
    }


    @Override
    int getUserReputation() {
        long id = getUserId()

        if (id == -1L) {
            return 0
        } else {
            return userCrudService.readById(id).userReputation
        }
    }
}
