$(function () {
    var append_Item = $("#append_Catepory");
    $.ajax({
        url: "category/items_cate.do",
        type: "post",
        data: {parent: 0},
        dataType: "json",
        success: function (data_1) {
            console.log(data_1.data);
            for (var i = 0; i < data_1.data.length; i++) {
                console.log(data_1.data[i].name);
                var e = "<li><h2 class=\"obFocus\">" + data_1.data[i].name + "<i></i></h2><div style=\"height: 2.5078rem;\" id=" + data_1.data[i].id + " class=\"secondary\"></div></li>";
                append_Item.append(e);
                ajax_default(data_1.data[i].id);
            }

        },
        error: function (msg) {
            console.log(msg);
        }
    });
});

function ajax_default(dataId) {
    var append_co = $("#" + dataId);
    console.log(append_co);
    $.ajax({
        url: "category/items_cate.do",
        type: "post",
        data: {parent: dataId},
        dataType: "json",
        success: function (data_Item) {
            console.log(data_Item.data);
            //var json=JSON.parse(data_1);
            // console.log(json.data);
            // console.log("当前json的个数："+json.length);
            for (var j = 0; j < data_Item.data.length; j++) {
                console.log(data_Item.data[j].name);
                var append_e = "<h3>" + data_Item.data[j].name + "</h3>";
                append_co.append(append_e);
            }

        },
        error: function (msg) {
            console.log(msg);
        }
    });
}