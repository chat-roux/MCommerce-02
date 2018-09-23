package com.mproduit.persistence.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <b>ENTITE REPRESENTANT L'OBJET CI-DESSOUS :</b><br/>
 *    ->OBJET : PRODUIT<br/>
 *    <br/>
 * @author 1603599
 */
@Entity
public class Produit {

	/**
	 * <b>OBJET QUI POSSEDE LES FONCTIONNALITES SUIVANTES : </b> <br/>
	 * <br/>
	 * Les fonctionnalites d'ecriture de messages de log dans la console.
	 */
	@Transient
	private static final Logger LOGGER = LoggerFactory.getLogger(Produit.class);

    @Id
    @GeneratedValue
    private Long id;

    private String titre;
    private String description;
    private String image;
    private Double prix;


    /**
     * <b>CONSTRUCTEUR SANS ARGUMENT</b><br/>
     */
    public Produit() {
		LOGGER.info("CLASS : Produit -- METHOD : CONSTRUCTEUR SANS ARGUMENT -- BEGIN");
		LOGGER.info("CLASS : Produit -- METHOD : CONSTRUCTEUR SANS ARGUMENT -- END");
    }

    /**
     * <b>CONSTRUCTEUR AVEC 5 ARGUMENTS</b><br/>
     * 
     * @param pTitre Titre du produit.
     * @param pDescription Description du produit.
     * @param pImage Image du produit.
     * @param pPrix Prix du produit.
     */
    public Produit(String pTitre, String pDescription, String pImage, Double pPrix) {

		LOGGER.info("CLASS : Produit -- METHOD : CONSTRUCTEUR AVEC 5 ARGUMENTS -- BEGIN");

        this.titre = pTitre;
        this.description = pDescription;
        this.image = pImage;
        this.prix = pPrix;
        
		LOGGER.info("CLASS : Produit -- METHOD : CONSTRUCTEUR AVEC 5 ARGUMENTS -- END");
    }

	public Long    getId         () { return this.id;          }
	public String  getTitre      () { return this.titre;       }
	public String  getDescription() { return this.description; }
	public String  getImage      () { return this.image;       }
	public Double  getPrix       () { return this.prix;        }


	public void setId         (Long    pId         ) { this.id          = pId;          }
	public void setTitre      (String  pTitre      ) { this.titre       = pTitre;       }
	public void setDescription(String  pDescription) { this.description = pDescription; }
	public void setImage      (String  pImage      ) { this.image       = pImage;       }
	public void setPrix       (Double  pPrix       ) { this.prix        = pPrix;        }	

	
    @Override
    public String toString() {
    	
		String result = "Produit" + "{"
												          + "id"          + "="       + this.id
									          + "," + " " + "titre"       + "=" + "'" + this.titre       + "'"
									          + "," + " " + "description" + "=" + "'" + this.description + "'"
									          + "," + " " + "image"       + "=" + "'" + this.image       + "'" 
									          + "," + " " + "prix"        + "="       + this.prix
          							  + "}";
		return result;
    }
}
