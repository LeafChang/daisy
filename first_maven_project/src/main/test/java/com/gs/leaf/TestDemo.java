package java.com.gs.leaf;

import com.gs.leaf.util.RedisUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;

public class TestDemo {

    @Test
    public void test1(){

        Jedis jedis = RedisUtils.getJedis();

        jedis.set("leaf","12345");

        System.out.print(jedis.get("leaf"));


    }


}
