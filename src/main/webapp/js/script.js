// JavaScript Document

function goTarget(url) {
    window.open(url, "_self");
}

var submenu = new Array()

//Set submenu contents. Expand as needed. For each content, make sure everything exists on ONE LINE. Otherwise, there will be JS errors.

submenu[0] = '&nbsp;&nbsp;<a href="../resume/resumecenter.htm" target="_self">简历中心</a> | <a href="../resume/addresume.htm" target="_self">录入简历</a> | <a href="../precontract/listprecontract.htm" target="_self">预约记录</a> | <a href="../interview/listinterview.htm" target="_self">面试评价</a> | <a href="../interview/accountinterview.htm" target="_self">客户评价</a>'

submenu[1] = '&nbsp;&nbsp;<a href="../system/system.htm" target="_self">用户管理</a> | <a href="../system/listrole.htm" target="_self">角色管理</a> | <a href="#">更改密码</a> | <a href="../system/listdepartment.htm" target="_self">部门管理</a>'

submenu[2] = '&nbsp;&nbsp;<a href="../channel/channeltype.htm" target="_self">渠道类型</a>'

submenu[3] = '&nbsp;&nbsp;<a href="../requirement/listrequirement.htm" target="_self">需求信息</a> | <a href="../requirement/listitem.htm" target="_self">项目管理</a>'

submenu[4] = '&nbsp;&nbsp;<a href="../gather/channeltype.htm" target="_self">渠道类型统计</a> | <a href="../gather/channelnumber.htm" target="_self">渠道简历数量统计</a> | <a href="../gather/channelstate.htm" target="_self">渠道简历状态统计</a> | <a href="../gather/achievement.htm" target="_self">招聘部门业绩统计</a> | <a href="../gather/stationnumber.htm" target="_self">到岗人数统计</a> | <a href="../gather/eligibilityresume.htm" target="_self">渠道合格简历统计</a> '
//Set delay before submenu disappears after mouse moves out of it (in milliseconds)
var delay_hide = 500

/////No need to edit beyond here

var menuobj = document.getElementById ? document.getElementById("describe") : document.all ? document.all.describe : document.layers ? document.dep1.document.dep2 : ""

function showit(which) {
    clear_delayhide()
    thecontent = (which == -1) ? "" : submenu[which]
    if (document.getElementById || document.all)
        menuobj.innerHTML = thecontent
    else if (document.layers) {
        menuobj.document.write(thecontent)
        menuobj.document.close()
    }
}

function resetit(e) {
    if (document.all && !menuobj.contains(e.toElement))
        delayhide = setTimeout("showit(-1)", delay_hide)
    else if (document.getElementById && e.currentTarget != e.relatedTarget && !contains_ns6(e.currentTarget, e.relatedTarget))
        delayhide = setTimeout("showit(-1)", delay_hide)
}

function clear_delayhide() {
    if (window.delayhide)
        clearTimeout(delayhide)
}

function contains_ns6(a, b) {
    while (b.parentNode)
        if ((b = b.parentNode) == a)
            return true;
    return false;
}

function addOnePurview() {
    var select1 = document.addForm.purviews;
    var option = new Option(select1.options[select1.selectedIndex].text, select1.options[select1.selectedIndex].value);
    option.selected = true;

    document.addForm.rpurviews.options.add(option);
}

function deleteOnePurview() {
    var select2 = document.addForm.rpurviews;
    document.addForm.rpurviews.options.remove(select2.selectedIndex);
    for (var i = 0; i < select2.length; i++) {
        select2.options[i].selected = true;
    }

}