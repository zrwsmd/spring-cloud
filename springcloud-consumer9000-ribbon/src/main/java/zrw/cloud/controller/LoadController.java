package zrw.cloud.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;




import zrw.cloud.entity.User;

/*
 * 这个控制器是ribbon整合eureka的实例，需要在SpringConsumer9000Application.java中
 * 添加@LoadBalanced注解实现负载均衡
 * */
@RestController
public class LoadController {
	private String  GET_URL="http://localhost:8000/";
	private String LIST_URL="http://localhost:8000/list";
	private static final Logger log=LoggerFactory.getLogger(LoadController.class);
  @Autowired
  RestTemplate template;
  @Autowired
  HttpHeaders header;
  
  @Autowired
  DiscoveryClient client;
  @Autowired
  private LoadBalancerClient loadBalancedClient;
  
  @GetMapping("/loadbalance/{id}")
  public User findById(@PathVariable Long id)
  {
	  /*
	   * *30,31行代码是提供者在进行了安全认证时，消费者需要改变的内容,32行是没有前的代码,在 application.yml中将prefer-ip-address: false
	  /才能获得hostname，否则会报错 no resourece avaible for provider,在目前情况下只有
	   * 访问端口是8000的才不会报401(没有授权)的错误，因为在SpringConsumer9000Application
	   * 中配置的是8000端口的provider的用户名密码，即http://localhost:9000/loadbalance/1
	   * 这个地址只有ribbon在负载均衡给8000提供者的时候才能访问,http://provider/的provider是8000,8001
	   * 提供者共同的hostname
	   * 
	   * 
	   * 
	 */
	return this.template.exchange("http://provider/"+id, HttpMethod.GET,
			new HttpEntity<Object>(this.header), User.class).getBody();
	//return this.template.getForObject("http://provider/"+id, User.class);

	  
  }
  
  @SuppressWarnings("unchecked")
@GetMapping("user/all")
  //http://localhost:9000/user/all,consumer端口是9000,provider端口是8000
  public List<User> findAll()
  {
	
	return this.template.exchange(LIST_URL, 
			HttpMethod.GET, new HttpEntity<Object>(this.header), 
			List.class).getBody();

	  
  }
 
  
  //负载均衡实例
  /*
  zrw.cloud.controller.LoadController : provider:zrw:8000
  zrw.cloud.controller.LoadController: provider:zrw:8001
 
  交替打印，说明请求被均匀的分布到了3个微服务上面，证明实现了负载均衡
   */
  @GetMapping("/log-balance")
  public void logUserInstance(){
	  ServiceInstance serviceInstance = this.loadBalancedClient.choose("provider");
	  LoadController.log.info("{}:{}:{}",
			  serviceInstance.getServiceId(),serviceInstance.getHost(),
			  serviceInstance.getPort());
  }
//Eureka元数据实例
  //http://localhost:9000/showmetadata
  @GetMapping("/showmetadata")
  public List<ServiceInstance> showInfo()
  {
	  return this.client.getInstances("provider8000");
  }
  
}
