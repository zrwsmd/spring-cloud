package zrw.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;

@SpringBootApplication
@EnableTurbineStream
public class SpringTurbineRabbitMQApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringTurbineRabbitMQApplication.class, args);
	}
}
