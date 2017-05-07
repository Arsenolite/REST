package com.qhs.DB_crud;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.qhs.DB_crud.DAO.studentDao;
import com.qhs.DB_crud.bean.Student;
import com.qhs.DB_crud.mapper.StudentMapper;

public class Main {
	// public boolean add(){
	// studentDao sd = new studentDao();
	// Student stu = new Student();
	// stu.setAim("aim");
	// stu.setCensor("cen");
	// stu.setDiarylink("diarylink");
	// stu.setMajor("major");
	// stu.setName("name");
	// stu.setOnlineclass("onlineclass");
	// stu.setOnlineno("onlineno");
	// stu.setQq("qq");
	// stu.setRecommender("reco");
	// stu.setSchool("school");
	// stu.setStart_date("start_Date");
	// stu.setWherefrom("wherefrom");
	// boolean flag = sd.add(stu);
	// return flag;
	// }
	public static void main(String[] args) throws IOException {
		//指定配置文件
		String resource = "mybatis-config.xml";
		//建立一个流读取这个文件
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//建立会话工厂，并且从中取出一个SqlSession
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		//指定一个mapper
		StudentMapper mapper = session.getMapper(StudentMapper.class);
		//listAll(session);
		//getStudent(session);
		addStudent(session);
		//getStudent(mapper);
		
		session.commit();
		session.close();
	}
	public void query() throws IOException {
		
	}
//用xml方式crud
//	private static void listAll(SqlSession session) {
//		List<Student> ss = session.selectList("listStudent");
//		for (Student s : ss) {
//			System.out.println(s.getName());
//		}
//	}
	private static void getStudent(SqlSession session){
		Student s = session.selectOne("selectStudent","李博文");
		System.out.println(s.getDiarylink());
	}
	private static void addStudent(SqlSession session){
		 Student stu = new Student();
		 stu.setAim("1");
		 stu.setCensor("2");
		 stu.setDiarylink("3");
		 stu.setMajor("4");
		 stu.setName("5");
		 stu.setOnlineclass("6");
		 stu.setOnlineno("7");
		 stu.setQq("8");
		 stu.setRecommender("9");
		 stu.setSchool("10");
		 stu.setStart_date("11");
		 stu.setWherefrom("12");
		 session.insert("insertStudent",stu);
		
	}
//	private static void getStudent(StudentMapper mapper){
//		Student s = mapper.get("李博文");
//		System.out.println(s.getDiarylink());
//	}
	

}
