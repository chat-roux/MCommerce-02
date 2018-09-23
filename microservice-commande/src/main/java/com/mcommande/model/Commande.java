package com.mcommande.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * <b>ENTITE REPRESENTANT L'OBJET CI-DESSOUS :</b><br/>
 *    ->OBJET : COMMANDE<br/>
 *    <br/>
 * @author 1603599
 */
@Entity
public class Commande {

	/**
	 * <b>OBJET QUI POSSEDE LES FONCTIONNALITES SUIVANTES : </b> <br/>
	 * <br/>
	 * Les fonctionnalites d'ecriture de messages de log dans la console.
	 */
	@Transient
	private static final Logger LOGGER = LoggerFactory.getLogger(Commande.class);

    @Id
    @GeneratedValue
    private Long id;

    private Date dateCreation;
    private Integer quantite;
    private Double montant;
    private Boolean estPaye;
    private Long produitId;

    /**
     * <b>CONSTRUCTEUR SANS ARGUMENT</b><br/>
     */
    public Commande() {
		LOGGER.info("CLASS : Commande -- METHOD : CONSTRUCTEUR SANS ARGUMENT -- BEGIN");
		LOGGER.info("CLASS : Commande -- METHOD : CONSTRUCTEUR SANS ARGUMENT -- END");
    }

    /**
     * <b>CONSTRUCTEUR AVEC 1 ARGUMENT</b><br/>
     * 
     * @param pProduitId Id du produit (associe a la commande).
     */
    public Commande(Long pProduitId) {
    	
		LOGGER.info("CLASS : Commande -- METHOD : CONSTRUCTEUR AVEC 1 ARGUMENT -- BEGIN");
		
		this.dateCreation = new Date();
		this.quantite = Integer.valueOf(1);
		this.estPaye = false;
		this.produitId = pProduitId;
        
		LOGGER.info("CLASS : Commande -- METHOD : CONSTRUCTEUR AVEC 1 ARGUMENT -- END");
    }

	/**
	 * <b>CONSTRUCTEUR AVEC 2 ARGUMENTS</b>
	 * 
     * @param pMontant Montant de la commande.
     * @param pProduitId Id du produit (associe a la commande).
	 */
	public Commande(Double pMontant, Long pProduitId) {
		
		LOGGER.info("CLASS : Commande -- METHOD : CONSTRUCTEUR AVEC 2 ARGUMENTS -- BEGIN");
		
		this.dateCreation = new Date();
		this.quantite = Integer.valueOf(1);
		this.montant = pMontant;
		this.estPaye = false;
		this.produitId = pProduitId;

		LOGGER.info("CLASS : Commande -- METHOD : CONSTRUCTEUR AVEC 2 ARGUMENTS -- END");
	}

    
    public Long    getId           () { return this.id;           }
    public Date    getDateCreation () { return this.dateCreation; }
    public Integer getQuantite     () { return this.quantite;     }
    public Double  getMontant      () { return this.montant;      }
    public Boolean getEstPaye      () { return this.estPaye;      }
    public Long    getProduitId    () { return this.produitId;    }


	public void setId           (Long    pId           ) { this.id           = pId;            }
    public void setDateCreation (Date    pDateCreation ) { this.dateCreation = pDateCreation;  }
    public void setQuantite     (Integer pQuantite     ) { this.quantite     = pQuantite;      }
    public void setMontant      (Double  pMontant      ) { this.montant      = pMontant;       }
    public void setEstPaye      (Boolean pEstPaye      ) { this.estPaye      = pEstPaye;       }
    public void setProduitId    (Long    pProduitId    ) { this.produitId    = pProduitId;     }
	
    
    @Override
    public String toString() {
    	
    	String result = "commande" + "{"
						                	  		+ "id"           + "=" + this.id
						                + "," + " " + "dateCreation" + "=" + this.dateCreation
						                + "," + " " + "quantite"     + "=" + this.quantite
						                + "," + " " + "montant"      + "=" + this.montant
						                + "," + " " + "estPaye"      + "=" + this.estPaye
						                + "," + " " + "produitId"    + "=" + this.produitId
						           + '}';
    	return result;
    }
}
