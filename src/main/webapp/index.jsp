<%--
  Created by IntelliJ IDEA.
  User: 小樱
  Date: 2018-08-08
  Time: 下午 04:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="js/javascript/pagehelper.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/bootstrap-paginator.js"></script>
</head>
<script>
    $(function () {
        /*$.ajax({
            url:'category/list.do',
            type:'POST',
            data:{},
            dataType:'JSON',
            success:function (callback) {
                var page_count=callback.pageNum;
                var page_cont=callback.pageSize;
                $('#last_page').text(page_count)
            }
        })*/
        var userEntity = '${sessionScope.currentUser.getUsername()}';
        var userPass = '${sessionScope.currentUser.getEmail()}';
        var pageCountS = '${sessionScope.pageCount}';
        alert(pageCountS);
        document.getElementById("username").innerText = userEntity;
    });
</script>
<body>
<h2>当前用户名：</h2><span id="username"></span>
<a href="categoryItem.jsp">商品分类表</a>
<table class="table">
    <thead>
    <tr>
        <th>任务ID</th>
        <th>任务名称</th>
        <th>执行用户</th>
        <th>执行结果</th>
    </tr>
    </thead>
    <c:forEach var="pageScope" items="${sessionScope.items}">
        <td>${pageScope.id}</td>
        <td>${pageScope.name}</td>
        <td>${pageScope.status}</td>
        <td>${pageScope.status}</td>
    </c:forEach>
    <tbody>

    </tbody>
</table>
<div id="example" style="text-align: center">
    <ul id="pageLimit"></ul>
</div>
<script>
    $('#pageLimit').bootstrapPaginator({
        currentPage: 1,
        totalPages: ${ sessionScope.pageCount },
        size: "normal",
        bootstrapMajorVersion: 3,
        alignment: "right",
        numberOfPages: 5,
        itemTexts: function (type, page, current) {
            switch (type) {
                case "first":
                    return "首页";
                case "prev":
                    return "上一页";
                case "next":
                    return "下一页";
                case "last":
                    return "末页";
                case "page":
                    return page;
            }//默认显示的是第一页。
        },
        onPageClicked: function (event, originalEvent, type, page) {//给每个页眉绑定一个事件，其实就是ajax请求，其中page变量为当前点击的页上的数字。
            $.ajax({
                url: 'category/pageSize.do',
                type: 'POST',
                data: {'pageNum': page, 'pageSize': 5},
                dataType: 'JSON',
                success: function (callback) {
                    var page_count = callback.pageNum;
                    var page_cont = callback.pageSize;
                    $('#last_page').text(page_count)
                }
            })
        }
    });
    $(function () {

    });
</script>

</body>
</html>
