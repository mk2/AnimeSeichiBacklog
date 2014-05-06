package com.me.h.lycaon.animeseichibacklog.loggable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by lycaon_h on 2014/03/02.
 * Loggableアノテーション
 * 自動でSLF4Jのロガーを注入します
 * 注入されるフィールドはstaticでないとダメです
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Loggable {
    // no methods
}

