package com.mclientui.web.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <b>COMPOSANT DECLARANT LES FONCTIONNALITES SUIVANTES :</b><br/>
 *    ->LA NAVIGATION ENTRE LES ECRANS DE L'APPLICATION.<br/>
 *    ->LE CONTROLE DES PROCESSUS DE L'APPLICATION.<br/>
 *    
 * @author 1603599
 */
public interface ClientController {

	
    /**
	 * <b>EFFECTUER LES OPERATIONS DE REQUETAGE-REDIRECTION CI-DESSOUS :</b><br/>
	 * <br/>
	 * ->(01.)REQUETAGE : SUR LA RESSOURCE 'produit'.<br/> 
	 * ->(02.)REDIRECTION : VERS LA VUE 'Accueil'.<br/>
	 *    
	 * @param pModel L'attribut "model" de la requête.
	 * @return String Le nom de la vue de destination.
	 */    
    public abstract String rechercherProduitListe(Model pModel);

	/**
	 * <b>EFFECTUER LES OPERATIONS DE REQUETAGE-REDIRECTION CI-DESSOUS :</b><br/>
	 * <br/>
	 * ->(01.)REQUETAGE : SUR LA RESSOURCE 'produit'.<br/> 
	 * ->(02.)REDIRECTION : VERS LA VUE 'produitDetail'.<br/>
	 *    
	 * @param pId L'id du produit.
	 * @param pModel L'attribut "model" de la requête.
	 * @return String Le nom de la vue de destination.
	 */    
    public abstract String rechercherProduitParId(@PathVariable(name="id") Long pId,  Model pModel);

	/**
	 * <b>EFFECTUER LES OPERATIONS DE REQUETAGE-REDIRECTION CI-DESSOUS :</b><br/>
	 * <br/>
	 * ->(01.)REQUETAGE : SUR LA RESSOURCE 'commande'.<br/> 
	 * ->(02.)REDIRECTION : VERS LA VUE 'commandeConfirmer'.<br/>
	 *    
	 * @param pProduitId L'id du produit (associe à la commande).
	 * @param pModel L'attribut "model" de la requête.
	 * @return String Le nom de la vue de destination.
	 */    
    public abstract String creerCommande(@PathVariable(name="produitId") Long pProduitId, Model pModel);

	/**
	 * <b>EFFECTUER LES OPERATIONS DE REQUETAGE-REDIRECTION CI-DESSOUS :</b><br/>
	 * <br/>
	 * ->(01.)REQUETAGE : SUR LA RESSOURCE 'commande'.<br/> 
	 * ->(02.)REDIRECTION : VERS LA VUE 'paiementConfirmer'.<br/>
	 *    
	 * @param pCommandeId L'id de la commande (associee au paiement).
	 * @param pProduitPrix Le prix du produit (associe a la commande, celle-ci etant associee au paiement).
	 * @param pCommandeQuantite La quantite de la commande (associee au paiement).
	 * @param pModel L'attribut "model" de la requête.
	 * @return String Le nom de la vue de destination.
	 */    
    public abstract String confirmerCommande(@RequestParam(value="commandeId") Long pCommandeId
			    							, @RequestParam(value="produitPrix") Double pProduitPrix
			    							, @RequestParam(value="commandeQuantite") Integer pCommandeQuantite
			    							, Model pModel);

	/**
	 * <b>EFFECTUER LES OPERATIONS DE REQUETAGE-REDIRECTION CI-DESSOUS :</b><br/>
	 * <br/>
	 * ->(01.)REQUETAGE : SUR LA RESSOURCE 'paiement'.<br/> 
	 * ->(02.)REDIRECTION : VERS LA VUE 'paiementResultat'.<br/>
	 *    
	 * @param pCommandeId L'id de la commande (associee au paiement).
	 * @param pCommandeMontant Le montant de la commande (associee au paiement).
	 * @param pPaiementCarteNumero Le numero de carte (associee au paiement).
	 * @param pModel L'attribut "model" de la requête.
	 * @return String Le nom de la vue de destination.
	 */    
    public abstract String confirmerPaiement(@RequestParam(name="commandeId") Long pCommandeId
			    							, @RequestParam(name="commandeMontant") Double pCommandeMontant
			    							, @RequestParam(name="paiementCarteNumero") Long pPaiementCarteNumero
			    							, Model pModel);
}
