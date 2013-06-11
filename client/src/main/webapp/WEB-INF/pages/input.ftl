<#import "/spring.ftl" as spring />
<html>
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
        <input type="submit" value="Run" />
    </form>
</body>
</html>