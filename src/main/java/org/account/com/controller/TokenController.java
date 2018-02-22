package org.account.com.controller;

import io.swagger.annotations.ApiOperation;
import org.account.com.model.TokenModel;
import org.account.com.service.TokenService;
import org.account.com.util.resultJson.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
            httpMethod = "PATCH")
    @RequestMapping(value = "/token",
            method = RequestMethod.PATCH)
    public ResponseResult updateByToken(@RequestParam("token") String token, @RequestParam("use") String use) {
        return service.updateByToken(token, use);
    }

    @ApiOperation(value = "根据账户获取token",
            notes = "200:成功，500：失败",
            response = ResponseResult.class,
            httpMethod = "GET")
    @RequestMapping(value = "/token/{account}",
            method = RequestMethod.GET)
    public ResponseResult getByAccount(@PathVariable("account") String account) {
        return service.getByAccount(account);
    }
}
