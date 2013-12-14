<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!-- insert the page content here -->
<h1>Search</h1>
<p>Search relations by follow conditions:</p>
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
<p> In the "Search" result, each entry has ten items.</p>
<p style="text-indent:20px;">(1) miRNA gene - the annotated miRNA gene name based on miRBase Release 17.</p>
<p style="text-indent:20px;">(2) miRNA product - the annotated miRNA product name based on miRBase Release 17.</p>
<p style="text-indent:20px;">(3) miRNA product original - the miRNA gene product reported in the original reference.</p>
<p style="text-indent:20px;">(4) phenotype - the annotated phenotype reported in the original reference.</p>
<p style="text-indent:20px;">(5) environmental factor (EF) - the annotated environmental factor reported in the original reference.</p>
<p style="text-indent:20px;">(6) Condition of EF - the condition environmental factor. This item indicates how the environmental factor acts on the experimental objects.</p>
<p style="text-indent:20px;">(7) Sample - the samples treated by the environmental factor.</p>
<p style="text-indent:20px;">(8) Species - the species selected in the experiment</p>
<p style="text-indent:20px;">(9) Evidence - this item indicates the detailed association result among the miRNA, environmental factor, and phenotype. </p>
<p style="text-indent:20px;">(10) PubMed ID - the reference ID in PubMed database.</p>
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
    <td>${r.status }</td>
  </tr>
  </c:forEach>
</table>
</c:if>