<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%
	request.setCharacterEncoding("utf-8");
	String reservationDate = request.getParameter("reservationDate");
	int reservationTime = request.getParameter("reservationTime");
	int totalPrice = request.getParameter("totalPrice");
	String cancelDate = request.getParameter("cancelDate");
  int designer_no = request.getParameter("designer_no");
  int shop_no = request.getParameter("shop_no");
	int user_no = request.getParameter("user_no");
  int styling_no = request.getParameter("styling_no");


	String url_mysql = "jdbc:mysql://localhost/mogastyle?serverTimezone=Asia/Seoul&characterEncoding=utf8&useSSL=false";
	String id_mysql = "root";
	String pw_mysql = "qwer1234";

	int result = 0;

	PreparedStatement ps = null;
	try{
	    Class.forName("com.mysql.jdbc.Driver");
	    Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);

	    String A = "insert into reservation (reservationDate, reservationTime,
        totalPrice, designer_no, shop_no, user_no, styling_no";
	    String B = ") values (?,?,?,?,?,?,?)";

	    ps = conn_mysql.prepareStatement(A+B);
	    ps.setString(1, reservationDate);
	    ps.setString(2, reservationTime);
	    ps.setInt(3, totalPrice);
	    ps.setInt(4, designer_no);
	    ps.setInt(5, shop_no);
	    ps.setInt(6, user_no);
	    ps.setInt(7, styling_no);

		result = ps.executeUpdate();
      out.print(1);
	    conn_mysql.close();
	}
	catch (Exception e){
    out.print(0);
	    e.printStackTrace();
	}

%>
