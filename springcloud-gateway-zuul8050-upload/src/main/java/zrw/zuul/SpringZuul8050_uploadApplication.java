package zrw.zuul;

import java.nio.charset.Charset;
import java.util.Base64;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.ConditionalOnEnabledHealthIndicator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;




@SpringBootApplication
@EnableEurekaClient

public class SpringZuul8050_uploadApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringZuul8050_uploadApplication.class, args);
	}
//	//实现负载均衡
//		@LoadBalanced
//		@Bean
//		public RestTemplate restTemplate(){
//			return new RestTemplate();
//		}
//	//安全校验的代码
//		@Bean
//		public HttpHeaders getHeaders(){
//			HttpHeaders headers=new HttpHeaders();
//			String auth="zrw:8000";  //provider的安全认证
//			byte[] encode = Base64.getEncoder().encode(auth.getBytes(Charset.forName("US-ASCII")));
//			String authHeader="Basic "+new String(encode);
//			headers.set("Authorization", authHeader);
//			return headers;
//			
//			
//		}

}
