package zrw.cloud.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import zrw.cloud.entity.User;

/*
 * 这个控制器是hystrix熔断器的使用，findById方法访问http://providerunsafe，即provider为8002的端口微服务，这个微服务不需要安全认证
 * 所以使用template.getForObject而不是template.exchange，当8002的微服务关闭后，进入了回退方法，
 * 调用了 findByIdFallBack()方法，只需要在findById方法加
 * 注解@HystrixCommand(fallbackMethod = "findByIdFallBack")，同时启动类加上
 * @EnableHystrix或者@EnableCircuitBreaker
 * */
@RestController
public class HystrixController {

	private static final Logger log = LoggerFactory.getLogger(HystrixController.class);

	@Autowired
	RestTemplate template;

	 @Autowired
	 private LoadBalancerClient loadBalancedClient;
	
	@HystrixCommand(fallbackMethod ="findByIdFallBack")
	@GetMapping("/hystrix/{id}")
	public User findById(@PathVariable Long id) {

		// return this.template.exchange("http://provider/"+id, HttpMethod.GET,
		// new HttpEntity<Object>(this.header), User.class).getBody();
		return this.template.getForObject("http://providerunsafe/" + id, User.class);

	}

	public User findByIdFallBack(Long id) {
		User user = new User();
		user.setId(-1);
		user.setAge(null);
		user.setUsername(null);
		user.setName("默认用户");
		user.setBalance(null);
		return user;

	}
	//观察控制台打印日志
	@GetMapping("/log-balance")
	  public void logUserInstance(){
		  ServiceInstance serviceInstance = this.loadBalancedClient.choose("providerunsafe");
		  HystrixController.log.info("{}:{}:{}",
				  serviceInstance.getServiceId(),serviceInstance.getHost(),
				  serviceInstance.getPort());
	  }
}
