package com.hjj;

import com.hjj.mapper.UserMapper;
import com.hjj.model.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * @Auther: HJJ
 * @Date: 2019/04/28 15:25
 * @Description:注解方式的测试
 */
public class AnnotationsUser {
    @Test
    public void test(){
        String resource = "conf.xml";
        InputStream is = XmlUser.class.getClassLoader().getResourceAsStream(resource);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        List<User> users=userMapper.getAllUser();
        System.out.println(users);
    }
}
