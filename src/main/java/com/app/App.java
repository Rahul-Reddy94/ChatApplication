package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

@EnableAutoConfiguration
@ComponentScan
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);	
	}
	
	@Bean
	public TilesConfigurer tilesConfig() {
		TilesConfigurer  tilesConfigurer = new TilesConfigurer();
		String[] defs = {"/WEB-INF/tilesBean.xml"};
		tilesConfigurer.setDefinitions(defs);
		return tilesConfigurer;
	}
	
	@Bean
	public UrlBasedViewResolver tilesViewResolver() {
		UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
		viewResolver.setViewClass(TilesView.class);
		return viewResolver;
	}

	
}


