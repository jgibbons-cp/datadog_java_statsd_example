//sample code for sending statsd metrics to Datadog
package dogstatsdclient;

import com.timgroup.statsd.NonBlockingStatsDClientBuilder;
import com.timgroup.statsd.NonBlockingStatsDClient;
import com.timgroup.statsd.StatsDClient;

public class DogStatsdClient {

    public static void main(String[] args) throws Exception {

	//get Datadog agent IP from the system property in the Dockerfile
	String dd_agent_ip = System.getProperty("DD_AGENT_IP");

        StatsDClient Statsd = new NonBlockingStatsDClientBuilder()
            .prefix("statsd")
            .hostname(dd_agent_ip)
            .port(8125)
            .build();
        
        for (int i = 0; i < 10; i++) {
            Statsd.incrementCounter("example_metric.increment", new String[]{"environment:dev"});
            Statsd.decrementCounter("example_metric.decrement", new String[]{"environment:dev"});
            Statsd.count("example_metric.count", 2, new String[]{"environment:dev"});
	    System.out.println("Sleeping");
            Thread.sleep(5);
}
        System.exit(0);
    }
}
