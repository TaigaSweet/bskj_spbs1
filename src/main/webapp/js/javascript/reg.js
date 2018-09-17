var status_email;
$(function () {
    $("#username").change(function () {
        var userN = document.getElementById("username").value;
        if (userN != null && userN != "") {
            $.ajax({
                url: "user/check_username.do",
                type: "post",
                data: {username: userN},
                dataType: "text",
                success: function (data) {
                    //alert(data);
                    var json = JSON.parse(data);

                    if (json.status == 0) {
                        document.getElementById("usernameError").innerText = "用户已存在！";
                    }
                    else {
                        document.getElementById("usernameError").innerText = "可以注册！";
                    }
                    var json = JSON.parse(data);
                    console.log(json);
                    console.log(json.status);
                    console.log(json.msg);
                    console.log(data);
                },
                error: function (msg) {
                    //alert(msg);
                    console.log(msg);
                }
            });
        } else {
            document.getElementById("usernameError").innerText = "请输入用户名";
        }
    })
    $("#userEmail").blur(function () {
        var email = document.getElementById("userEmail").value;
        if (email != null && email != "") {
            $.ajax({
                url: "user/check_email.do",
                type: "post",
                data: {email: email},
                success: function (data) {
                    // alert(data);
                    console.log(data);
                    status_email = data.status;
                    var node = document.getElementById("emailError");
                    //alert(data);
                    if (data.status == 1) {
                        node.style.color = "red";
                        document.getElementById("emailError").innerText = "邮箱已存在！";
                        //location.href="reg.jsp";
                    } else {
                        node.style.color = "green";
                        document.getElementById("emailError").innerText = "SUCCESS！";
                        //location.href="index.jsp";
                    }
                },
                error: function (msg) {
                    console.log(msg);
                }
            });
        } else {
            document.getElementById("emailError").innerText = "请输入邮箱！";
        }
    })
    $("#userPhone").blur(function () {
        var phone = document.getElementById("userPhone").value;
        if (phone != null && phone != "") {
            $.ajax({
                url: "user/check_phone.do",
                type: "post",
                data: {phone: phone},
                success: function (data) {
                    //alert(data);
                    console.log(data);
                    status_email = data.status;
                    var node = document.getElementById("phoneError");
                    //alert(data);
                    if (data.status != 0) {
                        node.style.color = "red";
                        document.getElementById("phoneError").innerText = "电话号码已存在！";
                        //location.href="reg.jsp";
                    } else {
                        node.style.color = "green";
                        document.getElementById("phoneError").innerText = "可以注册";
                        //location.href="index.jsp";
                    }
                },
                error: function (msg) {
                    console.log(msg);
                }
            });
        } else {
            document.getElementById("phoneError").innerText = "请输入电话号码";
        }
    })
    $("#userRePassword").blur(function () {
        var pass_1 = document.getElementById("userPassword").value;
        var pass_2 = document.getElementById("userRePassword").value;
        console.log(pass_1 + "  " + pass_2);
        var node = document.getElementById("passwordError");
        if (pass_1 != pass_2) {
            node.style.color = "red";
            document.getElementById("passwordError").innerText = "两次输入密码不一致";
        } else {
            document.getElementById("passwordError").innerText = "";
        }
    });
    $("#userEmail_2").blur(function () {
        var pass_1 = document.getElementById("userEmail_1").value;
        var pass_2 = document.getElementById("userEmail_2").value;
        console.log(pass_1 + "  " + pass_2);
        var node = document.getElementById("emailError");
        if (pass_1 != pass_2) {
            node.style.color = "red";
            document.getElementById("emailError").innerText = "两次输入不能一致";
        } else {
            document.getElementById("emailError").innerText = "";
        }
    });
    $("#xiao-submit-button").click(function () {
        var user = [];
        //user.username="";
        var myDate = new Date().format("yyyy-MM-dd hh:mm:ss");
        var username = document.getElementById("username").value;
        var password = document.getElementById("userPassword").value;
        var email = document.getElementById("userEmail").value;
        var phone = document.getElementById("userPhone").value;
        var question = document.getElementById("userEmail_1").value;
        var answer = document.getElementById("userEmail_2").value;
        var role = 0;
        var create_time = myDate;
        var update_time = myDate;
        var data_user = {
            username: username, password: password, email: email,
            phone: phone, question: question, answer: answer, role: role,
            create_time: create_time, update_time: update_time
        };
        user.push(data_user);
        console.log(data_user);
        $.ajax({
            url: "user/reg_user.do",
            type: "post",
            // contentType:"application/json",
            dataType: "json",
            data: {
                username: username, password: password, email: email,
                phone: phone, question: question, answer: answer, role: role,
                create_time: create_time, update_time: update_time
            },
            success: function (data_) {//如果注册成功 status=0；
                console.log("如果注册成功 status=0；" + data_);
            },
            error: function (msg) {
                alert(";';';';';';';';'" + JSON.stringify(user));
                console.log(msg);
                alert(msg);
            }
        });
    });
});
Date.prototype.format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1,                 //月份 六点半--->七点半--->八点半
        "d+": this.getDate(),                    //日
        "h+": this.getHours(),                   //小时
        "m+": this.getMinutes(),                 //分
        "s+": this.getSeconds(),                 //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds()             //毫秒
    };
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;
}