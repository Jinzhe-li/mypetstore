<%@ include file="../common/IncludeTop.jsp"%>
<script src="js/jquery-3.5.1.js"></script>
<script>
	$(function (){
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
<div id="Catalog"><form action="${pageContext.request.contextPath}/editAccount" method="post">

	<h3>User Information</h3>

	<table>
		<tr>
			<td>User ID:</td>
			<td><input name="username" value=${sessionScope.account.username} disabled/></td>
		</tr>
		<tr>
			<td>New password:</td>
			<td><input name="password" id="password" /></td>
		</tr>
		<tr>
			<td>Repeat password:</td>
			<td><input name="repeatedPassword" id="repeat"></td>
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
	<h3>Account Information</h3>

	<table>
		<tr>
			<td>First name:</td>
			<td><input name="firstName" value=${sessionScope.account.firstName}></td>
		</tr>
		<tr>
			<td>Last name:</td>
			<td><input name="lastName" value=${sessionScope.account.lastName}></td>
		</tr>
		<tr>
			<td>Email:</td>
			<td><input size="40" name="email" value=${sessionScope.account.email}></td>
		</tr>
		<tr>
			<td>Phone:</td>
			<td><input name="phone" value=${sessionScope.account.phone}></td>
		</tr>
		<tr>
			<td>Address 1:</td>
			<td><input size="40" name="address1" value=${sessionScope.account.address1}></td>
		</tr>
		<tr>
			<td>Address 2:</td>
			<td><input size="40" name="address2" value=${sessionScope.account.address2}></td>
		</tr>
		<tr>
			<td>City:</td>
			<td><input name="city" value=${sessionScope.account.city}></td>
		</tr>
		<tr>
			<td>State:</td>
			<td><input size="4" name="state" value=${sessionScope.account.state}></td>
		</tr>
		<tr>
			<td>Zip:</td>
			<td><input size="10" name="zip" value=${sessionScope.account.zip}></td>
		</tr>
		<tr>
			<td>Country:</td>
			<td><input size="15" name="country" value=${sessionScope.account.country}></td>
		</tr>
	</table>

	<h3>Profile Information</h3>

	<table>
		<tr>
			<td>Language Preference:</td>
			<td><select name="languagePreference" value=value=${sessionScope.account.languagePreference}>
				<option value="english">english</option>
				<option value="japanese">japanese</option>
			</select></td>
		</tr>
		<tr>
			<td>Favourite Category:</td>
			<td><select name="favouriteCategoryId" value=value=${sessionScope.account.favouriteCategoryId}>
				<option value="FISH">FISH</option>
				<option value="DOGS">DOGS</option>
				<option value="REPTILES">REPTILES</option>
				<option value="CATS">CATS</option>
				<option value="BIRDS">BIRDS</option>
			</select></td>
		</tr>
		<tr>
			<td>Enable MyList</td>
			<td><input type="checkbox" name="listOption" >Enable MyList</td>
		</tr>
		<tr>
			<td>Enable MyBanner</td>
			<td><input type="checkbox" name="bannerOption" >Enable MyBanner</td>
		</tr>

	</table>
	<button type="submit" name="newAccount" value="Save Account Information">Save Account Information</button>
</form>
	<a href="${pageContext.request.contextPath}/order?orders=5"><button type="button" name="myOrder" value="MyOrder">MyOrder</button></a>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>
