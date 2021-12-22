package com.szeastroc.middle.code.syn.config.db.mybatis;

import com.alibaba.druid.pool.DruidDataSource;
import com.szeastroc.middle.code.syn.config.db.datasource.MiddleBaseJdbcConfig;
import com.szeastroc.middle.code.syn.config.db.jdbc.DynamicDataSource;
import com.szeastroc.middle.code.syn.config.db.jdbc.JdbcConfig;
import com.szeastroc.middle.code.syn.config.db.jdbc.PageInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: futao
 * @Date: 2021/12/13
 **/
@Configuration
@MapperScan(basePackages = {"com.szeastroc.middle.code.syn.mapper"})
@EnableTransactionManagement
public class MybatisConfig {


    @Bean
    @Qualifier("sqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactory(@Autowired DataSource dataSource) throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.addInterceptor(new PageInterceptor());
        sqlSessionFactoryBean.setConfiguration(configuration);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
        return sqlSessionFactoryBean;
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(@Autowired SqlSessionFactory sqlSessionFactory) {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        return sqlSessionTemplate;
    }

    @Bean
    public DataSource basicDataSource(
            MiddleBaseJdbcConfig middleBaseJdbcConfig
    ) throws SQLException {


        DataSource middleBaseJdbcSource = createDataSource(middleBaseJdbcConfig);

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setDefaultTargetDataSource(middleBaseJdbcSource);


        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        targetDataSources.put(MiddleBaseJdbcConfig.NAME, middleBaseJdbcSource);


        dynamicDataSource.setTargetDataSources(targetDataSources);

        return dynamicDataSource;
    }

    public DataSource createDataSource(JdbcConfig config) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(config.getDriverClassName());
        dataSource.setUrl(config.getUrl());
        dataSource.setUsername(config.getUsername());
        dataSource.setPassword(config.getPassword());
        dataSource.setMaxActive(32);
        dataSource.setMinIdle(8);
        dataSource.setMaxWait(60000);
        return dataSource;
    }


}
