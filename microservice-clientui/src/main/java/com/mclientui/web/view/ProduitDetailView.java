package com.mclientui.web.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mclientui.feign.bean.ProduitBean;

public class ProduitDetailView {

	
	/**
	 * <b>OBJET QUI POSSEDE LES FONCTIONNALITES SUIVANTES : </b> <br/>
	 * <br/>
	 * Les fonctionnalites d'ecriture de messages de log dans la console.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ProduitDetailView.class);

	
	private ProduitBean produitBean;

	
	/**
	 * <b>CONSTRUCTEUR SANS ARGUMENT</b><br/>
	 */
	public ProduitDetailView() {
		super();
		LOGGER.info("CLASS : ProduitDetailView -- METHOD : CONSTRUCTEUR SANS ARGUMENTS -- BEGIN");
		LOGGER.info("CLASS : ProduitDetailView -- METHOD : CONSTRUCTEUR SANS ARGUMENTS -- END");
	}

	/**
	 * <b>CONSTRUCTEUR AVEC 1 ARGUMENT</b><br/>
	 * 
	 * @param pProduitBean
	 */
	public ProduitDetailView(ProduitBean pProduitBean) {
		
		LOGGER.info("CLASS : ProduitDetailView -- METHOD : CONSTRUCTEUR AVEC 1 ARGUMENT -- BEGIN");
		this.produitBean = pProduitBean;
		LOGGER.info("CLASS : ProduitDetailView -- METHOD : CONSTRUCTEUR AVEC 1 ARGUMENT -- END");
	}

	
	public ProduitBean getProduitBean() { return this.produitBean; }

	public void setProduitBean(ProduitBean pProduitBean) { this.produitBean = pProduitBean; }
}
