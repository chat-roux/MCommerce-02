package com.mpaiement.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mpaiement.business.exception.PaiementAlreadyExistsException;
import com.mpaiement.business.exception.PaiementNotValidException;
import com.mpaiement.business.service.PaiementService;
import com.mpaiement.persistence.model.Paiement;

/**
 * <b>COMPOSANT IMPLEMENTANT LES FONCTIONNALITES CI-DESSOUS:</b><br/>
 * ->LES FONCTIONNALITES D'INTERFACE REST ET DE NAVIGATION SUR LA SECTION FONCTIONNELLE CONCERNEE<br/>
 * ->SECTION FONCTIONNELLE : 'paiement'.<br/> 
 */    
@RestController
public class PaiementControllerImpl implements PaiementController {

	
	private static final String MESSAGE__PAIEMENT_CREER__PAIEMENT_NON_VALIDE = "Creer un paiement -- Paiement non valide";
	private static final String MESSAGE__PAIEMENT_CREER__PAIEMENT_EXISTE_DEJA = "Creer un paiement -- Paiement existe déjà";
	


	/**
	 * <b>OBJET QUI POSSEDE LES FONCTIONNALITES SUIVANTES : </b> <br/>
	 * <br/>
	 * Les fonctionnalites d'ecriture de messages de log dans la console.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(PaiementControllerImpl.class);


	/**
	 * <b>COMPOSANT METIER RELATIF A L'ENTITE CI-DESSOUS :</b><br/>
	 * <br/>
	 * ->ENTITE : 'Paiement'.<br/> 
	 */    
    @Autowired
    private PaiementService paiementService;

    
    /**
	 * <b>EFFECTUER L'OPERATION SPECIFIEE CI-DESSOUS :</b><br/>
	 * <br/>
	 * ->OPERATION A EFFECTUER : CREER UN PAIEMENT DANS LES DONNEES PERSISTANTES.<br/> 
	 * ->ENTITE A UTILISER     : L'ENTITE "Paiement".<br/> 
	 *   
	 * @param pPaiement Le paiement à ajouter.   
	 * @return ResponseEntity<Paiement> La réponse résultant de cette création.
	 */
    @RequestMapping(value = "/paiement", method=RequestMethod.POST)
    public ResponseEntity<Paiement> creer(@RequestBody Paiement pPaiement){

		LOGGER.info("CLASS : PaiementController -- METHOD : creer -- BEGIN");

		///////////////////////////////////////////////////////////////////////
		//(01.)TRAITER LES CAS D'ERREUR SUIVANTS :
		//
		//     CAS D'ERREUR : L'UNE DES CONDITIONS CI-DESSOUS EST SATISFAITE.
		//       ->L'OBJET FOURNI "Paiement" EST NULL.
		//       ->L'OBJET FOURNI "Paiement" EXISTE DEJA (= SON ATTRIBUT "id" EST NON NULL).
		///////////////////////////////////////////////////////////////////////
		if (pPaiement == null) {
    		LOGGER.info("ERROR : [" + MESSAGE__PAIEMENT_CREER__PAIEMENT_NON_VALIDE + "]");
    		LOGGER.info("CLASS : PaiementController -- METHOD : creer -- END");
			throw new PaiementNotValidException(MESSAGE__PAIEMENT_CREER__PAIEMENT_NON_VALIDE);
		}
		if (pPaiement.getId() != null) {
    		LOGGER.info("ERROR : [" + MESSAGE__PAIEMENT_CREER__PAIEMENT_EXISTE_DEJA + "]");
    		LOGGER.info("CLASS : PaiementController -- METHOD : creer -- END");
			throw new PaiementAlreadyExistsException(MESSAGE__PAIEMENT_CREER__PAIEMENT_EXISTE_DEJA);
		}
		//////////////////////////////////////////////////////////////////////////////////////////////
		//(02.)EFFECTUER L'OPERATION CI-DESSOUS :
		//
		//     ->OPERATION PRINCIPALE   : CREER UN PAIEMENT.<br/>
		//     ->OPERATION PRELIMINAIRE : RECHERCHER UN PAIEMENT D'APRES SON ATTRIBUT "commandeId".<br/>
		//     ->TRAITEMENT D'ERREUR    : CAS OU UN PAIEMENT EXISTE DEJA AVEC LA MEME VALEUR DE L'ATTRIBUT "commandeId".<br/>
		//////////////////////////////////////////////////////////////////////////////////////////////
		Paiement paiementCree = this.paiementService.creer(pPaiement);

		ResponseEntity<Paiement> paiementResponseEntity = new ResponseEntity<Paiement>(paiementCree, HttpStatus.CREATED);

		LOGGER.info("CLASS : PaiementController -- METHOD : creer -- END");
        return paiementResponseEntity;
    }
}
