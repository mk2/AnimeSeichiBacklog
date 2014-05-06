package com.me.h.lycaon.animeseichibacklog.dao.exception;

/**
 * Created by lycaon_h on 2014/03/22.
 */
public class DaoException extends Exception {

    public DaoException(
            String message,
            Throwable cause
    ) {
        super(message, cause);
    }

    public DaoException(String message) {
        super(message);
    }
}
