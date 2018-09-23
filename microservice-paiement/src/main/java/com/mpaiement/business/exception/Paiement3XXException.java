package com.mpaiement.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <b>EXCEPTION DU TYPE CI-DESSOUS :</b><br/>
 * TYPE : 'REQUETE 3XX'
 */
@ResponseStatus(HttpStatus.MULTIPLE_CHOICES)
public class Paiement3XXException extends RuntimeException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
     * <b>CONSTRUCTEUR AVEC UN ARGUMENT</b><br/>
     * @param pMessage Le message de l'exception
     */
	public Paiement3XXException(String pMessage) {
        super(pMessage);
    }
}
