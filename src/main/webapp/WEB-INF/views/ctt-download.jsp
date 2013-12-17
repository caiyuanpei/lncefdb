<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<s:url value="/download" var="URL_D" />
<!-- insert the page content here -->
<h1>Download</h1>
<p>Our site provided the LncRNA list, Environmental Factors list, and the fdr map files for advanced researches. You can just click the follow links to download. </p>
<p>nks in the footer.</p>
<h2>1. LncRNA list</h2>
  <a style="display: inline-block; margin-right: 1em; cursor: pointer;" href="${URL_D }/lnc/csv">Download CSV</a>
  <a style="display: inline-block; margin-right: 0em; cursor: pointer;" href="${URL_D }/lnc/xls">Download EXCEL</a>
<h2>2. Environmental Factors list</h2>
  <a style="display: inline-block; margin-right: 1em; cursor: pointer;" href="${URL_D }/ef/csv">Download CSV</a>
  <a style="display: inline-block; margin-right: 0em; cursor: pointer;" href="${URL_D }/ef/xls">Download EXCEL</a>
<h2>3. lncRNA-EF fdr table</h2>
<p>Experimentally Supported LncRNAs-EFs:<br />
  <a style="display: inline-block; margin-right: 1em; cursor: pointer;" href="${URL_D }/experiment/csv">Download CSV</a>
  <a style="display: inline-block; margin-right: 1em; cursor: pointer;" href="${URL_D }/experiment/tsv">Download TSV</a>
  <a style="display: inline-block; margin-right: 0em; cursor: pointer;" href="${URL_D }/experiment/xls">Download EXCEL</a>
</p>
<p>Predicted LncRNAs-EFs:<br />
  <a style="display: inline-block; margin-right: 1em; cursor: pointer;" href="${URL_D }/predict/csv">Download CSV</a>
  <a style="display: inline-block; margin-right: 1em; cursor: pointer;" href="${URL_D }/predict/tsv">Download TSV</a>
  <a style="display: inline-block; margin-right: 0em; cursor: pointer;" href="${URL_D }/predict/xls">Download EXCEL</a>
</p>