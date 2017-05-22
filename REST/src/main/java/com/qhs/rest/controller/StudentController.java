package com.qhs.rest.controller;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.qhs.rest.bean.Student;
import com.qhs.rest.mapper.StudentMapper;
@Controller
public class StudentController {
   @RequestMapping(value="/students",method=RequestMethod.GET)
   @ResponseBody
   //��ҳ��ѯ�����û�������Ĭ��ҳ������Ϊ1
   //2017��5��22��21:37:13 δ��ɸ���רҵ��ѯ�û�
   public ModelAndView getList(@RequestParam(value="pn",required=false,defaultValue="1") Integer pn) throws IOException{
       //ָ��mapper
       StudentMapper mapper = new SqlSessionFactoryBuilder()
               .build(Resources.getResourceAsStream("mybatis-config.xml")).openSession()
               .getMapper(StudentMapper.class);
       List<Student> list = new ArrayList<Student>();
       //����������pn=0������Ϊ0,10
       //��2������Ϊ10��20
       list = mapper.list((pn-1)*10,pn*10);
       ModelAndView mav = new ModelAndView("students");
       JSONArray json = new JSONArray();
       json.put(list);
       mav.addObject("msg",json);
       return mav;
   }
}