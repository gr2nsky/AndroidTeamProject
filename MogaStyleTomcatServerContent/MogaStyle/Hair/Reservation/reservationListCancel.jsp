<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%
	request.setCharacterEncoding("utf-8");
  int resNo = Integer.parseInt(request.getParameter("resNo"));

  String url_mysql = "jdbc:mysql://localhost/mogastyle?serverTimezone=Asia/Seoul&characterEncoding=utf8&useSSL=false";
	String id_mysql = "root";
	String pw_mysql = "qwer1234";


	int result = 0; // 입력 확인

	PreparedStatement ps = null;
	try{
      Class.forName("com.mysql.jdbc.Driver");
      Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);

      String query = "UPDATE reservation SET cancelDate = now() WHERE no = ?";

      ps = conn_mysql.prepareStatement(query);
      ps.setInt(1, resNo);

		  result = ps.executeUpdate();

      out.print(1);
	    conn_mysql.close();
	}
	catch (Exception e){
    out.print(0);
	  e.printStackTrace();
	}

%>
