<!DOCTYPE html>
<html>
<head>
    <script src="/resources/sigma.min.js"></script>
    <script src="/resources/jquery-1.8.3.min.js"></script>
    <script src="/resources/sigma.parseGexf.js"></script>
    <script src="/resources/sigma.forceatlas.js"></script>
    <script src="/resources/sigma.forceatlas.js"></script>
    <link href="/resources/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body>
    <h2>Execution statistics</h2>
    <dl>
        <dt>Time</dt><dd>${time} ms</dd>
        <dt>Memory</dt><dd>${memory} B</dd>
        <dt>Path Length</dt><dd>${pathLength}</dd>
    </dl>

    <button class="btn" id="show-visualization">Show visualization</button>

    <div id="graph-container" style="display: none">
        <div id="result-canvas" style="width: 100%; height: 600px; background: #cccccc; ">
        </div>
        <div class="buttons-container">
            <button class="btn" id="stop-layout">Start layout</button>
            <button class="btn" id="rescale-graph">Rescale Graph</button>
        </div>
    </div>

    <script type="text/javascript">
        var sigInst;

        function showGraph(){
            // Instantiate sigma.js and customize rendering :
            sigInst = sigma.init(document.getElementById('result-canvas')).drawingProperties({
                defaultLabelColor: '#fff',
                defaultLabelSize: 14,
                defaultLabelBGColor: '#fff',
                defaultLabelHoverColor: '#000',
                defaultNodeColor: 'yellow',
                labelThreshold: 5,
                defaultEdgeType: 'curve',
                directed: 1,
                defaultEdgeArrow: 'target'
            }).graphProperties({
                        minNodeSize: 3,
                        maxNodeSize: 10,
                        minEdgeSize: 3,
                        maxEdgeSize: 3,
                        arrowRatio: 3
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

            var isRunning = false;
            document.getElementById('stop-layout').addEventListener('click', function () {
                if (isRunning) {
                    isRunning = false;
                    sigInst.stopForceAtlas2();
                    document.getElementById('stop-layout').childNodes[0].nodeValue = 'Start Layout';
                } else {
                    isRunning = true;
                    sigInst.startForceAtlas2();
                    document.getElementById('stop-layout').childNodes[0].nodeValue = 'Stop Layout';
                }
            }, true);
            document.getElementById('rescale-graph').addEventListener('click', function () {
                sigInst.position(0, 0, 1).draw();
            }, true);

            // Draw the graph :
            sigInst.draw();
        }

        function init() {
            document.getElementById('show-visualization').addEventListener('click', function () {
                var graphContainer = document.getElementById('graph-container');
                if(graphContainer.style.display == 'block'){
                    graphContainer.style.display = 'none';
                    document.getElementById('result-canvas').innerHTML = '';
                }else{
                    graphContainer.style.display = 'block';
                    showGraph();
                }
            }, true);
        }

        if (document.addEventListener) {
            document.addEventListener("DOMContentLoaded", init, false);
        } else {
            window.onload = init;
        }
    </script>
</body>
</html>
