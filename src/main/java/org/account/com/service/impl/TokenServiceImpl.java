package org.account.com.service.impl;

import org.account.com.mapper.TokenMapper;
import org.account.com.model.TokenModel;
import org.account.com.service.TokenService;
import org.account.com.util.resultJson.ResponseResult;
import org.account.com.util.sl4j.Sl4jToString;
import org.account.com.util.uuidUtil.GetUuid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class TokenServiceImpl implements TokenService {

    private static Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Value("${spring.application.name}")
    private String serviceName;

    @Resource
    private ResponseResult<TokenModel> result;
    @Autowired
    private TokenMapper mapper;

    @Override
    public ResponseResult add(TokenModel model) {
        logger.info(Sl4jToString.info(1,
                serviceName,
                Thread.currentThread().getStackTrace()[1].getMethodName(),
                model.toString(),
                200,
                null));
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
        logger.info(Sl4jToString.info(2,
                serviceName,
                Thread.currentThread().getStackTrace()[1].getMethodName(),
                result.toString(),
                result.getCode(),
                null));
        return result;
    }

    @Override
    public ResponseResult updateByToken(String token) {
        logger.info(Sl4jToString.info(1,
                serviceName,
                Thread.currentThread().getStackTrace()[1].getMethodName(),
                token,
                200,
                null));
        int i = mapper.updateByToken(token);
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
    public ResponseResult getByToken(String token) {
        logger.info(Sl4jToString.info(1,
                serviceName,
                Thread.currentThread().getStackTrace()[1].getMethodName(),
                token,
                200,
                null));
        TokenModel model = mapper.getByToken(token);
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
}
