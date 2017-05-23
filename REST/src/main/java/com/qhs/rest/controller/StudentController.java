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
	
		// 分页查询所有用户，加入默认页数参数为1
		// 2017年5月22日21:37:13 未完成根据专业查询用户
		//2017年5月23日10:36:20完成根据专业查询，批量查询方法完成
	@RequestMapping(value = "/students", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView getStudentsByMajor(@RequestParam(value = "pn", required = false, defaultValue = "1") Integer pn,
			@RequestParam(value = "major", required = false, defaultValue = "all") String major) throws IOException {
		// 指定mapper
		StudentMapper mapper = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"))
				.openSession().getMapper(StudentMapper.class);
		List<Student> list = new ArrayList<Student>();
		// 不带参数，pn=0，参数为0,10
		// 带2，参数为10，20
		//由于major参数并不是数据库里实际存储的字段。。所以使用switch case块来匹配
		switch(major){
		case "all":
			list = mapper.list((pn - 1) * 10, pn * 10);
			break;
		case "java":
			list = mapper.listByMajor("JAVA工程师",(pn - 1) * 10, pn * 10);
			break;
		case "frontend":
			list = mapper.listByMajor("前端工程师",(pn - 1) * 10, pn * 10);
			break;
		case "op":
			list = mapper.listByMajor("运维工程师",(pn - 1) * 10, pn * 10);
			break;
		case "ios":
			list = mapper.listByMajor("iOS工程师",(pn - 1) * 10, pn * 10);
			break;
		case "android":
			list = mapper.listByMajor("Android工程师",(pn - 1) * 10, pn * 10);
			break;
		case "ui":
			list = mapper.listByMajor("UI设计师",(pn - 1) * 10, pn * 10);
			break;
		case "pm":
			list = mapper.listByMajor("产品经理",(pn - 1) * 10, pn * 10);
			break;
		}

		// 传回list
		//服务端跳转到students.jsp，在jsp中使用EL表达式显示查询到的Student对象
		ModelAndView mav = new ModelAndView("students");
		JSONArray json = new JSONArray();
		json.put(list);
		mav.addObject("msg", json);
		return mav;
	}
	//先搞定一次单个添加，再写一个批量添加
	@RequestMapping(value = "/students", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ModelAndView addStudent(@RequestBody Student student) throws IOException{
		// 指定mapper
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
	
	//在@RequestMapping中添加produces来进行编码转化
	@RequestMapping(value = "/student/{name}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public ModelAndView getStudentsByName(@PathVariable String name,@RequestParam(value = "pn", required = false, defaultValue = "1") Integer pn) throws IOException{
		//一句话Mapper
		//疑问：每个方法里都要写一个Mapper，看起来复用性比较低，后期是不是可以用Spring插进来。。
		//看其他人的解决方案里写了一个单独的Service类来处理。。
		StudentMapper mapper = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"))
				.openSession().getMapper(StudentMapper.class);
		List<Student> list = new ArrayList<Student>();
		//使用PathVariable注解将RequestMapping里#{name}传入方法体内，并且作为参数传入mapper，取回list
		//System.out.println(name);
		list = mapper.listByName(name,(pn - 1) * 10, pn * 10);
		//传回结果list
		ModelAndView mav = new ModelAndView("student");
		JSONArray json = new JSONArray();
		json.put(list);
		mav.addObject("msg", json);
		return mav;
	}
	
	
}