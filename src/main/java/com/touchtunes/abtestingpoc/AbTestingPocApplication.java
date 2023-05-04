package com.touchtunes.abtestingpoc;

import com.abtasty.flagship.main.Flagship;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class AbTestingPocApplication {

	private String envId = "cd7hgoegmgl01h6i6cqg";
	private String apiKey = "RRSDFSFnMBPQgSGgVgbmoBrqegUKqROkMjbBzkHb";

	public static void main(String[] args) {
		SpringApplication.run(AbTestingPocApplication.class, args);
	}

	@PostConstruct
	public void init() {
		Flagship.start(envId, apiKey);
	}


}
