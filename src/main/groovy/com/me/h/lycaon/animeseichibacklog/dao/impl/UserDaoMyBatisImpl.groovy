package com.me.h.lycaon.animeseichibacklog.dao.impl

import com.me.h.lycaon.animeseichibacklog.dao.UserDao
import com.me.h.lycaon.animeseichibacklog.dao.exception.DisallowedRequestDaoException
import com.me.h.lycaon.animeseichibacklog.domain.User
import com.me.h.lycaon.animeseichibacklog.persistence.UserMapper
import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

/**
 * Created by lycaon_h on 2014/03/21.
 */
@CompileStatic
@TypeChecked
@Slf4j
@Repository
class UserDaoMyBatisImpl extends DomainDaoMyBatisImpl<User> implements UserDao {

    @Autowired
    protected UserMapper userMapper


    @Override
    User selectByName(
            String userName
    ) {
        userMapper.selectByName(userName)
    }


    @Override
    User selectByEmail(
            String userEmail
    ) {
        userMapper.selectByEmail(userEmail)
    }


    @Override
    void insertUserRole(
            long roleId,
            long userId,
            String userRole
    ) {
        userMapper.insertUserRole(roleId, userId, userRole)
    }


    @Override
    long insert(
            User entity
    ) {

        userMapper.insert(entity)
        userMapper.selectLastSerialId()
    }


    @Override
    void update(
            User entity,
            long entityId
    ) {
        entity.userId = entityId
        userMapper.update(entity)
    }


    @Override
    List<User> selectAll() {
        userMapper.selectAll()
    }


    @Override
    User selectById(
            long entityId
    ) {
        userMapper.selectById(entityId)
    }


    @Override
    @Deprecated
    List<User> selectByRange(
            long offset,
            long limit
    ) {
        throw new DisallowedRequestDaoException("Tha user dao is NOT allowd the request.")
    }


    @Override
    List<User> selectByTag(
            String tag
    ) {
        userMapper.selectByTag(tag)
    }


    @Override
    void delete(
            long entityId
    ) {
        userMapper.delete(entityId)
    }


    @Override
    long count() {
        userMapper.count()
    }
}
