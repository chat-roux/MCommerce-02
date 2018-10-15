package com.mpaiement.business.exception;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import feign.Response;
import feign.Response.Body;
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
    	
		////////////////////////////////////////////////////////////////////////////////////////
		// (01.)EFFECTUER L'EXTRACTION CI-DESSOUS :
		//      ->SOURCE DE CETTE EXTRACTION  : LA REPONSE FOURNIE.
		//      ->PRODUIT DE CETTE EXTRACTION : UNE COLLECTION DE COUPLES 'CLE-VALEUR.'
		////////////////////////////////////////////////////////////////////////////////////////
		Set<Entry<String, Collection<String>>> entrySet = pReponse.headers().entrySet();

		////////////////////////////////////////////////////////////////////////////////////////
		// (02.)EFFECTUER LE TRANSVASEMENT CI-DESSOUS :
		//      ->SOURCE DE CE TRANSVASEMENT      : LA COLLECTION EXTRAITE CI-DESSUS.
		//      ->DESTINATION DE CE TRANSVASEMENT : UNE COLLECTION DES HEADERS ISSUS DE LA REPONSE FOURNIE.
		////////////////////////////////////////////////////////////////////////////////////////
		HttpHeaders responseHeaders = new HttpHeaders();
		for(Entry<String, Collection<String>> entry : entrySet) {
			responseHeaders.put(entry.getKey(), new ArrayList<>(entry.getValue()));
		}
		////////////////////////////////////////////////////////////////////////////////////////
		// (03.)EFFECTUER LES EXTRACTIONS CI-DESSOUS :
		//      ->SOURCE DE CES EXTRACTIONS   : LA REPONSE FOURNIE.
		//      ->PRODUITS DE CES EXTRACTIONS : 
		//           ->LE STATUT DE LA REPONSE
		//           ->LA RAISON DE LA REPONSE
		//           ->LE CORPS DE LA REPONSE
		////////////////////////////////////////////////////////////////////////////////////////
		HttpStatus responseStatus = HttpStatus.valueOf(pReponse.status());
        String responseReason = pReponse.reason();
        Body responseBody = pReponse.body();
        
        LOGGER.info("OBJECT : responseStatus -- VALUE : " + responseStatus);
        LOGGER.info("OBJECT : responseReason -- VALUE : " + responseReason);
        
		////////////////////////////////////////////////////////////////////////////////////////
		// (04.)EFFECTUER LES EXTRACTIONS CI-DESSOUS :
		//      ->SOURCE DE CES EXTRACTIONS   : LE CORPS DE REPONSE EXTRAIT PRECEDEMMENT.
		//      ->PRODUITS DE CES EXTRACTIONS : UN TABLEAU D'OCTETS.
		////////////////////////////////////////////////////////////////////////////////////////
        byte[] responseBodyBytes = null;
		try(InputStream inputStream = responseBody.asInputStream()) {
			//InputStream inputStream = responseBody.asInputStream();
        	IOUtils.readFully(inputStream, responseBodyBytes);
        	
		} catch (IOException e1) {
    		LOGGER.info("CLASS : PaiementErrorDecoder -- METHOD : decode -- END");
            throw new RuntimeException("Response-body : Not processable", e1);
            
		} catch (NullPointerException e2) {
    		LOGGER.info("CLASS : PaiementErrorDecoder -- METHOD : decode -- END");
            throw new RuntimeException("Response-body : Not present", e2);
		}
		////////////////////////////////////////////////////////////////////////////////////////
		// (05.)EFFECTUER LES TRAITEMENTS DES CAS D'ERREURS CI-DESSOUS :
		//      ->CAS N°1   : LE STATUT DE LA REPONSE EST ENTRE 400 ET 499.
		//      ->CAS N°2   : LE STATUT DE LA REPONSE EST ENTRE 500 ET 599.
		////////////////////////////////////////////////////////////////////////////////////////
        if((pReponse.status() >= 400) && (pReponse.status() <= 499)) {
        	
        	HttpClientErrorException httpClientErrorException = new HttpClientErrorException(responseStatus
        																					, responseReason
        																					, responseHeaders
        																					, responseBodyBytes
        																					, null);
    		LOGGER.info("CLASS : PaiementErrorDecoder -- METHOD : decode -- END");
            return httpClientErrorException;
        }
        if((pReponse.status() >= 500) && (pReponse.status() <= 599)) {
        	
        	HttpServerErrorException httpServerErrorException = new HttpServerErrorException(responseStatus
																							, responseReason
																							, responseHeaders
																							, responseBodyBytes
																							, null);
			LOGGER.info("CLASS : PaiementErrorDecoder -- METHOD : decode -- END");
			return httpServerErrorException;
        } 
        Exception exception = defaultErrorDecoder.decode(pInvoqueur, pReponse);
        
		LOGGER.info("CLASS : PaiementErrorDecoder -- METHOD : decode -- END");
        return exception;
    }
}
