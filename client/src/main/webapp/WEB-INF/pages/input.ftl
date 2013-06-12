<#import "/spring.ftl" as spring />
<html>
<head>
    <link href="/resources/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body>
    <@spring.bind "availableTypes" />
    <@spring.bind "algorithms" />
	<form action="/generate" method="post">
		Vertices: <@spring.formInput "formBean.vertices" /> 
		<br /> 
		Edges: <@spring.formInput "formBean.edges" /> 
		<br />
		Graph type: <@spring.formSingleSelect "formBean.type", availableTypes, ""/> 
        <br />
        Algorithm: <@spring.formSingleSelect "formBean.algorithm", algorithms, ""/>
        <br />
        <button type="submit">Run</button>
    </form>
</body>
</html>