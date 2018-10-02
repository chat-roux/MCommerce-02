package com.mcommande.web.controller;


import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mcommande.business.exception.CommandeCreateNotPossibleException;
import com.mcommande.business.exception.CommandeNotFoundException;
import com.mcommande.business.exception.CommandeUnprocessableEntityException;
import com.mcommande.configuration.ApplicationPropertiesConfiguration;
import com.mcommande.persistence.dao.CommandeDao;
import com.mcommande.persistence.model.Commande;

/**
 * <b>COMPOSANT POSSEDANT LES FONCTIONNALITES CI-DESSOUS:</b><br/>
 * ->LES FONCTIONNALITES D'INTERFACE REST ET DE NAVIGATION SUR LA SECTION FONCTIONNELLE CONCERNEE<br/>
 * ->SECTION FONCTIONNELLE : 'commande'.<br/> 
 */    
@RestController
public class CommandeController {

	
	private static final String MESSAGE__COMMANDE_CREER__COMMANDE_NON_VALIDE = "Creer une commande -- Commande non valide";
	private static final String MESSAGE__COMMANDE_CREER__CREATION_IMPOSSIBLE = "Creer une commande -- Creation impossible";
	private static final String MESSAGE__COMMANDE_MODIFIER__COMMANDE_NON_VALIDE = "Modifier une commande -- Commande non valide";
	private static final String MESSAGE__COMMANDE_MODIFIER__MODIFICATION_IMPOSSIBLE = "Modifier une commande -- Modification impossible";
	private static final String MESSAGE__COMMANDE_RECHERCHER_PAR_ID__COMMANDE_INTROUVABLE = "Rechercher une commande par id -- Commande introuvable";
	
	
	/**
	 * <b>OBJET QUI POSSEDE LES FONCTIONNALITES SUIVANTES : </b> <br/>
	 * <br/>
	 * Les fonctionnalites d'ecriture de messages de log dans la console.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(CommandeController.class);

	/**
	 * <b>COMPOSANT DE PERSISTANCE RELATIF A L'ENTITE CI-DESSOUS :</b><br/>
	 * <br/>
	 * ->ENTITE : 'commande'.<br/> 
	 */    
	@Autowired
    CommandeDao commandeDao;
	
	/**
	 * <b>COMPOSANT DE CONFIGURATION DE L'APPLICATION</b><br/>
	 * <br/>
	 */    
	@Autowired
	ApplicationPropertiesConfiguration applicationPropertiesConfiguration;


    /**
	 * <b>EFFECTUER L'OPERATION SPECIFIEE CI-DESSOUS :</b><br/>
	 * <br/>
	 * ->OPERATION A EFFECTUER : CREER UNE COMMANDE DANS LES DONNEES PERSISTANTES.<br/> 
	 * ->ENTITE A UTILISER     : L'ENTITE "Commande".<br/> 
	 *   
	 * @param pCommande La commande à utiliser.
	 * @return ResponseEntity<Commande> La réponse résultant de cette création.
	 */
    @RequestMapping(value="/commande", method=RequestMethod.POST)
    public ResponseEntity<Commande> creer(@RequestBody Commande pCommande){

		LOGGER.info("CLASS : CommandeController -- METHOD : creer -- BEGIN");
		
		///////////////////////////////////////////////////////////////////////
		//(01.)TRAITER LES CAS D'ERREUR SUIVANTS :
		//
		//     CAS D'ERREUR : L'UNE DES CONDITIONS CI-DESSOUS EST SATISFAITE.
		//       ->L'OBJET FOURNI "Commande" EST NULL.
		//       ->DANS L'OBJET FOURNI "Commande", L'ATTRIBUT "id" EST NON-NULL.
		///////////////////////////////////////////////////////////////////////
		if ((pCommande == null) || (pCommande.getId() != null)) {
			LOGGER.info("ERROR : [" + MESSAGE__COMMANDE_CREER__COMMANDE_NON_VALIDE + "]");
			LOGGER.info("CLASS : CommandeController -- METHOD : creer -- END");
			throw new CommandeUnprocessableEntityException(MESSAGE__COMMANDE_CREER__COMMANDE_NON_VALIDE);
		}
		///////////////////////////////////////////////////////////////////////
		//(02.)TRAITER LE CAS NOMINAL SUIVANT :
		//
		//     CAS NOMINAL : AUCUNE DES CONDITIONS CI-DESSOUS N'EST SATISFAITE.
		//       ->L'OBJET FOURNI "Commande" EST NULL.
		//       ->DANS L'OBJET FOURNI "Commande", L'ATTRIBUT "id" EST NON-NULL.
		//
		//     TRAITEMENT A EFFECTUER :
		//       ->CREER L'OBJET "Commande" (EN UTILISANT L'OBJET "Commande" FOURNI).
		///////////////////////////////////////////////////////////////////////
        Commande commandeCreee = commandeDao.save(pCommande);

        if(commandeCreee == null) {
    		LOGGER.info("ERROR : [" + MESSAGE__COMMANDE_CREER__CREATION_IMPOSSIBLE + "]");
    		LOGGER.info("CLASS : CommandeController -- METHOD : creer -- END");
        	throw new CommandeCreateNotPossibleException(MESSAGE__COMMANDE_CREER__CREATION_IMPOSSIBLE);
        }
        ResponseEntity<Commande> responseEntity = new ResponseEntity<Commande>(pCommande, HttpStatus.CREATED);
		LOGGER.info("CLASS : CommandeController -- METHOD : creer -- END");
        return responseEntity;
    }

    /**
	 * <b>EFFECTUER L'OPERATION SPECIFIEE CI-DESSOUS :</b><br/>
	 * <br/>
	 * ->OPERATION A EFFECTUER : MODIFIER UNE COMMANDE DANS LES DONNEES PERSISTANTES.<br/> 
	 * ->ENTITE A UTILISER     : L'ENTITE "Commande".<br/> 
	 *   
	 * @param pCommande La commande à utiliser.   
	 * @return ResponseEntity<Commande> La réponse résultant de cette modification.
	 */
    @RequestMapping(value="/commande", method=RequestMethod.PUT)
    public ResponseEntity<Commande> modifier(@RequestBody Commande pCommande){

		LOGGER.info("CLASS : CommandeController -- METHOD : modifier -- BEGIN");
		
		///////////////////////////////////////////////////////////////////////
		//(01.)TRAITER LES CAS D'ERREUR SUIVANTS :
		//
		//     CAS D'ERREUR : L'UNE DES CONDITIONS CI-DESSOUS EST SATISFAITE.
		//       ->L'OBJET FOURNI "Commande" EST NULL.
		//       ->DANS L'OBJET FOURNI "Commande", L'ATTRIBUT "id" EST NULL.
		///////////////////////////////////////////////////////////////////////
		if ((pCommande == null) || (pCommande.getId() == null)) {
			LOGGER.info("ERROR : [" + MESSAGE__COMMANDE_MODIFIER__COMMANDE_NON_VALIDE + "]");
			LOGGER.info("CLASS : CommandeController -- METHOD : modifier -- END");
			throw new CommandeUnprocessableEntityException(MESSAGE__COMMANDE_MODIFIER__COMMANDE_NON_VALIDE);
		}
		///////////////////////////////////////////////////////////////////////
		//(02.)TRAITER LE CAS NOMINAL SUIVANT :
		//
		//     CAS NOMINAL : AUCUNE DES CONDITIONS CI-DESSOUS N'EST SATISFAITE.
		//       ->L'OBJET FOURNI "Commande" EST NULL.
		//       ->DANS L'OBJET FOURNI "Commande", L'ATTRIBUT "id" EST NULL.
		//
		//     TRAITEMENT A EFFECTUER :
		//       ->MODIFIER L'OBJET "Commande" (EN UTILISANT L'OBJET "Commande" FOURNI).
		///////////////////////////////////////////////////////////////////////
        Commande commandeModifiee = commandeDao.save(pCommande);

        if(commandeModifiee == null) {
    		LOGGER.info("ERROR : [" + MESSAGE__COMMANDE_MODIFIER__MODIFICATION_IMPOSSIBLE + "]");
    		LOGGER.info("CLASS : CommandeController -- METHOD : modifier -- END");
        	throw new CommandeCreateNotPossibleException(MESSAGE__COMMANDE_MODIFIER__MODIFICATION_IMPOSSIBLE);
        }
        ResponseEntity<Commande> responseEntity = new ResponseEntity<Commande>(pCommande, HttpStatus.OK);
		LOGGER.info("CLASS : CommandeController -- METHOD : modifier -- END");
        return responseEntity;
    }
    
    /**
	 * <b>EFFECTUER L'OPERATION DE RECHERCHE CI-DESSOUS :</b><br/>
	 * <br/>
	 * ->(01.)CRITERE DE RECHERCHE : L'ATTRIBUT 'id'.<br/> 
	 * ->(02.)CONDITION A SATISFAIRE : L'ATTRIBUT 'id' EST EGAL A LA VALEUR FOURNIE.<br/>
	 *    
	 * @param pId L'attribut 'id' de l'objet 'Produit' a rechercher.
	 * @return Optional<Commande> L'objet 'Commande' trouve.
	 */
    @RequestMapping(value="/commande/{id}", method=RequestMethod.GET)
    public Optional<Commande> rechercherParId(@PathVariable(name="id") Long pId){

		LOGGER.info("CLASS : CommandeController -- METHOD : rechercherParId -- BEGIN");
		
		///////////////////////////////////////////////////////////////////////
		//(01.)EFFECTUER L'OPERATION SUIVANTE :
		//
		//     ->OPERATION A EFFECTUER : RECHERCHER UNE ENTITE "Commande".
		//     ->CRITERE DE RECHERCHE : DANS L'OBJET "Commande", L'ATTRIBUT "id".
		//     ->VALEUR RECHERCHEE    : LE PARAMETRE FOURNI 'pId'.
		///////////////////////////////////////////////////////////////////////
        Optional<Commande> commandeOptional = commandeDao.findById(pId);

		///////////////////////////////////////////////////////////////////////
		//(02.)TRAITER LE CAS D'ERREUR SUIVANT :
		//
		//     CAS D'ERREUR : LA CONDITION CI-DESSOUS EST SATISFAITE.
		//       ->L'OBJET TROUVE "Commande" EST VIDE.
		///////////////////////////////////////////////////////////////////////
        if(!commandeOptional.isPresent()) {
    		LOGGER.info("ERROR : [" + MESSAGE__COMMANDE_RECHERCHER_PAR_ID__COMMANDE_INTROUVABLE + "]");
    		LOGGER.info("CLASS : CommandeController -- METHOD : rechercherParId -- END");
        	throw new CommandeNotFoundException(MESSAGE__COMMANDE_RECHERCHER_PAR_ID__COMMANDE_INTROUVABLE + " -- " + "Commande-id : [" + pId + "]");
        }
		LOGGER.info("CLASS : CommandeController -- METHOD : rechercherParId -- END");
        return commandeOptional;
    }
}
