package com.mclientui.web.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mclientui.feign.bean.CommandeBean;

public class PaiementConfirmerView {

	
	/**
	 * <b>OBJET QUI POSSEDE LES FONCTIONNALITES SUIVANTES : </b> <br/>
	 * <br/>
	 * Les fonctionnalites d'ecriture de messages de log dans la console.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(PaiementConfirmerView.class);

	
	private CommandeBean commandeBean;


	/**
	 * <b>CONSTRUCTEUR SANS ARGUMENT</b><br/>
	 */
	public PaiementConfirmerView() {
		super();
		LOGGER.info("CLASS : PaiementConfirmerView -- METHOD : CONSTRUCTEUR SANS ARGUMENTS -- BEGIN");
		LOGGER.info("CLASS : PaiementConfirmerView -- METHOD : CONSTRUCTEUR SANS ARGUMENTS -- END");
	}

	/**
	 * <b>CONSTRUCTEUR AVEC 1 ARGUMENT</b><br/>
	 * 
	 * @param pCommandeBean
	 */
	public PaiementConfirmerView(CommandeBean pCommandeBean) {
		
		LOGGER.info("CLASS : PaiementConfirmerView -- METHOD : CONSTRUCTEUR AVEC 1 ARGUMENT -- BEGIN");
		this.commandeBean = pCommandeBean;
		LOGGER.info("CLASS : PaiementConfirmerView -- METHOD : CONSTRUCTEUR AVEC 1 ARGUMENT -- END");
	}
	

	public CommandeBean getCommandeBean() { return this.commandeBean; }

	public void setCommandeBean(CommandeBean pCommandeBean) { this.commandeBean = pCommandeBean; }
}
