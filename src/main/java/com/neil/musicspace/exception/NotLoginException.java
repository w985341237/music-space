package com.neil.musicspace.exception;

import com.neil.musicspace.models.enums.Result;

/**
 * @Description NotLoginException
 * @Author neil
 * @Date 2021/7/12 15:07
 * @Version 1.0
 **/

public class NotLoginException extends BasicException {
    public NotLoginException(Result result) {
        super(result);
    }
}
