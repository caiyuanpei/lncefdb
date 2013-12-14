<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%><%@ taglib prefix="spring" uri="http://www.springframework.org/tags" 
%><%@ taglib prefix="tiles"  uri="http://tiles.apache.org/tags-tiles" 
%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <title>Lnc - EF Database</title>
  <spring:url value="/resources/style/style-wide.css" var="URL_STYLE_CSS" /><link rel="stylesheet" type="text/css" href="${URL_STYLE_CSS }" />
</head>
<body>
  <div id="main">
    <div id="header">
      <div id="logo">
        <div id="logo_text">
          <!-- class="logo_colour", allows you to change the colour of the text -->
          <h1><a href="index.html">Lnc-Environment<span class="logo_colour">DB</span></a></h1>
          <h2>A Forecast DB for The Relationship Between Lnc And Environment.</h2>
        </div>
      </div>
      <div id="menubar"><tiles:insertAttribute name="menu" /></div>
    </div>
    <div id="content_header"></div>
	<div class="inner_copyright">Collect from <a href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a></div>
    <div id="site_content">
      <div id="content"><tiles:insertAttribute name="content" /></div>
    </div>
    <div id="content_footer"></div>
    <div id="footer">
      Copyright &copy; Harbin Medical University, 2014, All Rights Reserve.
    </div>
  </div>
</body>
</html>