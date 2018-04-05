package zrw.provider.sleuth.trace;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;







@SpringBootApplication
@EnableEurekaClient

public class Zipkin_sleuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(Zipkin_sleuthApplication.class, args);
	}
	

}
