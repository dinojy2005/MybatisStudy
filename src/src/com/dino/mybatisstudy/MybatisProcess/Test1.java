package com.dino.mybatisstudy.MybatisProcess;

import java.io.IOException;
import java.io.InputStream;

import com.dino.mybatisstudy.domain.User;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.util.Iterator;
import java.util.List;

class MybatisProcess
{
    public static void main(String[] args) throws IOException
    {
        System.out.println("Hello, World!");
        MybatisProcess t1  = new MybatisProcess();
        // 单行数据
        t1.testE1();
        // 多行数据
        t1.testE2();
        // 增加数据
        t1.testDualUser();
    }

    private void testE1()
    {
        //mybatis的配置文件
        String resource = "conf.xml";
        //使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
        InputStream is = MybatisProcess.class.getClassLoader().getResourceAsStream(resource);
        //构建sqlSession的工厂
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        //使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
        //Reader reader = Resources.getResourceAsReader(resource);
        //构建sqlSession的工厂
        //SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //创建能执行映射文件中sql的sqlSession
        SqlSession session = sessionFactory.openSession();
        /**
         * 映射sql的标识字符串，
         * com.dino.mybatisstudy.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值，
         * getUser是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
         */
        // String statement = "com.dino.mybatisstudy.mapping.userMapper.getUser";//映射sql的标识字符串
        // com.dino.mybatisstudy.mapping.userMapper.getUser
        String statement = "com.dino.mybatisstudy.mapping.userMapper.getUser";//映射sql的标识字符串
        //执行查询返回一个唯一user对象的sql
        User user = session.selectOne(statement, 1);
        System.out.println(user);
    }


    private void testE2()
    {
        System.out.println("开始！");
        String resource = "conf.xml";
        InputStream is = MybatisProcess.class.getClassLoader().getResourceAsStream(resource);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession();
        // 
        String statement = "com.dino.mybatisstudy.mapping.userMapper.getAllUser";//映射sql的标识字符串
        List<User> userList = session.selectList(statement);
        Iterator<User> it = userList.iterator();
        while (it.hasNext())
        {
            System.out.println(it.next());
        }
        System.out.println("结束！");
    }

    private void testDualUser()
    {
        System.out.println("开始DualUser！");
        String resource = "conf.xml";
        InputStream is = MybatisProcess.class.getClassLoader().getResourceAsStream(resource);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession();
        //
        String statement = "com.dino.mybatisstudy.mapping.userMapper.getDualUser";//映射sql的标识字符串
        List<User> userList = session.selectList(statement);
        Iterator<User> it = userList.iterator();
        while (it.hasNext())
        {
            System.out.println(it.next());
        }
        System.out.println("结束！");

    }
}