package com.neo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author ypf
 * @description: //TODO
 * @date 2021/3/30
 */

//@Configuration
//@MapperScan(basePackages = "com.neo.mapper.db2", sqlSessionTemplateRef = "db2SessionTemplate")
public class DB2DataSourceConfig {


    @Value("${spring.datasource.db2.druid.driver-class-name}")
    private String dirverClassName;
    @Value("${spring.datasource.db2.type}")
    private String dbType;
    @Value("${spring.datasource.db2.url}")
    private String url;
    @Value("${spring.datasource.db2.username}")
    private String userName;
    @Value("${spring.datasource.db2.password}")
    private String password;
    @Value("${spring.datasource.db2.druid.max-active}")
    private int maxActive;
    @Value("${spring.datasource.db2.druid.min-idle}")
    private int minIdle;
    @Value("${spring.datasource.db2.druid.test-while-idle}")
    private boolean testWhileIdle;
    @Value("${spring.datasource.db2.druid.initial-size}")
    private int initialSize;
    @Value("${spring.datasource.db2.filters}")
    private String filters;
    @Value("${spring.datasource.db2.connectionProperties}")
    private Properties properties;




    @Bean(name = "db2DataSource")
    @Qualifier("db2DataSource")
    @ConfigurationProperties(prefix="spring.datasource.db2")
    public DataSource db2DataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setDriverClassName(dirverClassName);//如果不配置druid会根据url自动识别dbType，然后选择相应的driverClassName

        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        dataSource.setInitialSize(10);
        dataSource.setMaxActive(20);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxWait(60000);
        dataSource.setPoolPreparedStatements(false);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(-1);
        dataSource.setValidationQuery("SELECT 1 FROM margin.WORKER");//用来检测连接是否有效
        dataSource.setValidationQueryTimeout(1);
        dataSource.setTestOnBorrow(false);//申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能
        dataSource.setTestOnReturn(false);//归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能
        //申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
        dataSource.setTestWhileIdle(true);//如果检测失败，则连接将被从池中去除
        dataSource.setTimeBetweenEvictionRunsMillis(6000);
        dataSource.setMinEvictableIdleTimeMillis(100000);
        dataSource.setFilters("stat,wall,logback");
        dataSource.setConnectProperties(properties);

        return dataSource;
    }

    /**
     * 创建 SqlSessionFactory
     */
    @Bean(name = "db2SessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("db2DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    /**
     * 配置事务管理
     */
    @Bean(name = "db2TransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("db2DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "db2SessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("db2SessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}

