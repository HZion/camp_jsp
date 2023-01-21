package com.example.camp_jsp.userdao;

import com.example.camp_jsp.uservo.UserVO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public static Connection getConnection(){
        Connection con=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://walab.handong.edu:3306/p3_21500772","p3_21500772","ahPh6auFei");
        }catch(Exception e){
            System.out.println(e);
        }
        return con;
    }
    public static int save(UserVO u){
        int status=0;
        try{
            Connection con=getConnection();
            PreparedStatement ps=con.prepareStatement("insert into register(name,password,email,sex,country) values(?,?,?,?,?)");
            ps.setString(1,u.getName());
            ps.setString(2,u.getPassword());
            ps.setString(3,u.getEmail());
            ps.setString(4,u.getSex());
            ps.setString(5,u.getCountry());
            status=ps.executeUpdate();
        }catch(Exception e){System.out.println(e);}
        return status;
    }
    public static int update(UserVO u){
        int status=0;
        try{
            Connection con=getConnection();
            PreparedStatement ps=con.prepareStatement("update register set name=?,password=?,email=?,sex=?,country=? where id=?");
            ps.setString(1,u.getName());
            ps.setString(2,u.getPassword());
            ps.setString(3,u.getEmail());
            ps.setString(4,u.getSex());
            ps.setString(5,u.getCountry());
            ps.setInt(6,u.getId());
            status=ps.executeUpdate();
        }catch(Exception e){System.out.println(e);}
        return status;
    }
    public static int delete(UserVO u){
        int status=0;
        try{
            Connection con=getConnection();
            PreparedStatement ps=con.prepareStatement("delete from register where id=?");
            ps.setInt(1,u.getId());
            status=ps.executeUpdate();
        }catch(Exception e){System.out.println(e);}

        return status;
    }
    public static List<UserVO> getAllRecords(){
        List<UserVO> list=new ArrayList<UserVO>();

        try{
            Connection con=getConnection();
            PreparedStatement ps=con.prepareStatement("select * from register");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                UserVO u=new UserVO();
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("name"));
                u.setPassword(rs.getString("password"));
                u.setEmail(rs.getString("email"));
                u.setSex(rs.getString("sex"));
                u.setCountry(rs.getString("country"));
                list.add(u);
            }
        }catch(Exception e){System.out.println(e);}
        return list;
    }

    public static UserVO getRecordById(int id){
        UserVO u=null;
        try{
            Connection con=getConnection();
            PreparedStatement ps=con.prepareStatement("select * from register where id=?");
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                u=new UserVO();
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("name"));
                u.setPassword(rs.getString("password"));
                u.setEmail(rs.getString("email"));
                u.setSex(rs.getString("sex"));
                u.setCountry(rs.getString("country"));
            }
        }catch(Exception e){System.out.println(e);}
        return u;
    }
}