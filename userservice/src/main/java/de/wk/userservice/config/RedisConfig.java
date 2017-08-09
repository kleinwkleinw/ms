package de.wk.userservice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {

	static private Logger logger = LoggerFactory.getLogger(RedisConfig.class);
	
	@Value("${redis.hostname:192.168.89.38}")
	private String hostName;
	@Value("${redis.portnumber:6379}")
	private int portNumber;

	@Value("${jedis.maxTotal:32}")
	private int maxTotalConnections;
	@Value("${jedis.minIdle:8}")
	private int minIdleConnections;
	@Value("${jedis.maxIdle:16}")
	private int maxIdleConnections;
	
	@Bean (name = "jedisConnectionFactory")
	JedisConnectionFactory jedisConnectionFactory() {
		logger.info("Setting up jedis connection factory.");
		JedisPoolConfig jpc = new JedisPoolConfig();
		jpc.setMaxTotal(maxTotalConnections);
		jpc.setMinIdle(minIdleConnections);
		jpc.setMaxIdle(maxIdleConnections);
		jpc.setJmxEnabled(true);

		JedisConnectionFactory jcf = new JedisConnectionFactory();
		jcf.setHostName(hostName);
		jcf.setPort(portNumber);
		jcf.setPoolConfig(jpc);
		jcf.setUsePool(true);

		logger.info("Initialized jedis connection pool successfully. Hostname : {}, port number: {}.", hostName, portNumber);

		return jcf;
	}
	
	@Bean (name = "redisTemplate")
	public StringRedisTemplate redisTemplate() {
		StringRedisTemplate template = new StringRedisTemplate();
		template.setConnectionFactory(jedisConnectionFactory());
		template.setDefaultSerializer(stringRedisSerializer());
		return template;
	}
	
	@Bean (name = "stringRedisSerializer")
	StringRedisSerializer stringRedisSerializer() {
		return new StringRedisSerializer();
	}
	
}
