package com.mybatis;

import cn.mybatis.mapper.UserMapper;
import cn.mybatis.po.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

public class UserMapperTest {
    private Logger logger = Logger.getLogger(this.toString());
    private SqlSession sqlSession = null;

    @Test
    public void userMapper() throws Exception {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.findUserById(1);
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

        // 通过工厂得到SqlSession
        sqlSession = sessionFactory.openSession();
        // 通过SqlSession操作数据库
        // 第一个参数：映射文件中statement的id，等于=namespace+"."+statement的id
        // 第二个参数：指定和映射文件中所匹配的parameterType类型的参数
        // sqlSession.selectOne结果 是与映射文件中所匹配的resultType类型的对象
        // selectOne查询出一条记录
    }

    @After
    public void end() {
        if (sqlSession != null) sqlSession.close();
    }
}
