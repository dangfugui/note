package dang.note.redis;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanResult;

import java.util.List;
import java.util.Set;

/**
 * Created by dangqihe on 2016/10/11.
 */
public class RedisJava {
    private Jedis jedis = null;

    @Before
    public void create() {
        jedis = new Jedis("localhost");
        System.out.println(jedis.ping());
        System.out.println("Connection to server sucessfully");
    }

    @Test
    public void testString() {
        String key = "stringKey";
        jedis.set(key, "String value1");//增
        System.out.println(jedis.get(key));//查询
        jedis.set(key, "new value2");//改
        System.out.println(jedis.get(key));
        jedis.del("stringKey");//删除
        System.out.println(jedis.get("stringKey"));
        jedis.sync();
    }

    @Test
    public void testList() {
        String key = "listKey";
        jedis.del(key);
        for (int i = 0; i < 10; i++) {
            jedis.lpush(key, "listValue_" + i);
        }
        Long size = jedis.llen(key);
        System.out.println("llen:" + size);
        List<String> list = jedis.lrange(key, 0, 20);//  获取列表指定范围内的元素

        for (String string : list) {
            System.out.println(string);
        }
        String lpop = jedis.lpop(key);//移出并获取列表的第一个元素
        System.out.println("blpop<<<<<<" + lpop);
        list = jedis.lrange(key, 0, 20);
        for (String string : list) {
            System.out.println(string);
        }
    }

    @Test
    public void testSet() {
        String key = "setKey";
        jedis.sadd(key, "setValue1", "setValue2", "setValue1", "setValue3");
        Long size = jedis.scard(key);
        System.out.println("scard:" + size);
        Set<String> set = jedis.smembers(key);
        for (String value : set) {
            System.out.println(value);
        }
        String value = jedis.spop(key);
        System.out.println("spopValue:" + value);
        for (String v : jedis.smembers(key)) {
            System.out.println(v);
        }
    }

    @Test
    public void testZset() {
        String key = "zsetKey";
        jedis.zadd(key, 20, "zvalue1");
        jedis.zadd(key, 20, "zvalue2");
        jedis.zadd(key, 20, "zvalue3");
        System.out.println("zcard:" + jedis.zcard(key));
        Double zscore = jedis.zscore(key, "2");
        jedis.zrem(key, "zvalue2");
        Set<String> zset = jedis.zrange(key, 0, 20);
        for (String s : zset) {
            System.out.println(s);
        }
    }

    @Test
    public void testKeys() {
        Set<String> keys = jedis.keys("*");
        for (String key : keys) {
            System.out.println(key);
        }
    }

}
