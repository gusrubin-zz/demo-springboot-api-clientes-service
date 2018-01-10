/**
* Classe da aplicação, responsável por iniciar o container Springboot
* @version 1.0
* @author Gustavo Rubin
*/

package com.gusrubin.proofs.clients.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages={"com.gusrubin.proofs.clients"})
@EnableJpaRepositories("com.gusrubin.proofs.clients.infra")
@EntityScan("com.gusrubin.proofs.clients.domain")
public class ApiClientsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiClientsServiceApplication.class, args);
	}
}
