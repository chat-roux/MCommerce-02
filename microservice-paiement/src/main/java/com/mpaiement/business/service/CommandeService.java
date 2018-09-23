package com.mpaiement.business.service;

import com.mpaiement.feign.bean.CommandeBean;

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
	 * ->(01.)OPERATION A EFFECTUER : RECHERCHER UNE COMMANDE.<br/> 
	 * ->(02.)CRITERE DE RECHERCHE  : L'ATTRIBUT "id" DE L'ENTITE "commande".<br/> 
	 * ->(03.)VALEUR A UTILISER     : L'ATTRIBUT "pCommandeId" FOURNI.<br/> 
	 * ->(04.)TRAITEMENT D'ERREUR   : CAS OU LA COMMANDE N'EXISTE PAS.<br/>
	 *    
	 * @param pId L'id de la commande.
	 * @return CommandeBean Objet 'CommandeBean' trouvé.
	 */    
    public abstract CommandeBean rechercherParId(Long pId);
    
	/**
	 * <b>EFFECTUER L'OPERATION SPECIFIEE CI-DESSOUS :</b><br/>
	 * <br/>
	 * ->(01.)OPERATION A EFFECTUER : FINALISER UNE COMMANDE.<br/> 
	 * ->(04.)TRAITEMENT D'ERREUR   : CAS OU LA COMMANDE N'EXISTE PAS.<br/>
	 *    
	 * @param pCommandeBean L'objet 'commandeBean'.
	 * @return CommandeBean Objet 'CommandeBean' trouvé.
	 */    
    public abstract CommandeBean finaliser(CommandeBean pCommandeBean);
}
