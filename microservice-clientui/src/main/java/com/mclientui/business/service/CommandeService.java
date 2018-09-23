package com.mclientui.business.service;

import com.mclientui.feign.bean.CommandeBean;

/**
 * <b>INTERFACE QUI EXPOSE LES FONCTIONNALITES SUIVANTES :</b><br/>
 *    ->LES TRAITEMENTS METIER RELATIFS A L'ENTITE 'Commande'.<br/>
 *    
 * @author 1603599
 */
public interface CommandeService {
	
	
	/**
	 * <b>EFFECTUER L'OPERATION SPECIFIEE CI-DESSOUS :</b><br/>
	 * <br/>
	 * ->(01.)OPERATION A EFFECTUER : CREER UNE COMMANDE.<br/> 
	 * ->(02.)ENTITE A UTILISER     : L'ENTITE 'commande'.<br/> 
	 * ->(03.)TRAITEMENT D'ERREURS  : CAS OU L'ENTITE EXISTE DEJA.<br/>
	 *    
	 * @param pProduitId L'id du produit (associe à la commande).
	 * @return CommandeBean Objet 'CommandeBean' créé.
	 */    
    public abstract CommandeBean creer(Long pProduitId);
    
	/**
	 * <b>EFFECTUER L'OPERATION SPECIFIEE CI-DESSOUS :</b><br/>
	 * <br/>
	 * ->(01.)OPERATION A EFFECTUER : COMPLETER UNE COMMANDE.<br/> 
	 * ->(02.)ENTITE A UTILISER     : L'ENTITE 'commande'.<br/> 
	 * ->(03.)TRAITEMENT D'ERREURS  : CAS OU L'ENTITE EST INTROUVABLE.<br/>
	 *    
	 * @param pCommandeId L'id de la commande (associee au paiement).
	 * @param pProduitPrix Le prix du produit (associe a la commande, celle-ci etant associee au paiement).
	 * @param pCommandeQuantite La quantite de la commande (associee au paiement).
	 * @return CommandeBean Objet 'CommandeBean' ajouté.
	 */    
    public abstract CommandeBean completer(Long pCommandeId
			    							, Double pProduitPrix
			    							, Integer pCommandeQuantite);
}
