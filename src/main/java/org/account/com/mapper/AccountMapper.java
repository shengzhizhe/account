package org.account.com.mapper;

import com.github.pagehelper.Page;
import org.account.com.mapper.sql.AccountSql;
import org.account.com.model.AccountModel;
import org.apache.ibatis.annotations.*;


/**
 * //@name 人员资料
 */
public interface AccountMapper {

    String tableName = " account_table ";

    /**
     * 新增账户
     *
     * @param model AccountModel
     * @return int
     */
    @Insert({
            "insert into " + tableName + " (uuid,account,password,acctype,source,authorization,times,level) " +
                    "values (#{model.uuid},#{model.account},#{model.password},#{model.acctype}," +
                    "#{model.source},#{model.authorization},#{model.times},#{model.level})"
    })
    int add(@Param("model") AccountModel model);

    /**
     * 修改密码
     *
     * @param account  String
     * @param password String
     * @return int
     */
    @Update({
            "update" + tableName + " set password = #{password} where account = #{account}"
    })
    int putPWD(@Param("account") String account, @Param("password") String password);

    /**
     * 根据id获取实体
     *
     * @param id String
     * @return AccountModel
     */
    @Select({
            "select * from " + tableName + " where uuid = #{id}"
    })
    AccountModel getById(@Param("id") String id);

    /**
     * 根据账户获取实体
     *
     * @param account String
     * @return AccountModel
     */
    @Select({
            "select * from " + tableName + " where account=#{account}"
    })
    AccountModel getByAccount(@Param("account") String account);

    /**
     * 获取所有的指定类型的账户
     *
     * @param type    String
     * @param account String
     * @return Page<AccountModel>
     */
    @SelectProvider(type = AccountSql.class, method = "select_page_type")
    Page<AccountModel> findAllPage(@Param("type") String type, @Param("account") String account);

    /**
     * 根据id删除实体
     *
     * @param id String
     * @return int
     */
    @Delete({
            "delete from account_table where uuid = #{id}"
    })
    int del(@Param("id") String id);
}
