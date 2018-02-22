package org.account.com.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.account.com.mapper.AccountMapper;
import org.account.com.model.AccountModel;
import org.account.com.service.AccountService;
import org.account.com.util.resultJson.ResponseResult;
import org.account.com.util.sl4j.Sl4jToString;
import org.account.com.util.uuidUtil.GetUuid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private static Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Value("${spring.application.name}")
    private String serviceName;

    @Resource
    private AccountMapper mapper;
    @Resource
    private ResponseResult<AccountModel> result;

    @Override
    public ResponseResult add(AccountModel model) {
        logger.info(Sl4jToString.info(1,
                serviceName,
                Thread.currentThread().getStackTrace()[1].getMethodName(),
                model.toString(),
                200,
                null));
        AccountModel model1 = mapper.getByAccount(model.getAccount());
        if (model1 != null) {
            result.setSuccess(false);
            result.setCode(501);
            result.setMessage(null);
            result.setData(null);
        } else {
            model.setUuid(GetUuid.getUUID());
            int i = mapper.add(model);
            switch (i) {
                case 1:
                    result.setSuccess(true);
                    result.setCode(200);
                    result.setMessage(null);
                    result.setData(model);
                    break;
                default:
                    result.setSuccess(false);
                    result.setCode(500);
                    result.setMessage(null);
                    result.setData(null);
                    break;
            }
        }
        logger.info(Sl4jToString.info(2,
                serviceName,
                Thread.currentThread().getStackTrace()[1].getMethodName(),
                result.toString(),
                result.getCode(),
                null));
        return result;
    }

    @Override
    public ResponseResult putPWD(String account, String password) {
        logger.info(Sl4jToString.info(1,
                serviceName,
                Thread.currentThread().getStackTrace()[1].getMethodName(),
                account + "," + password,
                200,
                null));
        int i = mapper.putPWD(account, password);
        switch (i) {
            case 0:
                result.setSuccess(true);
                result.setCode(201);
                result.setMessage(null);
                result.setData(null);
                break;
            case 1:
                result.setSuccess(true);
                result.setCode(200);
                result.setMessage(null);
                result.setData(null);
                break;
            default:
                result.setSuccess(false);
                result.setCode(500);
                result.setMessage(null);
                result.setData(null);
                break;
        }
        logger.info(Sl4jToString.info(2,
                serviceName,
                Thread.currentThread().getStackTrace()[1].getMethodName(),
                result.toString(),
                result.getCode(),
                null));
        return result;
    }

    @Override
    public ResponseResult getById(String id) {
        logger.info(Sl4jToString.info(1,
                serviceName,
                Thread.currentThread().getStackTrace()[1].getMethodName(),
                id,
                200,
                null));
        AccountModel model = mapper.getById(id);
        if (model != null) {
            result.setSuccess(true);
            result.setCode(200);
            result.setData(model);
        } else {
            result.setSuccess(false);
            result.setCode(404);
            result.setData(null);
        }
        logger.info(Sl4jToString.info(2,
                serviceName,
                Thread.currentThread().getStackTrace()[1].getMethodName(),
                result.toString(),
                200,
                null));
        return result;
    }

    @Override
    public ResponseResult getByAccount(String account) {
        logger.info(Sl4jToString.info(1,
                serviceName,
                Thread.currentThread().getStackTrace()[1].getMethodName(),
                account,
                200,
                null));
        AccountModel model = mapper.getByAccount(account);
        if (model != null) {
            result.setSuccess(true);
            result.setCode(200);
            result.setData(model);
        } else {
            result.setSuccess(false);
            result.setCode(404);
            result.setData(null);
        }
        logger.info(Sl4jToString.info(2,
                serviceName,
                Thread.currentThread().getStackTrace()[1].getMethodName(),
                result.toString(),
                result.getCode(),
                null));
        return result;
    }

    @Override
    public ResponseResult findAllPage(int pageNow, int pageSize, String type, String account) {
        ResponseResult<Page<AccountModel>> result = new ResponseResult<>();
        logger.info(Sl4jToString.info(1,
                serviceName,
                Thread.currentThread().getStackTrace()[1].getMethodName(),
                pageNow + "," + pageSize + "," + type,
                200,
                null));
        PageHelper.startPage(pageNow, pageSize);
        Page<AccountModel> page = mapper.findAllPage(type, account);
        result.setSuccess(true);
        if (page.size() > 0)
            result.setCode(200);
        else
            result.setCode(404);
        result.setData(page);
        logger.info(Sl4jToString.info(2,
                serviceName,
                Thread.currentThread().getStackTrace()[1].getMethodName(),
                result.toString(),
                result.getCode(),
                null));
        return result;
    }

    @Override
    public ResponseResult del(String id) {
        logger.info(Sl4jToString.info(1,
                serviceName,
                Thread.currentThread().getStackTrace()[1].getMethodName(),
                id,
                200,
                null));
        int i = mapper.del(id);
        switch (i) {
            case 0:
                result.setSuccess(true);
                result.setCode(201);
                result.setData(null);
                break;
            case 1:
                result.setSuccess(true);
                result.setCode(200);
                result.setData(null);
                break;
            default:
                result.setSuccess(false);
                result.setCode(500);
                result.setData(null);
                break;
        }
        logger.info(Sl4jToString.info(2,
                serviceName,
                Thread.currentThread().getStackTrace()[1].getMethodName(),
                result.toString(),
                result.getCode(),
                null));
        return result;
    }
}
