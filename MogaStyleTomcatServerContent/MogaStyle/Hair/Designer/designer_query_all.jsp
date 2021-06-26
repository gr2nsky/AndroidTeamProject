<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String url_mysql = "jdbc:mysql://localhost:3306/mogastyle?serverTimezone=Asia/Seoul&characterEncoding=utf8&useSSL=false";
 	String id_mysql = "root";
 	String pw_mysql = "qwer1234";

  String Q1 = "SELECT d.dno, u.name, d.introduction, u.image ";
	String Q2 = "FROM user u, designer d ";
	String Q3 = "WHERE d.user_no = u.no ";

  int count = 0;

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
        PreparedStatement ps = null;
        ps = conn_mysql.prepareStatement(Q1 + Q2 + Q3);
        ResultSet rs = ps.executeQuery();
%>
		{
  			"designer_info"  : [
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
			"no" : "<%=rs.getInt(1) %>",
			"name" : "<%=rs.getString(2) %>",
			"introduction" : "<%=rs.getString(3) %>",
      "image" : "<%=rs.getString(4) %>"
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
    }

%>
