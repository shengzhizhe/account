package org.account.com.mapper.sql;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountSql {
    private static Logger logger = LoggerFactory.getLogger(AccountSql.class);

    public String select_page_type(final @Param("type") String type, final @Param("account") String account) {
        String s = new SQL() {{
            SELECT("*");
            FROM("account_table");
            if (type != null && !type.trim().isEmpty()) {
                WHERE("acctype=#{type}");
            }
            if (account != null && !account.trim().isEmpty()) {
                WHERE("account=#{account}");
            }
        }}.toString();
        logger.info("[sql]---[" + s + "]");
        return s;
    }
}
