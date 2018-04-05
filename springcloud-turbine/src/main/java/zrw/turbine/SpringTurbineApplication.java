package zrw.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableTurbine
public class SpringTurbineApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringTurbineApplication.class, args);
	}
}
