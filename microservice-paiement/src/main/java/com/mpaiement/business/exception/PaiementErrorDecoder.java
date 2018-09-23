package com.mpaiement.business.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import feign.Response;
import feign.codec.ErrorDecoder;

/**
 * <b>DECODEUR DES ERREURS SPECIFIEES CI-DESSOUS :</b><br/>
 * TYPE : 'REQUETE INCORRECTE'
 */
public class PaiementErrorDecoder implements ErrorDecoder {

	
	/**
	 * <b>OBJET QUI POSSEDE LES FONCTIONNALITES SUIVANTES : </b> <br/>
	 * <br/>
	 * Les fonctionnalites d'ecriture de messages de log dans la console.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(PaiementErrorDecoder.class);
	
    private final ErrorDecoder defaultErrorDecoder = new Default();


    @Override
    public Exception decode(String pInvoqueur, Response pReponse) {

		LOGGER.info("CLASS : PaiementErrorDecoder -- METHOD : decode -- BEGIN");
    	
        if((pReponse.status() >= 300) && (pReponse.status() <= 399)) {
        	
        	Paiement3XXException paiement3XXException = new Paiement3XXException("Multiples choix");
        	LOGGER.info("pReponse.body : " + pReponse.body());
        	LOGGER.info("pReponse.reason : " + pReponse.reason());
        	LOGGER.info("pReponse.request : " + pReponse.request());
        	LOGGER.info("pReponse.toString : " + pReponse.toString());
        	
    		LOGGER.info("CLASS : PaiementErrorDecoder -- METHOD : decode -- END");
            return paiement3XXException;
        } 
        if((pReponse.status() >= 400) && (pReponse.status() <= 499)) {
        	
        	Paiement4XXException paiement4XXException = new Paiement4XXException("Requête incorrecte");
        	LOGGER.info("pReponse.body : " + pReponse.body());
        	LOGGER.info("pReponse.reason : " + pReponse.reason());
        	LOGGER.info("pReponse.request : " + pReponse.request());
        	LOGGER.info("pReponse.toString : " + pReponse.toString());
        	
    		LOGGER.info("CLASS : PaiementErrorDecoder -- METHOD : decode -- END");
            return paiement4XXException;
        } 
        if((pReponse.status() >= 500) && (pReponse.status() <= 599)) {
        	
        	Paiement5XXException paiement5XXException = new Paiement5XXException("Erreur interne");
        	LOGGER.info("pReponse.body : " + pReponse.body());
        	LOGGER.info("pReponse.reason : " + pReponse.reason());
        	LOGGER.info("pReponse.request : " + pReponse.request());
        	LOGGER.info("pReponse.toString : " + pReponse.toString());
        	
    		LOGGER.info("CLASS : PaiementErrorDecoder -- METHOD : decode -- END");
            return paiement5XXException;
        } 
        Exception exception = defaultErrorDecoder.decode(pInvoqueur, pReponse);
        
		LOGGER.info("CLASS : PaiementErrorDecoder -- METHOD : decode -- END");
        return exception;
    }
}
