package zrw.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableDiscoveryClient
@SpringBootApplication
public class SpringProvider8000Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringProvider8000Application.class, args);
	}
}
