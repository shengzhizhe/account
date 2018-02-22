package org.account.com.service;

import org.account.com.model.TokenModel;
import org.account.com.util.resultJson.ResponseResult;

public interface TokenService {

    ResponseResult add(TokenModel model);

    ResponseResult updateByToken(String token);

    ResponseResult getByToken(String token);
}
