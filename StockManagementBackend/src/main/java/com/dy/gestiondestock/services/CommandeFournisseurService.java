package com.dy.gestiondestock.services;

import com.dy.gestiondestock.dto.CommandeFournisseurDto;
import com.dy.gestiondestock.dto.LigneCommandeFournisseurDto;
import com.dy.gestiondestock.model.EtatCommande;
import java.math.BigDecimal;
import java.util.List;

public interface CommandeFournisseurService {

  CommandeFournisseurDto save(CommandeFournisseurDto dto);

  CommandeFournisseurDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande);

  CommandeFournisseurDto updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite);

  CommandeFournisseurDto updateFournisseur(Integer idCommande, Integer idFournisseur);

  CommandeFournisseurDto updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle);

  // Delete article ==> delete LigneCommandeFournisseur
  CommandeFournisseurDto deleteArticle(Integer idCommande, Integer idLigneCommande);

  CommandeFournisseurDto findById(Integer id);

  CommandeFournisseurDto findByCode(String code);

  List<CommandeFournisseurDto> findAll();

  List<LigneCommandeFournisseurDto> findAllLignesCommandesFournisseurByCommandeFournisseurId(Integer idCommande);

  void delete(Integer id);

}
