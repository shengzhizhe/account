package org.account.com.service;

import org.account.com.model.AccountModel;
import org.account.com.util.resultJson.ResponseResult;

/**
 * @name 账户接口
 */
public interface AccountService {
    /**
     * 新增账户
     *
     * @param model
     * @return int
     */
    ResponseResult add(AccountModel model);

    /**
     * 修改密码
     *
     * @param account
     * @param password
     * @return
     */
    ResponseResult putPWD(String account, String password);

    /**
     * 根据id获取实体
     *
     * @param id
     * @return
     */
    ResponseResult getById(String id);

    /**
     * 根据账户获取实体
     *
     * @param account
     * @return
     */
    ResponseResult getByAccount(String account);

    /**
     * 根据类型获取账户
     *
     * @return
     */
    ResponseResult findAllPage(int pageNow, int pageSize, String type,String account);

    /**
     * 根据id删除实体
     *
     * @param id
     * @return
     */
    ResponseResult del(String id);

}
