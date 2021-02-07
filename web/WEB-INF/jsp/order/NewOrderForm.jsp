<%@ include file="../common/IncludeTop.jsp"%>
<script src="js/jquery-3.5.1.js"></script>
<script>
		function clicked(obj){
			var html="<table id=\"second\">\n" +
					"\t\t<tr>\n" +
					"\t\t\t<th colspan=2>Shipping Address</th>\n" +
					"\t\t</tr>\n" +
					"\n" +
					"\t\t<tr>\n" +
					"\t\t\t<td>First name:</td>\n" +
					"\t\t\t<td><input type=\"text\" name=\"shipToFirstName\" /></td>\n" +
					"\t\t</tr>\n" +
					"\t\t<tr>\n" +
					"\t\t\t<td>Last name:</td>\n" +
					"\t\t\t<td><input type=\"text\" name=\"shipToLastName\" /></td>\n" +
					"\t\t</tr>\n" +
					"\t\t<tr>\n" +
					"\t\t\t<td>Address 1:</td>\n" +
					"\t\t\t<td><input type=\"text\" size=\"40\" name=\"shipAddress1\" /></td>\n" +
					"\t\t</tr>\n" +
					"\t\t<tr>\n" +
					"\t\t\t<td>Address 2:</td>\n" +
					"\t\t\t<td><input type=\"text\" size=\"40\" name=\"shipAddress2\" /></td>\n" +
					"\t\t</tr>\n" +
					"\t\t<tr>\n" +
					"\t\t\t<td>City:</td>\n" +
					"\t\t\t<td><input type=\"text\" name=\"shipCity\" /></td>\n" +
					"\t\t</tr>\n" +
					"\t\t<tr>\n" +
					"\t\t\t<td>State:</td>\n" +
					"\t\t\t<td><input type=\"text\" size=\"4\" name=\"shipState\" /></td>\n" +
					"\t\t</tr>\n" +
					"\t\t<tr>\n" +
					"\t\t\t<td>Zip:</td>\n" +
					"\t\t\t<td><input type=\"text\" size=\"10\" name=\"shipZip\" /></td>\n" +
					"\t\t</tr>\n" +
					"\t\t<tr>\n" +
					"\t\t\t<td>Country:</td>\n" +
					"\t\t\t<td><input type=\"text\" size=\"15\" name=\"shipCountry\" /></td>\n" +
					"\t\t</tr>\n" +
					"\n" +
					"\n" +
					"\t</table>";
			if($(obj).is(':checked')==true){
				$('#first').after(html);
				$('#orders').val("3");
			}
			else{
				$('#second').remove();
				$('#orders').val("2");
			}

		}
</script>
<div id="Catalog"><form action="${pageContext.request.contextPath}/order">

	<input type="hidden" name="orders" id="orders" value="2" />
	<table id="first">
		<tr>
			<th colspan=2>Payment Details</th>
		</tr>
		<tr>
			<td>Card Type:</td>
			<td><select name="cardType">
					<option value="Visa">Visa</option>
					<option value="MasterCard">MasterCard</option>
					<option value="AmericanExpress">AmericanExpress</option>
				</select></td>
		</tr>
		<tr>
			<td>Card Number:</td>
			<td><input type="text" name="creditCard" value="999 9999 9999 9999"/> * Use a fake
			number!</td>
		</tr>
		<tr>
			<td>Expiry Date (MM/YYYY):</td>
			<td><input type="text" name="expiryDate" value="12/03"/></td>
		</tr>
		<tr>
			<th colspan=2>Billing Address</th>
		</tr>

		<tr>
			<td>First name:</td>
			<td><input type="text" name="billToFirstName" value="${sessionScope.account.firstName}" readonly="readonly"/></td>
		</tr>
		<tr>
			<td>Last name:</td>
			<td><input type="text" name="billToLastName" value="${sessionScope.account.lastName}" readonly="readonly"/></td>
		</tr>
		<tr>
			<td>Address 1:</td>
			<td><input type="text" size="40" name="billAddress1" value="${sessionScope.account.address1}" readonly="readonly"/></td>
		</tr>
		<tr>
			<td>Address 2:</td>
			<td><input type="text" size="40" name="billAddress2" value="${sessionScope.account.address2}" readonly="readonly"/></td>
		</tr>
		<tr>
			<td>City:</td>
			<td><input type="text" name="billCity" value="${sessionScope.account.city}" readonly="readonly"/></td>
		</tr>
		<tr>
			<td>State:</td>
			<td><input type="text" size="4" name="billState" value="${sessionScope.account.state}" readonly="readonly"/></td>
		</tr>
		<tr>
			<td>Zip:</td>
			<td><input type="text" size="10" name="billZip" value="${sessionScope.account.zip}" readonly="readonly"/></td>
		</tr>
		<tr>
			<td>Country:</td>
			<td><input type="text" size="15" name="billCountry" value="${sessionScope.account.country}" readonly="readonly"/></td>
		</tr>

		<tr>
			<td colspan=2><input type="checkbox"  name="shippingAddressRequired" value="true" onclick="clicked(this)"/>
			Ship to different address...</td>
		</tr>

	</table>

	<button type="submit" name="newOrder" value="Continue">Continue</button>

</form></div>

<%@ include file="../common/IncludeBottom.jsp"%>