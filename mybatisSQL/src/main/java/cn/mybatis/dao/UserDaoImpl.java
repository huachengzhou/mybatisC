package cn.mybatis.dao;

import cn.mybatis.common.DaoEnum;
import cn.mybatis.po.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author zhou
 */
public class UserDaoImpl implements UserDao {
    private SqlSessionFactory sqlSessionFactory;

    /**
     * 通过构造器注入
     *
     * @param sqlSessionFactory
     */
    public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public SqlSession getSession() {
        return sqlSessionFactory.openSession();
    }

    public void sessionClose(SqlSession sqlSession) {
        if (sqlSession != null) sqlSession.close();
    }

    @Override
    public User findUserById(int id) throws Exception {
        SqlSession sqlSession = getSession();
        User user = sqlSession.selectOne(DaoEnum.DAO_ENUM.getVar() + "findUserById", id);
        sessionClose(sqlSession);
        return user;
    }

    @Override
    public List<User> findUserByName(String name) throws Exception {
        SqlSession sqlSession = getSession();
        List<User> users = sqlSession.selectList(DaoEnum.DAO_ENUM.getVar() + "selectByNameList", "%" + name + "%");
        sessionClose(sqlSession);
        return users;
    }

    @Override
    public void insertUser(User user) throws Exception {
        SqlSession sqlSession = getSession();
        sqlSession.insert(DaoEnum.DAO_ENUM.getVar() + "insertInToUser", user);
        sqlSession.commit();
        sessionClose(sqlSession);
    }

    @Override
    public void deleteUser(int id) throws Exception {
        SqlSession sqlSession = getSession();
        sqlSession.delete(DaoEnum.DAO_ENUM.getVar() + "deleteUser", id);
        sqlSession.commit();
        sessionClose(sqlSession);
    }
}
