package com.mcommande;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MCommandeApplication {

	
	/**
	 * <b>OBJET QUI POSSEDE LES FONCTIONNALITES SUIVANTES : </b> <br/>
	 * <br/>
	 * Les fonctionnalites d'ecriture de messages de log dans la console.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(MCommandeApplication.class);

	
	/**
	 * <b>METHODE D'ENTREE DE L'APPLICATION</b><br/>
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		LOGGER.info("CLASS : MCommandeApplication -- METHOD : main -- BEGIN");
		SpringApplication.run(MCommandeApplication.class, args);
		LOGGER.info("CLASS : MCommandeApplication -- METHOD : main -- END");
	}
}
