package com.mpaiement.business.service;

import com.mpaiement.persistence.model.Paiement;

/**
 * <b>INTERFACE EXPOSANT LES FONCTIONNALITES SUIVANTES :</b><br/>
 *    ->LES TRAITEMENTS METIER RELATIFS A L'ENTITE 'Paiement'.<br/>
 *    
 * @author 1603599
 */
public interface PaiementService {

	
	/**
	 * <b>EFFECTUER L'OPERATION SPECIFIEE CI-DESSOUS :</b><br/>
	 * <br/>
	 * ->(01.)OPERATION PRINCIPALE   : CREER UN PAIEMENT.<br/>
	 * ->(02.)OPERATION PRELIMINAIRE : RECHERCHER UN PAIEMENT D'APRES SON ATTRIBUT "commandeId".<br/>
	 * ->(03.)TRAITEMENT D'ERREUR    : CAS OU UN PAIEMENT EXISTE DEJA AVEC LA MEME VALEUR DE L'ATTRIBUT "commandeId".<br/>
	 *    
	 * @param pPaiement L'objet 'paiement'.
	 * @return Paiement L'objet 'paiement' créé.
	 */    
    public abstract Paiement creer(Paiement pPaiement);	
}
