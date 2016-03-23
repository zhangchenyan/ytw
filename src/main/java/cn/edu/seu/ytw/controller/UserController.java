package cn.edu.seu.ytw.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.edu.seu.ytw.bean.User;
import cn.edu.seu.ytw.exception.RegisterException;
import cn.edu.seu.ytw.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

//	@RequestMapping("/{id}")
//	public ModelAndView view(@PathVariable("id") Integer id, HttpServletRequest req) {
//
//		User user = userService.qryById(id);
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("user", user);
//		mv.setViewName("user/view");
//		return mv;
//	}

//	@RequestMapping(method = RequestMethod.GET)
//	public String register() {
//		return "user/register";
//	}

//	@RequestMapping(method = RequestMethod.POST)
//	public String create(@RequestParam String name, @RequestParam String password, @RequestParam String confirm) {
//
//		System.out.println(name + " " + password + " " + confirm);
//
//		try {
//			userService.register(name, password, confirm);
//		} catch (RegisterException e) {
//			e.printStackTrace();
//		}
//
//		return "redirect:/";
//	}

	@RequestMapping(path = "/ajax/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public User json(@PathVariable Integer id) {
		User user = userService.qryById(id);
		return user;
	}

	@RequestMapping(path="/ajax",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public User json(@RequestBody User user) {
		return user;
	}
	
	@RequestMapping(path = "/ajax", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object json(@RequestParam(name="page") Integer pageNo,@RequestParam(name="rows") Integer size){
		List<User> list = userService.qryAll(pageNo, size);
	
		ObjectMapper mapper = new ObjectMapper();
		Map<String,List<User>> map = new HashMap<String,List<User>>();
		map.put("rows", list);
		try {
			String json = mapper.writeValueAsString(map);
			return json;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * @RequestMapping(value = "/{id}/icon", method = RequestMethod.POST) public
	 * String uploadIcon(
	 * 
	 * @PathVariable("id") Integer id,
	 * 
	 * @RequestParam("icon") MultipartFile icon, Model model) throws IOException
	 * {
	 * 
	 * model.addAttribute("icon", icon.getBytes());
	 * 
	 * return "success"; }
	 * 
	 * 
	 * @RequestMapping(method = RequestMethod.POST, consumes =
	 * MediaType.APPLICATION_JSON_VALUE, produces =
	 * MediaType.APPLICATION_JSON_VALUE)
	 * 
	 * @ResponseBody public User json(@RequestBody User user) { return user; }
	 * 
	 * @RequestMapping(method = RequestMethod.POST, consumes =
	 * MediaType.APPLICATION_XML_VALUE, produces =
	 * MediaType.APPLICATION_XML_VALUE)
	 * 
	 * @ResponseBody public User xml(@RequestBody User user) { return user; }
	 * 
	 * 
	 * @RequestMapping("/exception") public String exception() { throw new
	 * IllegalArgumentException(); }
	 * 
	 * 
	 * @RequestMapping(value = "/async1", produces =
	 * MediaType.APPLICATION_JSON_VALUE)
	 * 
	 * @ResponseBody public Callable<User> async1(final User user) { return new
	 * Callable<User>() { public User call() throws Exception { try {
	 * Thread.sleep(1000L); } catch (InterruptedException e) { } return user; }
	 * }; }
	 * 
	 * @RequestMapping(value = "/async2", produces =
	 * MediaType.APPLICATION_JSON_VALUE)
	 * 
	 * @ResponseBody public DeferredResult<User> async2(final User user) { final
	 * DeferredResult<User> result = new DeferredResult<User>(); new Thread() {
	 * 
	 * @Override public void run() { try { Thread.sleep(1000L); } catch
	 * (InterruptedException e) { } result.setResult(user); } }.start(); return
	 * result; }
	 * 
	 * 
	 * @ExceptionHandler
	 * 
	 * @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) public String
	 * exceptionHandler(IllegalArgumentException e) { return "error"; }
	 */
}
