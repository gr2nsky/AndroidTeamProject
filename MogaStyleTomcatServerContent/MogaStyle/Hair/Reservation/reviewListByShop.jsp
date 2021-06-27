<%@page import="java.sql.*"%>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  request.setCharacterEncoding("utf-8");
  int sno = Integer.parseInt(request.getParameter("sno"));

  String url_mysql = "jdbc:mysql://localhost/mogastyle?serverTimezone=Asia/Seoul&characterEncoding=utf8&useSSL=false";
	String id_mysql = "root";
	String pw_mysql = "qwer1234";

    String q1 = "SELECT r.no, st.title, d.name dName, u.name uName, r.reviewContent, r.reviewScore, r.reviewPhoto, r.reservationDate ";
    String q2 = "FROM (SELECT * FROM reservation WHERE shop_no = ?) r, ";
    String q3 =	"(SELECT r.no, s.title FROM styling s, reservation r WHERE r.styling_no = s.no) st, ";
    String q4 = "(SELECT r.no, u.name FROM user u, reservation r WHERE r.user_no = u.no) u, ";
    String q5 = "(SELECT r.no, u.name FROM designer d, user u, reservation r WHERE r.designer_no = d.dno AND d.user_no = u.no) d ";
    String q6 = "WHERE r.no = st.no AND r.designer_no = d.no  AND r.user_no = u.no AND r.reviewContent IS NOT NULL ";
    String q7 = "ORDER BY r.reservationDate DESC, r.reservationTime DESC";
    String query = q1 + q2 + q3 + q4 + q5 + q6 + q7;

  int count = 0;
  Connection conn_mysql = null;
  PreparedStatement pstmt = null;
  ResultSet rs = null;

  try {
        conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
        pstmt = conn_mysql.prepareStatement(query);

        pstmt.setInt(1, sno);
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
        "no" : "<%=rs.getInt(1) %>",
  			"stylingTitle" : "<%=rs.getString(2) %>",
  			"designerName" : "<%=rs.getString(3) %>",
  			"userName" : "<%=rs.getString(4) %>",
        "reviewContent" : "<%=rs.getString(5) %>",
        "reviewScore" : "<%=rs.getString(6) %>",
        "reviewPhoto" : "<%=rs.getString(7) %>",
        "reservationDate" : "<%=rs.getString(8) %>"
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
