package com.qhs.rest.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
@Controller
public class StudentController {
	@RequestMapping(value="/student", method=RequestMethod,POST)
	//δ��ɣ������Σ���д�����õ�ҳ���Լ�Mybatis��䡣
	 public ModelAndView addStudent() throws Exception {
	 ModelAndView mav = new ModelAndView("index.jsp");
    mav.addObject("msg","success");
    return mav;
}
