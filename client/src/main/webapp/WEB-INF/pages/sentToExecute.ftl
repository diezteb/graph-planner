<#import "/spring.ftl" as spring />
<html>
<head>
    <link href="/resources/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body>
    <h5>${message.status}</h5>
    <span>Waiting for results to arrive...</span>

    <script type="text/javascript">
        function init() {
            setInterval(function(){
                ajaxCall = new XMLHttpRequest();
                ajaxCall.open('GET', '/results/${message.jobId}/check', false);
                ajaxCall.send();
                result = ajaxCall.responseText;
                if(result == 'true'){
                    window.location.href = "/results/${message.jobId}";
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