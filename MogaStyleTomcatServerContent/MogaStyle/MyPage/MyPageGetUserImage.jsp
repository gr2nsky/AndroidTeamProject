<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%
	request.setCharacterEncoding("utf-8");
  String userNo = request.getParameter("userNo");


//------
	String url_mysql = "jdbc:mysql://localhost/mogastyle?serverTimezone=Asia/Seoul&characterEncoding=utf8&useSSL=false";
	String id_mysql = "root";
	String pw_mysql = "qwer1234";

	String result = ""; // 입력 확인


	PreparedStatement ps = null;

  ResultSet resultSet = null;
	try{
	    Class.forName("com.mysql.jdbc.Driver");
	    Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
		  Statement stmt_mysql = conn_mysql.createStatement();



      String queryUserSignIn = "select image from user where no = "+userNo;
      ps = conn_mysql.prepareStatement(queryUserSignIn);


      resultSet = ps.executeQuery();

      if(resultSet.next()){

        result = resultSet.getString(1);

      }else{

        result = "userDefault.jpg";

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
