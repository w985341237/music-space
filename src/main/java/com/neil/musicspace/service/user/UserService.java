package com.neil.musicspace.service.user;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description UserService
 * @Author neil
 * @Date 2021/7/9 15:31
 * @Version 1.0
 **/
public interface UserService {

    /**
     * 绑定用户手机号码 （暂时没有用）
     *
     * @param encryptedData
     * @param iv
     * @param sessionKey
     */
    String bindPhone(HttpServletRequest request, String encryptedData, String iv, String sessionKey);
}
