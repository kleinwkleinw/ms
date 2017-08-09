package de.wk.userservice;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import de.wk.userservice.dao.RedisDao;

@RestController
public class UserRestController {
	
	static private Logger logger = LoggerFactory.getLogger(UserRestController.class);

	@Autowired
	private RedisDao redisDao;
	
	@RequestMapping("/getUser")
	public User getUser (@RequestParam(value="login") String login) {
		User ud = redisDao.getUser(login);
		return ud;
	}
	
	@RequestMapping(value = "/createUser", method = RequestMethod.PUT)
	@ResponseBody
	public void createUser(@RequestBody User user) {
		logger.info("User object : {}", user);
		logger.info("Autowired redisDao : {}.", redisDao);
		redisDao.createUser(user);
	}
	
}
