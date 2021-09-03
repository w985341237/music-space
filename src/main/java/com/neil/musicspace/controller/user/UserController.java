package com.neil.musicspace.controller.user;

import com.neil.musicspace.models.vo.UserIndexVO;
import com.neil.musicspace.service.user.UserService;
import com.neil.musicspace.service.user.impl.UserServiceExImpl;
import com.neil.musicspace.utils.jwt.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description 用户Controller
 * @Author neil
 * @Date 2021/6/29 9:44
 * @Version 1.0
 **/
@RestController
@RequestMapping("/user")
@Api("用户Controller")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/index")
    @ApiOperation("用户首页接口")
    public UserIndexVO index(@NotNull String accessToken) {
        JwtUtil.parseToekn(accessToken);
        UserIndexVO userIndexVO = new UserIndexVO();
        return userIndexVO;
    }

    @PostMapping("/bindPhone")
    @ApiOperation("用户绑定手机号码接口")
    public String bindPhone(HttpServletRequest request, String encryptedData, String iv, String sessionKey) {
        return userService.bindPhone(request, encryptedData, iv, sessionKey);
    }
}
