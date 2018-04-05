package org.spring.cloud.provider.sleuth.trace;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;







@SpringBootApplication
@EnableEurekaClient

public class Zipkin_sleuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(Zipkin_sleuthApplication.class, args);
	}
	
	//实现负载均衡
		
		@Bean
		public RestTemplate restTemplate(){
			return new RestTemplate();
		}
}
