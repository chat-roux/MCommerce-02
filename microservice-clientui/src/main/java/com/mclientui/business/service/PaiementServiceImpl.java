package com.mclientui.business.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mclientui.business.exception.ClientUi4XXException;
import com.mclientui.feign.bean.CommandeBean;
import com.mclientui.feign.bean.PaiementBean;
import com.mclientui.feign.proxy.MicroServiceCommandeProxy;
import com.mclientui.feign.proxy.MicroServicePaiementProxy;


/**
 * <b>COMPOSANT QUI IMPLEMENTE LES FONCTIONNALITES SUIVANTES :</b><br/>
 *    ->LES TRAITEMENTS METIER RELATIFS A L'ENTITE 'Paiement'.<br/>
 *    
 * @author 1603599
 */
@Service
public class PaiementServiceImpl implements PaiementService {

	
	private static final String MESSAGE__PAIEMENT_CREER__COMMANDE_INTROUVABLE = "Creer un paiement -- Commande introuvable";
	
	
	/**
	 * <b>OBJET QUI POSSEDE LES FONCTIONNALITES SUIVANTES : </b> <br/>
	 * <br/>
	 * Les fonctionnalites d'ecriture de messages de log dans la console.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(PaiementServiceImpl.class);
	
	
    /**
	 * <b>PROXY PERMETTANT DE REQUETER LA RESSOURCE CI-DESSOUS :</b><br/>
	 * <br/>
	 * ->RESSOURCE : 'commande'.<br/> 
	 */    
    @Autowired
    private MicroServiceCommandeProxy commandeProxy;
	
	/**
	 * <b>PROXY PERMETTANT DE REQUETER LA RESSOURCE CI-DESSOUS :</b><br/>
	 * <br/>
	 * ->RESSOURCE : 'paiement'.<br/> 
	 */    
    @Autowired
    private MicroServicePaiementProxy paiementProxy;

    
	@Override
	public PaiementBean creer(Long pCommandeId
							, Double pMontant
							, Long pCarteNumero) {
		
		LOGGER.info("CLASS : PaiementServiceImpl -- METHOD : creer -- BEGIN");
		
		//////////////////////////////////////////////////////////////////////////////////////////////
		//(01.)EFFECTUER LES OPERATIONS CI-DESSOUS :
		//     ->OPERATIONS : 
    	//         ->Rechercher la commande (critere de recherche : l'attribut "id").
		//////////////////////////////////////////////////////////////////////////////////////////////
    	Optional<CommandeBean> commandeBeanOptional = this.commandeProxy.rechercherParId(pCommandeId);
    	
		//////////////////////////////////////////////////////////////////////////////////////////////
		//(02.)TRAITER LES CAS D'ERREUR CI-DESSOUS :
		//     ->CAS D'ERREUR : La commande n'a pas été trouvée.
		//     ->TRAITEMENT : Ajouter un message d'erreur dans le modèle.
		//////////////////////////////////////////////////////////////////////////////////////////////
		if (!commandeBeanOptional.isPresent()) {
			LOGGER.info("CLASS : PaiementServiceImpl -- METHOD : creer -- END");
			throw new ClientUi4XXException(MESSAGE__PAIEMENT_CREER__COMMANDE_INTROUVABLE);
		}
		//////////////////////////////////////////////////////////////////////////////////////////////
		//(03.)TRAITER LE CAS NOMINAL CI-DESSOUS :
		//     ->CAS NOMINAL : La commande et le paiement ont été trouvés.
		//     ->TRAITEMENTS : 
		//        ->(03.01.)Créer un paiement (en alimentant ses attributs "montant", "carteNumero" et "commandeId").
		//        ->(03.02.)Sauvegarder le paiement créé.
		//        ->(03.03.)Ajouter les objets suivants dans le modèle : "paiement".
		//////////////////////////////////////////////////////////////////////////////////////////////
		PaiementBean paiementBean = new PaiementBean(pMontant, pCarteNumero, pCommandeId);
		ResponseEntity<PaiementBean> paiementBeanResponseEntity = this.paiementProxy.creer(paiementBean);

		PaiementBean paiementBeanCreated = paiementBeanResponseEntity.getBody();
		
		LOGGER.info("CLASS : PaiementServiceImpl -- METHOD : creer -- END");
		return paiementBeanCreated;
	}
}
