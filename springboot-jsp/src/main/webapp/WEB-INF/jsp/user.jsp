<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/8/6 0006
  Time: 13:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><html lang="en">

<html>
<head>
    <title>Title</title>
</head>
<body>
<div>***********************可以直接在 jsp ⻚页⾯面中使⽤用 Java 代码，如果是⼀⾏行 Java 代码使⽤用 <% %> 的语法，如果是多⾏行行 Java 代码 则使⽤用 <% %> 的语法，********************************</div>
  <h3>⼀⾏行 Java 代码</h3>
  <p>    今天的⽇日期是: <%=(new java.util.Date())%> </p>
  <h3>多⾏行 Java 代码</h3>
  <p>  你的 IP 地址是：
      <% out.println("Your IP address is " + request.getRemoteAddr()+"</br>");
       out.println("⼀一段代码 "); %></p>
<div>***********************************************************************************************************</div>

<h3>For 循环实例例</h3>
<%
    int count = Integer.parseInt(String.valueOf( session.getAttribute("count")));
for ( int fontSize = 1; fontSize <=count; fontSize++){
%>
纯洁的微笑 <br />
<%}%>

<div>*********************⻚页⾯面常常会使⽤用⼀一些逻辑判断，使⽤用 jstl 语法很容易易实现这些功能***************************</div>
<h3>标签 c:if</h3>
<c:if test="${username !=null}">
<p>⽤用户名为：${username}<p></c:if>
<div>**********************</div>
<h3>标签 c:choose</h3>
<c:choose>
    <c:when test="${salary <= 0}">   太惨了。</c:when>
    <c:when test="${salary > 1000}">    不错的薪⽔水，还能⽣生活。</c:when>
    <c:otherwise>    什么都没有。    </c:otherwise>
</c:choose>
<h1>页面布局</h1>
<%@include file="footer.jsp"%>
</body>
</html>
