function func(){
    //window.location.href=
    window.location.href = "/bskj_spbs1/reg.jsp";
}
$(function(){
    var file=$("#file").val();
    $("#file").click(function () {

        var file=$("#file").val();
        console.log(file);
    });
    $("#login-button_1").click(function(){
        location.href="reg.jsp";
    });
$("#login-button").click(function(){var file=$("#file").val();
    var userN=$("#userName").val();
    var userP=$("#userPsw").val();
    console.log(file);
    $.ajax({
        url:"user/login.do",
        type:"post",
        data:{username:userN,password:userP},
        dataType:"text",
        success:function(data_1) {

            var json=JSON.parse(data_1);
            console.log(json.data);
            if(json.status==1){
                alert("用户密码错误，请仔细检查是否输入有误");
            }else{
                location.href="index.jsp";
            }
           // var json_data=JSON.parse(json.data);
            //console.log("QWER"+"ddd+::::"+json_data+"   "+json_data.username);
          /*  if (data.status==1){
                document.getElementById("userErro").innerText="用户名或密码错误！";
            }*/


            console.log(json.data.username);
            console.log(json.status);
            console.log(json.msg);
            console.log(data_1);
            //location.href="index.jsp";
        },
        error:function (msg) {
            //alert(msg);
            console.log(msg);
            location.href="login.jsp";
        }
    });
});
});
