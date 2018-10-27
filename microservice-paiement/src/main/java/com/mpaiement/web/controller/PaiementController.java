package com.mpaiement.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mpaiement.persistence.model.Paiement;

/**
 * <b>COMPOSANT EXPOSANT LES FONCTIONNALITES CI-DESSOUS:</b><br/>
 * ->LES FONCTIONNALITES D'INTERFACE REST ET DE NAVIGATION SUR LA SECTION FONCTIONNELLE CONCERNEE<br/>
 * ->SECTION FONCTIONNELLE : 'paiement'.<br/> 
 */    
public interface PaiementController {


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
    public ResponseEntity<Paiement> creer(@RequestBody Paiement pPaiement);
}
