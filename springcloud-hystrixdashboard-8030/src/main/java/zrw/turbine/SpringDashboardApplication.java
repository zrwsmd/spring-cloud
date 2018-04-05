package zrw.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;



@SpringBootApplication
@EnableHystrixDashboard
public class SpringDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDashboardApplication.class, args);
	}
}
