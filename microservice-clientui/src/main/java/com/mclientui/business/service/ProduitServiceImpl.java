package com.mclientui.business.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mclientui.business.exception.ClientUi4XXException;
import com.mclientui.feign.bean.ProduitBean;
import com.mclientui.feign.proxy.MicroServiceProduitProxy;


/**
 * <b>COMPOSANT QUI IMPLEMENTE LES FONCTIONNALITES SUIVANTES :</b><br/>
 *    ->LES TRAITEMENTS METIER RELATIFS A L'ENTITE 'Produit'.<br/>
 *    
 * @author 1603599
 */
@Service
public class ProduitServiceImpl implements ProduitService {
	
	
	private static final String MESSAGE__PRODUIT_RECHERCHER_TOUS__PRODUITS_INTROUVABLES = "Rechercher tous les produits -- Produits introuvables";
	private static final String MESSAGE__PRODUIT_RECHERCHER_PAR_ID__PRODUIT_INTROUVABLE = "Rechercher un produit par id -- Produit introuvable";

	
	/**
	 * <b>OBJET QUI POSSEDE LES FONCTIONNALITES SUIVANTES : </b> <br/>
	 * <br/>
	 * Les fonctionnalites d'ecriture de messages de log dans la console.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ProduitServiceImpl.class);
	
	
	/**
	 * <b>PROXY PERMETTANT DE REQUETER LA RESSOURCE CI-DESSOUS :</b><br/>
	 * <br/>
	 * ->RESSOURCE : 'produit'.<br/> 
	 */    
    @Autowired
    private MicroServiceProduitProxy produitProxy;
    
	
	@Override
	public List<ProduitBean> rechercherTous() {
		
		LOGGER.info("CLASS : ProduitServiceImpl -- METHOD : rechercherTous -- BEGIN");
		
		//////////////////////////////////////////////////////////////////////////////////////////////
		//(01.)EFFECTUER LES OPERATIONS CI-DESSOUS :
		//     ->OPERATIONS : 
    	//         ->Rechercher tous les produits (critere de recherche : aucun).
		//////////////////////////////////////////////////////////////////////////////////////////////
		List<ProduitBean> productBeans =  this.produitProxy.rechercherTous();
		
		//////////////////////////////////////////////////////////////////////////////////////////////
		//(02.)TRAITER LE CAS D'ERREUR CI-DESSOUS :
		//     ->CAS D'ERREUR : Aucun produit n'a été trouvé.
		//     ->TRAITEMENT : Ajouter un message d'erreur dans le modèle.
		//////////////////////////////////////////////////////////////////////////////////////////////
		if (productBeans.isEmpty()) {
			LOGGER.info("CLASS : ProduitServiceImpl -- METHOD : rechercherTous -- END");
			throw new ClientUi4XXException(MESSAGE__PRODUIT_RECHERCHER_TOUS__PRODUITS_INTROUVABLES);
		}
		LOGGER.info("CLASS : ProduitServiceImpl -- METHOD : rechercherTous -- END");
		return productBeans;
	}

	@Override
	public ProduitBean rechercherParId(Long pId) {
		
		LOGGER.info("CLASS : ProduitServiceImpl -- METHOD : rechercherParId -- BEGIN");
		
		//////////////////////////////////////////////////////////////////////////////////////////////
		//(01.)EFFECTUER LES OPERATIONS CI-DESSOUS :
		//     ->OPERATIONS : 
    	//         ->Rechercher le produit (critere de recherche : l'attribut "id").
		//////////////////////////////////////////////////////////////////////////////////////////////
		Optional<ProduitBean> produitBeanOptional = this.produitProxy.rechercherParId(pId);
		
		//////////////////////////////////////////////////////////////////////////////////////////////
		//(02.)TRAITER LE CAS D'ERREUR CI-DESSOUS :
		//     ->CAS D'ERREUR : Le produit n'a pas été trouvé.
		//     ->TRAITEMENT : Ajouter un message d'erreur dans le modèle.
		//////////////////////////////////////////////////////////////////////////////////////////////
		if (!produitBeanOptional.isPresent()) {
			LOGGER.info("CLASS : ProduitServiceImpl -- METHOD : rechercherParId -- END");
			throw new ClientUi4XXException(MESSAGE__PRODUIT_RECHERCHER_PAR_ID__PRODUIT_INTROUVABLE);
		}
		//////////////////////////////////////////////////////////////////////////////////////////////
		//(03.)TRAITER LE CAS NOMINAL CI-DESSOUS :
		//     ->CAS NOMINAL : Le produit a été trouvé.
		//     ->TRAITEMENTS : 
		//        ->(03.01.)Ajouter les objets suivants dans le modèle : "produit".
		//////////////////////////////////////////////////////////////////////////////////////////////		
		ProduitBean produitBean = produitBeanOptional.get();
		
		LOGGER.info("CLASS : ProduitServiceImpl -- METHOD : rechercherParId -- END");
		return produitBean;
	}
}
