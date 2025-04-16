<%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <html>
 <head>
    <meta charset="UTF-8">
    <title>Title</title>
 </head>
 <body>
 <a href="/index.html">메인</a>
 <table>
    <thead>
    <th>id</th>
    <th>username</th>
    <th>age</th>
    </thead>
    <tbody>
    <c:forEach var="item" items="${members}">
        <tr>
            <td>${item.id}</td>
            <td>${item.username}</td>
            <td>${item.age}</td>
        </tr>
    </c:forEach>
    </tbody>
 </table>
 </body>
 </html>

 <%--
 → ${members}는 위에서 넘긴 List<Member> 객체입니다.
 → forEach가 리스트를 반복하면서 각각의 member를 item으로 꺼내 사용합니다
 --%>