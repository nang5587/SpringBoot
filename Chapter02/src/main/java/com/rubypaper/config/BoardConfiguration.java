package com.rubypaper.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rubypaper.jdbc.util.JDBCConnectionManager;

//@Configuration
public class BoardConfiguration {

//	@Bean
	JDBCConnectionManager getJDBCConnectionManager() {
		JDBCConnectionManager manager = new JDBCConnectionManager();
		manager.setDriverClass("com.mysql.cj.jdbc.Drvier");
		manager.setUrl("jdbc:mysql://localhost:3306/world");
		manager.setUsername("root");
		manager.setPassword("tiger");
		return manager;
	}
}
