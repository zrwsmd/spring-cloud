package zrw.cloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import zrw.cloud.entity.User;
import zrw.cloud.repository.UserRepository;

@RestController
public class UserController {
  @Autowired
  UserRepository repository;
  @Autowired
  RestTemplate template;
  @GetMapping("/{id}")
  public User findById(@PathVariable Long id)
  {
//	 List<User> list = repository.findAll();
//	 for (User user : list) {
//		System.out.println(user.getName());
//	}
	  User user=this.repository.findOne(id);
	  return user;
	
  }
  @GetMapping("/list")
  public List<User>fingAll(){
	  List<User> list = this.repository.findAll();
	  return list;
  }
  @GetMapping("/zrw/{id}")
  public User findById2(@PathVariable Long id)
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
//	return this.template.exchange("http://provider/"+id, HttpMethod.GET,
//			new HttpEntity<Object>(this.header), User.class).getBody();
	return this.template.getForObject("http://localhost:9410/"+id, User.class);

	  
  }
}
