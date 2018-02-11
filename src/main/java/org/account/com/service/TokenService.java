package org.account.com.service;

import org.account.com.model.TokenModel;
import org.account.com.util.resultJson.ResponseResult;

public interface TokenService {

    ResponseResult add(TokenModel model);

    ResponseResult updateByToken(String token, String use);

    ResponseResult getByAccount(String account);
}
