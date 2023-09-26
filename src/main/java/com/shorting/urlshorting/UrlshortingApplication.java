package com.shorting.urlshorting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*@Author lokesh
 * the springboot application is created to generate the qrcode for the url and to shorten the url.
*/
@SpringBootApplication
@EnableAutoConfiguration
public class UrlshortingApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlshortingApplication.class, args);
	}

}
