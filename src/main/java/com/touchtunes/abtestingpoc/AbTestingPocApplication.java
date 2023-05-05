package com.touchtunes.abtestingpoc;

import com.abtasty.flagship.main.Flagship;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class AbTestingPocApplication {
	@Value("${abtasty.envId}")
	private String envId;
	@Value("${abtasty.apiKey}")
	private String apiKey;

	public static void main(String[] args) {
		SpringApplication.run(AbTestingPocApplication.class, args);
	}

	@PostConstruct
	public void init() {
		Flagship.start(envId, apiKey);
	}

}
