package com.qhs.DB_crud.DAO;

import com.qhs.DB_crud.bean.Student;

public interface DAO {
	//增删改
	public int add(Student student);	
	public boolean update(Student student);	
	public boolean delete(Student student);	
	//查
	public Student get(Student student);	
}
