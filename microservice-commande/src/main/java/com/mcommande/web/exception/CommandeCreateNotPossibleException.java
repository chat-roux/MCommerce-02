package com.mcommande.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <b>EXCEPTION DU TYPE CI-DESSOUS :</b><br/>
 * TYPE : 'CREATION IMPOSSIBLE'
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CommandeCreateNotPossibleException extends RuntimeException {

	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * <b>CONSTRUCTEUR AVEC UN ARGUMENT</b><br/>
     * @param pMessage Le message de l'exception
     */
	public CommandeCreateNotPossibleException(String pMessage) {
        super(pMessage);
    }
}
