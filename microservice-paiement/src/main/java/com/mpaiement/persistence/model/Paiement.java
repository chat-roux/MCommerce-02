package com.mpaiement.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <b>ENTITE REPRESENTANT L'OBJET CI-DESSOUS :</b><br/>
 *    ->OBJET : PAIEMENT<br/>
 *    <br/>
 * @author 1603599
 */
@Entity
public class Paiement {

	
	/**
	 * <b>OBJET QUI POSSEDE LES FONCTIONNALITES SUIVANTES : </b> <br/>
	 * <br/>
	 * Les fonctionnalites d'ecriture de messages de log dans la console.
	 */
	@Transient
	private static final Logger LOGGER = LoggerFactory.getLogger(Paiement.class);
	
    @Id
    @GeneratedValue
    private Long id;

    private Double montant;
    private Long carteNumero;
    
    @Column(unique = true)
    private Long commandeId;

    
    /**
     * <b>CONSTRUCTEUR SANS ARGUMENT</b><br/>
     */
    public Paiement() {
		LOGGER.info("CLASS : Paiement -- METHOD : CONSTRUCTEUR SANS ARGUMENT -- BEGIN");
		LOGGER.info("CLASS : Paiement -- METHOD : CONSTRUCTEUR SANS ARGUMENT -- END");
    }

    /**
     * <b>CONSTRUCTEUR AVEC 2 ARGUMENTS</b><br/>
     * 
     * @param pMontant Montant du paiement.
     * @param pCommandeId Id de la commande (associee au paiement).
     */
    public Paiement(Double pMontant, Long pCommandeId) {
    	
		LOGGER.info("CLASS : Paiement -- METHOD : CONSTRUCTEUR AVEC 2 ARGUMENTS -- BEGIN");
    	
        this.montant = pMontant;
        this.commandeId = pCommandeId;
        
		LOGGER.info("CLASS : Paiement -- METHOD : CONSTRUCTEUR AVEC 2 ARGUMENTS -- END");
    }
    
    /**
     * <b>CONSTRUCTEUR AVEC 3 ARGUMENTS</b><br/>
     * 
     * @param pMontant Montant du paiement.
     * @param pCarteNumero Numéro de la carte du paiement.
     * @param pCommandeId Id de la commande (associee au paiement).
     */
	public Paiement(Double pMontant, Long pCarteNumero, Long pCommandeId) {
		
		LOGGER.info("CLASS : Paiement -- METHOD : CONSTRUCTEUR AVEC 3 ARGUMENTS -- BEGIN");
		
        this.montant = pMontant;
        this.carteNumero = pCarteNumero;
        this.commandeId = pCommandeId;

		LOGGER.info("CLASS : Paiement -- METHOD : CONSTRUCTEUR AVEC 3 ARGUMENTS -- END");
	}

	
    public Long    getId         () { return this.id;          }
    public Double  getMontant    () { return this.montant;     }
    public Long    getCarteNumero() { return this.carteNumero; }
    public Long    getCommandeId () { return this.commandeId;  }


    public void setId         (Long    pId         ) { this.id          = pId;          }
    public void setMontant    (Double  pMontant    ) { this.montant     = pMontant;     }
    public void setCarteNumero(Long    pCarteNumero) { this.carteNumero = pCarteNumero; }
    public void setCommandeId (Long    pCommandeId ) { this.commandeId  = pCommandeId;  }
    
    
    @Override
    public String toString() {
        String result = "Paiement" + "{"
					               			   + "id"          + "=" + this.id
					               + "," + " " + "montant"     + "=" + this.montant
					               + "," + " " + "carteNumero" + "=" + this.carteNumero
					               + "," + " " + "commandeId"  + "=" + this.commandeId
					               + '}';
        return result;
    }
}
