<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%
	request.setCharacterEncoding("utf-8");
  String userId = request.getParameter("userId");

//------
	String url_mysql = "jdbc:mysql://localhost/stylediary?serverTimezone=Asia/Seoul&characterEncoding=utf8&useSSL=false";
	String id_mysql = "root";
	String pw_mysql = "qwer1234";

	int result = 0; // 입력 확인


	PreparedStatement ps = null;

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
        
        result = 1;

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
