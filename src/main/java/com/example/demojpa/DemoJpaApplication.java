package com.example.demojpa;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.demojpa.model.Employee;
import com.example.demojpa.repo.EmployeeRepository;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
@SuppressWarnings("unused")
public class DemoJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoJpaApplication.class, args);
	}

	private static void log(String message, Object... arguments) {

		message = String.valueOf(message);
		message = message.endsWith("%n") ? message : message + "%n";

		System.err.printf(message, arguments);
	}

	@Bean
	ApplicationRunner runner(EmployeeRepository repository, JdbcTemplate template) {

		return args -> {

			Employee jonDoe = new Employee(1L, "Jon Doe", 45);

			log("New Employee is [%s]%n", jonDoe);

			assertThat(jonDoe).isNotNull();
			assertThat(jonDoe.getAge()).isEqualTo(45);
			assertThat(jonDoe.getId()).isEqualTo(1L);
			assertThat(jonDoe.getName()).isEqualTo("Jon Doe");

			Employee savedJonDoe = repository.save(jonDoe);

			log("Saved Employee is [%s]%n", savedJonDoe);

			assertThat(savedJonDoe).isEqualTo(jonDoe);

			template.execute("UPDATE employees SET age = null WHERE name = 'Jon Doe'");

			Employee loadedJonDoe = repository.findById(jonDoe.getId()).orElse(null);

			log("Loaded Employee is [%s]", loadedJonDoe);

			assertThat(loadedJonDoe).isEqualTo(jonDoe);
		};
	}
}
