package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import com.javaex.dao.GuestDao;
import com.javaex.vo.GuestVo;


@Controller
@RequestMapping(value = "/guest")
public class GuestController {
	
	@RequestMapping(value = "/list",method = {RequestMethod.GET,RequestMethod.POST})
	public String List(Model model) {
		System.out.println("list");
		GuestDao guestDao =new GuestDao();
		List<GuestVo> guestlist = guestDao.getList();
		model.addAttribute("glist",guestlist);
		return "addList";
	}
	@RequestMapping(value = "/write",method = {RequestMethod.GET,RequestMethod.POST})
	public String write(@ModelAttribute GuestVo guestVo) {
		System.out.println("등록");
		System.out.println(guestVo.toString());
		GuestDao guestDao = new GuestDao();
		guestDao.guestinsert(guestVo);
		
		return "redirect:/guest/list";
	}
	@RequestMapping(value = "/deleteForm",method = {RequestMethod.GET,RequestMethod.POST})
	public String deleteForm() {
		System.out.println("삭제 폼입니다.");
		return "deleteForm";
	}
	@RequestMapping(value = "/delete",method = {RequestMethod.GET,RequestMethod.POST})
	public String delete(@ModelAttribute GuestVo guestVo) {
		System.out.println("삭제입니다");
		GuestDao guestDao = new GuestDao();
		int delete =guestDao.guestdelete(guestVo);
		if(delete==1) {	
			return "redirect:/guest/list";
		}else {
			return"/passerror";
		}
		
		
		
	}
}
