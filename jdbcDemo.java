package jdbc01;

import javax.xml.transform.Result;
import java.sql.*;

public class jdbcDemo {
    public static  void main(String[] args)
    {
        createtable();
        insert(80, "小丽", "lili",16,"女","13188887777");
        insert(1, "小鱼", "qwer",15,"女","4653667");
        insert(20, "张三", "asdfg",20,"男","4658888");
        insert(40, "王五", "123456",26,"男","17759865872");
        insert(50, "小明", "jklove",16,"男","13159877777");
        select();
        update(1,"every");//从"qwer"变为"every"
        update(10,"none");//id不存在
        select();
        delete(30);//id不存在
        delete(50);
        select();
    }

    public static void createtable()
    {
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        String sql;

        try {
            con=jdbcUtils.getConnection();

            sql="create table db_user(id int,username varchar(20),password varchar(20),age int,gender varchar(8),tel varchar(13) )";
            pstmt=con.prepareStatement(sql);
            int result=pstmt.executeUpdate();
            if(result!=-1){
                System.out.println("建表成功");
            }else{
                System.out.println("建表失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            jdbcUtils.close(rs,pstmt,con);
        }
    }

    public static void select()
    {
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        String sql;

        try {
            con=jdbcUtils.getConnection();

            sql="select * from db_user";
            pstmt=con.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while(rs.next())
            {
                System.out.println(rs.getInt(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getInt(4)+","+rs.getString(5)+","+rs.getString(6));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            jdbcUtils.close(rs,pstmt,con);
        }
    }

    public static void insert(int id,String name,String password,int age,String gender,String tel )
    {
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        String sql;

        try {
            con=jdbcUtils.getConnection();

            sql="insert into db_user(id,username,password,age,gender,tel) values(?,?,?,?,?,?)";
            pstmt=con.prepareStatement(sql);
            pstmt.setInt(1,id);
            pstmt.setString(2,name);
            pstmt.setString(3,password);
            pstmt.setInt(4,age);
            pstmt.setString(5,gender);
            pstmt.setString(6,tel);
            int result=pstmt.executeUpdate();
            if(result>0){
                System.out.println("插入成功");
            }else{
                System.out.println("插入失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            jdbcUtils.close(rs,pstmt,con);
        }
    }

    public static void update(int id,String password)
    {
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        String sql;

        try {
            con=jdbcUtils.getConnection();

            sql="update db_user set password = ? where id = ?";
            pstmt=con.prepareStatement(sql);
            pstmt.setInt(2,id);
            pstmt.setString(1,password);
            int result=pstmt.executeUpdate();
            if(result>0){
                System.out.println("修改成功");
            }else{
                System.out.println("修改失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            jdbcUtils.close(rs,pstmt,con);
        }
    }

    public static void delete(int id)
    {
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        String sql;

        try {
            con=jdbcUtils.getConnection();

            sql="delete from db_user where id = ?";
            pstmt=con.prepareStatement(sql);
            pstmt.setInt(1,id);
            int result=pstmt.executeUpdate();
            if(result>0){
                System.out.println("删除成功");
            }else{
                System.out.println("删除失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            jdbcUtils.close(rs,pstmt,con);
        }
    }
} 
