package com.qhs.DB_crud.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.qhs.DB_crud.bean.Student;

public class studentDao implements DAO{
	
	public studentDao() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Connection getConnection() throws SQLException{
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/学员报名?characterEncoding=UTF-8","root","admin");
	}
	public int add(Student student) {
		//增
		String sql = "insert into signup values(UNIX_TIMESTAMP(now())*1000,UNIX_TIMESTAMP(now())*1000,null,?,?,?,?,?,?,?,?,?,?,?,?)";
		try(
		//try-resource-catch
				Connection c = getConnection();
				PreparedStatement ps = c.prepareStatement(sql);
			){
		//添加参数
			ps.setString(1, student.getName());
			ps.setString(2, student.getQq());
			ps.setString(3, student.getMajor());
			ps.setString(4, student.getStart_date());
			ps.setString(5, student.getSchool());
			ps.setString(6, student.getOnlineclass());
			ps.setString(7, student.getOnlineno());
			ps.setString(8, student.getDiarylink());
			ps.setString(9, student.getAim());
			ps.setString(10, student.getRecommender());
			ps.setString(11, student.getCensor());
			ps.setString(12, student.getWherefrom());
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()){
				//此时rs如果不为空，则只含有自增id
				int i = rs.getInt(1);
				return i;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
	}

	public boolean update(Student student) {
		// TODO Auto-generated method stub
		//改-完成
		boolean flag = false;
		//根据id去查找记录，并且修改
		String sql = "update signup set update_at = UNIX_TIMESTAMP(now())*1000, ?,?,?,?,?,?,?,?,?,?,?,? where Id = ?)";
		try(
		//try-resource-catch
				Connection c = getConnection();
				PreparedStatement ps = c.prepareStatement(sql);
			){
		//添加参数
			ps.setString(1, student.getName());
			ps.setString(2, student.getQq());
			ps.setString(3, student.getMajor());
			ps.setString(4, student.getStart_date());
			ps.setString(5, student.getSchool());
			ps.setString(6, student.getOnlineclass());
			ps.setString(7, student.getOnlineno());
			ps.setString(8, student.getDiarylink());
			ps.setString(9, student.getAim());
			ps.setString(10, student.getRecommender());
			ps.setString(11, student.getCensor());
			ps.setString(12, student.getWherefrom());
			ps.setInt(13, student.getId());
			//直接把执行结果赋值到定义的flag中
			//2017年5月2日20:49:33debug：该方法返回值不是该语句是否执行成功！！
			
			int i =ps.executeUpdate();
			if(i>0){
				flag = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	public boolean delete(Student student) {
		// TODO Auto-generated method stub
		//删-完成
		boolean flag = false;
		String sql = "delete from signup where id = ?)";
		try(
		//try-resource-catch
				Connection c = getConnection();
				PreparedStatement ps = c.prepareStatement(sql);
			){
			//直接把执行结果赋值到定义的flag中
			ps.setInt(1, student.getId());
			int i =ps.executeUpdate();
			if(i>0){
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	public Student get(Student student) {
		// TODO Auto-generated method stub
		//查
		String sql = "select * from signup where name = ?)";
		try(
		//try-resource-catch
				Connection c = getConnection();
				PreparedStatement ps = c.prepareStatement(sql);
			){
		//添加参数
			ps.setString(1, student.getName());
			ResultSet rs = ps.executeQuery();
			
			
			  
            if (rs.next()) {
            	student = new Student();
//            	Timestamp createtime = rs.getTimestamp(1);
//                String createtime2 = createtime.toString();
//                Timestamp updatetime = rs.getTimestamp(2);
//                String updatetime2 = createtime.toString();
                
                String name = rs.getString(4);
                String qq = rs.getString(5);
                String major = rs.getString(6);

                student.setName(name);
                student.setQq(qq);
                student.setMajor(major);
                }
            
            
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return student;
	}

}
