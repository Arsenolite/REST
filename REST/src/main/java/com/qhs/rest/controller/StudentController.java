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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.qhs.rest.bean.Student;
import com.qhs.rest.mapper.StudentMapper;
import com.qhs.rest.service.StudentService;

@RestController
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	// 分页查询所有用户，加入默认页数参数为1
	//2017年5月22日21:37:13 未完成根据专业查询用户
	//2017年5月23日10:36:20完成根据专业查询，批量查询方法完成
	//2017年5月24日08:37:25分离出Service类
	@RequestMapping(value = "/students", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ModelAndView getStudentsByMajor(@RequestParam(value = "pn", required = false, defaultValue = "1") Integer pn,
			@RequestParam(value = "major", required = false, defaultValue = "all") String major) throws IOException {
		//服务端跳转到students.jsp，在jsp中使用EL表达式显示查询到的Student对象
		ModelAndView mav = new ModelAndView("result");
		JSONArray json = new JSONArray();
		//业务逻辑交给Service做
		json.put(studentService.getStudentsByMajor(pn, major));
		mav.addObject("msg", json);
		return mav;
	}
	
	//根据姓名获取学员
	//在@RequestMapping中添加produces来进行编码转化
	@RequestMapping(value = "/student/{name}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ModelAndView getStudentsByName(@PathVariable String name,@RequestParam(value = "pn", required = false, defaultValue = "1") Integer pn) throws IOException{
		//传回结果list
		ModelAndView mav = new ModelAndView("result");
		JSONArray json = new JSONArray();
		json.put(studentService.getStudentsByName(name, pn));
		mav.addObject("msg", json);
		return mav;
	}
	//根据id精确获取
	@RequestMapping(value = "/student", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ModelAndView getStudentsById(@RequestParam(value = "id", required = true, defaultValue = "36") Integer id) throws IOException{
		//传回结果student
		ModelAndView mav = new ModelAndView("result");
		//student装入json对象
		JSONObject jo = new JSONObject(studentService.getStudentById(id));
		mav.addObject("msg", jo);
		return mav;
	}
	//添加新学员
		@RequestMapping(value = "/students", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
		@ResponseBody
		public ModelAndView addStudent(@RequestBody Student student) throws IOException{
					ModelAndView mav = new ModelAndView("result");
					JSONObject jo = new JSONObject();
					int flag = studentService.addStudent(student);
					
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
	//删除学员
	@RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ModelAndView deleteStudent(@PathVariable Integer id) throws IOException{
		ModelAndView mav = new ModelAndView("result");
		//2017年5月24日10:01:25居然忘记写service执行的语句了。。卧槽
		studentService.deleteStudent(id);
		JSONObject jo = new JSONObject();
		jo.put("Success", "true");
		jo.put("Deleted", id);
		mav.addObject("msg", jo);
		return mav;
		
	}
	//修改学员
	@RequestMapping(value = "/student/{id}", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ModelAndView updateStudent(@PathVariable Integer id,@RequestBody Student student) throws IOException{
		ModelAndView mav = new ModelAndView("result");
		JSONObject jo = new JSONObject();
		student.setId(id);
		int flag = studentService.updateStudent(student);
		if(flag>0){
			jo.put("Success", "true");
			jo.put("Updated", flag);
		}else{
			jo.put("Success", "false");
			jo.put("Updated", flag);
		}
		mav.addObject("msg", jo);
		return mav;
	}
}