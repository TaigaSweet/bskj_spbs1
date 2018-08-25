var status_email;
$(function() {
//JSON.stringify(data)

    $("#email").blur(function () {
        var email=document.getElementById("email").value;
        $.ajax({
            url: "user/check_email.do",
            type: "post",
            data:{email:email},
            success: function (data) {
                status_email=data.status;
                var node = document.getElementById("emailError");
                //alert(data);
                if (data.msg=="电子邮箱已存在，") {
                    node.style.color = "red";
                    document.getElementById("emailError").innerText = "邮箱已存在！";
                    //location.href="reg.jsp";
                }else  {
                    node.style.color = "green";
                    document.getElementById("emailError").innerText = "SUCCESS！";
                    //location.href="index.jsp";
                }
            },
            error: function (msg) {
                console.log(msg);
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
    $("#success_by_reg_user").click(function(){
        alert(status_email);
        var user=[];
        //user.username="";
        var myDate=new Date().format("yyyy-MM-dd hh:mm:ss");
        var username = document.getElementById("username").value;
        var password = document.getElementById("password").value;
        var email = document.getElementById("email").value;
        var phone = document.getElementById("phone").value;
        var question = document.getElementById("question").value;
        var answer = document.getElementById("answer").value;
        var role = 0;
        var create_time =myDate;
        var update_time = myDate;
        var data_user={username:username,password:password,email:email,
            phone:phone,question:question,answer:answer,role:role,
            create_time:create_time,update_time:update_time};
        user.push(data_user);
        console.log(data_user);
        $.ajax({
            url: "user/reg_user.do",
            type: "post",
           // contentType:"application/json",
            dataType:"json",
            data:{username:username,password:password,email:email,
                phone:phone,question:question,answer:answer,role:role,
                create_time:create_time,update_time:update_time},
            success: function (data_) {//如果注册成功 status=0；
                //var node = document.getElementById("emailError");data: JSON.stringify(param)
                //alert(data);
                console.log(data_);
                alert(",.,/./,./,/,/."+JSON.stringify(user));
                /*if (data.msg=="success") {
                   // node.style.color = "red";
                  //  document.getElementById("emailError").innerText = "邮箱已存在！";
                    //location.href="success.jsp";
                }else  {
                  //  node.style.color = "green";
                  //  document.getElementById("emailError").innerText = "SUCCESS！";

                   // location.href="index.jsp?name="+data.msg;
                }*/
            },
            error: function (msg) {
                alert(";';';';';';';';'"+JSON.stringify(user));
                console.log(msg);
                alert(msg);
            }
        });
    });
});
Date.prototype.format = function(fmt) {
    var o = {
        "M+" : this.getMonth()+1,                 //月份 六点半--->七点半--->八点半
        "d+" : this.getDate(),                    //日
        "h+" : this.getHours(),                   //小时
        "m+" : this.getMinutes(),                 //分
        "s+" : this.getSeconds(),                 //秒
        "q+" : Math.floor((this.getMonth()+3)/3), //季度
        "S"  : this.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt)) {
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    }
    for(var k in o) {
        if(new RegExp("("+ k +")").test(fmt)){
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        }
    }
    return fmt;
}