package com.me.h.lycaon.animeseichibacklog.dao.exception;

/**
 * Created by lycaon_h on 2014/03/22.
 */
public class DisallowedRequestDaoException extends DaoException {

    public DisallowedRequestDaoException(
            String message,
            Throwable cause
    ) {
        super(message, cause);
    }

    public DisallowedRequestDaoException(String message) {
        super(message);
    }
}
