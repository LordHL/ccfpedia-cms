package org.ccf.ccfpedia.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.springframework.boot.SpringApplication.*;

@SpringBootApplication(scanBasePackages = "org.ccf.ccfpedia.cms")
public class CcfpediaCmsApplication {
	public static void main(String[] args) {
		run(CcfpediaCmsApplication.class, args);
	}
}
