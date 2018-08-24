$(function() {
//JSON.stringify(data)
    $("#email").blur(function () {
        var email=document.getElementById("email").value;
        $.ajax({
            url: "user/check_email.do",
            type: "post",
            data:{email:email},
            success: function (data) {
                var node = document.getElementById("emailError");
                //alert(data);
                if (data.msg=="电子邮箱已存在，") {
                    node.style.color = "red";
                    document.getElementById("emailError").innerText = "邮箱已存在！";
                }else  {
                    node.style.color = "green";
                    document.getElementById("emailError").innerText = "SUCCESS！";
                }
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
        var node = document.getElementById("pswError_1");
        if (pass_1 != pass_2) {
            node.style.color = "red";
            document.getElementById("pswError_1").innerText = "输入不一致！";
        } else {
            node.style.color = "green";
            document.getElementById("pswError_1").innerText = "SUCCESS！";
        }
    });
});