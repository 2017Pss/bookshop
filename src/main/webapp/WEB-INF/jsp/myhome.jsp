<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>个人主页</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/reset.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/myBookshelf.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/myhome.css">
</head>
<body>
<!-- 导航条 -->
<nav class="navbar">
    <div class="nav-menu">
        <ul class="menu">
            <li><a class="active" href="/home.do">首页</a></li>
            <li><a href="/goBookStore.do">书籍良品</a></li>
            <li><a href="/goAskBookStore.do">求书区</a></li>
            <li><a href="#">服务区</a></li>
        </ul>
    </div><!-- nav-menu -->

    <div class="nav-search">
        <form action="searchBook.do" method="post">
            <input type="search" class="searchIn" name="name" placeholder="搜图书...">
            <button class="search-logo"><img src="<%=request.getContextPath()%>/img/search2.png"></button>
        </form>
    </div>

    <div class="nav-info">
        <a href="#" class="username">${user.getName()}</a>
        <a href="/myBookshelf.do" class="bookshelf">||&nbsp;&nbsp;&nbsp;我的书架</a>
        <a href="/logout.do" class="logout">[ 退 出 ]</a>
    </div> <!-- nav-info-end -->
</nav>

<!-- 个人信息 -->
<div class="person-info">
    <p>
        <a href="javascript:;" class="name">${user.getName()}</a>
    </p>
    <input type="hidden" id="sexInput" value="${user.getSex()}">
    <p>
        <a href="javascript:;" class="sex" id="sexText"></a><img id="sex-pic" src="<%=request.getContextPath()%>/img/girl.png">
    </p>
    <p class="set">
        <a href="javascript:;" class="edit">编辑个人信息</a>
        <img class="set-icon" src="<%=request.getContextPath()%>/img/setting.png">
    </p>
    <div class="contact" readonly="true">
        <p>
            <span class="call-icon"></span>
            联&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;系：<span class="num">${user.getTel()}</span></p>
        <p>
            <span class="address-icon"></span>
            住&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址：<span class="address">${user.getAddress()}</span>
        </p>
        <p>
            <span class="major-icon"></span>年级专业：<span class="major">${user.getMajor()}</span>
        </p>
    </div>

</div><!-- person-info-end -->

<!-- 书摊和求书选项卡 -->
<div class="card-btn">
    <span title="sell-book" id="sell-btn" class="active">我的收藏</span>
</div>

<!-- 我的书摊 -->
<div id="container">
    <div id="sell-book">
        <table class="gridtable">
            <thead>
            <th width="10%">书名</th>
            <th width="20%">封面</th>
            <th width="15%">原价</th>
            <th width="15%">售价</th>
            <th width="30%">商品描述</th>
            <th width="10%">操作</th>
            </thead>
            <tbody>
            <c:forEach items="${collectMap}" var="collectMaps" varStatus="collectStatus">
                <c:forEach items="${collectMaps.value}" var="collectBook" varStatus="bookStatus">
                    <tr>
                        <td><span class="book-name">${collectBook.getName()}</span></td>
                        <td><img
                                src="<%=request.getContextPath()%>/img/book-list/article/${collectBook.getBookImage().getId()}.jpg">
                        </td>
                        <td>￥${collectBook.getOriginalPrice()}</td>
                        <td>￥${collectBook.getPrice()}</td>
                        <td>${collectBook.getDescription()}</td>
                        <td>移除收藏</td>
                    </tr>
                </c:forEach>
            </c:forEach>

            <tr>
                <td colspan="6" style="border-bottom-width: 0px">
                    <div class="pagination" pageCount="${pageCount}">
                        <a href="/users/myhome/1">首页</a>
                        <a href="/users/myhome/${intId-1}" currentPage="${intId-1}" class="previousPage"
                        <c:if test="${intId-1==0}">
                            class="disabled"
                        </c:if>>上一页</a>
                        <c:forEach items="${pageCount}" var="pageCount" varStatus="countStatus">
                            <a href="/users/myhome/${pageCount.toString()}">${pageCount.toString()}</a>
                        </c:forEach>
                        <a href="/users/myhome/${intId+1}" class="nextPage">下一页</a>
                        <a href="/users/myhome/${pageCount.size()}" class="previousPage">尾页</a>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
    </div>

</div> <!-- container-end -->

<footer>
    <a href="#">©2018-2019 二手书交易</a>
    <a href="#">意见反馈&nbsp;&nbsp;&nbsp;联系我们&nbsp;&nbsp;&nbsp;隐私权声明&nbsp;&nbsp;&nbsp;使用条款</a>
</footer>

<!-- javascript- -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/myBookshelf.js"></script>
<script>

    // $(function () {
    //     $(".pagination a.previousPage").click(function () {
    //         var currentPage = $(this).attr('currentPage');
    //         if (currentPage==0){
    //             $(this).addClass(disabled);
    //         }
    //     });
    // });


    function convertEditInput(obj, value) {
        var inputObj = $("<input type='text' />").val(value);
        $(obj).html(inputObj);
    }

    function convertText(obj, value) {
        $(obj).text(value);
    }

    function isNull(value) {
        if (!value || value.trim() == '') {
            return true;
        }
        return false;
    }


    $(function () {
        $(".person-info a.edit").click(function () {
            var numObj = $(".contact .num");
            var addressObj = $(".contact .address");
            var majorObj = $(".contact .major");
            if ($(".contact").find("input").length > 0) {
                var tel = $(numObj).children("input").val();
                var address = $(addressObj).children("input").val();
                var major = $(majorObj).children("input").val();
                if (isNull(tel) || isNull(address) || isNull(major)) {
                    alert("编辑信息框不能为空，修改失败");
                    return;
                }
                if (tel.length != 11) {
                    alert("手机号码格式不正确");
                    return;
                }
                var _self = this;
                $.ajax({
                    type: "POST",
                    url: "/users/change-info",
                    async: false,
                    dataType: "json",
                    data: {'tel': tel, 'address': address, 'major': major},
                    success: function (result) {
                        if (result.resultCode == 200) {
                            convertText(numObj, tel);
                            convertText(addressObj, address);
                            convertText(majorObj, major);
                            $(_self).text("编辑个人信息");
                            alert("保存信息成功");
                        } else {
                            convertEditInput(numObj, tel);
                            convertEditInput(addressObj, address);
                            convertEditInput(majorObj, major);
                            alert("保存信息失败；原因：" + result.message);
                        }
                    },
                    error: function () {
                        alert("修改失败");
                    }
                });
            } else {
                convertEditInput(numObj, numObj.text());
                convertEditInput(addressObj, addressObj.text());
                convertEditInput(majorObj, majorObj.text());
                $(this).text("保存个人信息");
            }
        });
    });
</script>
</body>
</html>