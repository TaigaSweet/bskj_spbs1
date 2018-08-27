<%--
  Created by IntelliJ IDEA.
  User: 小樱
  Date: 2018-08-27
  Time: 上午 08:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>

    <style>
        body {
            margin:0px;
            background-image:none;
            position:relative;
            left:-0px;
            width:800px;
            margin-left:auto;
            margin-right:auto;
            text-align:left;

        }
        .file-box{
            display: inline-block;
            position: relative;
            padding: 3px 5px;
            overflow: hidden;
            color:#fff;
            background-color: #ccc;
        }
        .file-btn{
            position: absolute;
            width: 100%;
            height: 100%;
            top: 0;
            left: 0;
            outline: none;
            background-color: transparent;
            filter:alpha(opacity=0);
            -moz-opacity:0;
            -khtml-opacity: 0;
            opacity: 0;
        }
    </style>
</head>
<body>
<div>
    <div class="file-box">
        <input type="file" class="file-btn"/>
        上传文件
    </div>
    <img style="height:100px;width:100px;" src="webapp/picture/time.jpg" />
</div>
</body>
</html>
