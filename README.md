# Datadog Java StatsD Example
Sample code to send metrics via statsd from Java in Docker to Datadog  
  
The base setup is based on 
[DataDog/java-dogstatsd-client](https://github.com/DataDog/java-dogstatsd-client)  
    
Assumptions and Note:  

1. The [Datadog agent](https://docs.datadoghq.com/agent/docker/?tab=standard)
 is running in a container on the host.  
 
1. There is a valid Datadog account available to send metrics    
  
1.  This was tested on Ubuntu Ubuntu 18.04.4 LTS  
  
Files:  
  
1. Dockerfile - to create the container where the Java code
will execute.  
  
1. pom.xml - the pom file that includes the java-dogstatsd-client dependency 
from [DataDog/java-dogstatsd-client](https://github.com/DataDog/java-dogstatsd-client#installation)
  
1. /src/main/java/dogstatsdclient/DogStatsdClient.java - the 
sample Java code to send StatsD metrics to Datadog  

Configuration:  
  
1. Execute ```docker ps``` and grab the container ID of the Datadog agent   
  
1. Execute ```docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' <container_id>```
to get the IP of the container running the Datadog agent.  
  
1. In the Dockerfile change <IP_OF_DD_AGENT_CONTAINER> to the ip in 
step 2.  
  
1. Get your Datadog API key from the [Datadog UI](https://app.datadoghq.com/account/settings#api)  
    
Execution:  
  
1. Execute ```mvn compile && mvn package && docker build -t my-java-app .```
 to compile and package the Java application and build the 
Docker image.  
  
1. After replacing <DATADOG_API_KEY> with that from step 4 above execute ```docker run -it --rm -v /var/run/docker.sock:/var/run/docker.sock:ro               -v /proc/:/host/proc/:ro               -v /sys/fs/cgroup/:/host/sys/fs/cgroup:ro               -e DD_API_KEY="<DATADOG_API_KEY>" -e DD_DOGSTATSD_NON_LOCAL_TRAFFIC=true --name my-running-app my-java-app```
 to run the application.  
  
1. Go [here](https://app.datadoghq.com/metric/summary) and type example_metric to view the metrics in Datadog or [here](https://app.datadoghq.com/metric/explorer) to see them visualized.  
  
  



