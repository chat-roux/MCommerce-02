package com.mproduit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@EnableConfigurationProperties
@SpringBootApplication
public class MProduitApplication {
	
	
	/**
	 * <b>OBJET QUI POSSEDE LES FONCTIONNALITES SUIVANTES : </b> <br/>
	 * <br/>
	 * Les fonctionnalites d'ecriture de messages de log dans la console.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(MProduitApplication.class);

	
	/**
	 * <b>METHODE D'ENTREE DE L'APPLICATION</b><br/>
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		LOGGER.info("CLASS : MProduitApplication -- METHOD : main -- BEGIN");
		SpringApplication.run(MProduitApplication.class, args);
		LOGGER.info("CLASS : MProduitApplication -- METHOD : main -- END");
	}
}
