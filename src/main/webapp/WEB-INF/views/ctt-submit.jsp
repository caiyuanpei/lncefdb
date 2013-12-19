<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!-- insert the page content here -->
<h1>Submit</h1>
<h2>${substatus }</h2>
<p>LncEnvironmentDB provides submission page that allows other researchers to submit experimentally supported (or predicted) relationships between lncRNAs and environmental factors.</p>
<spring:url value="/submit" var="URL_DOSUBMIT" />
<form id="form1" action="${URL_DOSUBMIT }" method="post">
  <div class="form_settings">
     <p><span>Lnc RNA*</span><input class="contact" type="text" id="lnc" name="lnc" value="" /></p>
     <p><span>Environmental factors*</span><input class="contact" type="text" id="ef" name="ef" value="" /></p>
     <p><span>Status*</span>
       <select name="status" style="height: 2em; width: 8em;">
         <option value="predict">predict</option>
         <option value="experiment">experiment</option>
       </select>
     </p>
     <p><span>PubMed ID</span><input class="contact" type="text" name="pubmedid" value="" style="width: 15em;" /></p>
     <p><span>Email</span><input class="contact" type="text" name="email" value="" style="width: 25em;" /></p>
     <p><span>Description</span><textarea class="contact textarea" rows="8" cols="50" name="descrip"></textarea></p>
     <p style="padding-top: 15px"><span>&nbsp;</span><input class="submit" style="margin-left: 212px;" type="submit" name="contact_submitted" value="submit" /></p>
   </div>
</form>
<spring:url value="/resources/themes/cupertino/jquery-ui.min.css" var="URL_JQUI_CSS" />
<style type="text/css">
@import "${URL_JQUI_CSS }";
</style>
<spring:url value="/javascripts/jquery.min.js" var="URL_JQUERY" /><script type="text/javascript" src="${URL_JQUERY }"></script>
<spring:url value="/javascripts/jquery.form.js" var="URL_JQUERYFORM" /><script type="text/javascript" src="${URL_JQUERYFORM }"></script>
<spring:url value="/javascripts/jquery-ui-custom.min.js" var="URL_JQUERYUI" /><script type="text/javascript" src="${URL_JQUERYUI }"></script>

<spring:url value="/lncRNAs" var="URL_AJAXLNC"></spring:url>
<spring:url value="/Efs" var="URL_AJAXEF"></spring:url>
<script type="text/javascript">
$(function(){
	$("#form1").submit(function(evt){
		if( $.trim($("input[name='lnc']").val()) == "" ) {
			alert("Lnc RNA must not be empty!");
			evt.preventDefault();
			$("input[name='lnc']").focus();
			return;
		}
		
		if( $.trim($("input[name='ef']").val()) == "" ) {
			alert("Environmental factors must not be empty!");
			evt.preventDefault();
			$("input[name='ef']").focus();
			return;
		}
	});
	
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