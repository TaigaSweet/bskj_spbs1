$(function(){
    var img_path=document.getElementById("img_class");
    //picture/timg.jpg
    img_path.src="picture/timg.jpg";
    $(".file-btn").change(function(){
        var filepath=$(".file-btn").val();//img_class
        alert(filepath);
        $.ajax({
            url: "user/upload_path.do",
            type: "post",
            data:{img_path:filepath},
            success: function (data) {
                // alert(data);
                console.log("成功过后："+data);

            },
            error: function (msg) {
                console.log("失败过后："+msg);
            }
        });
    });
    $("#file-load").click(function(){

    });

});