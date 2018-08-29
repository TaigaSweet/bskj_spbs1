<%--
  Created by IntelliJ IDEA.
  User: 小樱
  Date: 2018-08-08
  Time: 下午 04:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
</head>
<script>
    $(function(){
        var userEntity='${sessionScope.currentUser.getUsername()}';
        var userPass='${sessionScope.currentUser.getEmail()}';
        alert(userEntity+"  "+userPass);
        /*console.log(userEntity);
        var search = location.search.slice(1); //得到get方式提交的查询字符串
        console.log(userEntity+"\n\t"+search);*/
        document.getElementById("username").innerText=userEntity;
    });
</script>
<body>
<h2>当前用户名：</h2><span id="username"></span>
<a href="categoryItem.jsp">商品分类表</a>
</body>
</html>
