<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%><%@ taglib prefix="spring" uri="http://www.springframework.org/tags" 
%><%@ taglib prefix="tiles"  uri="http://tiles.apache.org/tags-tiles" 
%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <title>LncEnvironmentDB</title>
  <spring:url value="/resources/style/style.css" var="URL_STYLE_CSS" /><link rel="stylesheet" type="text/css" href="${URL_STYLE_CSS }" />
</head>
<body>
  <div id="main">
    <div id="header">
      <div id="logo">
        <div id="logo_text">
          <!-- class="logo_colour", allows you to change the colour of the text -->
          <h1><a href="index.html">LncEnvironment<span class="logo_colour">DB</span></a></h1>
          <h2>A reference database of putative associations between lncRNA and environmental factors.</h2>
        </div>
      </div>
      <div id="menubar"><tiles:insertAttribute name="menu" /></div>
    </div>
    <div id="content_header"></div>
	<div class="inner_copyright">Collect from <a href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a></div>
    <div id="site_content">
      <div class="sidebar">
        <!-- insert your sidebar items here -->
        <h3>Latest News</h3>
        <h4>Website Launched</h4>
        <h5>December 20th, 2013</h5>
        <p>First version of our site has been launched. It provided browse, search, submit and download functions.</p>
        <p></p>
        <h3>Useful Links</h3>
        <ul>
          <spring:url value="/resources/logos/ctdlogo_sm.v13237.png" var="LOGO_CTD" />
          <li><a href="http://ctdbase.org/"><img src="${LOGO_CTD }" alt="http://ctdbase.org/" width="90" height="37" /></a></li>
          <spring:url value="/resources/logos/starBase.png" var="LOGO_SB" />
          <li><a href="http://starbase.sysu.edu.cn/"><img src="${LOGO_SB }" alt="http://ctdbase.org/" width="100" height="37" /></a></li>
          <li><a href="http://202.38.126.151/hmdd/tools/miren.html">miREnvironment</a></li>
          <li><a href="http://www.drugbank.ca/">DrugBank</a></li>
        </ul>
        <h3>Contact US</h3>
        <p>biofomeng@hotmail.com</p>
      </div>
      <div id="content"><tiles:insertAttribute name="content" /></div>
    </div>
    <div id="content_footer"></div>
    <div id="footer">
      Copyright &copy; Harbin Medical University, 2014, All Rights Reserve.
    </div>
  </div>
</body>
</html>