package com.mproduit.web.controller;

import com.mproduit.business.exception.ProduitNotFoundException;
import com.mproduit.configuration.ApplicationPropertiesConfiguration;
import com.mproduit.persistence.dao.ProduitDao;
import com.mproduit.persistence.model.Produit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


/**
 * <b>COMPOSANT POSSEDANT LES FONCTIONNALITES CI-DESSOUS:</b><br/>
 * ->LES FONCTIONNALITES D'INTERFACE REST ET DE NAVIGATION SUR LA SECTION FONCTIONNELLE CONCERNEE<br/>
 * ->SECTION FONCTIONNELLE : 'produit'.<br/> 
 */    
@RestController
public class ProduitController {

	
	private static final String MESSAGE__PRODUIT_FIND_ALL__NOT_AVAILABLE = "Recherche de tous les produits -- Produits indisponibles";
	private static final String MESSAGE__PRODUIT_FIND_BY_ID__NOT_FOUND = "Recherche d'un produit par id -- Produit introuvable";

	/**
	 * <b>OBJET QUI POSSEDE LES FONCTIONNALITES SUIVANTES : </b> <br/>
	 * <br/>
	 * Les fonctionnalites d'ecriture de messages de log dans la console.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ProduitController.class);
	
	
	/**
	 * <b>COMPOSANT DE CONFIGURATION DE L'APPLICATION</b><br/>
	 * <br/>
	 */    
	@Autowired
	private ApplicationPropertiesConfiguration applicationPropertiesConfiguration;
	
	
	/**
	 * <b>COMPOSANT DE PERSISTANCE RELATIF A L'ENTITE CI-DESSOUS :</b><br/>
	 * <br/>
	 * ->ENTITE : 'Produit'.<br/> 
	 */    
    @Autowired
    ProduitDao produitDao;


    /**
	 * <b>EFFECTUER L'OPERATION DE RECHERCHE CI-DESSOUS :</b><br/>
	 * <br/>
	 * ->(01.)CRITERE DE RECHERCHE : AUCUN.<br/> 
	 * ->(02.)CONDITION A SATISFAIRE : AUCUNE.<br/>
	 *    
	 * @return List<Produit> La liste de produits trouvee.
	 */
	@RequestMapping(value="/produit", method=RequestMethod.GET)
    public List<Produit> rechercherTous(){

		LOGGER.info("CLASS : ProduitController -- METHOD : rechercherTous -- BEGIN");
		
        List<Produit> produits = produitDao.findAll();

        if(produits.isEmpty()) {
    		LOGGER.info("CLASS : ProduitController -- METHOD : rechercherTous -- END");
        	throw new ProduitNotFoundException(MESSAGE__PRODUIT_FIND_ALL__NOT_AVAILABLE);
        } 
        
        List<Produit> produitsPartielle = produits.subList(0, applicationPropertiesConfiguration.getProduitNombreMax());
        
		LOGGER.info("CLASS : ProduitController -- METHOD : rechercherTous -- END");
        return produitsPartielle;
    }

	/**
	 * <b>EFFECTUER L'OPERATION DE RECHERCHE CI-DESSOUS :</b><br/>
	 * <br/>
	 * ->(01.)CRITERE DE RECHERCHE : L'ATTRIBUT 'id'.<br/> 
	 * ->(02.)CONDITION A SATISFAIRE : L'ATTRIBUT 'id' EST EGAL A LA VALEUR FOURNIE.<br/>
	 *    
	 * @param pId L'attribut 'id' de l'objet 'Produit' a rechercher.
	 * @return Produit L'objet 'Produit' trouve.
	 */
    @RequestMapping(value="/produit/{id}", method=RequestMethod.GET)
    public Optional<Produit> rechercherParId(@PathVariable(name="id") Long pId) {

		LOGGER.info("CLASS : ProduitController -- METHOD : rechercherParId -- BEGIN");

		Optional<Produit> produitTrouve = produitDao.findById(pId);

        if(!produitTrouve.isPresent()) {
    		LOGGER.info("CLASS : ProduitController -- METHOD : rechercherParId -- END");
        	throw new ProduitNotFoundException(MESSAGE__PRODUIT_FIND_BY_ID__NOT_FOUND + " -- " + "Produit-id : [" + pId + "]");
        }  
		LOGGER.info("CLASS : ProduitController -- METHOD : rechercherParId -- END");
        return produitTrouve;
    }
}
