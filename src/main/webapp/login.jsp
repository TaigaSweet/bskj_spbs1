<%--
  Created by IntelliJ IDEA.
  User: 小樱
  Date: 2018-08-07
  Time: 下午 04:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page language="java" import="java.*" %>
<%@ page import="javax.swing.plaf.synth.SynthStyle" %>
<%
    String path = request.getContextPath();

%>
<!DOCTYPE html>
<html>
<head>

    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
<div class="htmleaf-container">
    <input id="dataPath" type="text" hidden="hidden" value="<%=path%>"/>
    <div class="wrapper">
        <div class="container">
            <h1>Welcome</h1>

            <form class="form" action="user/login.do" method="post" onsubmit="return checkAll();">
                <input type="text" required id="userName" name="userName" placeholder="Username">
                <input type="password" required id="userPsw" name="userPsw" placeholder="Password">
                <button type="submit" id="login-button">Login</button>
                <button type="button" id="login-button_1">register</button>
            </form>
        </div>

        <ul class="bg-bubbles">

            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
        </ul>
    </div>
</div>
<script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="js/vue.js"></script>
<script type="text/javascript" src="js/layer.js"></script>
<script type="text/javascript" src="js/javascript/login.js"></script>

<div style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';color:#000000">
    <h1>XX管理系统</h1>
</div>
</body>
</html>