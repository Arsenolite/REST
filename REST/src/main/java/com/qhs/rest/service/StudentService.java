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
		// ����������pn=0������Ϊ0,10
		// ��2������Ϊ10��20
		// ����major�������������ݿ���ʵ�ʴ洢���ֶΡ�������ʹ��switch case����ƥ��
		List<Student> list = new ArrayList<Student>();
		switch (major) {
		case "all":
			list = mapper.list((pn - 1) * 10, pn * 10);
			break;
		case "java":
			list = mapper.listByMajor("JAVA����ʦ", (pn - 1) * 10, pn * 10);
			break;
		case "frontend":
			list = mapper.listByMajor("ǰ�˹���ʦ", (pn - 1) * 10, pn * 10);
			break;
		case "op":
			list = mapper.listByMajor("��ά����ʦ", (pn - 1) * 10, pn * 10);
			break;
		case "ios":
			list = mapper.listByMajor("iOS����ʦ", (pn - 1) * 10, pn * 10);
			break;
		case "android":
			list = mapper.listByMajor("Android����ʦ", (pn - 1) * 10, pn * 10);
			break;
		case "ui":
			list = mapper.listByMajor("UI���ʦ", (pn - 1) * 10, pn * 10);
			break;
		case "pm":
			list = mapper.listByMajor("��Ʒ����", (pn - 1) * 10, pn * 10);
			break;
		}
		// �ύ���񣬹رջỰ
		session.commit();
		session.close();
		return list;

	}

	public List<Student> getStudentsByName(String name, Integer pn) throws IOException {
		SqlSession session = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"))
				.openSession();
		StudentMapper mapper = session.getMapper(StudentMapper.class);
		List<Student> list = new ArrayList<Student>();
		// ʹ��PathVariableע�⽫RequestMapping��#{name}���뷽�����ڣ�������Ϊ��������mapper��ȡ��list
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
