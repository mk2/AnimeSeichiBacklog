package com.me.h.lycaon.animeseichibacklog.dao.impl

import com.me.h.lycaon.animeseichibacklog.domain.Feature
import com.me.h.lycaon.animeseichibacklog.persistence.FeatureMapper
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Ignore
import spock.lang.Specification

/**
 * Created by lycaon_h on 2014/03/21.
 */
@ContextConfiguration(locations = "/spring/test-dao-context.xml")
@Slf4j
class FeatureDaoMyBatisImplSpec extends Specification {

    FeatureMapper featureMapper = GroovyMock(FeatureMapper)

    @Autowired
    DelegatingFeatureMapper delegatingFeatureMapper

    @Autowired
    FeatureDaoMyBatisImpl featureDao

    void setup() {
        delegatingFeatureMapper.featureMapperDelegate = featureMapper
    }

    void cleanup() {

    }

    def "insert test"() {
        setup:
        Feature feature = GroovyMock(Feature)
        featureMapper.insert(_) >> 0L

        when:
        long id = featureDao.insert(feature)
        featureDao.selectById(id)

        then:
        1 * featureMapper.insert(_)
        0 * featureMapper.selectById(_)
        featureDao.featureCache.size() == 1
    }

    def "update test"() {
        setup:
        Feature feature = GroovyMock(Feature)

        when:
        featureDao.update(feature, 0L)

        then:
        1 * featureMapper.update(_)
        featureDao.featureCache.size() == 1
    }

    def "delete test"() {
        setup:
        Feature feature = GroovyMock(Feature)
        feature.getFeatureId() >> 0L
        featureDao.insert(feature)

        when:
        featureDao.delete(0L)

        then:
        1 * featureMapper.delete(_)
        featureDao.featureCache.size() == 0
    }

    @Ignore
    def "select by range test"() {
        setup:
        featureMapper.selectByRange(_, _) >> { [] }

        when:
        featureDao.selectByRange(0L, 1L)
        featureDao.selectByRange(0L, 1L)

        then:
        (1.._) * featureMapper.selectByRange(_, _)
    }

}
