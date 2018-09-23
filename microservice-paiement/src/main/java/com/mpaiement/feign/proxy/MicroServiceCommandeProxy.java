package com.mpaiement.feign.proxy;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mpaiement.feign.bean.CommandeBean;

/**
 * <b>PROXY PERMETTANT D'ACCEDER AU COMPOSANT CI-DESSOUS :</b><br/>
 *    ->LE MICRO-SERVICE 'MCommande'<br/>
 *    <br/>
 * @author 1603599
 *
 */
@FeignClient(name = "microservice-commande", url = "localhost:9002")
public interface MicroServiceCommandeProxy {
	
	@RequestMapping(value="/commande", method=RequestMethod.PUT)
	public abstract ResponseEntity<CommandeBean> modifier(@RequestBody CommandeBean pCommandeBean);

	@RequestMapping(value = "/commande/{id}", method=RequestMethod.GET)
	public abstract Optional<CommandeBean> rechercherParId(@PathVariable(name="id") Long pId);
}
