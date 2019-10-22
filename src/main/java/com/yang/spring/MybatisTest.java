package com.yang.spring;

import com.yang.spring.bean.Account;
import com.yang.spring.dao.AccountDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisTest {

    public static void main(String[] args) throws Exception {

        String resource = "mybatis-config2.xml";
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader(resource));

        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            AccountDao accountDao = sqlSession.getMapper(AccountDao.class);

//            Account account=new Account("张三","232342");
//            accountDao.insert(account);
//            sqlSession.commit();

            System.out.println(accountDao.getAccounts(new Account()));
        }
    }
}
