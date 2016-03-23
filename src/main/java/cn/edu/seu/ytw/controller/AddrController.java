package cn.edu.seu.ytw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.seu.ytw.service.InfoService;

@Controller
@RequestMapping("/Encrypt")
public class AddrController 
{
	private InfoService infoService;

	@Autowired
	public void setInfoService(InfoService infoService) 
	{
		this.infoService = infoService;
	}
	
	@RequestMapping(value="/detail/{id}", method = {RequestMethod.GET})
	public ModelAndView getDetail(@PathVariable(value="id") Integer id)
	{	    
	    ModelAndView modelAndView = new ModelAndView(); 
	    String add =infoService.getAddbyID(id);
	    modelAndView.addObject("id", add);  
	    modelAndView.setViewName("reciAddr");  
	    return modelAndView;
	}
}
