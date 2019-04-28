package com.hjj.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * @Auther: HJJ
 * @Date: 2019/04/28 15:34
 * @Description:
 * 用mybatis实现的dao层，具体核对对象是SqlSession。
 * 由于dao层依赖于SqlSession。而SqlSession的创建过程需要SqlSessionFactory对象，
 * 此对象对于多次的CRUD操作只需要一个对象，所以这里引用另一个自定义的MyBatisUtils工具类。
 *
 */
public class MybatisUtil {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        String resource = "conf.xml";
        //加载mybatis 的配置文件（它也加载关联的映射文件）
        InputStream is = MybatisUtil.class.getClassLoader().getResourceAsStream(resource);
        //构建sqlSession 的工厂
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
    }

    /**
     *  返回一个SqlSession对象(每次返回一个新的SqlSession对象)
     * 若涉及多个表的操作，涉及事务的，要做到操作失败时回滚，那么建议自定义一个TransactionUtils的工具类
     *  用ThreadLocal类来保存SqlSession类，这样跨多个dao操作时，确保获取的都是同一SqlSession对象。
     *  然后在service层中捕获异常，再catch上用session的回滚。
     */
    public static SqlSession openSesison(){
        return  sqlSessionFactory.openSession();//使用参数 true 则为自动提交
    }
}
