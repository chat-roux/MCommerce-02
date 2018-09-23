package com.mclientui.business.service;

import com.mclientui.feign.bean.PaiementBean;

/**
 * <b>INTERFACE QUI EXPOSE LES FONCTIONNALITES SUIVANTES :</b><br/>
 *    ->LES TRAITEMENTS METIER RELATIFS A L'ENTITE 'Paiement'.<br/>
 *    
 * @author 1603599
 */
public interface PaiementService {

	
	/**
	 * <b>EFFECTUER L'OPERATION SPECIFIEE CI-DESSOUS :</b><br/>
	 * <br/>
	 * ->(01.)OPERATION A EFFECTUER : CREER UN PAIEMENT.<br/> 
	 * ->(02.)ENTITE A UTILISER     : L'ENTITE 'paiement'.<br/> 
	 * ->(02.)TRAITEMENT D'ERREURS : CAS OU L'ENTITE EXISTE DEJA.<br/>
	 *    
	 * @param pCommandeId L'id de la commande (associe au paiement).
	 * @param pCommandeMontant Le montant de la commande (associe au paiement).
	 * @param pCarteNumero Le numero de carte (associee au paiement).
	 * @return String Le nom de la vue de destination.
	 */    
    public abstract PaiementBean creer(Long pCommandeId
		    							, Double pMontant
		    							, Long pCarteNumero);	
}
