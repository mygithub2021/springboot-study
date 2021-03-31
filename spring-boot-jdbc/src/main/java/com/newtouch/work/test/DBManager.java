package com.newtouch.work.test;

import java.sql.*;

/**
 * @program: DBManager
 * @description: 调用db2接口
 * @author: yepengfei
 * @create: 2021/3/19、16:36
 * @Version 1.0
 **/
public   class  DBManager {
    /*Connection处理与特定数据库的连接*/
    private Connection c_ = null;
    /*Statement在指定连接中处理SQL语句*/
    private Statement st_ = null;
    /*处理数据库操作结果集*/
    private ResultSet rs_ = null;
    private  String FILE_PATH = "";

    public boolean connect(){
        String driver   = "";
        String url      = "";
        String userName = "";
        String password = "";
        FILE_PATH = this.getClass().getResource("/application.properties").getFile();
        Configration conf = new Configration(FILE_PATH);

        driver   = conf.getValue("driver");
        url      = conf.getValue("url");
        userName = conf.getValue("userName");
        password = conf.getValue("password");

        try {
            Class.forName(driver);
            /*DriverManager处理驱动程序的加载和建立新数据库连接*/
            this.c_ = DriverManager.getConnection(url,userName,password);
            this.st_ = this.c_.createStatement();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false ;
        } catch (SQLException e) {
            e.printStackTrace();
            return false ;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false ;
        }

        return true;
    }

    /**
     * create table phrase
     */
    private String SQL_PHRASE_CREATE = "CREATE TABLE USER (" +
            "USER_ID INTEGER     NOT NULL, " +
            "NAME    VARCHAR(30) NOT NULL, " +
            "AGE     VARCHAR(2))";

    public boolean create() {
        try {
            this.st_.execute(SQL_PHRASE_CREATE);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * select phrase
     */
    private String SQL_PHRASE_SELECT = "select * from user";

    /**
     * excute select
     * @return boolean
     */
    public boolean select() {
        try {
            this.rs_ = this.st_.executeQuery(SQL_PHRASE_SELECT);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excute(String sql) {
        try {
            this.st_.execute(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @return void
     */
    public void print() {
        try {
            while (rs_.next()) {
                System.out.println(rs_.getString("USER_ID") + " " + rs_.getString("NAME") + " " + rs_.getString("AGE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main (String[] args) {
        DBManager dbm = new DBManager();

        // connect db
        if (!dbm.connect()) {
            System.out.println("Failed to connect DB!");
            System.exit(0);
        }

        // create table
        if (!dbm.create()){
            System.out.println("Failed to create table!");
            System.exit(0);
        }

        // insert data
        String insert_1 = "insert into USER values (1,'Scofield' ,'25')";
        String insert_2 = "insert into USER values (2,'Burrows' ,'30')";
        if (!dbm.excute(insert_1)) {
            System.out.println("Failed to insert_1!");
        }
        if (!dbm.excute(insert_2)) {
            System.out.println("Failed to insert_2!");
        }

        // query
        if (!dbm.select()) {
            System.out.println("Failed to select!");
            System.exit(0);
        }

        dbm.print();
    }
}

