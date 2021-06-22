<%@page import="java.sql.*"%>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  request.setCharacterEncoding("utf-8");
  int designerNo = Integer.parseInt(request.getParameter("dseignerNo"));

  String url_mysql = "jdbc:mysql://localhost/mogastyle?serverTimezone=Asia/Seoul&characterEncoding=utf8&useSSL=false";
	String id_mysql = "root";
	String pw_mysql = "qwer1234";

  String query1 = "SELECT reservationDate, reservationTime";
  String query2 = "FROM (SELECT *, TIMESTAMPDIFF(DAY, now(), checkInDate) timeDiff FROM reservation WHERE designer_no = ? AND cancelDate IS NULL) r ";
  String query3 = "WHERE r.timeDiff BETWEEN 0 AND 32 ";
  String query4 = "ORDER BY reservationDate ASC, reservationTime ASC";
  String query = query1 + query2 + query3 + query4;

  int count = 0;
  Connection conn_mysql = null;
  PreparedStatement pstmt = null;
  ResultSet rs = null;

  try {
        Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
        pstmt = conn_mysql.prepareStatement(query);

        pstmt.setInt(1, userNo);
        rs = pstmt.executeQuery();
%>
		{
  			"designer_reservation_list"  : [
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
      "reservationDate" : "<%=rs.getString(1) %>",
			"reservationTime" : "<%=rs.getInt(2) %>"
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
