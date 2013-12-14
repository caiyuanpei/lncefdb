<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!-- insert the page content here -->
<h1>Submit New Term</h1>
<h2>${substatus }</h2>
<p>Submit ~~~~~~~~~~~~~~~:</p>
<spring:url value="/submit" var="URL_DOSUBMIT" />
<form action="${URL_DOSUBMIT }" method="post">
  <div class="form_settings">
     <p><span>Lnc RNA</span><input class="contact" type="text" name="lnc" value="" /></p>
     <p><span>Environmental factors</span><input class="contact" type="text" name="ef" value="" /></p>
     <p><span>Status</span>
       <select name="status" style="height: 2em; width: 8em;">
         <option value="predict">predict</option>
         <option value="experiment">experiment</option>
       </select>
     </p>
     <p><span>Email</span><input class="contact" type="text" name="email" value="" style="width: 25em;" /></p>
     <p><span>Description</span><textarea class="contact textarea" rows="8" cols="50" name="descrip"></textarea></p>
     <p style="padding-top: 15px"><span>&nbsp;</span><input class="submit" style="margin-left: 212px;" type="submit" name="contact_submitted" value="submit" /></p>
   </div>
</form>
<div style="margin-top: 2em;">
<p> In the "Submit" result, each entry has ten items.</p>
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
