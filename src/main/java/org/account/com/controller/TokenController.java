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
            response = ResponseResult.class,
            httpMethod = "POST",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/token",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseResult add(@RequestBody TokenModel model) {
        return service.add(model);
    }

    @ApiOperation(value = "根据token修改使用状态",
            response = ResponseResult.class,
            httpMethod = "POST",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/updateToken",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseResult updateToken(@RequestParam("token") String token) {
        return service.updateToken(token);
    }

    @ApiOperation(value = "根据token获取token",
            response = ResponseResult.class,
            httpMethod = "GET",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/token/{token}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseResult getByToken(@PathVariable("token") String token) {
        return service.getByToken(token);
    }
}
