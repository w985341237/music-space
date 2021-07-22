package com.neil.musicspace.controller.index;

import com.neil.musicspace.models.vo.IndexVO;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 首页接口
 * @Author neil
 * @Date 2021/7/7 22:06
 * @Version 1.0
 **/
@RestController
@RequestMapping("/index")
@Api("首页接口")
public class IndexController {

    public IndexVO getIndexInfo() {
        return null;
    }
}
