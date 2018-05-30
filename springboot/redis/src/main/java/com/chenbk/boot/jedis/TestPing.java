package com.chenbk.boot.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

public class TestPing {
	public static void main(String[] args) 
	{
		JedisShardInfo shardInfo=new JedisShardInfo("www.chenbk.club",6379);
		shardInfo.setPassword("lUjWdM91");
		Jedis jedis = new Jedis(shardInfo);
		System.out.println(jedis.ping());
	}
}
