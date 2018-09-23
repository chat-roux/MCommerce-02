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

import com.mpaiement.business.exception.PaiementUnprocessableEntityException;
import com.mpaiement.business.service.CommandeService;
import com.mpaiement.business.service.PaiementService;
import com.mpaiement.feign.bean.CommandeBean;
import com.mpaiement.persistence.model.Paiement;

/**
 * <b>COMPOSANT POSSEDANT LES FONCTIONNALITES CI-DESSOUS:</b><br/>
 * ->LES FONCTIONNALITES D'INTERFACE REST ET DE NAVIGATION SUR LA SECTION FONCTIONNELLE CONCERNEE<br/>
 * ->SECTION FONCTIONNELLE : 'paiement'.<br/> 
 */    
@RestController
public class PaiementController {

	
	private static final String MESSAGE__PAIEMENT_CREER__PAIEMENT_NON_VALIDE = "Creer un paiement -- Paiement non valide";

	
	/**
	 * <b>OBJET QUI POSSEDE LES FONCTIONNALITES SUIVANTES : </b> <br/>
	 * <br/>
	 * Les fonctionnalites d'ecriture de messages de log dans la console.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(PaiementController.class);
	
	
	/**
	 * <b>COMPOSANT METIER RELATIF A L'ENTITE CI-DESSOUS :</b><br/>
	 * <br/>
	 * ->ENTITE : 'Paiement'.<br/> 
	 */    
    @Autowired
    private PaiementService paiementService;

	/**
	 * <b>COMPOSANT METIER RELATIF A L'ENTITE CI-DESSOUS :</b><br/>
	 * <br/>
	 * ->ENTITE : 'Commande'.<br/> 
	 */    
    @Autowired
    private CommandeService commandeService;

    
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
		//       ->DANS L'OBJET FOURNI "Paiement", L'ATTRIBUT "id" EST NON-NULL.
		///////////////////////////////////////////////////////////////////////
		if ((pPaiement == null) || (pPaiement.getId() != null)) {
    		LOGGER.info("CLASS : PaiementController -- METHOD : creer -- END");
			throw new PaiementUnprocessableEntityException(MESSAGE__PAIEMENT_CREER__PAIEMENT_NON_VALIDE);
		}
		//////////////////////////////////////////////////////////////////////////////////////////////
		//(02.)EFFECTUER L'OPERATION CI-DESSOUS :
        //
		//     ->OPERATIONS A EFFECTUER : RECHERCHER UNE ENTITE "Commande". 
		//     ->CRITERE DE RECHERCHE   : L'ATTRIBUT "id" DE L'ENTITE "Commande".
		//     ->VALEUR A UTILISER      : L'ATTRIBUT "commandeId" DE L'ENTITE "Paiement" FOURNIE. 
		//////////////////////////////////////////////////////////////////////////////////////////////
		CommandeBean commandeBeanTrouve = this.commandeService.rechercherParId(pPaiement.getCommandeId());
		
		//////////////////////////////////////////////////////////////////////////////////////////////
		//(03.)EFFECTUER L'OPERATION CI-DESSOUS :
		//
		//     ->OPERATION PRINCIPALE   : CREER UN PAIEMENT.<br/>
		//     ->OPERATION PRELIMINAIRE : RECHERCHER UN PAIEMENT D'APRES SON ATTRIBUT "commandeId".<br/>
		//     ->TRAITEMENT D'ERREUR    : CAS OU UN PAIEMENT EXISTE DEJA AVEC LA MEME VALEUR DE L'ATTRIBUT "commandeId".<br/>
		//////////////////////////////////////////////////////////////////////////////////////////////
		Paiement paiementCree = this.paiementService.creer(pPaiement);

		//////////////////////////////////////////////////////////////////////////////////////////////
		//(04.)TRAITER LE CAS NOMINAL CI-DESSOUS :
        //
		//     ->CAS NOMINAL : 
        //        ->Le paiement n'a pas été trouvé.
        //        ->La commande a été trouvée.
        //
		//     ->TRAITEMENTS : Finaliser la commande.
		//////////////////////////////////////////////////////////////////////////////////////////////
        this.commandeService.finaliser(commandeBeanTrouve);
        
        ResponseEntity<Paiement> paiementResponseEntity = new ResponseEntity<Paiement>(paiementCree, HttpStatus.CREATED);
        
		LOGGER.info("CLASS : PaiementController -- METHOD : creer -- END");
        return paiementResponseEntity;
    }
}
