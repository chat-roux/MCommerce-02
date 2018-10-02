package com.mclientui.business.exception;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.Map.Entry;

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
    	
		HttpHeaders responseHeaders = new HttpHeaders();
		Set<Entry<String, Collection<String>>> entrySet = pReponse.headers().entrySet();
		
		for(Entry<String, Collection<String>> entry : entrySet) {
			responseHeaders.put(entry.getKey(), new ArrayList<>(entry.getValue()));
		}
        byte[] responseBodyBytes = null;
        Body responseBody = pReponse.body();
        
		try {
			InputStream inputStream = responseBody.asInputStream();
        	IOUtils.readFully(inputStream, responseBodyBytes);
        	
		} catch (IOException e1) {
            throw new RuntimeException("Failed to process response body.", e1);
		}
		HttpStatus responseStatus = HttpStatus.valueOf(pReponse.status());
        String responseReason = pReponse.reason();

        if((pReponse.status() >= 400) && (pReponse.status() <= 499)) {
        	
        	HttpClientErrorException httpClientErrorException = new HttpClientErrorException(responseStatus
        																					, responseReason
        																					, responseHeaders
        																					, responseBodyBytes
        																					, null);
    		LOGGER.info("CLASS : ClientUiErrorDecoder -- METHOD : decode -- END");
            return httpClientErrorException;
        }
        if((pReponse.status() >= 500) && (pReponse.status() <= 599)) {
        	
        	HttpServerErrorException httpServerErrorException = new HttpServerErrorException(responseStatus
																							, responseReason
																							, responseHeaders
																							, responseBodyBytes
																							, null);
			LOGGER.info("CLASS : ClientUiErrorDecoder -- METHOD : decode -- END");
			return httpServerErrorException;
        } 
        Exception exception = defaultErrorDecoder.decode(pInvoqueur, pReponse);
        
		LOGGER.info("CLASS : ClientUiErrorDecoder -- METHOD : decode -- END");
        return exception;
    }
}
