package com.mclientui.web.view;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mclientui.feign.bean.ProduitBean;

public class ProduitListeView {

	
	/**
	 * <b>OBJET QUI POSSEDE LES FONCTIONNALITES SUIVANTES : </b> <br/>
	 * <br/>
	 * Les fonctionnalites d'ecriture de messages de log dans la console.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ProduitListeView.class);

	
	private List<ProduitBean> produitBeans;
	
	
	/**
	 * <b>CONSTRUCTEUR SANS ARGUMENT</b><br/>
	 */
	public ProduitListeView() {
		super();
		LOGGER.info("CLASS : ProduitListeView -- METHOD : CONSTRUCTEUR SANS ARGUMENTS -- BEGIN");
		LOGGER.info("CLASS : ProduitListeView -- METHOD : CONSTRUCTEUR SANS ARGUMENTS -- END");
	}

	/**
	 * <b>CONSTRUCTEUR AVEC 1 ARGUMENT</b><br/>
	 */
	public ProduitListeView(List<ProduitBean> pProduitBeans) {

		LOGGER.info("CLASS : ProduitListeView -- METHOD : CONSTRUCTEUR AVEC 1 ARGUMENT -- BEGIN");
		this.produitBeans = pProduitBeans;
		LOGGER.info("CLASS : ProduitListeView -- METHOD : CONSTRUCTEUR AVEC 1 ARGUMENT -- END");
	}

	
	public List<ProduitBean> getProduitBeans() { return this.produitBeans; }

	public void setProduitBeans(List<ProduitBean> pProduitBeans) { this.produitBeans = pProduitBeans; }
}
