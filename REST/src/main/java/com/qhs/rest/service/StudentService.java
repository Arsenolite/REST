package com.qhs.rest.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import com.qhs.rest.bean.Student;
import com.qhs.rest.mapper.StudentMapper;
@Service
public class StudentService {
	public List<Student> getStudentsByMajor(Integer pn, String major) throws IOException {
		SqlSession session = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"))
				.openSession();
		StudentMapper mapper = session.getMapper(StudentMapper.class);
		// 不带参数，pn=0，参数为0,10
		// 带2，参数为10，20
		// 由于major参数并不是数据库里实际存储的字段。。所以使用switch case块来匹配
		List<Student> list = new ArrayList<Student>();
		switch (major) {
		case "all":
			list = mapper.list((pn - 1) * 10, pn * 10);
			break;
		case "java":
			list = mapper.listByMajor("JAVA工程师", (pn - 1) * 10, pn * 10);
			break;
		case "frontend":
			list = mapper.listByMajor("前端工程师", (pn - 1) * 10, pn * 10);
			break;
		case "op":
			list = mapper.listByMajor("运维工程师", (pn - 1) * 10, pn * 10);
			break;
		case "ios":
			list = mapper.listByMajor("iOS工程师", (pn - 1) * 10, pn * 10);
			break;
		case "android":
			list = mapper.listByMajor("Android工程师", (pn - 1) * 10, pn * 10);
			break;
		case "ui":
			list = mapper.listByMajor("UI设计师", (pn - 1) * 10, pn * 10);
			break;
		case "pm":
			list = mapper.listByMajor("产品经理", (pn - 1) * 10, pn * 10);
			break;
		}
		// 提交事务，关闭会话
		session.commit();
		session.close();
		return list;

	}

	public List<Student> getStudentsByName(String name, Integer pn) throws IOException {
		SqlSession session = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"))
				.openSession();
		StudentMapper mapper = session.getMapper(StudentMapper.class);
		List<Student> list = new ArrayList<Student>();
		// 使用PathVariable注解将RequestMapping里#{name}传入方法体内，并且作为参数传入mapper，取回list
		list = mapper.listByName(name, (pn - 1) * 10, pn * 10);
		session.commit();
		session.close();
		return list;
	}
	public Student getStudentById(int id) throws IOException{
		SqlSession session = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"))
				.openSession();
		StudentMapper mapper = session.getMapper(StudentMapper.class);
		Student student = mapper.getStudentById(id);
		session.commit();
		session.close();
		return student;
	}
	public int addStudent(Student student) throws IOException {
		SqlSession session = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"))
				.openSession();
		StudentMapper mapper = session.getMapper(StudentMapper.class);

		int flag = mapper.add(student);
		session.commit();
		session.close();
		return flag;
	}

	public void deleteStudent(Integer id) throws IOException {
		SqlSession session = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"))
				.openSession();
		StudentMapper mapper = session.getMapper(StudentMapper.class);
		mapper.delete(id);
		session.commit();
		session.close();
	}
	public int updateStudent(Student student) throws IOException{
		SqlSession session = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"))
				.openSession();
		StudentMapper mapper = session.getMapper(StudentMapper.class);
		int flag = mapper.update(student);
		session.commit();
		session.close();
		return flag;
	}
	
}
