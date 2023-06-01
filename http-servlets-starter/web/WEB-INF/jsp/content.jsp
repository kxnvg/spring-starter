<%--
  Created by IntelliJ IDEA.
  User: Никита
  Date: 22.05.2023
  Time: 20:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="header.jsp"%>
<dib>
  <span>Content. Русский</span>
  <p>Size: ${requestScope.fligths.size}</p>
  <p>Id: ${requestScope.fligths.get(0).id}</p>
  <p>Id 2: ${requestScope.fligths[1].id}</p>
  <p>Map Id 2: ${requestScope.fligthsMap[1]}</p>
  <p>JSESSION id: ${cookie["JSESSIONID"]}, unique identifier</p>
  <p>Header: ${header["Cookie"]}</p>
  <p>Param id: ${param.id}</p>
  <p>Param test: ${param.test}</p>
</dib>
<%@include file="footer.jsp"%>
</body>
</html>
