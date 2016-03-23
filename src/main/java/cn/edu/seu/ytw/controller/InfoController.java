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
import cn.edu.seu.ytw.bean.Order;
import cn.edu.seu.ytw.service.InfoService;

@Controller
@RequestMapping("/info")
public class InfoController {

	private InfoService infoService;

	@Autowired
	public void setInfoService(InfoService infoService) {
		this.infoService = infoService;
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public String create(@RequestParam String sender,@RequestParam String senderphone,@RequestParam String idnum,@RequestParam String Select4,@RequestParam String Select5,@RequestParam String Select6,@RequestParam String recipient,@RequestParam String recipientphone,@RequestParam String Select1,@RequestParam String Select2,@RequestParam String Select3,@RequestParam String addr1,@RequestParam String addr2) {

		System.out.println( sender +" "+ senderphone +" "+ idnum +" "+ Select4 +" "+ Select5 +" "+ Select6 +" "+ recipient +" "+ recipientphone +" "+ Select1 +" "+ Select2 +" "+ Select3 );
		String sAddr=Select4+' '+Select5+' '+Select6+' '+addr1;
		String rAddr=Select1+' '+Select2+' '+Select3+' '+addr2;
		infoService.createOrder(sender,senderphone,idnum,sAddr,recipient,recipientphone,rAddr);
		

		return "redirect:/";
	}
}