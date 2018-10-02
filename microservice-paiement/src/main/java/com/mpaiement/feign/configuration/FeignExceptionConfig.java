package com.mpaiement.feign.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mpaiement.business.exception.PaiementErrorDecoder;


/**
 * <b>GESTIONNAIRE DES DECODEURS D'EXCEPTIONS SPECIFIEES CI-DESSOUS :</b><br/>
 * TYPE : 'PaiementErrorDecoder'
 */
@Configuration
public class FeignExceptionConfig {
	
	
	/**
	 * <b>OBJET QUI POSSEDE LES FONCTIONNALITES SUIVANTES : </b> <br/>
	 * <br/>
	 * Les fonctionnalites d'ecriture de messages de log dans la console.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(FeignExceptionConfig.class);
	
	
	@Bean
	public PaiementErrorDecoder createPaiementErrorDecoder() {
		
		LOGGER.info("CLASS : FeignExceptionConfig -- METHOD : createPaiementErrorDecoder -- BEGIN");
		PaiementErrorDecoder paiementErrorDecoder = new PaiementErrorDecoder();
		LOGGER.info("CLASS : FeignExceptionConfig -- METHOD : createPaiementErrorDecoder -- END");
		return paiementErrorDecoder;
	}
}
