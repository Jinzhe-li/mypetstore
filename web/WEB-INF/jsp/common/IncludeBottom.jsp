
</div>

<div id="Footer">

    <div id="PoweredBy">&nbsp<a href="www.csu.edu.cn">www.csu.edu.cn</a>
    </div>

    <div id="Banner"><c:if test="${sessionScope.account != null }">
            <c:if test="${sessionScope.account.bannerOption}">
                <c:if test="${sessionScope.account.favouriteCategoryId eq 'DOGS'}"><img src="${pageContext.request.contextPath}/images/banner_dogs.gif" /></c:if>
<%--                <c:if test="${sessionScope.account.favouriteCategoryId eq 'CATS'}"><img src="${pageContext.request.contextPath}/images/banner_cats.gif" /></c:if>--%>
<%--                <c:if test="${sessionScope.account.favouriteCategoryId eq 'BIRDS'}"><img src="${pageContext.request.contextPath}/images/banner_birds.gif" /></c:if>--%>
<%--                <c:if test="${sessionScope.account.favouriteCategoryId eq 'FISH'}"><img src="${pageContext.request.contextPath}/images/banner_fish.gif" /></c:if>--%>
<%--                <c:if test="${sessionScope.account.favouriteCategoryId eq 'REPTILES'}"><img src="${pageContext.request.contextPath}/images/banner_reptiles.gif" /></c:if>--%>
            </c:if>
        </c:if></div>

</div>

</body>
</html>
