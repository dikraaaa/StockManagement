package com.dy.gestiondestock.controleur;
import com.dy.gestiondestock.dto.CommandeFournisseurDto;
import com.dy.gestiondestock.dto.LigneCommandeFournisseurDto;
import com.dy.gestiondestock.model.EtatCommande;
import com.dy.gestiondestock.services.CommandeFournisseurService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.dy.gestiondestock.utils.Constants.*;

@RestController
public class CommandeFournisseurControleur {

  private CommandeFournisseurService commandeFournisseurService;
  @Autowired
  public CommandeFournisseurControleur(CommandeFournisseurService commandeFournisseurService) {
    this.commandeFournisseurService = commandeFournisseurService;
  }

  @PostMapping(CREATE_COMMANDE_FOURNISSEUR_ENDPOINT)
  public CommandeFournisseurDto save(@RequestBody CommandeFournisseurDto dto) {
    return commandeFournisseurService.save(dto);
  }

  @PatchMapping(COMMANDE_FOURNISSEUR_ENDPOINT + "/update/etat/{idCommande}/{etatCommande}")
  public CommandeFournisseurDto updateEtatCommande(@PathVariable("idCommande")Integer idCommande, EtatCommande etatCommande) {
    return commandeFournisseurService.updateEtatCommande(idCommande, etatCommande);
  }

  @PatchMapping(COMMANDE_FOURNISSEUR_ENDPOINT + "/update/quantite/{idCommande}/{idLigneCommande}/{quantite}")
  public CommandeFournisseurDto updateQuantiteCommande(@PathVariable("idCommande")Integer idCommande, Integer idLigneCommande, BigDecimal quantite) {
    return commandeFournisseurService.updateQuantiteCommande(idCommande, idLigneCommande, quantite);
  }

  @PatchMapping(COMMANDE_FOURNISSEUR_ENDPOINT + "/update/fournisseur/{idCommande}/{idFournisseur}")
  public CommandeFournisseurDto updateFournisseur(@PathVariable("idCommande")Integer idCommande, Integer idFournisseur) {
    return commandeFournisseurService.updateFournisseur(idCommande, idFournisseur);
  }

  @PatchMapping(COMMANDE_FOURNISSEUR_ENDPOINT + "/update/article/{idCommande}/{idLigneCommande}/{idArticle}")
  public CommandeFournisseurDto updateArticle(@PathVariable("idCommande")Integer idCommande, Integer idLigneCommande, Integer idArticle) {
    return commandeFournisseurService.updateArticle(idCommande, idLigneCommande, idArticle);
  }

  @DeleteMapping(COMMANDE_FOURNISSEUR_ENDPOINT + "/delete/article/{idCommande}/{idLigneCommande}")
  public CommandeFournisseurDto deleteArticle(@PathVariable("idCommande")Integer idCommande, Integer idLigneCommande) {
    return commandeFournisseurService.deleteArticle(idCommande, idLigneCommande);
  }

  @GetMapping(FIND_COMMANDE_FOURNISSEUR_BY_ID_ENDPOINT)
  public CommandeFournisseurDto findById(@PathVariable("idCommandeFournisseur") Integer id) {
    return commandeFournisseurService.findById(id);
  }

  @GetMapping(FIND_COMMANDE_FOURNISSEUR_BY_CODE_ENDPOINT)
  public CommandeFournisseurDto findByCode(@PathVariable("codeCommandeFournisseur")String code) {
    return commandeFournisseurService.findByCode(code);
  }

  @GetMapping(FIND_ALL_COMMANDE_FOURNISSEUR_ENDPOINT)
  public List<CommandeFournisseurDto> findAll() {
    return commandeFournisseurService.findAll();
  }

  @GetMapping(COMMANDE_FOURNISSEUR_ENDPOINT + "/lignesCommande/{idCommande}")
  public List<LigneCommandeFournisseurDto> findAllLignesCommandesFournisseurByCommandeFournisseurId(@PathVariable("idCommande")Integer idCommande) {
    return commandeFournisseurService.findAllLignesCommandesFournisseurByCommandeFournisseurId(idCommande);
  }

  @DeleteMapping(DELETE_COMMANDE_FOURNISSEUR_ENDPOINT)
  public void delete(@PathVariable("idCommandeFournisseur")Integer id) {
    commandeFournisseurService.delete(id);
  }
}
