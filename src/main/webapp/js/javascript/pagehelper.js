$(function () {
    alert("123");
    $.ajax({
        url: 'category/list.do',
        type: 'POST',
        data: {},
        dataType: 'JSON',
        success: function (callback) {
            alert(callback.length);
            var page_count = callback.pageNum;
            var page_cont = callback.pageSize;
            $('#last_page').text(page_count)
        }
    })
})