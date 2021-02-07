<%@ include file="../common/IncludeTop.jsp"%>
<script src="js/jquery-3.5.1.js"></script>
<script>
	$(function (){
		$('#username').focusout(function (){
			var username=$('#username').serialize();
			$.post('usernameExistServlet',username,function (data){
				if(data==1){
					$('#load').remove();
					$('#username').after('<br><font color="#dc143c" id="load">username has been exist</font>');
				}
				else{
					$('#load').remove();
				}
			});
		})
		$('#repeat').focusout(function (){
			var password=$('#password').val();
			var repeat=$('#repeat').val();
			if(password==repeat){
				$('#p').remove();
			}
			else {
				$('#p').remove();
				$('#repeat').after('<br><font color="#dc143c" id="p">The two passwords are inconsistent</font>');
			}
		})
	})
</script>
<div id="Catalog">
	<form action="${pageContext.request.contextPath}/newAccountServlet" method="post">

	<h3>User Information</h3>

	<table>
		<tr>
			<td>User ID:</td>
			<td><input name="username" id="username" /></td>
		</tr>
		<tr>
			<td>New password:</td>
			<td><input name="password" id="password"/></td>
		</tr>
		<tr>
			<td>Repeat password:</td>
			<td><input name="repeatedPassword" id="repeat"/></td>
		</tr>
		<tr>
			<td>VerifyCode:</td>
			<td><input type="text" name="res"><img src="${pageContext.request.contextPath}/VerifyCode" onclick="changeImg(this)"></td>
		</tr>
	</table>
		<script>
			function changeImg(img) {
				// 浏览器有一个缓存特性,
				// 第一次从服务器获取图片，浏览器第二次发送请求时发现地址没有改变，就会使用第一次的图片，而不会真正发送请求
				// 为了让浏览器每次都把请求发送给服务器，给一个时间参数
				img.src = "${pageContext.request.contextPath}/VerifyCode?t="+new Date().getTime(); // 给src赋一个新值，就会向新值的地址发送一次请求
			}
		</script>

	<%@ include file="IncludeAccountFields.jsp"%>

	<button type="submit" name="newAccount" value="Save Account Information" >Save Account Information</button>

</form></div>

<%@ include file="../common/IncludeBottom.jsp"%>