<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<meta name="layout" content="main"/>
<title>Search Results</title>
</head>
<body>
  <div class="body">
  	<h1>Results</h1>
  	<p>
  		Searched ${totalDocuments} records for items matching <em>${term}</em>.
  		Found <strong>${documents.size()}</strong> hits.
  	</p>
  	<ul>
  		<g:each var="document" in="${documents}">
  			<li>${document.documentId}</li>
  		</g:each>
  	</ul>
  	<g:link action='search'>Search Again</g:link>
  
  </div>
</body>
</html>