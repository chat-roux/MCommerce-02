package com.mpaiement.business.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mpaiement.business.exception.PaiementAlreadyExistsException;
import com.mpaiement.business.exception.PaiementCreateNotPossibleException;
import com.mpaiement.business.exception.PaiementNotValidException;
import com.mpaiement.persistence.dao.PaiementDao;
import com.mpaiement.persistence.model.Paiement;

/**
 * <b>COMPOSANT QUI IMPLEMENTE LES FONCTIONNALITES SUIVANTES :</b><br/>
 *    ->LES TRAITEMENTS METIER RELATIFS A L'ENTITE 'Paiement'.<br/>
 *    
 * @author 1603599
 */
@Service
public class PaiementServiceImpl implements PaiementService {

	
	private static final String MESSAGE__PAIEMENT_CREER__PAIEMENT_NON_VALIDE = "Creer un paiement -- Paiement non valide";
	private static final String MESSAGE__PAIEMENT_CREER__PAIEMENT_EXISTE_DEJA = "Creer un paiement -- Paiement existe déjà";
	private static final String MESSAGE__PAIEMENT_CREER__CREATION_IMPOSSIBLE = "Créer un paiement -- Création impossible";

	
	/**
	 * <b>OBJET QUI POSSEDE LES FONCTIONNALITES SUIVANTES : </b> <br/>
	 * <br/>
	 * Les fonctionnalites d'ecriture de messages de log dans la console.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(PaiementServiceImpl.class);
	

	/**
	 * <b>COMPOSANT DE PERSISTANCE RELATIF A L'ENTITE CI-DESSOUS :</b><br/>
	 * <br/>
	 * ->ENTITE : 'Paiement'.<br/> 
	 */    
    @Autowired
    private PaiementDao paiementDao;

    
	@Override
	public Paiement creer(Paiement pPaiement) {
		
		LOGGER.info("CLASS : PaiementServiceImpl -- METHOD : creer -- BEGIN");

		///////////////////////////////////////////////////////////////////////
		//(01.)TRAITER LES CAS D'ERREUR SUIVANTS :
		//
		//     CAS D'ERREUR : L'UNE DES CONDITIONS CI-DESSOUS EST SATISFAITE.
		//       ->L'OBJET FOURNI "Paiement" EST NULL.
		//       ->DANS L'OBJET FOURNI "Paiement", L'ATTRIBUT "id" EST NON-NULL.
		///////////////////////////////////////////////////////////////////////
		if ((pPaiement == null) || (pPaiement.getId() != null)) {
			LOGGER.info("ERROR : [" + MESSAGE__PAIEMENT_CREER__PAIEMENT_NON_VALIDE + "]");
			LOGGER.info("CLASS : PaiementServiceImpl -- METHOD : creer -- END");
			throw new PaiementNotValidException(MESSAGE__PAIEMENT_CREER__PAIEMENT_NON_VALIDE);
		}
		//////////////////////////////////////////////////////////////////////////////////////////////
		//(02.)EFFECTUER L'OPERATION CI-DESSOUS :
		//
		//     ->OPERATIONS A EFFECTUER : RECHERCHER UNE ENTITE "Paiement". 
		//     ->CRITERE DE RECHERCHE   : L'ATTRIBUT "commandeId" DE L'ENTITE "Paiement".
		//     ->VALEUR A UTILISER      : L'ATTRIBUT "commandeId" FOURNI. 
		//////////////////////////////////////////////////////////////////////////////////////////////
        Optional<Paiement> paiementOptional = this.paiementDao.findByCommandeId(pPaiement.getCommandeId());

		///////////////////////////////////////////////////////////////////////
		//(03.)TRAITER LES CAS D'ERREUR SUIVANTS :
		//
		//     ->CAS D'ERREUR : L'OBJET TROUVE "Paiement" EST NON-VIDE.
		//     ->TRAITEMENT   : LANCER UNE EXCEPTION SPECIFIQUE AU PROBLEME RENCONTRE.
		///////////////////////////////////////////////////////////////////////
        if(paiementOptional.isPresent()) {
			LOGGER.info("ERROR : [" + MESSAGE__PAIEMENT_CREER__PAIEMENT_EXISTE_DEJA + "]");
    		LOGGER.info("CLASS : PaiementServiceImpl -- METHOD : creer -- END");
        	throw new PaiementAlreadyExistsException(MESSAGE__PAIEMENT_CREER__PAIEMENT_EXISTE_DEJA);
        }	
		//////////////////////////////////////////////////////////////////////////////////////////////
		//(04.)TRAITER LE CAS NOMINAL CI-DESSOUS :
        //
		//     ->CAS NOMINAL : Le paiement n'a pas été trouvé.
		//     ->TRAITEMENT  : Sauvegarder le paiement fourni.
		//////////////////////////////////////////////////////////////////////////////////////////////
        Paiement paiementCree = this.paiementDao.save(pPaiement);
        
        if(paiementCree == null) {
			LOGGER.info("ERROR : [" + MESSAGE__PAIEMENT_CREER__CREATION_IMPOSSIBLE + "]");
    		LOGGER.info("CLASS : PaiementController -- METHOD : creer -- END");
        	throw new PaiementCreateNotPossibleException(MESSAGE__PAIEMENT_CREER__CREATION_IMPOSSIBLE);
        }
		LOGGER.info("CLASS : PaiementServiceImpl -- METHOD : creer -- END");
		return paiementCree;
	}
}
