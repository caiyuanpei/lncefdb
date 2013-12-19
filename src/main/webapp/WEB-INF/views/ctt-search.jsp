<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!-- insert the page content here -->
<h1>Search</h1>
<p>Users can search all entries by follow form, detailed information shown in search results.</p>
<p>If you want to change predict term to experiment: first, search the term, then, click the link on predict field.</p>
<spring:url value="/dosearch" var="URL_DOSEARCH" />
<form action="${URL_DOSEARCH }" method="post">
  <div class="form_settings">
    <p>
	    <select name="logic1" style="width: 6em;">
	    	<option value="true" ${logic1==true?"selected=\"selected\"":"" }>AND</option>
	    	<option value="false" ${logic1==false?"selected=\"selected\"":"" }>NOT</option>
	    </select>
    	<input id="lnc" name="lnc" style="width: 10em; margin-left: 1em;" value="${lnc }" />
    	<label style="margin: 0 2em 0 1em; width: 16em; display: inline-block;">Lnc RNA</label>
    	<input type="radio" name="exact1" value="true" ${(empty exact1 or exact1==true)?"checked=\"checked\"":"" } /><label style="display: inline-block; margin-right: 1em;">exact</label>
    	<input type="radio" name="exact1" value="false" ${exact1==false?"checked=\"checked\"":"" } /><label>fuzzy</label>
    </p>
    <p>
	    <select name="logic2" style="width: 6em;">
	    	<option value="true" ${logic2==true?"selected=\"selected\"":"" }>AND</option>
	    	<option value="false" ${logic2==false?"selected=\"selected\"":"" }>NOT</option>
	    </select>
    	<input id="ef" name="ef" style="width: 10em; margin-left: 1em;" value="${ef }" />
    	<label style="margin: 0 2em 0 1em; width: 16em; display: inline-block;">Environmental factors</label>
    	<input type="radio" name="exact2" value="true" ${(empty exact2 or exact2==true)?"checked=\"checked\"":"" } /><label style="display: inline-block; margin-right: 1em;">exact</label>
    	<input type="radio" name="exact2" value="false" ${exact2==false?"checked=\"checked\"":"" } /><label>fuzzy</label>
    </p>
    <p>
	    <select name="logic3" style="width: 6em;">
	    	<option value="true" ${logic3==true?"selected=\"selected\"":"" }>AND</option>
	    	<option value="false" ${logic3==false?"selected=\"selected\"":"" }>NOT</option>
	    </select>
    	<select name="status" style="width: 11em; margin-left: 1em;" >
    		<option value="any" ${status=='any'?"selected=\"selected\"":"" }>any</option>
    		<option value="experiment" ${status=='experiment'?"selected=\"selected\"":"" }>experiment</option>
    		<option value="predict" ${status=='predict'?"selected=\"selected\"":"" }>predict</option>
    	</select>
    	<label style="margin: 0 2em 0 1em; width: 16em; display: inline-block;">Status</label>
    </p>
    <p style="padding-top: 15px"><span>&nbsp;</span><input class="submit" type="submit" name="contact_submitted" value="submit" /></p>
  </div>
</form>
<spring:url value="/resources/themes/cupertino/jquery-ui.min.css" var="URL_JQUI_CSS" />
<spring:url value="/javascripts/ui.jqgrid.css" var="URL_GRID_CSS" />
<style type="text/css">
@import "${URL_JQUI_CSS }";
@import "${URL_GRID_CSS }";
</style>
<spring:url value="/javascripts/jquery.min.js" var="URL_JQUERY" /><script type="text/javascript" src="${URL_JQUERY }"></script>
<spring:url value="/javascripts/jquery-ui-custom.min.js" var="URL_JQUERYUI" /><script type="text/javascript" src="${URL_JQUERYUI }"></script>
<spring:url value="/javascripts/jquery.jqGrid.src.js" var="URL_JQUERYGRID" /><script type="text/javascript" src="${URL_JQUERYGRID }"></script>
<spring:url value="/javascripts/jquery.layout.js" var="URL_JQUERYLAYOUT" /><script type="text/javascript" src="${URL_JQUERYLAYOUT }"></script>
<spring:url value="/javascripts/grid.locale-en.js" var="URL_GRIDLOCALE" /><script type="text/javascript" src="${URL_GRIDLOCALE }"></script>

<spring:url value="/lncRNAs" var="URL_AJAXLNC"></spring:url>
<spring:url value="/Efs" var="URL_AJAXEF"></spring:url>
<script type="text/javascript">
$(function(){
	$("#lnc").autocomplete({
		source: "${URL_AJAXLNC}",
		minLength: 1,
		select: function( event, ui ) {
			//do nothing
		}
	});
	
	$("#ef").autocomplete({
		source: "${URL_AJAXEF}",
		minLength: 1,
		select: function( event, ui ) {
			//do nothing
		}
	});
});
</script>

<c:if test="${not showresult }">
<div style="margin-top: 2em;">
<p>In the "Search" result, each entry has three items.</p>
<p style="text-indent:20px;">(1) LncRNA name</p>
<p style="text-indent:20px;">(2) Environmental factors name</p>
<p style="text-indent:20px;">(3) Status, experiment or predict.</p>
</div>
</c:if>
<c:if test="${showresult }">
<c:if test="${not empty search_result }">
<h2>Search Result</h2>
<table id="atable" style="width: 100%;">
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
<div id="ptable"></div>
<script type="text/javascript">
$(function(){tableToGrid('#atable', {
	height: 200,
   	pager: "#ptable",
   	rowNum: 0,
    viewrecords: true,
    sortable: false,
    cmTemplate: {sortable: false}
});});
</script>
</c:if>
<c:if test="${empty search_result }">
<h2>No Match Result.</h2>
</c:if>
</c:if>