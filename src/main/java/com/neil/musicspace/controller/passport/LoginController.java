package com.neil.musicspace.controller.passport;

import com.neil.musicspace.models.dto.LoginDTO;
import com.neil.musicspace.models.dto.WxCode2SessionDTO;
import com.neil.musicspace.models.vo.UserVO;
import com.neil.musicspace.service.passport.WechatLoginService;
import com.neil.musicspace.service.user.UserLoginService;
import com.neil.musicspace.service.weixin.WeixinService;
import com.neil.musicspace.utils.WeixinUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description 登录接口
 * @Author neil
 * @Date 2021/6/29 10:01
 * @Version 1.0
 **/
@RestController
@RequestMapping("/passport")
@Api("登录验证控制器")
public class LoginController {
    @Autowired
    private WeixinService weixinService;

    @Autowired
    private WechatLoginService wechatLoginService;

    @Autowired
    private UserLoginService userLoginService;

    @ApiOperation("小程序登录")
    @PostMapping("/login")
    public UserVO login(HttpServletResponse response, LoginDTO loginDTO) {
        WxCode2SessionDTO content = weixinService.code2Session(loginDTO.getCode());

        return userLoginService.miniProgramLogin(response, loginDTO, content);

    }
}
