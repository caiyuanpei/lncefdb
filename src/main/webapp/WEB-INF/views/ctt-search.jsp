<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!-- insert the page content here -->
<h1>Search</h1>
<p>Users can search all entries by follow form, detailed information shown in search results.</p>
<p>If you want to change predict term to experiment: first, search the term, then, click the link on predict field.</p>
<spring:url value="/search" var="URL_DOSEARCH" />
<form action="${URL_DOSEARCH }" method="post">
  <div class="form_settings">
    <p>
	    <select name="logic1" style="width: 6em;">
	    	<option value="true">AND</option>
	    	<option value="false">NOT</option>
	    </select>
    	<input name="lnc" style="width: 10em; margin-left: 1em;" />
    	<label style="margin: 0 2em 0 1em; width: 16em; display: inline-block;">Lnc RNA</label>
    	<input type="radio" name="exact1" value="true" checked="checked" /><label style="display: inline-block; margin-right: 1em;">exact</label>
    	<input type="radio" name="exact1" value="false" /><label>fuzzy</label>
    </p>
    <p>
	    <select name="logic2" style="width: 6em;">
	    	<option value="true">AND</option>
	    	<option value="false">NOT</option>
	    </select>
    	<input name="ef" style="width: 10em; margin-left: 1em;" />
    	<label style="margin: 0 2em 0 1em; width: 16em; display: inline-block;">Environmental factors</label>
    	<input type="radio" name="exact2" value="true" checked="checked" /><label style="display: inline-block; margin-right: 1em;">exact</label>
    	<input type="radio" name="exact2" value="false" /><label>fuzzy</label>
    </p>
    <p>
	    <select name="logic3" style="width: 6em;">
	    	<option value="true">AND</option>
	    	<option value="false">NOT</option>
	    </select>
    	<select name="status" style="width: 11em; margin-left: 1em;" >
    		<option value="any">any</option>
    		<option value="experiment">experiment</option>
    		<option value="predict">predict</option>
    	</select>
    	<label style="margin: 0 2em 0 1em; width: 16em; display: inline-block;">Status</label>
    </p>
    <p style="padding-top: 15px"><span>&nbsp;</span><input class="submit" type="submit" name="contact_submitted" value="submit" /></p>
  </div>
</form>
<c:if test="${empty search_result }">
<div style="margin-top: 2em;">
<p> In the "Search" result, each entry has three items.</p>
<p style="text-indent:20px;">(1) Lnc RNA</p>
<p style="text-indent:20px;">(2) Environmental factors</p>
<p style="text-indent:20px;">(3) Status, experiment or predict.</p>
</div>
</c:if>
<c:if test="${not empty search_result }">
<h2>Search Result</h2>
<table>
  <tr>
    <th>Lnc RNA</th>
    <th>Environmental factors</th>
    <th>P value (FDR correction)</th>
    <th>Status</th>
  </tr>
  <c:forEach items="${search_result }" var="r">
  <tr>
    <td>${r.lnc }</td>
    <td>${r.ef }</td>
    <td>${r.fdr }</td>
    <c:if test="${r.status == 'predict' }">
    
    <spring:url value="/update" var="URL_UPDATE">
    	<spring:param name="lnc" value="${r.lnc }" />
    	<spring:param name="ef" value="${r.ef }" />
    </spring:url>
    <td><a href="${URL_UPDATE }">${r.status }</a></td>
    </c:if>
    <c:if test="${r.status == 'experiment' }">
    <td>${r.status }</td>
    </c:if>
  </tr>
  </c:forEach>
</table>
</c:if>