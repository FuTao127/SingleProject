package com.szeastroc.middle.code.syn.config.db.datasource;


import com.szeastroc.middle.code.syn.config.db.jdbc.JdbcConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: futao
 * @Date: 2021/12/13
 **/
@Component
@ConfigurationProperties(prefix = "middle.base.jdbc")
public class MiddleBaseJdbcConfig extends JdbcConfig {
    public static final String NAME = "middle_base";
}
