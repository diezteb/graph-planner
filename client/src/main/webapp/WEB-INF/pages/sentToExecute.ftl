<#import "/spring.ftl" as spring />
<html>
<head>
    <link href="/resources/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body>
    <#if message = "OK">
        <h5>Planning task scheduled for execution</h5>
        <span>Waiting for results to arrive...</span>
    <#else>
        <h1>Graph type unsupported by chosen algorithm</h1>
    </#if>

    <script type="text/javascript">
        function init() {
            setInterval(function(){
                ajaxCall = new XMLHttpRequest();
                ajaxCall.open('GET', '/results/${jobId}/check', false);
                ajaxCall.send();
                result = ajaxCall.responseText;
                if(result == 'true'){
                    window.location.href = "/results/${jobId}";
                }
            },1000);
        }

        if (document.addEventListener) {
            document.addEventListener("DOMContentLoaded", init, false);
        } else {
            window.onload = init;
        }
    </script>
</body>
</html>