<%--
  Created by IntelliJ IDEA.
  User: 小樱
  Date: 2018-08-23
  Time: 上午 08:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
    <script src="js/javascript/reg.js"></script>
    <script src></script>
    <style type="text/css">
        body {
            margin:0px;
            background-image:none;
            position:relative;
            left:-0px;
            width:722px;
            margin-left:auto;
            margin-right:auto;
            text-align:left;
        }
    </style>
</head>
<body>
<div></div><br /><br />
    <div id="base">
        <div><span>用  户   名  称：</span><input id="username" name="username" type="text" value="" style="width: 531px;height:20px"/>
            <span id="userError" style="color:red"></span></div><br />
        <div><span>用   户   密   码：</span><input  id="password" name="password" type="password" value="" style="width: 531px;height:20px"/>
            <span id="pswError" style="color:red"></span></div><br />
        <div><span>确 定   密   码：</span><input  id="password_1" type="password" value="" style="width: 531px;height:20px"/>
            <span id="pswError_1" style="color:red"></span></div><br />
        <div><span>电 子  邮   箱：</span><input  id="email" name="email" type="text" value="" style="width: 531px;height:20px"/>
            <span id="emailError" style="color:red"></span></div><br />
        <div><span>电  话  号  码：</span><input id="phone" name="phone" type="text" value="" style="width: 531px;height:20px"/>
            <span id="phoneError" style="color:red"></span></div><br />
        <div><span>忘记密码提示问题：</span><input id="question" name="question" type="text" value="" style="width: 531px;height:20px"/>
            <span id="questionError" style="color:red"></span></div><br />
        <div><span>忘记密码提示答案：</span><input id="answer" name="answer" type="text" value="" style="width: 531px;height:20px"/>
            <span id="answerError" style="color:red"></span></div><br />
        <div style="text-align: center;"><button id="success_by_reg_user" type="button" value="提交">提交</button></div>
    </div>
</body>
</html>
