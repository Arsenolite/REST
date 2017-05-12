package com.qhs.rest.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
@Controller
public class IndexController {
   @RequestMapping("/index")
   public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
       ModelAndView mav = new ModelAndView("index.jsp");
       mav.addObject("msg","HelloSpringMVC");
       return mav;
   }
}