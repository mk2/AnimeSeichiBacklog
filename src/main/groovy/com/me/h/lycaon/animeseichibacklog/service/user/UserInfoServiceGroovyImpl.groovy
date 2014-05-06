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


    @Override
    String getUserAlias() {
        long id = getUserId()

        if (id == -1L) {
            return "NO NAME"
        } else {
            return userCrudService.readById(id).userAlias
        }
    }


    @Override
    String getUserImagePath() {
        long id = getUserId()

        if (id == -1L) {
            return "nophoto.png"
        } else {
            return userCrudService.readById(id).userImagePath
        }
    }


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
    boolean isUserEnabled() {
        long id = getUserId()

        if (id == -1L) {
            return false
        } else {
            return userCrudService.readById(id).userEmail
        }
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
