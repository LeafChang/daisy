package java.com.gs.leaf;

import com.gs.leaf.mapper.UserMapper;
import com.gs.leaf.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-dao.xml")
public class MybatisMapperTest {


    //private SqlSessionFactory sqlSessionFactory = null;

   /* @Before
    public void init() throws Exception {
        // 1. 创建SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();

        // 2. 加载SqlMapConfig.xml配置文件
        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");

        // 3. 创建SqlSessionFactory对象
        this.sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
    }*/


   /* @Test
    public void findUserByIds() throws Exception{

        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user  = userMapper.queryUserById(15);

        System.out.println(user);


    }
*/

    @Autowired
    private UserMapper userMapper;


    @Test
    public void insertAndSelect() throws  Exception{
        User user = userMapper.queryUserById(10);

        System.out.println(user);
    }


}
