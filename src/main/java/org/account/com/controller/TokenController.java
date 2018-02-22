package org.account.com.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.account.com.model.TokenModel;
import org.account.com.service.TokenService;
import org.account.com.util.resultJson.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api(value = "token", description = "token")
@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private TokenService service;

    @ApiOperation(value = "添加token",
            notes = "200:成功；501:重复,400:数据格式不符合要求",
            response = ResponseResult.class,
            httpMethod = "POST",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/token",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseResult add(@RequestBody TokenModel model) {
        return service.add(model);
    }

    @ApiOperation(value = "根据token修改使用状态",
            notes = "200:成功，500：失败，201：已不存在",
            response = ResponseResult.class,
            httpMethod = "POST")
    @RequestMapping(value = "/updateToken",
            method = RequestMethod.POST)
    public ResponseResult updateToken(@RequestParam("token") String token) {
        return service.updateToken(token);
    }

    @ApiOperation(value = "根据token获取token",
            notes = "200:成功，500：失败",
            response = ResponseResult.class,
            httpMethod = "GET")
    @RequestMapping(value = "/token/{token}",
            method = RequestMethod.GET)
    public ResponseResult getByToken(@PathVariable("token") String token) {
        return service.getByToken(token);
    }
}
