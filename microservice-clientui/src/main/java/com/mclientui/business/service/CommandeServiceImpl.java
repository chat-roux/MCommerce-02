package com.mclientui.business.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mclientui.business.exception.CommandeNotFoundException;
import com.mclientui.business.exception.ProduitNotFoundException;
import com.mclientui.feign.bean.CommandeBean;
import com.mclientui.feign.bean.ProduitBean;
import com.mclientui.feign.proxy.MicroServiceCommandeProxy;
import com.mclientui.feign.proxy.MicroServiceProduitProxy;
import com.mclientui.business.exception.CommandeNotValidException;


/**
 * <b>COMPOSANT QUI IMPLEMENTE LES FONCTIONNALITES SUIVANTES :</b><br/>
 *    ->LES TRAITEMENTS METIER RELATIFS A L'ENTITE 'Commande'.<br/>
 *    
 * @author 1603599
 */
@Service
public class CommandeServiceImpl implements CommandeService {

	private static final String MESSAGE__COMMANDE_RECHERCHER_PAR_ID__COMMANDE_INTROUVABLE = "Rechercher une commande par id -- Commande introuvable";
	private static final String MESSAGE__COMMANDE_CREER__PRODUIT_INTROUVABLE = "Créer une commande -- Produit introuvable";
	private static final String MESSAGE__COMMANDE_PAYER__COMMANDE_INTROUVABLE = "Payer une commande -- Commande introuvable";
	private static final String MESSAGE__COMMANDE_FINALISER__COMMANDE_NON_VALIDE = "Finaliser une commande -- Commande non valide";
	
	
	/**
	 * <b>OBJET QUI POSSEDE LES FONCTIONNALITES SUIVANTES : </b> <br/>
	 * <br/>
	 * Les fonctionnalites d'ecriture de messages de log dans la console.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(CommandeServiceImpl.class);
	

	/**
	 * <b>PROXY PERMETTANT DE REQUETER LA RESSOURCE CI-DESSOUS :</b><br/>
	 * <br/>
	 * ->RESSOURCE : 'produit'.<br/> 
	 */    
    @Autowired
    private MicroServiceProduitProxy produitProxy;

    /**
	 * <b>PROXY PERMETTANT DE REQUETER LA RESSOURCE CI-DESSOUS :</b><br/>
	 * <br/>
	 * ->RESSOURCE : 'commande'.<br/> 
	 */    
    @Autowired
    private MicroServiceCommandeProxy commandeProxy;

	
	@Override
	public CommandeBean rechercherParId(Long pId) {
		
		LOGGER.info("CLASS : CommandeServiceImpl -- METHOD : rechercherParId -- BEGIN");
		
		//////////////////////////////////////////////////////////////////////////////////////////////
		//(01.)EFFECTUER L'OPERATION CI-DESSOUS :
        //
		//     ->OPERATIONS A EFFECTUER : RECHERCHER UNE ENTITE "Commande". 
		//     ->CRITERE DE RECHERCHE   : DANS L'ENTITE "Commande", L'ATTRIBUT "id".
		//     ->VALEUR A UTILISER      : L'ATTRIBUT "pId" FOURNI. 
		//////////////////////////////////////////////////////////////////////////////////////////////
        Optional<CommandeBean> commandeBeanOptional = this.commandeProxy.rechercherParId(pId);
		
		///////////////////////////////////////////////////////////////////////
		//(02.)TRAITER LES CAS D'ERREUR SUIVANTS :
		//
		//     CAS D'ERREUR : L'UNE DES CONDITIONS CI-DESSOUS EST SATISFAITE.
		//       ->L'OBJET TROUVE "Commande" EST VIDE.
        //
		//     TRAITEMENT : LANCER UNE EXCEPTION SPECIFIQUE AU PROBLEME RENCONTRE.
		///////////////////////////////////////////////////////////////////////
        if(!commandeBeanOptional.isPresent()) {
    		LOGGER.info("ERROR : [" + MESSAGE__COMMANDE_RECHERCHER_PAR_ID__COMMANDE_INTROUVABLE + "]");
    		LOGGER.info("CLASS : CommandeServiceImpl -- METHOD : rechercherParId -- END");
        	throw new CommandeNotFoundException(MESSAGE__COMMANDE_RECHERCHER_PAR_ID__COMMANDE_INTROUVABLE);
        }
        CommandeBean commandeBean = commandeBeanOptional.get();
        
		LOGGER.info("CLASS : CommandeServiceImpl -- METHOD : rechercherParId -- END");
		return commandeBean;
	}
	
	@Override
	public CommandeBean creer(Long pProduitId) {
		
		LOGGER.info("CLASS : CommandeServiceImpl -- METHOD : creer -- BEGIN");
		
		//////////////////////////////////////////////////////////////////////////////////////////////
		//(01.)EFFECTUER LES OPERATIONS CI-DESSOUS :
		//     ->OPERATIONS : 
    	//         ->Rechercher le produit (critere de recherche : l'attribut "id").
		//////////////////////////////////////////////////////////////////////////////////////////////
		Optional<ProduitBean> produitBeanOptional = this.produitProxy.rechercherParId(pProduitId);
		
		//////////////////////////////////////////////////////////////////////////////////////////////
		//(02.)TRAITER LE CAS D'ERREUR CI-DESSOUS :
		//     ->CAS D'ERREUR : Le produit n'a pas été trouvé.
		//     ->TRAITEMENT : Ajouter un message d'erreur dans le modèle.
		//////////////////////////////////////////////////////////////////////////////////////////////
		if (!produitBeanOptional.isPresent()) {
			LOGGER.info("CLASS : CommandeServiceImpl -- METHOD : creer -- END");
			throw new ProduitNotFoundException(MESSAGE__COMMANDE_CREER__PRODUIT_INTROUVABLE);
		}
		//////////////////////////////////////////////////////////////////////////////////////////////
		//(03.)TRAITER LE CAS NOMINAL CI-DESSOUS :
		//     ->CAS NOMINAL : Le produit a été trouvé.
		//     ->TRAITEMENTS : 
		//        ->(03.01.)Creer une commande (en alimentant son attribut "produitId").
		//        ->(03.02.)Sauvegarder la commande créé.
		//        ->(03.03.)Ajouter les objets suivants dans le modèle : "commande", "produit".
		//////////////////////////////////////////////////////////////////////////////////////////////
		CommandeBean commandeBean = new CommandeBean(pProduitId);
		ResponseEntity<CommandeBean> commandeBeanResponseEntity = this.commandeProxy.creer(commandeBean);
		CommandeBean commandeBeanCreated = commandeBeanResponseEntity.getBody();
		
		LOGGER.info("CLASS : CommandeServiceImpl -- METHOD : creer -- END");
		return commandeBeanCreated;
	}

	@Override
	public CommandeBean completer(Long pCommandeId
								, Double pProduitPrix
								, Integer pCommandeQuantite) {
		
		LOGGER.info("CLASS : CommandeServiceImpl -- METHOD : completer -- BEGIN");

		//////////////////////////////////////////////////////////////////////////////////////////////
		//(01.)EFFECTUER L'OPERATION CI-DESSOUS :
		//     ->OPERATION : Rechercher la commande (par son attribut "id").
		//////////////////////////////////////////////////////////////////////////////////////////////
		Optional<CommandeBean> commandeBeanOptional = this.commandeProxy.rechercherParId(pCommandeId);

		//////////////////////////////////////////////////////////////////////////////////////////////
		//(02.)TRAITER LE CAS D'ERREUR CI-DESSOUS :
		//     ->CAS D'ERREUR : La commande n'a pas été trouvée.
		//     ->TRAITEMENT : Ajouter un message d'erreur dans le modèle.
		//////////////////////////////////////////////////////////////////////////////////////////////
		if (!commandeBeanOptional.isPresent()) {
			LOGGER.info("CLASS : CommandeServiceImpl -- METHOD : completer -- END");
			throw new CommandeNotFoundException(MESSAGE__COMMANDE_PAYER__COMMANDE_INTROUVABLE);
		}
		//////////////////////////////////////////////////////////////////////////////////////////////
		//(03.)TRAITER LE CAS NOMINAL CI-DESSOUS :
		//     ->CAS NOMINAL : La commande a été trouvée.
		//     ->TRAITEMENTS : 
		//        ->(03.01.)Calculer le montant de la commmande.
		//        ->(03.02.)Mettre à jour la commmande trouvée (ses attributs "quantite" et "montant").
		//        ->(03.03.)Sauvegarder la commande trouvée.
		//////////////////////////////////////////////////////////////////////////////////////////////
		//Double montant = pProduitPrix*pCommandeQuantite;

		CommandeBean commandeBean = commandeBeanOptional.get();
		commandeBean.setQuantite(pCommandeQuantite);
		//commandeBean.setMontant(montant);
		
		ResponseEntity<CommandeBean> commandeBeanResponseEntity = this.commandeProxy.completer(pProduitPrix, commandeBean);
		CommandeBean  commandeBeanUpdated = commandeBeanResponseEntity.getBody();
		
		LOGGER.info("CLASS : CommandeServiceImpl -- METHOD : completer -- END");
		return commandeBeanUpdated;
	}

	@Override
	public CommandeBean finaliser(CommandeBean pCommandeBean) {
		
		LOGGER.info("CLASS : CommandeServiceImpl -- METHOD : finaliser -- BEGIN");
		
		///////////////////////////////////////////////////////////////////////
		//(01.)TRAITER LES CAS D'ERREUR SUIVANTS :
		//
		//     CAS D'ERREUR : L'UNE DES CONDITIONS CI-DESSOUS EST SATISFAITE.
		//       ->L'OBJET FOURNI "CommandeBean" EST NULL.
		//       ->OBJET "CommandeBean" EXISTE DEJA, AVEC LA MEME VALEUR DE L'ATTRIBUT "id".
		///////////////////////////////////////////////////////////////////////
		if (pCommandeBean == null) {
			LOGGER.info("ERROR : [" + MESSAGE__COMMANDE_FINALISER__COMMANDE_NON_VALIDE + "]");
			LOGGER.info("CLASS : CommandeServiceImpl -- METHOD : finaliser -- END");
			throw new CommandeNotValidException(MESSAGE__COMMANDE_FINALISER__COMMANDE_NON_VALIDE);
		}
		//////////////////////////////////////////////////////////////////////////////////////////////
		//(02.)EFFECTUER L'OPERATION CI-DESSOUS :
		//
		//     ->OPERATION : RECHERCHER UNE COMMANDE D'APRES SON ATTRIBUT "id".<br/>
		//////////////////////////////////////////////////////////////////////////////////////////////
		CommandeBean commandeBean = this.rechercherParId(pCommandeBean.getId());
		
		//////////////////////////////////////////////////////////////////////////////////////////////
		//(03.)EFFECTUER LES OPERATIONS CI-DESSOUS :
		//
		//     ->MODIFIER LA COMMANDE (ATTRIBUT "estPaye" : true).<br/>
		//     ->SAUVEGARDER LA COMMANDE.<br/>
		//////////////////////////////////////////////////////////////////////////////////////////////
		//commandeBean.setEstPaye(true);
		this.commandeProxy.finaliser(commandeBean);
		
		LOGGER.info("CLASS : CommandeServiceImpl -- METHOD : finaliser -- END");
		return commandeBean;
	}
}
