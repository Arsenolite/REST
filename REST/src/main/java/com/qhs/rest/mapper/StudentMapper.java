package com.qhs.rest.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Select;
import com.qhs.rest.bean.Student;
public interface StudentMapper {
   @Select("select * from signup")
   public List<Student> list();  
}