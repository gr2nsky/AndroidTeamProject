<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%
	request.setCharacterEncoding("utf-8");
  String userName = request.getParameter("userName");
  String userId = request.getParameter("userId");
  String userImage = request.getParameter("userImage");


//------
	String url_mysql = "jdbc:mysql://localhost/mogastyle?serverTimezone=Asia/Seoul&characterEncoding=utf8&useSSL=false";
	String id_mysql = "root";
	String pw_mysql = "qwer1234";

	int result = 0; // 입력 확인


  PreparedStatement ps2 = null;

	try{
	    Class.forName("com.mysql.jdbc.Driver");
	    Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
		  Statement stmt_mysql = conn_mysql.createStatement();


        String A = "insert into user(id, name,image, joinType , userCheck ";
        String B = ") values (?,?,?,1,0)";
        ps2 = conn_mysql.prepareStatement(A+B);
        ps2.setString(1,userId);
        ps2.setString(2,userName);
        ps2.setString(3,userImage);


        result = ps2.executeUpdate();



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
