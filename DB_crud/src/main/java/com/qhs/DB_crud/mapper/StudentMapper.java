package com.qhs.DB_crud.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.qhs.DB_crud.bean.Student;

public interface StudentMapper {
	@Select("select * from signup")
	public List<Student> list();  
	@Select("select * from signup where name = #{name}")
	public Student get(String name); 
	
}	

