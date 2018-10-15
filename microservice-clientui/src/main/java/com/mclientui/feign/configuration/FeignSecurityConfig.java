package com.mclientui.feign.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.auth.BasicAuthRequestInterceptor;


/**
 * <b>COMPOSANT QUI IMPLEMENTE LES FONCTIONNALITES CI-DESSOUS:</b><br/>
 *    ->GESTION DES INTERCEPTEURS SPECIFIES CI-DESSOUS.<br/>
 *    ->TYPE D'INTERCEPTEURS : INTERCEPTEURS DE REQUETES D'AUTHENTIFICATION 'BasicAuthRequestInterceptor'
 */
@Configuration
public class FeignSecurityConfig {
	
	
	/**
	 * <b>OBJET QUI POSSEDE LES FONCTIONNALITES SUIVANTES : </b> <br/>
	 * <br/>
	 * Les fonctionnalites d'ecriture de messages de log dans la console.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(FeignSecurityConfig.class);
	
	
	@Bean
    public BasicAuthRequestInterceptor mBasicAuthRequestInterceptor(){
		
		LOGGER.info("CLASS : FeignSecurityConfig -- METHOD : mBasicAuthRequestInterceptor -- BEGIN");
		BasicAuthRequestInterceptor basicAuthRequestInterceptor = new BasicAuthRequestInterceptor("utilisateur", "mdp");
		
		
		LOGGER.info("CLASS : FeignSecurityConfig -- METHOD : mBasicAuthRequestInterceptor -- END");
        return  basicAuthRequestInterceptor;
    }
}
