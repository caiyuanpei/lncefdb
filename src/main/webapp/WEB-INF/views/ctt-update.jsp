<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!-- insert the page content here -->
<h1>Update Status</h1>
<p>LncEnvironmentDB provides update page that allows other researchers to change status(predict to experiment) LncRNA - Environmental factor relationships when make sure. Once approved by the submission review committee, the submitted record will be included in the database, and made available to the public in the coming release. LncEnvironmentDB will be updated bimonthly.</p>
<spring:url value="/doupdate" var="URL_DOUPDATE" />
<form id="form1" action="${URL_DOUPDATE }" method="post">
  <div class="form_settings">
     <p><span>Lnc RNA*</span><input readonly="readonly" class="contact" type="text" name="lnc" value="${lnc }" /></p>
     <p><span>Environmental factors*</span><input readonly="readonly" class="contact" type="text" name="ef" value="${ef }" /></p>
     <p><span>Status*</span>
       <select name="status" style="height: 2em; width: 8em;">
         <option value="experiment" selected="selected">experiment</option>
       </select>
     </p>
     <p><span>PubMed ID</span><input class="contact" type="text" name="pubmedid" value="" style="width: 15em;" /></p>
     <p><span>Email</span><input class="contact" type="text" name="email" value="" style="width: 25em;" /></p>
     <p><span>Description</span><textarea class="contact textarea" rows="8" cols="50" name="descrip"></textarea></p>
     <p style="padding-top: 15px"><span>&nbsp;</span><input class="submit" style="margin-left: 212px;" type="submit" name="contact_submitted" value="submit" /></p>
   </div>
</form>
<spring:url value="/javascripts/jquery.min.js" var="URL_JQUERY" /><script type="text/javascript" src="${URL_JQUERY }"></script>
<spring:url value="/javascripts/jquery.form.js" var="URL_JQUERYFORM" /><script type="text/javascript" src="${URL_JQUERYFORM }"></script>
<script type="text/javascript">
$(function(){
});
</script>