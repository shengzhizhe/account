package org.account.com.mapper;

import org.account.com.model.TokenModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface TokenMapper {

    @Insert({
            "insert into token_table (uuid,account,token,end_time,is_use)" +
                    " values (#{model.uuid},#{model.account},#{model.token},#{model.endTimes},#{model.isUse})"
    })
    int add(@Param("model") TokenModel model);

    @Update({
            "update token_table set is_use = #{use} where token = #{token}"
    })
    int updateByToken(@Param("token") String token, @Param("use") String use);

    @Select({
            "select uuid,account,token,end_time endTimes,is_use isUse from token_table where account=#{account}"
    })
    TokenModel getByAccount(@Param("account") String account);
}
