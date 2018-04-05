package zrw.zuul.controller;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.DeferredResult;

import rx.Observable;
import rx.Observer;
import zrw.zuul.entity.User;
import zrw.zuul.service.AggregationUnauthService;

/*

 * 访问的是auth(授权)的8000,8001和它对应的consumer9000
 * 
 * */

@RestController
public class AggregationauthController {

	private static final Logger log = LoggerFactory.getLogger(AggregationauthController.class);

	@Autowired
	AggregationUnauthService service;

	
	@GetMapping("/auth/aggregate/{id}")
	public DeferredResult<ConcurrentHashMap<String, User>> aggregate(@PathVariable long id) {
		Observable<ConcurrentHashMap<String, User>> result = this.aggregateObserable(id);
		return this.toDeferreresult(result);
	}

	public Observable<ConcurrentHashMap<String, User>> aggregateObserable(long id) {
		// 合并2个或多个Observable发射出的数据项，根据指定的函数变换它们
		return Observable.zip(this.service.getUserById(id), this.service.getmovieUserById(id),
				(user, movieUser) -> {
			ConcurrentHashMap<String, User> map = new ConcurrentHashMap<>();
			map.put("user", user);
			map.put("movieUser", movieUser);
			return map;
		}

		);

	}

	public DeferredResult<ConcurrentHashMap<String, User>> toDeferreresult(
			Observable<ConcurrentHashMap<String, User>> details) {
		DeferredResult<ConcurrentHashMap<String, User>> result = new DeferredResult<>();
		// 订阅
		details.subscribe(new Observer<ConcurrentHashMap<String, User>>() {

			@Override
			public void onCompleted() {
				log.info("完成");

			}

			@Override
			public void onError(Throwable e) {
				log.info("错误");

			}

			@Override
			public void onNext(ConcurrentHashMap<String, User> movieDetails) {
				result.setResult(movieDetails);

			}

		});
		return result;

	}

}
