package com.mclientui.feign.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mclientui.business.exception.ClientUiErrorDecoder;


/**
 * <b>COMPOSANT QUI IMPLEMENTE LES FONCTIONNALITES CI-DESSOUS:</b><br/>
 *    ->GESTION DES DECODEURS D'EXCEPTIONS SPECIFIES CI-DESSOUS.<br/>
 *    ->TYPE DE DECODEURS D'EXCEPTION : 'ClientUiErrorDecoder'
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
	public ClientUiErrorDecoder createErrorDecoder() {
		
		LOGGER.info("CLASS : FeignExceptionConfig -- METHOD : createErrorDecoder -- BEGIN");
		ClientUiErrorDecoder clientUiErrorDecoder = new ClientUiErrorDecoder();
		LOGGER.info("CLASS : FeignExceptionConfig -- METHOD : createErrorDecoder -- END");
		return clientUiErrorDecoder;
	}
}
