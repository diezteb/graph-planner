<html>
<head>
    <script src="/resources/sigma.min.js"></script>
    <script src="/resources/jquery-1.8.3.min.js"></script>
    <script src="/resources/sigma.parseGexf.js"></script>
</head>
<body>
    <h1>Results for job: ${planningJob.result.jobId}</h1>
    <h2>Execution statistics</h2>
    <dl>
        <dt>Time</dt><dd>${time}</dd>
        <dt>Memory</dt><dd>${memory}</dd>
        <dt>Path Length</dt><dd>${pathLength}</dd>
    </dl>

    <div id="result-canvas" style="width: 1000px; height: 600px; background: #cccccc">
    </div>

    <script type="text/javascript">
        var sigInst;
        function init() {
            // Instantiate sigma.js and customize rendering :
            sigInst = sigma.init(document.getElementById('result-canvas')).drawingProperties({
                defaultLabelColor: '#fff',
                defaultLabelSize: 14,
                defaultLabelBGColor: '#fff',
                defaultLabelHoverColor: '#000',
                defaultNodeColor: 'yellow',
                labelThreshold: 6,
                defaultEdgeType: 'curve',
                directed: 1,
                defaultEdgeArrow: 'target'
            }).graphProperties({
                        minNodeSize: 0.5,
                        maxNodeSize: 5,
                        minEdgeSize: 3,
                        maxEdgeSize: 3
                    }).mouseProperties({
                        maxRatio: 32
                    });

            // Parse a GEXF encoded file to fill the graph
            // (requires "sigma.parseGexf.js" to be included)
            sigInst.parseGexf('/results/${planningJob.result.jobId}/graph');

            var path = [${path}];
            sigInst.iterEdges(function(e){
                edgeId = e.source + '#' + e.target;
                if(path.indexOf(edgeId) != -1){
                    e.color = 'blue';
                }
            });
            // Draw the graph :
            sigInst.draw();
        }

        if (document.addEventListener) {
            document.addEventListener("DOMContentLoaded", init, false);
        } else {
            window.onload = init;
        }
    </script>
</body>
</html>
