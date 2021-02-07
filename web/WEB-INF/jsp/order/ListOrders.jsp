<%@ include file="../common/IncludeTop.jsp"%>

<h2>My Orders</h2>

<table>
	<tr>
		<th>Order ID</th>
		<th>Date</th>
		<th>Total Price</th>
	</tr>

	<c:forEach var="order" items="${sessionScope.orderList}">
		<tr>
			<td><a href="${pageContext.request.contextPath}/order?orderId=${order.orderId}"><c:out  value="${order.orderId}" /></a></td>
			<td><c:out  value="${order.orderDate}" /></td>
			<td><fmt:formatNumber value="${order.totalPrice}" pattern="$#,##0.00" /></td>
		</tr>
	</c:forEach>
</table>

<%@ include file="../common/IncludeBottom.jsp"%>


