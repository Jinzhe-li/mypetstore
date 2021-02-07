<%@ include file="../common/IncludeTop.jsp"%>

<div id="Catalog">
	<form action="${pageContext.request.contextPath}/signOn" method="post" id="login">

		<p>Please enter your username and password.</p>
		<p>Username:<input name="username" value="j2ee" id="username" /> <br />
		Password:<input name="password" value="j2ee" /><br/>
		VerifyCode:<input type="text" name="res"><img src="${pageContext.request.contextPath}/VerifyCode" onclick="changeImg(this)"></p>
		<button type="submit" name="signon" value="Login" id="log">Login</button>
	</form>
	Need a user name and password?
	<a href="Skip?jsp=3">Register Now!</a>
</div>
<script>
	function changeImg(img) {
		// 浏览器有一个缓存特性,
		// 第一次从服务器获取图片，浏览器第二次发送请求时发现地址没有改变，就会使用第一次的图片，而不会真正发送请求
		// 为了让浏览器每次都把请求发送给服务器，给一个时间参数
		img.src = "${pageContext.request.contextPath}/VerifyCode?t="+new Date().getTime(); // 给src赋一个新值，就会向新值的地址发送一次请求
	}
</script>

<%@ include file="../common/IncludeBottom.jsp"%>

