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
	
	// ��ҳ��ѯ�����û�������Ĭ��ҳ������Ϊ1
	//2017��5��22��21:37:13 δ��ɸ���רҵ��ѯ�û�
	//2017��5��23��10:36:20��ɸ���רҵ��ѯ��������ѯ�������
	//2017��5��24��08:37:25�����Service��
	@RequestMapping(value = "/students", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ModelAndView getStudentsByMajor(@RequestParam(value = "pn", required = false, defaultValue = "1") Integer pn,
			@RequestParam(value = "major", required = false, defaultValue = "all") String major) throws IOException {
		//�������ת��students.jsp����jsp��ʹ��EL���ʽ��ʾ��ѯ����Student����
		ModelAndView mav = new ModelAndView("result");
		JSONArray json = new JSONArray();
		//ҵ���߼�����Service��
		json.put(studentService.getStudentsByMajor(pn, major));
		mav.addObject("msg", json);
		return mav;
	}
	
	//����������ȡѧԱ
	//��@RequestMapping�����produces�����б���ת��
	@RequestMapping(value = "/student/{name}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ModelAndView getStudentsByName(@PathVariable String name,@RequestParam(value = "pn", required = false, defaultValue = "1") Integer pn) throws IOException{
		//���ؽ��list
		ModelAndView mav = new ModelAndView("result");
		JSONArray json = new JSONArray();
		json.put(studentService.getStudentsByName(name, pn));
		mav.addObject("msg", json);
		return mav;
	}
	//����id��ȷ��ȡ
	@RequestMapping(value = "/student", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ModelAndView getStudentsById(@RequestParam(value = "id", required = true, defaultValue = "36") Integer id) throws IOException{
		//���ؽ��student
		ModelAndView mav = new ModelAndView("result");
		//studentװ��json����
		JSONObject jo = new JSONObject(studentService.getStudentById(id));
		mav.addObject("msg", jo);
		return mav;
	}
	//�����ѧԱ
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
	//ɾ��ѧԱ
	@RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ModelAndView deleteStudent(@PathVariable Integer id) throws IOException{
		ModelAndView mav = new ModelAndView("result");
		//2017��5��24��10:01:25��Ȼ����дserviceִ�е�����ˡ����Բ�
		studentService.deleteStudent(id);
		JSONObject jo = new JSONObject();
		jo.put("Success", "true");
		jo.put("Deleted", id);
		mav.addObject("msg", jo);
		return mav;
		
	}
	//�޸�ѧԱ
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