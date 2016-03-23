package cn.edu.seu.ytw.service.impl;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.edu.seu.ytw.bean.User;
import cn.edu.seu.ytw.exception.LoginException;
import cn.edu.seu.ytw.exception.RegisterException;
import cn.edu.seu.ytw.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserServiceImplTest extends AbstractJUnit4SpringContextTests{



	@Resource
	private UserService userService;
	
	@Resource
	private UserService userServiceCommon;
	
	
	@Test
	public void testLogin() {
		
		//IOC  inervsion of control   DI   dependency injection
		
//		UserService userService = (UserService) ctx.getBean("userService");
//		UserService userService = new UserServiceImpl();
		
//		try {
//			User user = userService.login("zhangsan", "12345");
//			assertEquals("zhangsan",user.getName());
//		} catch (LoginException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
	}
	
	
	@Test
	public void testCommonLogin() {
		
		//IOC  inervsion of control   DI   dependency injection
		
//		UserService userService = (UserService) ctx.getBean("userService");
//		UserService userService = new UserServiceImpl();
//		
//		try {
//			User user = userServiceCommon.login("zhangsan", "11111");
//			assertEquals("zhangsan",user.getName());
//		} catch (LoginException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}

	@Test
	public void testRegister() {
//		try {
//			userService.register("qinshou", "44444", "44444");
//		} catch (RegisterException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	@Test
	public void testList(){
		List<User> list = userService.qryAll(2, 2);
		ObjectMapper mapper = new ObjectMapper();
		Map<String,List> map = new HashMap<String,List>();
		map.put("rows", list);
		try {
			String json  = mapper.writeValueAsString(map);
			System.out.println(json);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
