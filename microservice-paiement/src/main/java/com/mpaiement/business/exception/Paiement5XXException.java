package com.mpaiement.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <b>EXCEPTION DU TYPE CI-DESSOUS :</b><br/>
 * TYPE : 'REQUETE 5XX'
 * ENTITE : 'Produit'
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class Paiement5XXException extends RuntimeException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
     * <b>CONSTRUCTEUR AVEC UN ARGUMENT</b><br/>
     * @param pMessage Le message de l'exception
     */
	public Paiement5XXException(String pMessage) {
        super(pMessage);
    }
}
