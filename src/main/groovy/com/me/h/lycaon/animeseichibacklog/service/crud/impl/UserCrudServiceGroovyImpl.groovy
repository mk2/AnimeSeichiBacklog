package com.me.h.lycaon.animeseichibacklog.service.crud.impl

import com.google.common.cache.Cache
import com.google.common.cache.CacheBuilder
import com.me.h.lycaon.animeseichibacklog.dao.UserDao
import com.me.h.lycaon.animeseichibacklog.domain.User
import com.me.h.lycaon.animeseichibacklog.service.crud.UserCrudService
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
/**
 * Created by lycaon_h on 2014/03/07.
 */
@CompileStatic
@Slf4j
@Service
final class UserCrudServiceGroovyImpl implements UserCrudService {

    Cache<Serializable, Collection<User>> userCache


    @Autowired
    UserCrudServiceGroovyImpl(
            CacheBuilder crudCacheBuilder
    ) {
        userCache = crudCacheBuilder.build()
    }

    @Autowired
    UserDao userDao


    @Override
    long create(
            User user
    ) {
        long userId = userDao.insert(user)

        userDao.insertUserRole(0L, userId, "ROLE_USER")

        return userId
    }


    @Override
    User readByEmail(
            String userEmail
    ) {
        return userDao.selectByEmail(userEmail)
    }


    @Override
    User readById(
            long userId
    ) {
        return userDao.selectById(userId)
    }


    @Override
    User readByName(
            String name
    ) {
        return userDao.selectByName(name)
    }


    @Override
    List<User> readByRange(
            long offset,
            long limit
    ) {
        return userDao.selectByRange(offset, limit)
    }


    @Override
    List<User> readAll() {
        return userDao.selectAll()
    }


    @Override
    List<User> readByTag(
            String tag
    ) {
        return userDao.selectByTag(tag)
    }


    @Override
    List<User> readByTags(
            String[] tags
    ) {
        return null
    }


    @Override
    List<User> readByTags(
            List<String> tags
    ) {
        return readByTags(tags as String[])
    }


    @Override
    void update(
            long id,
            User entity
    ) {
        userDao.update(entity, id)
    }


    @Override
    void delete(
            long id
    ) {
        userDao.delete(id)
    }


    @Override
    long count() {
        return userDao.count()
    }
}
