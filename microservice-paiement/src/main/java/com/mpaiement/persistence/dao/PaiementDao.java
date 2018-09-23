package com.mpaiement.persistence.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mpaiement.persistence.model.Paiement;

/**
 * <b>COMPOSANT POSSEDANT LES FONCTIONNALITES CI-DESSOUS:</b><br/>
 * ->LES FONCTIONNALITES DE PERSISTANCE SUR L'ENTITE CONCERNEE<br/>
 * ->ENTITE : 'paiement'.<br/> 
 */    
@Repository
public interface PaiementDao extends JpaRepository<Paiement, Long>{

    Optional<Paiement> findByCommandeId(Long pCommandeId);
}
