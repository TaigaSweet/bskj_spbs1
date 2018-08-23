function func(){
    //window.location.href=
    window.location.href = "/bskj_spbs1/reg.jsp";
}
$(function(){
    $("#userName").blur(function () {
        var userN=document.getElementById("userName").value;
        var userP=document.getElementById("userPsw").value;
        $.ajax({
            url:"user/check_username.do",
            type:"post",
            data:{username:userN,userPsw:userP},
            dataType:"text",
            success:function(data) {
                //alert(data);

                document.getElementById("userErro").innerText="用户名不存在！";
                var json=JSON.parse(data);
                console.log(json);
                console.log(json.status);
                console.log(json.msg);
                console.log(data);
            },
            error:function (msg) {
                //alert(msg);
                console.log(msg);
            }
        });
    })

});
