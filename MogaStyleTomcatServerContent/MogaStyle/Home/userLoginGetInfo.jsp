<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");

  String  userNo = request.getParameter("userNo");

	String url_mysql = "jdbc:mysql://localhost/stylediary?serverTimezone=Asia/Seoul&characterEncoding=utf8&useSSL=false";
 	String id_mysql = "root";
 	String pw_mysql = "qwer1234";
    String WhereDefault = "select no ,id, name, image, userPhone, joinType, userCheck from user where no = "+userNo;
    int count = 0;

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
        Statement stmt_mysql = conn_mysql.createStatement();

        ResultSet rs = stmt_mysql.executeQuery(WhereDefault); //
%>
		{
  			"user_info"  : [
<%
        while (rs.next()) {
            if (count == 0) {

            }else{
%>
            ,
<%
            }
%>
			{
			"no" : "<%=rs.getString(1) %>",
			"id" : "<%=rs.getString(2) %>",
			"name" : "<%=rs.getString(3) %>",
			"image" : "<%=rs.getString(4) %>",
      "phone" : "<%=rs.getString(5) %>",
      "joinType" : "<%=rs.getString(6) %>",
      "userCheck" : "<%=rs.getString(7) %>"
			}

<%
        count++;
        }
%>
		  ]
		}
<%
        conn_mysql.close();
    } catch (Exception e) {
        e.printStackTrace();
        out.println(e);
    }

%>
