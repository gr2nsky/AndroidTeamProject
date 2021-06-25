<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String sno = request.getParameter("sno");
	String url_mysql = "jdbc:mysql://localhost/mogastyle?serverTimezone=Asia/Seoul&characterEncoding=utf8&useSSL=false";
 	String id_mysql = "root";
 	String pw_mysql = "qwer1234";
    String Q1 = "select name,tel,address,postCode,introduction,holiday, image,no from shop where no="+sno;
    int count = 0;

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
        Statement stmt_mysql = conn_mysql.createStatement();
        ResultSet rs = stmt_mysql.executeQuery(Q1); //
%>
		{
  			"shop_info"  : [
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
			"name" : "<%=rs.getString(1) %>",
			"tel" : "<%=rs.getString(2) %>",
			"address" : "<%=rs.getString(3)%>",
			"postCode" : "<%=rs.getString(4)%>",
			"introduction" : "<%=rs.getString(5)%>",
			"holiday" : "<%=rs.getString(6)%>",
			"image" : "<%=rs.getString(7)%>",
			"no" : "<%=rs.getInt(8) %>"
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
