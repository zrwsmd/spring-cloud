package zrw.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaSpringBootApplication {
  public static void main(String[] args) {
	SpringApplication.run(EurekaSpringBootApplication.class, args);
}
}
