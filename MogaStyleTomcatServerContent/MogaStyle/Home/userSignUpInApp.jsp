<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%
	request.setCharacterEncoding("utf-8");
  String userId = request.getParameter("userId");
  String userPw = request.getParameter("userPw");
  String userPhone = request.getParameter("userPhone");
  String userCheck = request.getParameter("userCheck");
  String userType = request.getParameter("joinType");

//------
	String url_mysql = "jdbc:mysql://localhost/stylediary?serverTimezone=Asia/Seoul&characterEncoding=utf8&useSSL=false";
	String id_mysql = "root";
	String pw_mysql = "qwer1234";

	int result = 0; // 입력 확인


	PreparedStatement ps = null;
  PreparedStatement ps2 = null;
  ResultSet resultSet = null;
	try{
	    Class.forName("com.mysql.jdbc.Driver");
	    Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
		  Statement stmt_mysql = conn_mysql.createStatement();



      String queryUserCheck = "select id from user where id = ?";
      ps = conn_mysql.prepareStatement(queryUserCheck);
      ps.setString(1,userId);
      resultSet = ps.executeQuery();
      if(resultSet.next()){
        result = 2;
      }else{
        String A = "insert into user(id, pw, userPhone, joinType , userCheck";
        String B = ") values (?,?,?,?,?)";
        ps2 = conn_mysql.prepareStatement(A+B);
        ps2.setString(1,userId);
        ps2.setString(2,userPw);
        ps2.setString(3,userPhone);
        ps2.setString(4,userType);
        ps2.setString(5,userCheck);
        result = ps2.executeUpdate();

}

%>
		{
			"result" : "<%=result%>"
		}

<%
	    conn_mysql.close();
	}
	catch (Exception e){
%>
		{
			"result" : "<%=result%>"
		}
<%
	    e.printStackTrace();
      out.println(e);
	}

%>
