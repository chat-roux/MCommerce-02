package com.mclientui.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mclientui.business.exception.ClientUi4XXException;
import com.mclientui.business.service.CommandeService;
import com.mclientui.business.service.PaiementService;
import com.mclientui.business.service.ProduitService;
import com.mclientui.feign.bean.CommandeBean;
import com.mclientui.feign.bean.PaiementBean;
import com.mclientui.feign.bean.ProduitBean;
import com.mclientui.web.view.CommandePayerView;
import com.mclientui.web.view.PaiementConfirmerView;
import com.mclientui.web.view.PaiementResultatView;
import com.mclientui.web.view.ProduitDetailView;
import com.mclientui.web.view.ProduitListeView;


/**
 * <b>COMPOSANT POSSEDANT LES FONCTIONNALITES SUIVANTES :</b><br/>
 *    ->LA NAVIGATION ENTRE LES ECRANS DE L'APPLICATION.<br/>
 *    ->LE CONTROLE DES PROCESSUS DE L'APPLICATION.<br/>
 *    
 * @author 1603599
 */
@Controller
public class ClientController {


	private static final String MESSAGE__PRODUIT_RECHERCHER_PAR_ID__PRODUIT_INTROUVABLE = "Rechercher un produit par id -- Produit introuvable";
	private static final String MESSAGE__COMMANDE_CREER__PRODUIT_INTROUVABLE = "Creer une commande -- Produit introuvable";
	private static final String MESSAGE__COMMANDE_PAYER__COMMANDE_INTROUVABLE = "Payer une commande -- Commande introuvable";
	private static final String MESSAGE__PAIEMENT_CONFIRMER__COMMANDE_INTROUVABLE = "Confirmer un paiement -- Commande introuvable";
	
	
	/**
	 * <b>OBJET QUI POSSEDE LES FONCTIONNALITES SUIVANTES : </b> <br/>
	 * <br/>
	 * Les fonctionnalites d'ecriture de messages de log dans la console.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);
	
	
	/**
	 * <b>COMPOSANT QUI IMPLEMENTE LES FONCTIONALITES CI-DESSOUS :</b><br/>
	 * <br/>
	 * ->FONCTIONNALITES : LES TRAITEMENTS METIER RELATIFS A L'ENTITE 'Produit'.<br/> 
	 */    
    @Autowired
    private ProduitService produitService;

	/**
	 * <b>COMPOSANT QUI IMPLEMENTE LES FONCTIONALITES CI-DESSOUS :</b><br/>
	 * <br/>
	 * ->FONCTIONNALITES : LES TRAITEMENTS METIER RELATIFS A L'ENTITE 'Commande'.<br/> 
	 */    
    @Autowired
    private CommandeService commandeService;

	/**
	 * <b>COMPOSANT QUI IMPLEMENTE LES FONCTIONALITES CI-DESSOUS :</b><br/>
	 * <br/>
	 * ->FONCTIONNALITES : LES TRAITEMENTS METIER RELATIFS A L'ENTITE 'Paiement'.<br/> 
	 */    
    @Autowired
    private PaiementService paiementService;
    
    
    /**
	 * <b>EFFECTUER LES OPERATIONS DE REQUETAGE-REDIRECTION CI-DESSOUS :</b><br/>
	 * <br/>
	 * ->(01.)REQUETAGE : SUR LA RESSOURCE 'produit'.<br/> 
	 * ->(02.)REDIRECTION : VERS LA VUE 'Accueil'.<br/>
	 *    
	 * @param pModel L'attribut "model" de la requête.
	 * @return String Le nom de la vue de destination.
	 */    
    @RequestMapping(value="/produit", method=RequestMethod.GET)
    public String rechercherProduitListe(Model pModel){

		LOGGER.info("CLASS : ClientController -- METHOD : rechercherProduitListe -- BEGIN");

		List<ProduitBean> produitBeans =  this.produitService.rechercherTous();
		ProduitListeView produitListeView = new ProduitListeView(produitBeans);
		
		pModel.addAttribute("produitListeView", produitListeView);
        
		LOGGER.info("CLASS : ClientController -- METHOD : rechercherProduitListe -- END");
        return "produitListe";
    }
    
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
    @RequestMapping(value="/produit/{id}", method=RequestMethod.GET)
    public String rechercherProduitParId(@PathVariable(name="id") Long pId,  Model pModel){

		LOGGER.info("CLASS : ClientController -- METHOD : rechercherProduitParId -- BEGIN");

		//////////////////////////////////////////////////////////////////////////////////////////////
		//(01.)EFFECTUER LES OPERATIONS CI-DESSOUS :
		//     ->TRAITEMENT NOMINAL : 
    	//         ->Rechercher le produit (par son attribut "id").
		//     ->TRAITEMENT D'ERREUR : 
		//     	   ->CAS D'ERREUR : Le produit n'a pas été trouvé.
		//         ->TRAITEMENT   : Ajouter un message d'erreur dans le modèle.
		//////////////////////////////////////////////////////////////////////////////////////////////
		ProduitBean produitBean = null;
		try {
			produitBean = this.produitService.rechercherParId(pId);
			
		} catch (ClientUi4XXException e) {
			pModel.addAttribute("error", MESSAGE__PRODUIT_RECHERCHER_PAR_ID__PRODUIT_INTROUVABLE);
			LOGGER.info("CLASS : ClientController -- METHOD : rechercherProduitParId -- END");
			return "produitListe";
		}
		//////////////////////////////////////////////////////////////////////////////////////////////
		//(02.)TRAITER LE CAS NOMINAL CI-DESSOUS :
		//     ->CAS NOMINAL : Le produit a été trouvé.
		//     ->TRAITEMENTS : 
		//        ->(02.01.)Créer un bean de vue 'produitDetailView' (en alimentant son attribut 'produitBean' ).
		//        ->(02.02.)Ajouter le bean de vue dans le modèle.
		//////////////////////////////////////////////////////////////////////////////////////////////
		ProduitDetailView ProduitDetailView = new ProduitDetailView(produitBean);
        pModel.addAttribute("produitDetailView", ProduitDetailView);
        
		LOGGER.info("CLASS : ClientController -- METHOD : rechercherProduitParId -- END");
        return "produitDetail";
    }
    
	/**
	 * <b>EFFECTUER LES OPERATIONS DE REQUETAGE-REDIRECTION CI-DESSOUS :</b><br/>
	 * <br/>
	 * ->(01.)REQUETAGE : SUR LA RESSOURCE 'commande'.<br/> 
	 * ->(02.)REDIRECTION : VERS LA VUE 'commandePayer'.<br/>
	 *    
	 * @param pProduitId L'id du produit (associe à la commande).
	 * @param pModel L'attribut "model" de la requête.
	 * @return String Le nom de la vue de destination.
	 */    
    @RequestMapping(value="/commande/{produitId}", method=RequestMethod.GET)
    public String creerCommande(@PathVariable(name="produitId") Long pProduitId, Model pModel){

		LOGGER.info("CLASS : ClientController -- METHOD : creerCommande -- BEGIN");

		//////////////////////////////////////////////////////////////////////////////////////////////
		//(01.)EFFECTUER LES OPERATIONS CI-DESSOUS :
		//     ->TRAITEMENT NOMINAL : 
    	//         ->Rechercher le produit (par son attribut "id").
		//     ->TRAITEMENT D'ERREUR : 
		//     	   ->CAS D'ERREUR : Le produit n'a pas été trouvé.
		//         ->TRAITEMENT   : Ajouter un message d'erreur dans le modèle.
		//////////////////////////////////////////////////////////////////////////////////////////////
		ProduitBean produitBean = null;
		try {
			produitBean = this.produitService.rechercherParId(pProduitId);
			
		} catch (ClientUi4XXException e) {
			pModel.addAttribute("error", MESSAGE__COMMANDE_CREER__PRODUIT_INTROUVABLE);
			LOGGER.info("CLASS : ClientController -- METHOD : creerCommande -- END");
			return "produitDetail";
		}
		//////////////////////////////////////////////////////////////////////////////////////////////
		//(02.)EFFECTUER LES OPERATIONS CI-DESSOUS :
		//     ->TRAITEMENT NOMINAL : 
    	//         ->Creer une commande (en alimentant son attribut "produitId").
		//     ->TRAITEMENT D'ERREUR : 
		//     	   ->CAS D'ERREUR : Le produit n'a pas été trouvé.
		//         ->TRAITEMENT   : Ajouter un message d'erreur dans le modèle.
		//////////////////////////////////////////////////////////////////////////////////////////////
		CommandeBean commandeBean = null;
		try {
			commandeBean = this.commandeService.creer(pProduitId);
			
		} catch (ClientUi4XXException e) {
			pModel.addAttribute("error", MESSAGE__COMMANDE_CREER__PRODUIT_INTROUVABLE);
			LOGGER.info("CLASS : ClientController -- METHOD : creerCommande -- END");
			return "produitDetail";
		}
		//////////////////////////////////////////////////////////////////////////////////////////////
		//(03.)TRAITER LE CAS NOMINAL CI-DESSOUS :
		//     ->CAS NOMINAL : Le produit a été trouvé.
		//     ->TRAITEMENTS : 
		//        ->(03.01.)Créer un bean de vue 'commandePayerView' (en alimentant ses attributs 'produitBean' et 'commandeBean').
		//        ->(03.02.)Ajouter le bean de vue dans le modèle.
		//////////////////////////////////////////////////////////////////////////////////////////////
		CommandePayerView commandePayerView = new CommandePayerView(produitBean, commandeBean);
        pModel.addAttribute("commandePayerView", commandePayerView);
        
		LOGGER.info("CLASS : ClientController -- METHOD : creerCommande -- END");
        return "commandePayer";
    }
    
	/**
	 * <b>EFFECTUER LES OPERATIONS DE REQUETAGE-REDIRECTION CI-DESSOUS :</b><br/>
	 * <br/>
	 * ->(01.)REQUETAGE : SUR LA RESSOURCE 'paiement'.<br/> 
	 * ->(02.)REDIRECTION : VERS LA VUE 'paiementConfirmer'.<br/>
	 *    
	 * @param pCommandeId L'id de la commande (associee au paiement).
	 * @param pProduitPrix Le prix du produit (associe a la commande, celle-ci etant associee au paiement).
	 * @param pCommandeQuantite La quantite de la commande (associee au paiement).
	 * @param pModel L'attribut "model" de la requête.
	 * @return String Le nom de la vue de destination.
	 */    
    @RequestMapping(value="/commande/payer", method=RequestMethod.POST)
    public String payerCommande(@RequestParam(value="commandeId") Long pCommandeId
    							, @RequestParam(value="produitPrix") Double pProduitPrix
    							, @RequestParam(value="commandeQuantite") Integer pCommandeQuantite
    							, Model pModel){

		LOGGER.info("CLASS : ClientController -- METHOD : payerCommande -- BEGIN");

		//////////////////////////////////////////////////////////////////////////////////////////////
		//(01.)EFFECTUER LES OPERATIONS CI-DESSOUS :
		//     ->TRAITEMENT NOMINAL : 
    	//         ->Payer la commande en utilisant les données suivantes :
		//         ->Dans l'entité "Commande" : Les attributs "id" et "quantite".
		//         ->Dans l'entité "Produit"  : L'attribut "prix".
		//     ->TRAITEMENT D'ERREUR : 
		//     	   ->CAS D'ERREUR : La commande n'a pas été trouvée.
		//         ->TRAITEMENT   : Ajouter un message d'erreur dans le modèle.
		//////////////////////////////////////////////////////////////////////////////////////////////
		CommandeBean commandeBean = null;
		try {
			commandeBean = this.commandeService.completer(pCommandeId, pProduitPrix, pCommandeQuantite);
			
		} catch(ClientUi4XXException e) {
			pModel.addAttribute("error", MESSAGE__COMMANDE_PAYER__COMMANDE_INTROUVABLE);
			LOGGER.info("CLASS : ClientController -- METHOD : payerCommande -- END");
			return "commandePayer";
		}
		//////////////////////////////////////////////////////////////////////////////////////////////
		//(02.)EFFECTUER LE TRAITEMENT NOMINAL CI-DESSOUS :
		//     ->CAS NOMINAL : La commande a été payée.
		//     ->TRAITEMENTS : 
		//        ->(03.01.)Créer un bean de vue 'commandeConfirmerView' (en alimentant son attribut 'commandeBean').
		//        ->(03.02.)Ajouter le bean de vue dans le modèle.
		//////////////////////////////////////////////////////////////////////////////////////////////
		PaiementConfirmerView paiementConfirmerView = new PaiementConfirmerView(commandeBean);
        pModel.addAttribute("paiementConfirmerView", paiementConfirmerView);
        
		LOGGER.info("CLASS : ClientController -- METHOD : payerCommande -- END");
        return "paiementConfirmer";
    }
    
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
    @RequestMapping(value="/paiement/confirmer", method=RequestMethod.POST)
    public String confirmerPaiement(@RequestParam(name="commandeId") Long pCommandeId
	    							, @RequestParam(name="commandeMontant") Double pCommandeMontant
	    							, @RequestParam(name="paiementCarteNumero") Long pPaiementCarteNumero
	    							, Model pModel) {
    	
		LOGGER.info("CLASS : ClientController -- METHOD : confirmerPaiement -- BEGIN");

		//////////////////////////////////////////////////////////////////////////////////////////////
		//(01.)EFFECTUER LES OPERATIONS CI-DESSOUS :
		//     ->TRAITEMENT NOMINAL : 
    	//         ->Confirmer le paiement en utilisant les données suivantes:
		//         ->Dans l'entité "Commande" : Les attributs "id" et "montant".
		//         ->Dans l'entité "Paiement" : L'attribut "carteNumero".
		//     ->TRAITEMENT D'ERREUR : 
		//     	   ->CAS D'ERREUR : La commande n'a pas été trouvée.
		//         ->TRAITEMENT   : Ajouter un message d'erreur dans le modèle.
		//////////////////////////////////////////////////////////////////////////////////////////////
		PaiementBean paiementBean = null;
		try {
			paiementBean = this.paiementService.creer(pCommandeId, pCommandeMontant, pPaiementCarteNumero);
			
		} catch (ClientUi4XXException e) {
			pModel.addAttribute("error", MESSAGE__PAIEMENT_CONFIRMER__COMMANDE_INTROUVABLE);
			LOGGER.info("CLASS : ClientController -- METHOD : confirmerPaiement -- END");
			return "commandePayer";
		}
		//////////////////////////////////////////////////////////////////////////////////////////////
		//(02.)EFFECTUER LE TRAITEMENT NOMINAL CI-DESSOUS :
		//     ->CAS NOMINAL : Le paiement a été confirmé.
		//     ->TRAITEMENTS : 
		//        ->(03.01.)Créer un bean de vue 'paiementResultatView' (en alimentant son attribut 'paiementBean').
		//        ->(03.02.)Ajouter le bean de vue dans le modèle.
		//////////////////////////////////////////////////////////////////////////////////////////////
		PaiementResultatView paiementResultatView = new PaiementResultatView(paiementBean);
        pModel.addAttribute("paiementResultatView", paiementResultatView);
        
		LOGGER.info("CLASS : ClientController -- METHOD : confirmerPaiement -- END");
    	return "paiementResultat";
    }
}
