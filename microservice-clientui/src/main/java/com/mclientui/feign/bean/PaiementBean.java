package com.mclientui.feign.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * <b>BEAN REPRESENTANT L'OBJET CI-DESSOUS :</b><br/>
 *    ->OBJET : PAIEMENT<br/>
 *    <br/>
 * @author 1603599
 */
public class PaiementBean {

	
	/**
	 * <b>OBJET QUI POSSEDE LES FONCTIONNALITES SUIVANTES : </b> <br/>
	 * <br/>
	 * Les fonctionnalites d'ecriture de messages de log dans la console.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(PaiementBean.class);
	
    private Long id;
    private Double montant;
    private Long carteNumero;
    private Long commandeId;
	
	
	/**
	 * <b>CONSTRUCTEUR SANS ARGUMENTS</b>
	 */
	public PaiementBean() {
		super();
		LOGGER.info("CLASS : PaiementBean -- METHOD : CONSTRUCTEUR SANS ARGUMENTS -- BEGIN");
		LOGGER.info("CLASS : PaiementBean -- METHOD : CONSTRUCTEUR SANS ARGUMENTS -- END");
	}
	
    /**
     * <b>CONSTRUCTEUR AVEC 2 ARGUMENTS</b><br/>
     * 
     * @param pMontant Montant du paiement.
     * @param pCommandeId Id de la commande (associee au paiement).
     */
	public PaiementBean(Double pMontant, Long pCommandeId) {
		
		LOGGER.info("CLASS : PaiementBean -- METHOD : CONSTRUCTEUR AVEC 2 ARGUMENTS -- BEGIN");
		
        this.montant = pMontant;
        this.commandeId = pCommandeId;

		LOGGER.info("CLASS : PaiementBean -- METHOD : CONSTRUCTEUR AVEC 2 ARGUMENTS -- END");
	}

    /**
     * <b>CONSTRUCTEUR AVEC 3 ARGUMENTS</b><br/>
     * 
     * @param pMontant Montant du paiement.
     * @param pCarteNumero Numéro de la carte du paiement.
     * @param pCommandeId Id de la commande (associee au paiement).
     */
	public PaiementBean(Double pMontant, Long pCarteNumero, Long pCommandeId) {
		
		LOGGER.info("CLASS : PaiementBean -- METHOD : CONSTRUCTEUR AVEC 3 ARGUMENTS -- BEGIN");
		
        this.carteNumero = pCarteNumero;
        this.montant = pMontant;
        this.commandeId = pCommandeId;

		LOGGER.info("CLASS : PaiementBean -- METHOD : CONSTRUCTEUR AVEC 3 ARGUMENTS -- END");
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
        String result = "PaiementBean" + "{"
					               			   + "id"          + "=" + this.id
					               + "," + " " + "montant"     + "=" + this.montant
					               + "," + " " + "carteNumero" + "=" + this.carteNumero
					               + "," + " " + "commandeId"  + "=" + this.commandeId
					               + '}';
        return result;
    }
}
