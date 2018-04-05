package zrw.zuul.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import rx.Observable;
import zrw.zuul.entity.User;
/*
 * 访问的是auth(授权)的8000,8001和它对应的consumer9000
 * 为了测试稍微简单，8000和8001的用户名密码都是zrw,8000,在此密码不做区分了
 * */
@Service
public class AggregationauthService {
	@Autowired
	RestTemplate template;
	@Autowired
	HttpHeaders headers;
	@HystrixCommand(fallbackMethod = "fallback")
	public Observable<User> getUserById(long id) {
		// 创建一个被观察者
		return Observable.create(observer -> {
		
//			User user=this.template.exchange("http://provider/", HttpMethod.GET,
//					new HttpEntity<Object>(this.header), User.class,id).getBody();
			// 请求用户微服务的{id}端点
			//http://zrw:8001@localhost:8001/1   加密认证，用户名密码分别是zrw,8000
			User user = template.getForObject("http://provider/{id}", User.class, id);
			
			//User user2 = template.getForObject("http://zrw:8001@provider/{id}", User.class, id);
			observer.onNext(user);
			//observer.onNext(user2);
			observer.onCompleted();
		}

		);
	}

	@HystrixCommand(fallbackMethod = "fallback")
	public Observable<User> getmovieUserById(long id) {
		// 创建一个被观察者
		return Observable.create(observer -> {
			// 请求电影微服务的loadbalance/{id}端点
//			this.template.exchange("http://provider/"+id, HttpMethod.GET,
//					new HttpEntity<Object>(this.header), User.class).getBody();
			User movieUser = template.getForObject("http://consumer9000/loadbalance/{id}", User.class, id);
			observer.onNext(movieUser);
			observer.onCompleted();
		}

		);
	}

	public User fallback(long id) {
		User user = new User();
		user.setId(-1);
		return user;
	}
}
