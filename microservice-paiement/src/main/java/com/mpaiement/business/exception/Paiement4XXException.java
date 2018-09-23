package com.mpaiement.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <b>EXCEPTION DU TYPE CI-DESSOUS :</b><br/>
 * TYPE : 'REQUETE 4XX'
 * ENTITE : 'Produit'
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class Paiement4XXException extends RuntimeException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
     * <b>CONSTRUCTEUR AVEC UN ARGUMENT</b><br/>
     * @param pMessage Le message de l'exception
     */
	public Paiement4XXException(String pMessage) {
        super(pMessage);
    }
}
