package com.mclientui.feign.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * <b>BEAN REPRESENTANT L'OBJET CI-DESSOUS :</b><br/>
 *    ->OBJET : PRODUIT<br/>
 *    <br/>
 * @author 1603599
 */
public class ProduitBean {

	
	/**
	 * <b>OBJET QUI POSSEDE LES FONCTIONNALITES SUIVANTES : </b> <br/>
	 * <br/>
	 * Les fonctionnalites d'ecriture de messages de log dans la console.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ProduitBean.class);
	
	private Long id;
	private String titre;
	private String description;
	private String image;
	private Double prix;
	
	
	/**
	 * <b>CONSTRUCTEUR SANS ARGUMENTS</b>
	 */
	public ProduitBean() {
		super();
		LOGGER.info("CLASS : ProduitBean -- METHOD : CONSTRUCTEUR SANS ARGUMENTS -- BEGIN");
		LOGGER.info("CLASS : ProduitBean -- METHOD : CONSTRUCTEUR SANS ARGUMENTS -- END");
	}

    /**
     * <b>CONSTRUCTEUR AVEC 4 ARGUMENTS</b><br/>
     * 
     * @param pTitre Titre du produit.
     * @param pDescription Description du produit.
     * @param pImage Image du produit.
     * @param pPrix Prix du produit.
     */
    public ProduitBean(String pTitre, String pDescription, String pImage, Double pPrix) {

		LOGGER.info("CLASS : ProduitBean -- METHOD : CONSTRUCTEUR AVEC 4 ARGUMENTS -- BEGIN");

        this.titre = pTitre;
        this.description = pDescription;
        this.image = pImage;
        this.prix = pPrix;
        
		LOGGER.info("CLASS : ProduitBean -- METHOD : CONSTRUCTEUR AVEC 4 ARGUMENTS -- END");
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
		
		String result = "ProduitBean" + "{"
												          + "id"          + "="       + id
								              + "," + " " + "titre"       + "=" + "'" + titre       + "'"
								              + "," + " " + "description" + "=" + "'" + description + "'"
								              + "," + " " + "image"       + "=" + "'" + image       + "'" 
								              + "," + " " + "prix"        + "="       + prix
									  + "}";
		return result;
	}
}
