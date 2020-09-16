<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="istore" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="functions" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="productList">
    <jsp:include page="../fragment/products-list.jsp"/>
    <div class="text-center hidden-print">
        <img id="loadMoreIndicator" src="/static/img/loading.gif" class="hidden" alt="Loading...">
        <a id="loadMore" class="btn btn-success">Load more products</a>
    </div>
</div>
<istore:add-product-popup/>