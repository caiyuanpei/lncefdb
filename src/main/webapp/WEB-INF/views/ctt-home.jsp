<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- insert the page content here -->
<h1>Welcome to LncEnvironmentDB Websit.</h1>
<p>Complex traits of an organism are associated with genetic factors (GFs) and environmental factors (EFs) as well as interactions between them. The majority of human complex diseases, such as cancer, heart disease and diabetes, are caused by the complex interplay between GFs and EFs. LncEnvironmentDB is a user-friendly web-based database which aims to provide comprehensive resource platform for lncRNAs and environmental factors. LncEnvironmentDB is a useful bioinformatic resource for the analysis of the relationships of lncRNAs and EFs, and will facilitate our understanding of the contribution of environment-lncRNA interaction to environmental human disease.</p>
<ul>
  <li>Browse the database;</li>
  <li>Search the database;</li>
  <li>Submit new entries to the database;</li>
  <li>Download all data in the database.</li>
</ul>
<h2>Statistics</h2>
<p>(Last statistics at ${today })</p>
<ul>
  <li>Number of LncRNAs: ${(empty lnc)?"Unknown":lnc }</li>
  <li>Number of Environmental factors: ${(empty ef)?"Unknown":ef }</li>
  <li>Number of experimentally supported LncRNAs-EFs: ${(empty experiment)?"Unknown":experiment }</li>
  <li>Number of predicted LncRNAs-EFs: ${(empty predict)?"Unknown":predict }</li>
</ul>
<h2>Reference</h2>

<p><%-- Jie Sun1, †, Yuanpei Cai2, †, Zhenzhen Wang1, jiahui Zhang1, Hongbo Shi1, Dapeng Hao1,* and Meng Zhou1,*, (2013)<br />--%>
LncEnvironmentDB: a reference database of putative associa-tions between lncRNA and environmental factors.</p>
