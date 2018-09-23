package com.mclientui.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("ma-config")
@RefreshScope
public class ApplicationPropertiesConfiguration {

	
	private int parametreDExemple;
	

	public int getParametreDExemple() { return this.parametreDExemple; }

	public void setParametreDExemple(int pParametreDExemple) { this.parametreDExemple = pParametreDExemple; }
}
