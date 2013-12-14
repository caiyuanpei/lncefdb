<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!-- insert the page content here -->
<spring:url value="/javascripts/ui.jqgrid.css" var="URL_GRID_CSS" />
<spring:url value="/resources/themes/cupertino/jquery-ui.min.css" var="URL_JQUI_CSS" />
<style>
<!--
@import "${URL_GRID_CSS }";
@import "${URL_JQUI_CSS}";
#tree ul {background: none; margin: 0; padding: 2px; padding-left: 1em;}
#tree ul li {background: none; padding: 0; margin: 0;}
#gbox_table {width: 100%; height: 100%;}
-->
</style>
<h1>Browse</h1>
<p>You can browse the relationships by select node on tree.</p>
<table style="border-collapse: collapse; border: 1px solid #ccc; width: 100%; height: 40em; table-layout: fixed;">
  <tr>
    <td style="border: 1px solid #ccc; width: 30%;">
      <div id="tree" style="height: 100%; overflow: auto;"></div>
    </td>
    <td style="border: 1px solid #ccc; width: 70%;">
      <table id="table" style="height: 100%; width:100%;"></table>
      <div id="ptable"></div>
    </td>
  </tr>
</table>

<spring:url value="/javascripts/jquery.min.js" var="URL_JQUERY" /><script type="text/javascript" src="${URL_JQUERY }"></script>
<spring:url value="/javascripts/jquery.jstree.js" var="URL_JQUERYTREE" /><script type="text/javascript" src="${URL_JQUERYTREE }"></script>
<spring:url value="/javascripts/jquery.jqGrid.min.js" var="URL_JQUERYGRID" /><script type="text/javascript" src="${URL_JQUERYGRID }"></script>
<spring:url value="/javascripts/jquery.layout.js" var="URL_JQUERYLAYOUT" /><script type="text/javascript" src="${URL_JQUERYLAYOUT }"></script>
<spring:url value="/javascripts/jquery-ui-custom.min.js" var="URL_JQUERYUI" /><script type="text/javascript" src="${URL_JQUERYUI }"></script>
<spring:url value="/javascripts/grid.locale-en.js" var="URL_GRIDLOCALE" /><script type="text/javascript" src="${URL_GRIDLOCALE }"></script>

<spring:url value="/fetch-tree" var="URL_FETCHTREE" />
<spring:url value="/fetch-data" var="URL_FETCHDATA" />
<script type="text/javascript">
$("#tree").jstree({
	"plugins" : ["themes","json_data","ui"],
	"json_data" : { 
		"ajax" : {
			"url" : "${URL_FETCHTREE }",
			"data" : function (n) { 
				return { 
					"operation" : "get_children", 
					"id" : n.attr ? n.attr("id") : "__root",
					"grp" : n.attr ? n.attr("grp") : ""
				}; 
			}
		}
	}
}).bind("select_node.jstree", function (event, data) {
	
	if(data.rslt.obj.attr("grp") == "_lnc" || data.rslt.obj.attr("grp") == "_ef"){
		var grp = data.rslt.obj.attr("grp");
		var id = data.rslt.obj.attr("id");
		
		$("#table").jqGrid("setGridParam",{url:'${URL_FETCHDATA }?grp='+encodeURIComponent(grp)+'&id='+encodeURIComponent(id)}).trigger("reloadGrid");

	}
});


jQuery("#table").jqGrid({
   	//url:'${URL_FETCHDATA }?grp='+grp+'&id='+id,
   	url:'',
	datatype: "json",
   	colNames:['Lnc RNA','Environmental factors', 'P value (FDR correction)', 'Status'],
   	colModel:[
   		{name:'LNC',index:'id', width:150},
   		{name:'EF',index:'ef', width:150},
   		{name:'FDR',index:'fdr desc', width:150, align:"right"},
   		{name:'STATUS',index:'amount', width:130, align:"right"}	
   	],
   	height: 500,
   	pager: "#ptable",
   	rowNum: 300,
	rowList: [300],
    viewrecords: true,
    sortorder: "desc",
    caption:"Relations"
});

</script>