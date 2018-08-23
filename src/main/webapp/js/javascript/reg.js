$(function() {

    $("#email").blur(function () {
        $.ajax({
            url: "user/check_email.do",
            type: "post",
            success: function (data) {
                //alert(data);
                document.getElementById("emailError").innerText = "邮箱已存在！";
                var json = JSON.parse(data);
                console.log(json);
            },
            error: function (msg) {

                console.log(json);
            }
        });
    })
    $("#password_1").blur(function () {
        var pass_1 = document.getElementById("password").value;
        var pass_2 = document.getElementById("password_1").value;
        console.log(pass_1+"  "+pass_2);
        if (pass_1 == pass_2) {
            document.getElementById("pswError_1").innerText = "输入不一致！";
        } else {
            document.getElementById("pswError_1").innerText = "SUCCESS！";
        }
    });
});