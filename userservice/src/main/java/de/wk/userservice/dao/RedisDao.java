package de.wk.userservice.dao;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.wk.userservice.User;

public class RedisDao {
	
	static private Logger logger = LoggerFactory.getLogger(RedisDao.class);

	private StringRedisTemplate stringRedisTemplate;
	
	public User getUser (String login) {
		logger.info("Searching user with login : {}.", login);
		String userJsnData = stringRedisTemplate.opsForValue().get(login);
		ObjectMapper om = new ObjectMapper ();
		User user = null;
		try {
			user = om.readValue(userJsnData, User.class);
		} catch (JsonParseException e) {
			logger.error(e.getLocalizedMessage());
		} catch (JsonMappingException e) {
			logger.error(e.getLocalizedMessage());
		} catch (IOException e) {
			logger.error(e.getLocalizedMessage());
		}
		return user;
	}
	
	public void createUser (User user) {
		ObjectMapper om = new ObjectMapper ();
		RedisConnectionFactory cf = stringRedisTemplate.getConnectionFactory();
		cf.toString();
		try {
			String userAsJson = om.writeValueAsString(user);
			stringRedisTemplate.opsForValue().set(user.getLogin(), userAsJson);
		} catch (JsonProcessingException e) {
			logger.error(e.getLocalizedMessage());
		}
	}

	public StringRedisTemplate getStringRedisTemplate() {
		return stringRedisTemplate;
	}

	public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
		this.stringRedisTemplate = stringRedisTemplate;
	}
	
}
