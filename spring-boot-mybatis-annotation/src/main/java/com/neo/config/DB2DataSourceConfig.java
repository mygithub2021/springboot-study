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

@Configuration
@MapperScan(basePackages = "com.neo.mapper.db2", sqlSessionTemplateRef = "db2SessionTemplate")
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
        dataSource.setDriverClassName(dirverClassName);//如果不配置druid会根据url自动识别dbType，然后选择相应的driverClassName？（个人使用有问题）

        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        /*默认值0。初始化时建立物理连接的个数，初始化发生在显示调用init方法，或者第一次getConnection时。*/
        dataSource.setInitialSize(10);
       /* 默认值8。最大连接池数量*/
        dataSource.setMaxActive(20);
//        dataSource.setMinIdle(minIdle);
        dataSource.setMaxWait(60000);
//        默认值为false。是否缓存preparedStatement，也就是PSCache。PSCache对支持游标的数据库性能提升巨大，比如说oracle。在mysql下建议关闭
        dataSource.setPoolPreparedStatements(false);
//        默认值-1。要启用PSCache，必须配置大于0，当大于0时，poolPreparedStatements自动触发修改为true。在Druid中，不会存在Oracle下PSCache占用内存过多的问题，可以把这个数值配置大一些，比如说100
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(-1);
//        用来检测连接是否有效的sql，要求是一个查询语句，常用select ‘X’。如果validationQuery为null，
        dataSource.setValidationQuery("select 1 from sysibm.sysdummy1");//用来检测连接是否有效
//        单位：秒，检测连接是否有效的超时时间。底层调用jdbc Statement对象的void setQueryTimeout(int seconds)方法。
        dataSource.setValidationQueryTimeout(1);
        dataSource.setTestOnBorrow(false);//申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能
        dataSource.setTestOnReturn(false);//归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能
        //申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
        dataSource.setTestWhileIdle(true);//如果检测失败，则连接将被从池中去除
        dataSource.setTimeBetweenEvictionRunsMillis(6000);
//        连接保持空闲而不被驱逐的最小时间，单位毫秒。
        dataSource.setMinEvictableIdleTimeMillis(100000);
//        属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有：监控统计用的filter:stat、日志用的filter:log4j、防御sql注入的filter:wall。
        dataSource.setFilters(filters);
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

