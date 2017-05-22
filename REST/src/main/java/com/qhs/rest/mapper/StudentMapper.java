package com.qhs.rest.mapper;
import java.util.List;
import org.apache.ibatis.annotations.*;
import com.qhs.rest.bean.Student;
public interface  StudentMapper {
   @Select("select * from signup limit #{start},#{count}")
   public List<Student> list(@Param("start") int start, @Param("count")int count);  
   @Select("select * from signup where id= #{id}")
   public Student get(int id);
   @Select("select * from signup where id= #{name}")
   public Student getall(String name);
   @Insert("INSERT INTO signup VALUES "
           + "(UNIX_TIMESTAMP(now())*1000,UNIX_TIMESTAMP(now())*1000,null,#{name},#{qq},#{major},#{start_date},"
           + "#{school},#{onlineclass},#{onlineno},#{diarylink},"
           + "#{aim},#{recommender},#{censor},#{wherefrom})")
   public int add(Student student);
   @Delete("delete * from signup where id= #{id}")
   public boolean delete(int id);
}