package com.mproduit.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("ma-config")
@RefreshScope
public class ApplicationPropertiesConfiguration {

	
	private int produitNombreMax;
	

	public int getProduitNombreMax() { return this.produitNombreMax; }

	public void setProduitNombreMax(int pProduitNombreMax) { this.produitNombreMax = pProduitNombreMax; }
}
