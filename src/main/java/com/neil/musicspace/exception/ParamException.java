package com.neil.musicspace.exception;

import com.neil.musicspace.models.enums.Result;

/**
 * @Description ParamException
 * @Author neil
 * @Date 2021/7/12 15:06
 * @Version 1.0
 **/

public class ParamException extends BasicException {
    public ParamException(Result result) {
        super(result);
    }
}
