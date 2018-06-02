package java.com.gs.leaf;

import com.gs.leaf.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisTest {


    private SqlSessionFactory sqlSessionFactory = null;

    @Before
    public void init() throws Exception {
        // 1. 创建SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();

        // 2. 加载SqlMapConfig.xml配置文件
        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");

        // 3. 创建SqlSessionFactory对象
        this.sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
    }

    @Test
    public void testQueryUserById() throws Exception {
        // 4. 创建SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 5. 执行SqlSession对象执行查询，获取结果User
        // 第一个参数是User.xml的statement的id，第二个参数是执行sql需要的参数；

        Object user = sqlSession.selectOne("queryUserById",1);

        // 6. 打印结果
        System.out.println(user);

        // 7. 释放资源
        sqlSession.close();
    }


    @Test
    public void testQueryUserListByUserName() throws Exception {
        // 4. 创建SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 5. 执行SqlSession对象执行查询，获取结果User
        // 第一个参数是User.xml的statement的id，第二个参数是执行sql需要的参数；

        List<User> userList = sqlSession.selectList("test.queryUserListByUserName","zh");

        for (int i = 0;i<userList.size();i++){
            System.out.println(userList.get(i));
        }

        // 6. 打印结果


        // 7. 释放资源
        sqlSession.close();

    }
    @Test
    public void insertUserToDb() throws Exception {
        // 4. 创建SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 5. 执行SqlSession对象执行查询，获取结果User
        // 第一个参数是User.xml的statement的id，第二个参数是执行sql需要的参数；
        User user = new User();
        user.setUsername("zhangrui1");
        user.setPassword("abc123");
        user.setPhone("111111111111");
        user.setEmail("35456323223545@qq.com");
        user.setCreated(new Date());
        user.setUpdated(new Date());

         int id = sqlSession.insert("test.insertUserToDb",user);
            sqlSession.commit();
            System.out.println(user.getId());

        // 6. 打印结果


        // 7. 释放资源
        sqlSession.close();
    }

    @Test
    public void tsetssss() throws Exception {
        // 4. 创建SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 5. 执行SqlSession对象执行查询，获取结果User
        // 第一个参数是User.xml的statement的id，第二个参数是执行sql需要的参数；
        User user = new User();
        user.setId(15);
        user.setUsername("zhang00212212212121211");
        user.setPassword("abc123");
        user.setPhone("1388877777");
        user.setEmail("naonaoby22245@163.com");
        user.setCreated(new Date());
        user.setUpdated(new Date());

         int id = sqlSession.update("test.updateUserById",user);
            sqlSession.commit();
            System.out.println(user.getId());

        // 6. 打印结果


        // 7. 释放资源
        sqlSession.close();
    }


}
