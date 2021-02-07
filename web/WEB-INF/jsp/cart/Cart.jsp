<%@ include file="../common/IncludeTop.jsp"%>
<script src="js/jquery-3.5.1.js"></script>
<script>
	function updateCart(obj) {
		var item=obj.value;
		console.log(item);
		$.post('updateCartQuantities',{
			itemId:obj.name,
			quantity:obj.value,
		},function (data) {
			$('#'+obj.name).val(data);
		})
	}
</script>
<div id="BackLink">
	<a href="main">Return to Main Menu</a>
</div>

<div id="Catalog">

<div id="Cart">

<h2>Shopping Cart</h2>
	<form action="updateCartQuantities" method="post">
		<table>
			<tr>
				<th><b>Item ID</b></th>
				<th><b>Product ID</b></th>
				<th><b>Description</b></th>
				<th><b>In Stock?</b></th>
				<th><b>Quantity</b></th>
				<th><b>List Price</b></th>
				<th><b>Total Cost</b></th>
				<th>&nbsp;</th>
			</tr>

			<c:if test="${sessionScope.cart.numberOfItems == 0}">
				<tr>
					<td colspan="8"><b>Your cart is empty.</b></td>
				</tr>
			</c:if>

			<c:forEach var="cartItem" items="${sessionScope.cart.cartItems}">
				<tr>
					<td>
						<a href="viewItem?itemId=${cartItem.item.itemId}">${cartItem.item.itemId}</a>
					</td>
					<td>
						${cartItem.item.product.productId}
					</td>
					<td>
						${cartItem.item.attribute1} ${cartItem.item.attribute2}
						${cartItem.item.attribute3} ${cartItem.item.attribute4}
						${cartItem.item.attribute5} ${cartItem.item.product.name}
					</td>
					<td>
						${cartItem.inStock}
					</td>
					<td>
						<input type="text" name="${cartItem.item.itemId}"  value="${cartItem.quantity}" onfocusout='updateCart(this)'/>
					</td>
					<td>
						<fmt:formatNumber  value="${cartItem.item.listPrice}" pattern="$#,##0.00" />
					</td>
					<td>
						<input id="${cartItem.item.itemId}" value="${cartItem.total}" style="border: none;" pattern="$#,##0.00" disabled />
					</td>
					<td>
						<a href="removeItemFromCart?workingItemId=${cartItem.item.itemId}" class="Button">Remove</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</form>

		<c:if test="${sessionScope.cart.numberOfItems > 0}">
			<a href="${pageContext.request.contextPath}/order?orders=1" class="Button">Proceed to Checkout</a>
		</c:if>
</div>
	<div id="Separator">&nbsp;</div>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>