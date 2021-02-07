
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <link rel="StyleSheet" href="${pageContext.request.contextPath}/css/jpetstore.css" type="text/css" media="screen" />

    <meta name="generator" content="HTML Tidy for Linux/x86 (vers 1st November 2002), see www.w3.org" />
    <title>MyPetStore</title>
    <meta content="text/html; charset=windows-1252" http-equiv="Content-Type" />
    <meta http-equiv="Cache-Control" content="max-age=0" />
    <meta http-equiv="Cache-Control" content="no-cache" />
    <meta http-equiv="expires" content="0" />
    <meta http-equiv="Expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
    <meta http-equiv="Pragma" content="no-cache" />
    <script src="js/jquery-3.5.1.js"></script>
</head>

<body>
<script>
    $(function (){
        $('#keyword').keyup(function () {
            var keyword=$('#keyword').serialize();
            $.post('autoCompleteServlet',keyword,function (data) {
                if(data=="not"||$('#keyword').val()==""){
                    $('#tips').empty();
                    $('#tips').css("display","none");
                }
                else {
                    $('#tips').empty();
                    var res=data.split(",");
                    var html="";
                    for(var i=0;i<res.length;i++){
                        html+="<div onclick='setSearch_onclick(this)' onmouseout='changeBackColor_out(this)' onmouseover='changeBackColor_over(this)'>"+res[i]+"</div>"
                    }
                    $('#tips').append(html);
                    $('#tips').css("display","block");
                }
            })
        })
    })
    function changeBackColor_over(div){
        $(div).css("background-color","#CCCCCC");
    }
    //鼠标离开内容
    function changeBackColor_out(div){
        $(div).css("background-color","");
    }
    //将点击的内容放到搜索框
    function setSearch_onclick(div){
        $("#keyword").val(div.innerText);
        $("#tips").css("display","none");
    }
</script>
<div id="Header">

    <div id="Logo">
        <div id="LogoContent">
            <a href="main"><img src="${pageContext.request.contextPath}/images/logo-topbar.gif" /></a>
        </div>
    </div>

    <div id="Menu">
        <div id="MenuContent">
            <c:if test="${sessionScope.account==null}">
                <a href="viewCart"><img align="middle" name="img_cart" src="${pageContext.request.contextPath}/images/cart.gif" /></a>
                <img align="middle" src="${pageContext.request.contextPath}/images/separator.gif" />
                <a href="Skip?jsp=1">Sign In</a>
                <img align="middle" src="${pageContext.request.contextPath}/images/separator.gif" />
                <a href="Skip?jsp=2">?</a>
            </c:if>
            <c:if test="${sessionScope.account!=null}">
                <a href="viewCart"><img align="middle" name="img_cart" src="${pageContext.request.contextPath}/images/cart.gif" /></a>
                <img align="middle" src="${pageContext.request.contextPath}/images/separator.gif" />
                <a href="Skip?jsp=4">Sign Out</a>
                <img align="middle" src="${pageContext.request.contextPath}/images/separator.gif" />
                <a href="Skip?jsp=5">My Account</a>
                <img align="middle" src="${pageContext.request.contextPath}/images/separator.gif" /> <a href="../help.html">?</a>
            </c:if>
        </div>
    </div>

    <div id="Search">
        <div id="SearchContent">
            <form action="searchProduct" method="post">
                <input type="text" name="keyword" size="14" autocomplete="on" id="keyword"/>
                <input type="submit" name="searchProducts" value="Search" />
                <div id="tips" style="position: absolute;z-index: 99;width: 134px;background-color: white;border: 1px solid black;display: none;left: 74.3%;bottom: auto;top: 50px;"></div>
            </form>
        </div>
    </div>

    <div id="QuickLinks">
        <a href="viewCategory?categoryId=FISH">
            <img src="${pageContext.request.contextPath}/images/sm_fish.gif" />
        </a>
        <img src="${pageContext.request.contextPath}/images/separator.gif" />
        <a href="viewCategory?categoryId=DOGS">
            <img src="${pageContext.request.contextPath}/images/sm_dogs.gif" />
        </a>
        <img src="${pageContext.request.contextPath}/images/separator.gif" />
        <a href="viewCategory?categoryId=REPTILES">
            <img src="${pageContext.request.contextPath}/images/sm_reptiles.gif" />
        </a>
        <img src="${pageContext.request.contextPath}/images/separator.gif" />
        <a href="viewCategory?categoryId=CATS">
            <img src="${pageContext.request.contextPath}/images/sm_cats.gif" />
        </a>
        <img src="images/separator.gif" />
        <a href="viewCategory?categoryId=BIRDS">
            <img src="${pageContext.request.contextPath}/images/sm_birds.gif" />
        </a>
    </div>

</div>

<div id="Content">
