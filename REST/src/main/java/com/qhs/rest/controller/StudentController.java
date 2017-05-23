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
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.qhs.rest.bean.Student;
import com.qhs.rest.mapper.StudentMapper;

@Controller
public class StudentController {
	
		// ��ҳ��ѯ�����û�������Ĭ��ҳ������Ϊ1
		// 2017��5��22��21:37:13 δ��ɸ���רҵ��ѯ�û�
		//2017��5��23��10:36:20��ɸ���רҵ��ѯ��������ѯ�������
	@RequestMapping(value = "/students", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView getStudentsByMajor(@RequestParam(value = "pn", required = false, defaultValue = "1") Integer pn,
			@RequestParam(value = "major", required = false, defaultValue = "all") String major) throws IOException {
		// ָ��mapper
		StudentMapper mapper = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"))
				.openSession().getMapper(StudentMapper.class);
		List<Student> list = new ArrayList<Student>();
		// ����������pn=0������Ϊ0,10
		// ��2������Ϊ10��20
		//����major�������������ݿ���ʵ�ʴ洢���ֶΡ�������ʹ��switch case����ƥ��
		switch(major){
		case "all":
			list = mapper.list((pn - 1) * 10, pn * 10);
			break;
		case "java":
			list = mapper.listByMajor("JAVA����ʦ",(pn - 1) * 10, pn * 10);
			break;
		case "frontend":
			list = mapper.listByMajor("ǰ�˹���ʦ",(pn - 1) * 10, pn * 10);
			break;
		case "op":
			list = mapper.listByMajor("��ά����ʦ",(pn - 1) * 10, pn * 10);
			break;
		case "ios":
			list = mapper.listByMajor("iOS����ʦ",(pn - 1) * 10, pn * 10);
			break;
		case "android":
			list = mapper.listByMajor("Android����ʦ",(pn - 1) * 10, pn * 10);
			break;
		case "ui":
			list = mapper.listByMajor("UI���ʦ",(pn - 1) * 10, pn * 10);
			break;
		case "pm":
			list = mapper.listByMajor("��Ʒ����",(pn - 1) * 10, pn * 10);
			break;
		}

		// ����list
		//�������ת��students.jsp����jsp��ʹ��EL���ʽ��ʾ��ѯ����Student����
		ModelAndView mav = new ModelAndView("students");
		JSONArray json = new JSONArray();
		json.put(list);
		mav.addObject("msg", json);
		return mav;
	}
	//�ȸ㶨һ�ε�����ӣ���дһ���������
	@RequestMapping(value = "/students", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ModelAndView addStudent(@RequestBody Student student) throws IOException{
		// ָ��mapper
				StudentMapper mapper = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"))
						.openSession().getMapper(StudentMapper.class);
				ModelAndView mav = new ModelAndView("students");
				JSONObject jo = new JSONObject();
				//jo.put("Student", student.getAim());
				int flag = mapper.add(student);
				
				if(flag>0){
					jo.put("Success", "true");
					jo.put("Added", flag);
				}else{
					jo.put("Success", "false");
					jo.put("Added", flag);
				}
				mav.addObject("msg", jo);
				return mav;
	}
	
	//��@RequestMapping�����produces�����б���ת��
	@RequestMapping(value = "/student/{name}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public ModelAndView getStudentsByName(@PathVariable String name,@RequestParam(value = "pn", required = false, defaultValue = "1") Integer pn) throws IOException{
		//һ�仰Mapper
		//���ʣ�ÿ�������ﶼҪдһ��Mapper�������������ԱȽϵͣ������ǲ��ǿ�����Spring���������
		//�������˵Ľ��������д��һ��������Service����������
		StudentMapper mapper = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"))
				.openSession().getMapper(StudentMapper.class);
		List<Student> list = new ArrayList<Student>();
		//ʹ��PathVariableע�⽫RequestMapping��#{name}���뷽�����ڣ�������Ϊ��������mapper��ȡ��list
		//System.out.println(name);
		list = mapper.listByName(name,(pn - 1) * 10, pn * 10);
		//���ؽ��list
		ModelAndView mav = new ModelAndView("student");
		JSONArray json = new JSONArray();
		json.put(list);
		mav.addObject("msg", json);
		return mav;
	}
	
	
}