package com.mybatis;

import cn.mybatis.dao.UserDao;
import cn.mybatis.dao.UserDaoImpl;
import cn.mybatis.po.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

public class UserDaoImplTest {
    private Logger logger = Logger.getLogger(this.toString());
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void findUserById() throws Exception {
        UserDao userDao = new UserDaoImpl(sqlSessionFactory);
        User user = userDao.findUserById(1);
        logger.info("" + user);
    }

    @Before
    public void isit() throws IOException {
        // mybatis配置文件
        String resource = "SqlMapConfig.xml";
        //得到配置文件输入流
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 创建会话工厂，传入mybatis的配置文件信息
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        this.sqlSessionFactory = sessionFactory;
    }
}
