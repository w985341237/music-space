package com.neil.musicspace.controller.passport;

import com.neil.musicspace.service.passport.WechatLoginManager;
import com.neil.musicspace.service.user.UserLoginManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;

/**
 * @Description TODO
 * @Author neil
 * @Date 2021/6/29 10:01
 * @Version 1.0
 **/
@RestController
@RequestMapping("/passport")
@Api("登录验证接口")
public class LoginController {
    @Autowired
    private WechatLoginManager wechatLoginManager;

    @Autowired
    private UserLoginManager userLoginManager;

    @ApiOperation("小程序登录")
    @GetMapping("/login")
    public void login(@NotEmpty(message = "code不能为空")String code) {
        String content = wechatLoginManager.code2Session(code);

        userLoginManager.miniProgramLogin(content);
    }
}
