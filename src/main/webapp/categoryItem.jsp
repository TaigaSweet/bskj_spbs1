<%--
  Created by IntelliJ IDEA.
  User: 小樱
  Date: 2018-08-29
  Time: 下午 03:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户管理--->商品分类</title>
    <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
    <script src="js/adapter.js"></script> <!--rem适配js-->

    <link rel="stylesheet" href="css/base.css"> <!--初始化文件-->
    <link rel="stylesheet" href="css/menu.css"> <!--主样式-->
    <script src="js/javascript/categoryItem.js"></script> <!--rem适配js-->
</head>
<style>
    input{
        border: 1px solid #ccc;
        padding: 7px 0px;
        border-radius: 3px;
        padding-left:5px;
        -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
        box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
        -webkit-transition: border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
        -o-transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
        transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s
    }
    input:focus{
        border-color: #66afe9;
        outline: 0;
        -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,233,.6);
        box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,233,.6)
    }
</style>
<body>

<div id="menu">
    <!--隐藏菜单-->
    <div id="ensconce">
        <h2>
            <img src="images/show.png" alt="">
            商品分类
        </h2>
    </div>

    <!--显示菜单-->
    <div id="open">
        <div class="navH">
            分类列表
            <span><img class="obscure" src="images/obscure.png" alt=""></span>
        </div>
        <div class="navBox">
            <ul id="append_Catepory">

            </ul>
        </div>
    </div>
</div>

<script src="js/menu.js"></script> <!--控制js-->
</body>
</html>