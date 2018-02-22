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
            "update token_table set is_use = 'Y' where token = #{token}"
    })
    int updateToken(@Param("token") String token);

    @Select({
            "select uuid,account,token,end_time endTimes,is_use isUse from token_table where token=#{token} ORDER BY end_time desc LIMIT 1"
    })
    TokenModel getByToken(@Param("token") String token);
}
