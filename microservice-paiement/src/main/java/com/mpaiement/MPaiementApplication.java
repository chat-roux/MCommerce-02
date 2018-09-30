package com.mpaiement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableConfigurationProperties
@EnableEurekaClient
@EnableFeignClients(basePackages="com.mpaiement.feign.proxy")
@SpringBootApplication
public class MPaiementApplication {

	
	/**
	 * <b>OBJET QUI POSSEDE LES FONCTIONNALITES SUIVANTES : </b> <br/>
	 * <br/>
	 * Les fonctionnalites d'ecriture de messages de log dans la console.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(MPaiementApplication.class);

	
	/**
	 * <b>METHODE D'ENTREE DE L'APPLICATION</b><br/>
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		LOGGER.info("CLASS : MPaiementApplication -- METHOD : main -- BEGIN");
		SpringApplication.run(MPaiementApplication.class, args);
		LOGGER.info("CLASS : MPaiementApplication -- METHOD : main -- END");
	}
}
