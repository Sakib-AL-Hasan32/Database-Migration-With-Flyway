package com.db_migration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DatabaseMigrationWithFlywayApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatabaseMigrationWithFlywayApplication.class, args);
	}

}
