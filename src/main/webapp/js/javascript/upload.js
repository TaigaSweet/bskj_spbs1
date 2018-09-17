function setImg(obj) {//用于进行图片上传，返回地址
    var f = $(obj).val();
    if (f == null || f == undefined || f == '') {
        return false;
    }
    if (!/\.(?:png|jpg|bmp|gif|PNG|JPG|BMP|GIF)$/.test(f)) {
        alertLayel("类型必须是图片(.png|jpg|bmp|gif|PNG|JPG|BMP|GIF)");
        $(obj).val('');
        return false;
    }
    var data = new FormData();
    alert(data);
    alert(f);
    $.each($(obj)[0].files, function (i, file) {
        data.append('file', file);
    });
    $.ajax({
        type: "POST",
        url: "product/Manager/uploadPicture.do",
        data: data,
        cache: false,
        contentType: false,    //不可缺
        processData: false,    //不可缺
        dataType: "json",
        success: function (suc) {
            if (suc.code == 0) {
                console.log(suc);
                $("#thumbUrl").val(suc.message);//将地址存储好
                $("#thumburlShow").attr("src", suc.message);//显示图片
            } else {
                alert("上传失败");
                $("#url").val("");
                $(obj).val('');
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert("上传失败，请检查网络后重试");
            $("#url").val("");
            $(obj).val('');
        }
    });
}
