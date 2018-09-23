package com.mclientui.web.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mclientui.feign.bean.PaiementBean;

public class PaiementResultatView {

	
	/**
	 * <b>OBJET QUI POSSEDE LES FONCTIONNALITES SUIVANTES : </b> <br/>
	 * <br/>
	 * Les fonctionnalites d'ecriture de messages de log dans la console.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(PaiementResultatView.class);


	private PaiementBean paiementBean;


	/**
	 * <b>CONSTRUCTEUR SANS ARGUMENT</b><br/>
	 */
	public PaiementResultatView() {
		super();
		LOGGER.info("CLASS : PaiementResultatView -- METHOD : CONSTRUCTEUR SANS ARGUMENTS -- BEGIN");
		LOGGER.info("CLASS : PaiementResultatView -- METHOD : CONSTRUCTEUR SANS ARGUMENTS -- END");
	}

	/**
	 * <b>CONSTRUCTEUR AVEC 1 ARGUMENT</b><br/>
	 * 
	 * @param pPaiementBean
	 */
	public PaiementResultatView(PaiementBean pPaiementBean) {
		
		LOGGER.info("CLASS : PaiementResultatView -- METHOD : CONSTRUCTEUR AVEC 1 ARGUMENT -- BEGIN");
		this.paiementBean = pPaiementBean;
		LOGGER.info("CLASS : PaiementResultatView -- METHOD : CONSTRUCTEUR AVEC 1 ARGUMENT -- END");
	}
	

	public PaiementBean getPaiementBean() { return this.paiementBean; }

	public void setPaiementBean(PaiementBean pPaiementBean) { this.paiementBean = pPaiementBean; }
}
