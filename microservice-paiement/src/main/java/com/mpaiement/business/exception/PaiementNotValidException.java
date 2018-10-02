package com.mpaiement.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <b>EXCEPTION DU TYPE CI-DESSOUS :</b><br/>
 * TYPE : 'PAIEMENT NON VALIDE'
 */
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class PaiementNotValidException extends RuntimeException {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * <b>CONSTRUCTEUR AVEC UN ARGUMENT</b><br/>
     * @param pMessage Le message de l'exception
     */
	public PaiementNotValidException(String message) {
        super(message);
    }
}
