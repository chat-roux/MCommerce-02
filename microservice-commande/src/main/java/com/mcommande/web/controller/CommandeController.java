package com.mcommande.web.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mcommande.persistence.model.Commande;

/**
 * <b>COMPOSANT EXPOSANT LES FONCTIONNALITES CI-DESSOUS:</b><br/>
 * ->LES FONCTIONNALITES D'INTERFACE REST ET DE NAVIGATION SUR LA SECTION FONCTIONNELLE CONCERNEE<br/>
 * ->SECTION FONCTIONNELLE : 'commande'.<br/> 
 */    
public interface CommandeController {

	
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
    public Optional<Commande> rechercherParId(@PathVariable(name="id") Long pId);

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
    public ResponseEntity<Commande> creer(@RequestBody Commande pCommande);

    /**
	 * <b>EFFECTUER L'OPERATION SPECIFIEE CI-DESSOUS :</b><br/>
	 * <br/>
	 * ->OPERATION A EFFECTUER : COMPLETER UNE COMMANDE DANS LES DONNEES PERSISTANTES.<br/> 
	 * ->ENTITE A UTILISER     : L'ENTITE "Commande".<br/> 
	 *   
	 * @param pProduitPrix Le prix de produit à utiliser.   
	 * @param pCommande La commande à utiliser.   
	 * @return ResponseEntity<Commande> La réponse résultant de cette modification.
	 */
	@RequestMapping(value="/commande/completer/{produitPrix}", method=RequestMethod.PATCH)
	public ResponseEntity<Commande> completer(@PathVariable(name="produitPrix") Double pProduitPrix
												, @RequestBody Commande pCommande);
	
    /**
	 * <b>EFFECTUER L'OPERATION SPECIFIEE CI-DESSOUS :</b><br/>
	 * <br/>
	 * ->OPERATION A EFFECTUER : FINALISER UNE COMMANDE DANS LES DONNEES PERSISTANTES.<br/> 
	 * ->ENTITE A UTILISER     : L'ENTITE "Commande".<br/> 
	 *   
	 * @param pCommande La commande à utiliser.   
	 * @return ResponseEntity<Commande> La réponse résultant de cette modification.
	 */
	@RequestMapping(value="/commande/finaliser", method=RequestMethod.PATCH)
	public ResponseEntity<Commande> finaliser(@RequestBody Commande pCommande);
}
