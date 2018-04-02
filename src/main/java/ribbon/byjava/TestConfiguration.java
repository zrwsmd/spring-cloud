package ribbon.byjava;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
/*
 * 使用RibbonClient,为特定的name的Ribbon Client自定义配置
 * configuration=RibbonConfiguration.class指定配置类
 * */
@Configuration
@RibbonClient(name="provider",configuration=RibbonConfiguration.class)
public class TestConfiguration {
   @Bean
   public IRule ribbonRule(){
	   //负载均衡规则，改为随机
	   return new RandomRule();
   }
}
