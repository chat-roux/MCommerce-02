package com.mpaiement.business.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mpaiement.business.exception.CommandeNotFoundException;
import com.mpaiement.business.exception.PaiementUnprocessableEntityException;
import com.mpaiement.feign.bean.CommandeBean;
import com.mpaiement.feign.proxy.MicroServiceCommandeProxy;

/**
 * <b>COMPOSANT QUI IMPLEMENTE LES FONCTIONNALITES SUIVANTES :</b><br/>
 *    ->LES TRAITEMENTS METIER RELATIFS A L'ENTITE 'Commande'.<br/>
 *    
 * @author 1603599
 */
@Service
public class CommandeServiceImpl implements CommandeService {

	
	private static final String MESSAGE__COMMANDE_RECHERCHER_PAR_ID__COMMANDE_INTROUVABLE = "Rechercher une commande par id -- Commande introuvable";
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
    		LOGGER.info("CLASS : CommandeServiceImpl -- METHOD : rechercherParId -- END");
        	throw new CommandeNotFoundException(MESSAGE__COMMANDE_RECHERCHER_PAR_ID__COMMANDE_INTROUVABLE);
        }
        CommandeBean commandeBean = commandeBeanOptional.get();
        
		LOGGER.info("CLASS : CommandeServiceImpl -- METHOD : rechercherParId -- END");
		return commandeBean;
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
			LOGGER.info("CLASS : CommandeServiceImpl -- METHOD : finaliser -- END");
			throw new PaiementUnprocessableEntityException(MESSAGE__COMMANDE_FINALISER__COMMANDE_NON_VALIDE);
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
		commandeBean.setEstPaye(true);
		this.commandeProxy.modifier(commandeBean);
		
		LOGGER.info("CLASS : CommandeServiceImpl -- METHOD : finaliser -- END");
		return commandeBean;
	}
}
