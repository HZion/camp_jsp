<%@page import="com.example.camp_jsp.userdao.UserDao"%>
<jsp:useBean id="u" class="com.example.camp_jsp.uservo.UserVO"></jsp:useBean>
<jsp:setProperty property="*" name="u"/>

<%
int i=UserDao.save(u);
if(i>0){
response.sendRedirect("adduser-success.jsp");
}else{
response.sendRedirect("adduser-error.jsp");
}
%>