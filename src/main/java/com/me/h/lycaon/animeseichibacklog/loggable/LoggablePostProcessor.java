package com.me.h.lycaon.animeseichibacklog.loggable;

import net.vidageek.mirror.dsl.Mirror;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by lycaon_h on 2014/03/02.
 */
public class LoggablePostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(
            Object bean,
            String beanName
    ) throws BeansException {
        List<Field> fields = new Mirror().on(bean.getClass())
                                         .reflectAll()
                                         .fields();
        for (Field field : fields) {
            if (Logger.class.isAssignableFrom(field.getType()) && new Mirror().on(field)
                                                                              .reflect()
                                                                              .annotation(Loggable.class) != null) {
                new Mirror().on(bean)
                            .set()
                            .field(field)
                            .withValue(LoggerFactory.getLogger(bean.getClass()));
            }
        }
        return bean;
    }

    public Object postProcessAfterInitialization(
            Object bean,
            String beanName
    ) throws BeansException {
        return bean;
    }

}
