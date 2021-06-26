<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String url_mysql = "jdbc:mysql://localhost/mogastyle?serverTimezone=Asia/Seoul&characterEncoding=utf8&useSSL=false";
 	String id_mysql = "root";
 	String pw_mysql = "qwer1234";

  String query1 = "SELECT name, tel, address, postCode, introduction, holiday, image, no, avg(reviewScore) rating, ";
  String query2 = "count(case when reviewScore IS NOT NULL then 1 end) cnt ";
  String query3 = "FROM shop LEFT JOIN (SELECT reviewContent, reviewPhoto, reviewScore, shop_no FROM reservation) r ";
  String query4 = "ON shop.no = r.shop_no GROUP BY shop.no";
  String query = query1 + query2 + query3 + query4;
  int count = 0;

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
        Statement stmt_mysql = conn_mysql.createStatement();
        ResultSet rs = stmt_mysql.executeQuery(query); //
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
			"no" : "<%=rs.getInt(8) %>",
			"rating" : "<%=rs.getDouble(9) %>",
			"cnt" : "<%=rs.getInt(10) %>"
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
        out.print(e);
    }

%>
