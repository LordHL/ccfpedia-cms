package org.ccf.ccfpedia.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.ccf.ccfpedia.cms")
public class CcfpediaCmsApplication {
	public static void main(String[] args) {
		SpringApplication.run(CcfpediaCmsApplication.class, args);
	}
}
