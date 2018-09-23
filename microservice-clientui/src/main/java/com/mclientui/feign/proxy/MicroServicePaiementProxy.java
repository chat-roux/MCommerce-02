package com.mclientui.feign.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mclientui.feign.bean.PaiementBean;

/**
 * <b>PROXY PERMETTANT D'ACCEDER AU COMPOSANT CI-DESSOUS :</b><br/>
 *    ->LE MICRO-SERVICE 'MPaiement'<br/>
 *    <br/>
 * @author 1603599
 *
 */
@FeignClient(name = "microservice-paiement", url = "localhost:9003")
public interface MicroServicePaiementProxy {
	
	@RequestMapping(value="/paiement", method=RequestMethod.POST)
	public abstract ResponseEntity<PaiementBean> creer(@RequestBody PaiementBean pPaiementBean);
}
