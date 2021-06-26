<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
  request.setCharacterEncoding("utf-8");
  int dno = Integer.parseInt(request.getParameter("dno"));

	String url_mysql = "jdbc:mysql://localhost:3306/mogastyle?serverTimezone=Asia/Seoul&characterEncoding=utf8&useSSL=false";
 	String id_mysql = "root";
 	String pw_mysql = "qwer1234";

  String Q1 = "SELECT u.name, d.certificationDate, d.introduction, s.no sno, u.image ";
	String Q2 = "FROM user u, designer d, shop s, employ e ";
	String Q3 = "WHERE d.dno = " + dno + " and d.user_no = u.no and d.dno = e.designer_no and e.shop_no = s.no";

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
			"name" : "<%=rs.getString(1) %>",
      "certificationDate" : "<%=rs.getString(2) %>",
			"introduction" : "<%=rs.getString(3) %>",
      "sno" : "<%=rs.getInt(4) %>",
      "image" : "<%=rs.getString(5) %>"
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
        out.print(e);
    }

%>
