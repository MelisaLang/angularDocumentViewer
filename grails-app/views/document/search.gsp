<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<meta name="layout" content="main"/>
<title>Search DocViewer</title>
</head>
<body>
  <div class="body">
  	<formset>
  		<legend>Search for Documents</legend>
  		<g:form action="results">
  			<label for="documentId">Document ID</label>
  			<g:textField name="documentId" />
  			<g:submitButton name="search" value="Search"/>
  		</g:form>
  	</formset>
  
  </div>
</body>
</html>