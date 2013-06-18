Authors: Stanisław Podgórski, Paweł Kaczmarczyk

Prerequisites:
 - Java 1.7
 - Maven (application requires Maven both for build process *and* for running)
 - Applicaton requires that Maven /bin directory is in system path environmental variable (if typing "mvn" in console triggers maven then it is ok)
 
Building:
 - to build application you need to get into main application directory and type:
		mvn clean install
 Maven should collect all dependencies and build application
 
Running:
 - Planning Service:
	- in order to run planning service you need to go inside /core directory (this is actually compulsory, since application itself depends a bit on current-working-directory and thus it *has* to be started directly from /core directory) and type:
		mvn exec:java
	This should start Planning Service and publish SOAP WebService, which will be indicated by appropriate logging information

 - Client Application: 
	- in order to run client application you simply need to copy client.war archive located in /client/target (after successful build) to web container (eg. tomcat) or application server (eg. glassfish) and start server
	
Using:
Client application main page will display simple form to specify graph parameters. After generation, this graph will be sent to Planning Service for processing. While it is being processed client application is waiting for results and will be redirected automatically once results are fetched.
 