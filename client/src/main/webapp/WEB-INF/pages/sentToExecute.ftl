<#import "/spring.ftl" as spring />
<html>
<body>
    <h5>${message.status}</h5>
    <span>Waiting for results to arrive...</span>
    <a href="/results/${message.jobId}">Results</a>
</body>
</html>