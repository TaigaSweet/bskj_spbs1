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
    <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
    <script src="js/javascript/upload.js"></script>
    <style>
        body {
            margin: 0px;
            background-image: none;
            position: relative;
            left: -0px;
            width: 800px;
            margin-left: auto;
            margin-right: auto;
            text-align: left;

        }

        .file-box {
            display: inline-block;
            position: relative;
            padding: 3px 5px;
            overflow: hidden;
            color: #fff;
            background-color: #ccc;
        }

        .file-btn {
            position: absolute;
            width: 100%;
            height: 100%;
            top: 0;
            left: 0;
            outline: none;
            background-color: transparent;
            filter: alpha(opacity=0);
            -moz-opacity: 0;
            -khtml-opacity: 0;
            opacity: 0;
        }

        #file-load {
            width: 127px;
            height: 38px;
            background: inherit;
            background-color: rgba(22, 155, 213, 1);
            border: none;
            border-radius: 5px;
            -moz-box-shadow: none;
            -webkit-box-shadow: none;
            box-shadow: none;
            font-family: '微软雅黑';
            font-weight: 400;
            font-style: normal;
            font-size: 14px;
            position: relative;
            top: 0px;
            left: 39%;
            text-align: center;
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="row">
    <span class="personattr">身份证正面照片:</span>
    <input type="hidden" name="img" id="thumbUrl"/>
    <input type="file" name="logoFile" id="logoFile" onchange="setImg(this);">
    <span><img id="thumburlShow" src="" width="120" height="120"/></span>
</div>
</body>
</html>
