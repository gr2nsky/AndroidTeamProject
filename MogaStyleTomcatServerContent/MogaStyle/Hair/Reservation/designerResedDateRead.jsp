<%@page import="java.sql.*"%>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  request.setCharacterEncoding("utf-8");
  int dno = Integer.parseInt(request.getParameter("dno"));

  String url_mysql = "jdbc:mysql://localhost/mogastyle?serverTimezone=Asia/Seoul&characterEncoding=utf8&useSSL=false";
	String id_mysql = "root";
	String pw_mysql = "qwer1234";

  String query1 = "SELECT r.no, r.reservationDate, r.reservationTime, s.leadTime ";
  String query2 = "FROM (SELECT *, TIMESTAMPDIFF(DAY, now(), reservationDate) timeDiff ";
  String query3 = "FROM reservation WHERE designer_no = ? AND cancelDate IS NULL) r, styling s ";
  String query4 = "WHERE r.timeDiff BETWEEN 0 AND 32 AND r.styling_no = s.no ";
  String query5 = "ORDER BY reservationDate ASC, reservationTime ASC";
  String query = query1 + query2 + query3 + query4 + query5;

  int count = 0;
  Connection conn_mysql = null;
  PreparedStatement pstmt = null;
  ResultSet rs = null;

  try {
        conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
        pstmt = conn_mysql.prepareStatement(query);

        pstmt.setInt(1, dno);
        rs = pstmt.executeQuery();
%>
		{
  			"reservation_list"  : [
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
			"reservationDate" : "<%=rs.getString(2) %>",
			"reservationTime" : "<%=rs.getString(3) %>",
			"leadTime" : "<%=rs.getInt(4) %>"
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
