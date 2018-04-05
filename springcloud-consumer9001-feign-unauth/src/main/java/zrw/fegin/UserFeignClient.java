package zrw.fegin;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import zrw.cloud.entity.User;
/*
 * @FeignClient的(name="provider")是一个任意的客户端名称，这里是提供者8002
 * */
@FeignClient(name="providerunsafe")
public interface UserFeignClient {
  @RequestMapping(value="/unsafe/{id}",method=RequestMethod.GET)
  public User findById(@PathVariable("id") long id);
}
