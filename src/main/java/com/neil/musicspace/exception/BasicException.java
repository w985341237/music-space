package com.neil.musicspace.exception;

/**
 * @Description BasicException
 * @Author neil
 * @Date 2021/7/12 15:03
 * @Version 1.0
 **/

import com.neil.musicspace.models.enums.Result;
import lombok.Getter;

@Getter
public class BasicException extends RuntimeException {
    private Integer code;

    public BasicException(Result result) {
        super(result.getMsg());
        this.code = result.getCode();
    }
}
