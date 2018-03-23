package org.account.com.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.account.com.model.AccountModel;
import org.account.com.service.AccountService;
import org.account.com.util.resultJson.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * '@name' 用户管理
 * <p>
 * '@Api' 用在类上，说明该类的作用
 * '@ApiOperation' 用在方法上，说明方法的作用
 * '@ApiImplicitParams' 用在方法上包含一组参数说明
 * '@ApiImplicitParam' 用在@ApiImplicitParams注解中，指定一个请求参数的各个方面 paramType：参数放在哪个地方
 * header-->请求参数的获取：@RequestHeader
 * query-->请求参数的获取：@RequestParam
 * path（用于restful接口）-->请求参数的获取：@PathVariable
 * body（不常用）
 * form（不常用）
 * name：参数名
 * dataType：参数类型
 * required：参数是否必须传
 * value：参数的意思
 * defaultValue：参数的默认值
 * '@ApiResponses'：用于表示一组响应
 * '@ApiResponse'：用在@ApiResponses中，一般用于表达一个错误的响应信息 code：数字，例如400
 * message：信息，例如"请求参数没填好"
 * response：抛出异常的类
 * '@ApiModel'：描述一个Model的信息（这种一般用在post创建的时候，使用@RequestBody这样的场景，
 * 请求参数无法使用@ApiImplicitParam注解进行描述的时候）
 * '@ApiModelProperty'：描述一个model的属性
 */
@Api(value = "account", description = "账户")
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @ApiOperation(value = "账户分页",
            response = ResponseResult.class,
            httpMethod = "GET",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/page",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseResult init(@RequestParam(value = "pageNow") int pageNow,
                               @RequestParam(value = "pageSize") int pageSize,
                               @RequestParam(value = "type", required = false) String type,
                               @RequestParam(value = "account", required = false) String account) {
        return accountService.findAllPage(pageNow, pageSize, type, account);
    }


    @ApiOperation(value = "根据账户修改密码",
            response = ResponseResult.class,
            httpMethod = "POST",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/pwd",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseResult password(@RequestParam("account") String account,
                                   @RequestParam("password") String password) {
        return accountService.putPWD(account, password);
    }

    @ApiOperation(value = "根据id删除",
            response = ResponseResult.class,
            httpMethod = "GET",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/del",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseResult del(@RequestParam("id") String id) {
        return accountService.del(id);
    }

    /**
     * produces = "application/json;charset=utf-8"
     * consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
     */
    @ApiOperation(value = "添加账户",
            response = ResponseResult.class,
            httpMethod = "POST",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/account",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseResult<AccountModel> add(@Valid @RequestBody AccountModel model,
                                            BindingResult bindingResult) {
        ResponseResult<AccountModel> result = new ResponseResult<>();
        //数据验证
        if (bindingResult.hasErrors()) {
            result.setSuccess(false);
            result.setMessage(bindingResult.getFieldError().getDefaultMessage());
            result.setData(null);
            return result;
        }
//新增账户
        return accountService.add(model);
    }

    @ApiOperation(value = "根据账户查找",
            response = ResponseResult.class,
            httpMethod = "GET",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/acc",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseResult getByAccount(@RequestParam("account") String account) {
        return accountService.getByAccount(account);
    }

    @ApiOperation(value = "根据id查找",
            response = ResponseResult.class,
            httpMethod = "GET",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/id",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseResult get(@RequestParam("id") String id) {
        return accountService.getById(id);
    }
}
