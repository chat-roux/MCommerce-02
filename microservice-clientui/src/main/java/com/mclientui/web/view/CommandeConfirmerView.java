package com.mclientui.web.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mclientui.feign.bean.CommandeBean;
import com.mclientui.feign.bean.ProduitBean;

public class CommandeConfirmerView {

	
	/**
	 * <b>OBJET QUI POSSEDE LES FONCTIONNALITES SUIVANTES : </b> <br/>
	 * <br/>
	 * Les fonctionnalites d'ecriture de messages de log dans la console.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(CommandeConfirmerView.class);

	
	private ProduitBean produitBean;
	private CommandeBean commandeBean;


	/**
	 * <b>CONSTRUCTEUR SANS ARGUMENT</b><br/>
	 */
	public CommandeConfirmerView() {
		super();
		LOGGER.info("CLASS : CommandeConfirmerView -- METHOD : CONSTRUCTEUR SANS ARGUMENTS -- BEGIN");
		LOGGER.info("CLASS : CommandeConfirmerView -- METHOD : CONSTRUCTEUR SANS ARGUMENTS -- END");
	}

	/**
	 * <b>CONSTRUCTEUR AVEC 2 ARGUMENTS</b><br/>
	 * 
	 * @param pProduitBean
	 * @param pCommandeBean
	 */
	public CommandeConfirmerView(ProduitBean pProduitBean, CommandeBean pCommandeBean) {
		
		LOGGER.info("CLASS : CommandeConfirmerView -- METHOD : CONSTRUCTEUR AVEC 2 ARGUMENTS -- BEGIN");
		
		this.produitBean = pProduitBean;
		this.commandeBean = pCommandeBean;
		
		LOGGER.info("CLASS : CommandeConfirmerView -- METHOD : CONSTRUCTEUR AVEC 2 ARGUMENTS -- END");
	}
	

	public ProduitBean  getProduitBean () { return this.produitBean;  }
	public CommandeBean getCommandeBean() { return this.commandeBean; }

	public void setProduitBean (ProduitBean  pProduitBean ) { this.produitBean  = pProduitBean;  }
	public void setCommandeBean(CommandeBean pCommandeBean) { this.commandeBean = pCommandeBean; }
}
