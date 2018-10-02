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


/**
 * <b>COMPOSANT QUI IMPLEMENTE LES FONCTIONNALITES SUIVANTES :</b><br/>
 *    ->LES TRAITEMENTS METIER RELATIFS A L'ENTITE 'Commande'.<br/>
 *    
 * @author 1603599
 */
@Service
public class CommandeServiceImpl implements CommandeService {

	private static final String MESSAGE__COMMANDE_CREER__PRODUIT_INTROUVABLE = "Créer une commande -- Produit introuvable";
	private static final String MESSAGE__COMMANDE_PAYER__COMMANDE_INTROUVABLE = "Payer une commande -- Commande introuvable";
	
	
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
		Double montant = pProduitPrix*pCommandeQuantite;

		CommandeBean commandeBean = commandeBeanOptional.get();
		commandeBean.setQuantite(pCommandeQuantite);
		commandeBean.setMontant(montant);
		
		ResponseEntity<CommandeBean> commandeBeanResponseEntity = this.commandeProxy.modifier(commandeBean);
		CommandeBean  commandeBeanUpdated = commandeBeanResponseEntity.getBody();
		
		LOGGER.info("CLASS : CommandeServiceImpl -- METHOD : completer -- END");
		return commandeBeanUpdated;
	}
}
