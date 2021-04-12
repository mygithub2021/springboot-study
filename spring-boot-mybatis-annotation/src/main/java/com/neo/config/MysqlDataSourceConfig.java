package com.neo.config;

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

import javax.sql.DataSource;

/**
 * @author ypf
 * @description: //TODO
 * @date 2021/3/30
 */

@Configuration
@MapperScan(basePackages = "com.neo.mapper.mysql", sqlSessionTemplateRef = "mysqlSessionTemplate")
public class MysqlDataSourceConfig {


    @Value("${spring.datasource.mysql.driver-class-name}")
    private String dirverClassName;
    @Value("${spring.datasource.mysql.jdbc-url}")
    private String url;
    @Value("${spring.datasource.mysql.username}")
    private String userName;
    @Value("${spring.datasource.mysql.password}")
    private String password;
    @Value("${spring.datasource.mysql.hikari.pool-name}")
    private String poolName;
    @Value("${spring.datasource.mysql.hikari.minimum-idle}")
    private int minimumIdle;
    @Value("${spring.datasource.mysql.hikari.maximum-pool-size}")
    private int maximumPoolSize;
    @Value("${spring.datasource.mysql.hikari.max-lifetime}")
    private long maxLifetime;
    @Value("${spring.datasource.mysql.hikari.auto-commit}")
    private boolean autoCommit;
    @Value("${spring.datasource.mysql.hikari.idle-timeout}")
    private long idleTimeout;
    @Value("${spring.datasource.mysql.hikari.connection-timeout}")
    private long connectionTimeout;
    @Value("${spring.datasource.mysql.hikari.connection-test-query}")
    private String connectionTestQuery;




    @Bean(name = "mysqlDataSource")
    @Qualifier("mysqlDataSource")
    @ConfigurationProperties(prefix="spring.datasource.mysql")
    public DataSource mysqlDataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDriverClassName(dirverClassName);
        hikariDataSource.setJdbcUrl(url);
        hikariDataSource.setUsername(userName);
        hikariDataSource.setPassword(password);
        hikariDataSource.setConnectionTimeout(connectionTimeout);
        hikariDataSource.setConnectionTestQuery(connectionTestQuery);
        hikariDataSource.setPoolName(poolName);
        hikariDataSource.setMinimumIdle(minimumIdle);
        hikariDataSource.setMaximumPoolSize(maximumPoolSize);
        hikariDataSource.setMaxLifetime(maxLifetime);
        hikariDataSource.setAutoCommit(autoCommit);
        hikariDataSource.setIdleTimeout(idleTimeout);
        return hikariDataSource;
    }

    /**
     * 创建 SqlSessionFactory
     */
    @Bean(name = "mysqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("mysqlDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    /**
     * 配置事务管理
     */
    @Bean(name = "mysqlTransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("mysqlDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "mysqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("mysqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}

