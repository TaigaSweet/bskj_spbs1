<%--
  Created by IntelliJ IDEA.
  User: 小樱
  Date: 2018-08-07
  Time: 下午 04:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page language="java" import="java.*"  %>
<!DOCTYPE html>
<html>
<head>
    <title>区域产能管理系统</title>
    <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="js/layer.js"></script>
    <script src="js/javascript/login.js"></script>
</head>

<style>
    body {
        margin:0px;
        background-image:none;
        width:1846px;
        margin-left:auto;
        margin-right:auto;
        text-align:left;
    }
    #base{
        background-image: url(picture/u0.png);
        width:1836px;
        height: 971px;
    }
    .tables{
        background-color:transparent;
        border-style:none;
        border:0;
        width: 285px;
        height: 33px;
        color: rgb(153, 153, 153);
        background-color: transparent;
        font-family: 'Arial Normal', 'Arial';
        font-weight: 400;
        font-style: normal;
        font-size: 16px;
        text-decoration: none;
        color: #75C4EE;
        text-align: left;
        border-color: transparent;
        outline-style: none;
    }
    .fonts{
        color: white;
        font-size: 20px;
        font-family: '微软雅黑';
        font-weight: 400;
        font-style: normal;
        font-size: 16px;
        color: #75C4EE;
    }
    #a1{
        position: relative;
        top: 269px;
        left: 882px;
        width: 840px;
    }
    #a2{
        position: relative;
        top: -7px;
        left: 6px;width: 840px;
    }
    #a3{
        position: relative;
        top: 07px;
        left: 04px;width: 840px;
    }
    #a4{
        position: relative;
        top: 24px;
        left: 0px;
        width: 337px;
        height: 46px;
        background: inherit;
        background-color: rgba(117, 196, 238, 1);
        border: none;
        border-radius: 0px;
        -moz-box-shadow: none;
        -webkit-box-shadow: none;
        box-shadow: none;
        font-family: '微软雅黑';
        font-weight: 400;
        font-style: normal;
        font-size: 18px;
        cursor: pointer;
        color: white;
        word-wrap: break-word;

    }
    #denglu{
        position: relative;
        top: 12px;
        left: 129px;
        color: white;
        word-wrap: break-word;
    }
</style>

<body>
<div id="base">
    <form action="user/login.do" method="post">
        <div id="a1">
            <div id="a2">
                <span class="fonts">登录:</span><input class="tables" type="text" name="username" id="userName" />
                <span id="userErro" style="width: 200px;color: red;"></span>
            </div><br />
            <div id="a3">
                <span class="fonts">密码:</span><input class="tables" type="password" name="userPsw" id="userPsw" />
            </div>
            <button  id="a4" value="确定" type="submit">确定</button>
        </div>

       <div style="color: aliceblue" ><a href="reg.jsp">注册账号</a></div>

    </form>
</div>
</body>
</html>
