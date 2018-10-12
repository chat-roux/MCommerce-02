package com.mclientui.feign.proxy;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mclientui.feign.bean.ProduitBean;

/**
 * <b>PROXY PERMETTANT D'ACCEDER AU COMPOSANT CI-DESSOUS :</b><br/>
 *    ->LE MICRO-SERVICE 'MProduit'<br/>
 *    <br/>
 * @author 1603599
 *
 */
@RibbonClient(name = "microservice-produit")
@FeignClient(name = "zuul-server")
public interface MicroServiceProduitProxy {
	
	@RequestMapping(value="/microservice-produit/produit", method=RequestMethod.GET)
	public abstract List<ProduitBean> rechercherTous();

	@RequestMapping(value = "/microservice-produit/produit/{id}", method=RequestMethod.GET)
	public abstract Optional<ProduitBean> rechercherParId(@PathVariable(name="id") Long pId);
}
