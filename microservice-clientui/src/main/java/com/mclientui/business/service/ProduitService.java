package com.mclientui.business.service;

import java.util.List;

import com.mclientui.feign.bean.ProduitBean;

/**
 * <b>INTERFACE QUI EXPOSE LES FONCTIONNALITES SUIVANTES :</b><br/>
 *    ->LES TRAITEMENTS METIER RELATIFS A L'ENTITE 'Produit'.<br/>
 *    
 * @author 1603599
 */
public interface ProduitService {
	
	
    /**
	 * <b>EFFECTUER L'OPERATION DE RECHERCHE CI-DESSOUS :</b><br/>
	 * <br/>
	 * ->(01.)ENTITE RECHERCHEE    : LES ENTITES 'produit'.<br/> 
	 * ->(02.)CRITERE DE RECHERCHE : AUCUN.<br/> 
	 * ->(03.)CONDITION            : AUCUNE.<br/> 
	 * ->(04.)TRAITEMENT D'ERREURS : CAS OU L'ENTITE EST INTROUVABLE.<br/>
	 *    
	 * @return List<ProduitBean> Liste des objets 'ProduitBean' trouvés.
	 */    
    public abstract List<ProduitBean> rechercherTous();
    
	/**
	 * <b>EFFECTUER L'OPERATION DE RECHERCHE CI-DESSOUS :</b><br/>
	 * <br/>
	 * ->(01.)ENTITE A RECHERCHER  : L'ENTITE 'produit'.<br/> 
	 * ->(02.)CRITERE DE RECHERCHE : L'ATTRIBUT 'id'.<br/> 
	 * ->(03.)CONDITION            : ETRE IDENTIQUE A LA VALEUR FOURNIE.<br/> 
	 * ->(04.)TRAITEMENT D'ERREURS : CAS OU L'ENTITE EST INTROUVABLE.<br/>
	 *    
	 * @param pId L'id du produit.
	 * @return ProduitBean Objet 'ProduitBean' trouvé.
	 */    
    public abstract ProduitBean rechercherParId(Long pId);
}
