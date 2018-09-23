package com.mclientui.business.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import feign.Response;
import feign.codec.ErrorDecoder;

/**
 * <b>DECODEUR DES ERREURS SPECIFIEES CI-DESSOUS :</b><br/>
 * TYPE : 'REQUETE INCORRECTE'
 */
public class ClientUiErrorDecoder implements ErrorDecoder {

	
	/**
	 * <b>OBJET QUI POSSEDE LES FONCTIONNALITES SUIVANTES : </b> <br/>
	 * <br/>
	 * Les fonctionnalites d'ecriture de messages de log dans la console.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ClientUiErrorDecoder.class);
	
    private final ErrorDecoder defaultErrorDecoder = new Default();


    @Override
    public Exception decode(String pInvoqueur, Response pReponse) {

		LOGGER.info("CLASS : ClientUiErrorDecoder -- METHOD : decode -- BEGIN");
    	
        if((pReponse.status() >= 300) && (pReponse.status() <= 399)) {
        	
        	ClientUi3XXException clientUi3XXException = new ClientUi3XXException("Multiples choix");
        	LOGGER.info("pReponse.body : " + pReponse.body());
        	LOGGER.info("pReponse.reason : " + pReponse.reason());
        	LOGGER.info("pReponse.request : " + pReponse.request());
        	LOGGER.info("pReponse.toString : " + pReponse.toString());
        	
    		LOGGER.info("CLASS : ClientUiErrorDecoder -- METHOD : decode -- END");
            return clientUi3XXException;
        } 
        if((pReponse.status() >= 400) && (pReponse.status() <= 499)) {
        	
        	ClientUi4XXException clientUi4XXException = new ClientUi4XXException("Requête incorrecte");
        	LOGGER.info("pReponse.body : " + pReponse.body());
        	LOGGER.info("pReponse.reason : " + pReponse.reason());
        	LOGGER.info("pReponse.request : " + pReponse.request());
        	LOGGER.info("pReponse.toString : " + pReponse.toString());
        	
    		LOGGER.info("CLASS : ClientUiErrorDecoder -- METHOD : decode -- END");
            return clientUi4XXException;
        } 
        if((pReponse.status() >= 500) && (pReponse.status() <= 599)) {
        	
        	ClientUi5XXException clientUi5XXException = new ClientUi5XXException("Erreur interne");
        	LOGGER.info("pReponse.body : " + pReponse.body());
        	LOGGER.info("pReponse.reason : " + pReponse.reason());
        	LOGGER.info("pReponse.request : " + pReponse.request());
        	LOGGER.info("pReponse.toString : " + pReponse.toString());
        	
    		LOGGER.info("CLASS : ClientUiErrorDecoder -- METHOD : decode -- END");
            return clientUi5XXException;
        } 
        Exception exception = defaultErrorDecoder.decode(pInvoqueur, pReponse);
        
		LOGGER.info("CLASS : ClientUiErrorDecoder -- METHOD : decode -- END");
        return exception;
    }
}
