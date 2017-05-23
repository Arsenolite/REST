package com.qhs.rest.mapper;

import java.util.List;
import org.apache.ibatis.annotations.*;
import com.qhs.rest.bean.Student;

public interface StudentMapper {
	// ��ҳ��ѯ
	@Select("select * from signup limit #{start},#{count}")
	public List<Student> list(@Param("start") int start, @Param("count") int count);

	// ����רҵ��ҳ��ѯ
	@Select("select * from signup where major= #{major} limit #{start},#{count}")
	public List<Student> listByMajor(@Param("major") String major, @Param("start") int start,
			@Param("count") int count);

	// ����������ѯ
	@Select("select * from signup where name= #{name} limit #{start},#{count}")
	public List<Student> listByName(@Param("name") String name, @Param("start") int start, @Param("count") int count);

	// ����ID��ȷ��ѯ
	@Select("select * from signup where id= #{id}")
	public Student get(int id);

	// ���
	@Insert("INSERT INTO signup VALUES "
			+ "(UNIX_TIMESTAMP(now())*1000,UNIX_TIMESTAMP(now())*1000,null,#{student.name},#{student.qq},#{student.major},#{student.start_date},"
			+ "#{student.school},#{student.onlineclass},#{student.onlineno},#{student.diarylink},"
			+ "#{student.aim},#{student.recommender},#{student.censor},#{student.wherefrom})")
	public int add(@Param("student") Student student);

	// ɾ��
	@Delete("delete * from signup where id = #{id}")
	public boolean delete(int id);

	// �޸�
	@Update("<script> " + "update signup" + "update_at = (UNIX_TIMESTAMP(now())*1000" + "<set>"
			+ "<if test=\"name != null\">name=#{name},</if>" + "<if test=\"qq != null\">price=#{qq},</if>"
			+ "<if test=\"major != null\">price=#{major},</if>"
			+ "<if test=\"start_date != null\">price=#{start_date},</if>"
			+ "<if test=\"school != null\">price=#{school},</if>"
			+ "<if test=\"onlineclass != null\">price=#{onlineclass},</if>"
			+ "<if test=\"onlineno != null\">price=#{onlineno},</if>"
			+ "<if test=\"diarylink != null\">price=#{diarylink},</if>" + "<if test=\"aim != null\">price=#{aim},</if>"
			+ "<if test=\"recommender != null\">price=#{recommender},</if>"
			+ "<if test=\"censor != null\">price=#{censor},</if>"
			+ "<if test=\"wherefrom != null\">price=#{wherefrom},</if>" + "</set>" + "where id = #{id}" + "</script>")
	public boolean update(Student student);
}