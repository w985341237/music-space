package com.neil.musicspace.service.user;

import com.neil.musicspace.models.dto.LoginDTO;
import com.neil.musicspace.models.dto.WxCode2SessionDTO;
import com.neil.musicspace.models.entity.User;
import com.neil.musicspace.models.vo.UserVO;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description TODO
 * @Author neil
 * @Date 2021/6/29 14:15
 * @Version 1.0
 **/
public interface UserLoginService {

    /**
     * 小程序登录
     * @param content
     */
    UserVO miniProgramLogin(HttpServletResponse response, LoginDTO loginDTO, WxCode2SessionDTO content);
}
