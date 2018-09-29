package com.mcommerce.configserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;


@EnableConfigServer
@SpringBootApplication
public class ConfigServerApplication {

	/**
	 * <b>OBJET QUI POSSEDE LES FONCTIONNALITES SUIVANTES : </b> <br/>
	 * <br/>
	 * Les fonctionnalites d'ecriture de messages de log dans la console.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigServerApplication.class);

	
	public static void main(String[] args) {

		LOGGER.info("CLASS : ConfigServerApplication -- METHOD : main -- BEGIN");
		SpringApplication.run(ConfigServerApplication.class, args);
		LOGGER.info("CLASS : ConfigServerApplication -- METHOD : main -- END");
	}
}
