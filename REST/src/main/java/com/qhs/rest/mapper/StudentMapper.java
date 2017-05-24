package com.qhs.rest.mapper;

import java.util.List;
import org.apache.ibatis.annotations.*;
import com.qhs.rest.bean.Student;

public interface StudentMapper {
	// 分页查询
	@Select("select * from signup limit #{start},#{count}")
	public List<Student> list(@Param("start") int start, @Param("count") int count);

	// 根据专业分页查询
	@Select("select * from signup where major= #{major} limit #{start},#{count}")
	public List<Student> listByMajor(@Param("major") String major, @Param("start") int start,
			@Param("count") int count);

	// 根据姓名查询
	@Select("select * from signup where name= #{name} limit #{start},#{count}")
	public List<Student> listByName(@Param("name") String name, @Param("start") int start, @Param("count") int count);

	// 根据ID精确查询
	@Select("select * from signup where id= #{id}")
	public Student getStudentById(@Param("id") int id);

	// 添加
	@Insert("INSERT INTO `signup` VALUES "
			+ "(UNIX_TIMESTAMP(now())*1000,UNIX_TIMESTAMP(now())*1000,null,#{student.name},#{student.qq},#{student.major},#{student.start_date},"
			+ "#{student.school},#{student.onlineclass},#{student.onlineno},#{student.diarylink},"
			+ "#{student.aim},#{student.recommender},#{student.censor},#{student.wherefrom})")
	public int add(@Param("student") Student student);

	// 删除
	@Delete("delete from signup where id = #{id}")
	public void delete(@Param("id") int id);

	// 修改
	@Update("<script>" + "update `signup`" 
			+ "<set>"
			+ " update_at = UNIX_TIMESTAMP(now())*1000," 
			+ "<if test=\"student.name != null\">name=#{student.name},</if>" 
			+ "<if test=\"student.qq != null\">qq=#{student.qq},</if>"
			+ "<if test=\"student.major != null\">major=#{student.major},</if>"
			+ "<if test=\"student.start_date != null\">start_date=#{student.start_date},</if>"
			+ "<if test=\"student.school != null\">school=#{student.school},</if>"
			+ "<if test=\"student.onlineclass != null\">onlineclass=#{student.onlineclass},</if>"
			+ "<if test=\"student.onlineno != null\">onlineno=#{student.onlineno},</if>"
			+ "<if test=\"student.diarylink != null\">diarylink=#{student.diarylink},</if>" 
			+ "<if test=\"student.aim != null\">aim=#{student.aim},</if>"
			+ "<if test=\"student.recommender != null\">recommender=#{student.recommender},</if>"
			+ "<if test=\"student.censor != null\">censor=#{student.censor},</if>"
			+ "<if test=\"student.wherefrom != null\">wherefrom=#{student.wherefrom}</if>" 
			+ "</set>" 
			+ " where id = #{student.id}" + "</script>")
	public int update(@Param("student") Student student); 
}