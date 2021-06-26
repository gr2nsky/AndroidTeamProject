<%@page import="java.sql.*"%>
<%@page import="javax.servlet.ServletContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    request.setCharacterEncoding("utf-8");
    String diaryNo = request.getParameter("diaryNo");
	String url_mysql = "jdbc:mysql://localhost/stylediary?serverTimezone=Asia/Seoul&characterEncoding=utf8&useSSL=false";
 	String id_mysql = "root";
 	String pw_mysql = "qwer1234";
    String WhereDefault = "select no, image , hairShop , designerName , styleDate , contents from hair_diary_page where deletedate is null and hair_diary_no="+diaryNo;
    int count = 0;

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
        Statement stmt_mysql = conn_mysql.createStatement();

        ResultSet rs = stmt_mysql.executeQuery(WhereDefault); //
%>
		{
  			"diary_book_list"  : [
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
			"image" : "<%=rs.getString(2) %>",
			"hairshop" : "<%=rs.getString(3) %>",
            "designername" : "<%=rs.getString(4)%>",
            "styledate" : "<%=rs.getString(5)%>",
            "comments" : "<%=rs.getString(6)%>"
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
