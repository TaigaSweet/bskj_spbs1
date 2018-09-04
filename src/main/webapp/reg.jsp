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
    <meta charset="UTF-8">
    <title>账户注册</title>
    <link type="text/css" href="css/user_reg_deal.css" rel="Stylesheet" />
    <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
    <script src="js/javascript/reg.js"></script>
</head>
<script>
$(function(){
    var userEntity='${sessionScope.currentUser.getUsername()}';
});
</script>
<body>
<div class="xiao-container">
    <div class="xiao-register-box">
        <div class="xiao-title-box">
            <span>用户注册</span>
        </div>
            <div class="xiao-username-box">
                <span class="xiao-require">*</span>
                <label for="username">用户名</label>
                <div class="xiao-username-input">
                    <input type="text" id="username" name="username" required="required" placeholder="请输入用户名 长度:6-12个字符" />
                    <span id="usernameError" style="font-size: 12px;color: red;"></span>
                </div>
            </div>

            <div class="xiao-userPassword-box">
                <span class="xiao-require">*</span>
                <label for="userPassword">密码</label>
                <div class="xiao-userPassword-input">
                    <input type="password" id="userPassword" required="required" name="password" placeholder="请输入密码 长度:6-12个字符" />
                    <span  style="font-size: 12px;color: red;"></span>
                </div>
            </div>

            <div class="xiao-userRePassword-box">
                <span class="xiao-require">*</span>
                <label for="userRePassword">确认密码</label>
                <div class="xiao-userRePassword-input">
                    <input type="password" id="userRePassword" required="required" name="userRePassword" placeholder="请重复输入密码" />
                    <span id="passwordError" style="font-size: 12px;color: red;"></span>
                </div>
            </div>

            <div class="xiao-userPhone-box">
                <span class="xiao-require">*</span>
                <label for="userPhone">手机号码</label>
                <div class="xiao-userPhone-input">
                    <input type="text" id="userPhone" required="required" name="phone" placeholder="请输入您的手机号码，11位有效数字" />
                    <span id="phoneError" style="font-size: 12px;color: red;"></span>
                </div>
            </div>

            <div class="xiao-userEmail-box">
                <span class="xiao-require">*</span>
                <label for="userEmail">电子邮箱</label>
                <div class="xiao-userEmail-input">
                    <input type="text" id="userEmail" required="required" name="email" placeholder="请输入您的邮箱地址，如：123@qq.com" />
                    <span id="emailError" style="font-size: 12px;color: red;"></span>
                </div>
            </div>

            <div class="xiao-userGender-box">
                <span class="xiao-require">*</span>
                <label for="userGender">密保问题</label>
                <div class="xiao-userGender-input">
                    <input type="text" id="userEmail_1" required="required" name="question" placeholder="请输入您的问题，和答案不要一致" />
                    <span id="questionError" style="font-size: 12px;color: red;"></span>
                </div>
            </div>
            <div class="xiao-userGender-box_1">
                <span class="xiao-require">*</span>
                <label for="userGender_1">密保答案</label>
                <div class="xiao-userGender-input_1">
                    <input type="text" id="userEmail_2" required="required" name="answer" placeholder="请输入您的答案，和问题不要一致" />
                    <span id="answerError" style="font-size: 12px;color: red;"></span>
                </div>
            </div>
            <div class="xiao-submit-box">
                <input id = "xiao-submit-button" type="button" value="注册">
            </div>

        <div class="xiao-goLogin-box">
            <a href="login.jsp" style="text-decoration: none;">已有账号？去登录</a>
        </div>
        <div class="xiao-goLogin-box_1">
            <a href="fileUpload.jsp" style="text-decoration: none;">图片上传</a>
        </div>

    </div>
</div>
</body>
</html>
