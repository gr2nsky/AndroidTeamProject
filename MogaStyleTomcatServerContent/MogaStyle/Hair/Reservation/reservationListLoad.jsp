<%@page import="java.sql.*"%>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  request.setCharacterEncoding("utf-8");
  int userNo = Integer.parseInt(request.getParameter("userNo"));

  String url_mysql = "jdbc:mysql://localhost/mogastyle?serverTimezone=Asia/Seoul&characterEncoding=utf8&useSSL=false";
	String id_mysql = "root";
	String pw_mysql = "qwer1234";

  String q1 = "SELECT r.no reservationNo, r.reservationDate, r.reservationTime, r.totalPrice, r.cancelDate, st.title stylingName, sp.name shopName, d.name designerName, sp.sImage, d.uImage, sp.address ";
  String q2 = "FROM (SELECT * FROM reservation WHERE user_no = ?) r, ";
  String q3 =	"(SELECT r.no, s.title FROM styling s, reservation r WHERE r.styling_no = s.no) st, ";
  String q4 = "(SELECT r.no, s.name, s.image sImage, s.address FROM shop s, reservation r WHERE r.shop_no = s.no) sp, ";
  String q5 = "(SELECT r.no, u.name, u.image uImage FROM designer d, user u, reservation r WHERE r.designer_no = d.dno AND d.user_no = u.no) d ";
  String q6 = "WHERE r.no = st.no AND st.no = sp.no AND sp.no = d.no ";
  String q7 = "ORDER BY r.reservationDate DESC, r.reservationTime DESC";
  String query = q1 + q2 + q3 + q4 + q5 + q6 + q7;

  int count = 0;
  Connection conn_mysql = null;
  PreparedStatement pstmt = null;
  ResultSet rs = null;

  try {
        conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
        pstmt = conn_mysql.prepareStatement(query);

        pstmt.setInt(1, userNo);
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
			"reservationDate" : "<%=rs.getString(2) %>",
			"reservationTime" : "<%=rs.getInt(3) %>",
			"totalPrice" : "<%=rs.getInt(4) %>",
      "cancelDate" : "<%=rs.getString(5) %>",
      "stylingTitle" : "<%=rs.getString(6) %>",
      "shopName" : "<%=rs.getString(7) %>",
      "designerName" : "<%=rs.getString(8) %>",
      "shopImage" : "<%=rs.getString(9) %>",
      "designerImage" : "<%=rs.getString(10) %>",
      "shopAddress" : "<%=rs.getString(11) %>"
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
