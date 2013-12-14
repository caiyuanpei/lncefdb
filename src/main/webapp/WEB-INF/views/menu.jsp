<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<tiles:useAttribute name="current" id="crt" />
<ul id="menu">
  <li ${crt == "1"?"class=\"selected\"":""} ><spring:url value="/" var="URL_INDEX" /><a href="${URL_INDEX }" >Home</a></li>
  <li ${crt == "2"?"class=\"selected\"":""} ><spring:url value="/browse" var="URL_BROWSE" /><a href="${URL_BROWSE }" >Browse</a></li>
  <li ${crt == "3"?"class=\"selected\"":""} ><spring:url value="/search" var="URL_SEARCH" /><a href="${URL_SEARCH }" >Search</a></li>
  <li ${crt == "4"?"class=\"selected\"":""} ><spring:url value="/submit" var="URL_SUBMIT" /><a href="${URL_SUBMIT }" >Submit</a></li>
  <li ${crt == "5"?"class=\"selected\"":""} ><spring:url value="/download" var="URL_DOWNLOAD" /><a href="${URL_DOWNLOAD }" >Download</a></li>
</ul>