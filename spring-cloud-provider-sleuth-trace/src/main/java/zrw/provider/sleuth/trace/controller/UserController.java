package zrw.provider.sleuth.trace.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import zrw.provider.sleuth.trace.entity.User;
import zrw.provider.sleuth.trace.repository.UserRepository;

@RestController
public class UserController {
  @Autowired
  UserRepository repository;
  
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
 
}
