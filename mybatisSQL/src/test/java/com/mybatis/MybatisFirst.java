package com.mybatis;

import cn.mybatis.po.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.zhou.help.Zhou_Word;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class MybatisFirst {
    private Logger logger = Logger.getLogger(this.toString());
    private SqlSession sqlSession = null;


    @Test
    public void findUserByIdTest() {
        User user = sqlSession.selectOne("test.findUserById", 1);
        logger.info("user:" + user);
    }

    @Test
    public void selectByNameList() {
        List<User> list = sqlSession.selectList("test.selectByNameList", "%陈%");
        list.forEach((s) -> logger.info(s + ""));
    }

    @Test
    public void insertInToUser() {
        User user = new User();
        user.setAddress(new Random().nextBoolean() ? "成都" : "天津").setBirthday(new Date()).setSex(new Random().nextBoolean() ? "1" : "2").setUsername(Zhou_Word.getChineseName());
        sqlSession.insert("test.insertInToUser", user);
        sqlSession.commit();
        logger.info("end! id:" + user.getId());
    }

    @Test
    public void deleteUser() {
        sqlSession.delete("test.deleteUser", 2018);
        sqlSession.commit();
        logger.info("end!");
    }

    @Test
    public void updateUser() {
        User user = sqlSession.selectOne("test.findUserById", 26);
        user.setSex(new Random().nextBoolean() ? "1" : "2");
        user.setAddress(new Random().nextBoolean() ? "北京" : "南京");
        user.setBirthday(new Date());
        logger.info("" + user);
        sqlSession.update("test.updateUser", user);
        sqlSession.commit();
        logger.info("end!");
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
