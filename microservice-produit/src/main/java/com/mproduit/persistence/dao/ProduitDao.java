package com.mproduit.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mproduit.persistence.model.Produit;


/**
 * <b>COMPOSANT POSSEDANT LES FONCTIONNALITES CI-DESSOUS:</b><br/>
 * ->LES FONCTIONNALITES DE PERSISTANCE SUR L'ENTITE CONCERNEE<br/>
 * ->ENTITE : 'produit'.<br/> 
 */    
@Repository
public interface ProduitDao extends JpaRepository<Produit, Long>{
}
