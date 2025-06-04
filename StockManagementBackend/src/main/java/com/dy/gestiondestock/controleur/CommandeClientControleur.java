package com.dy.gestiondestock.controleur;
import com.dy.gestiondestock.dto.CommandeClientDto;
import com.dy.gestiondestock.dto.LigneCommandeClientDto;
import com.dy.gestiondestock.model.EtatCommande;
import com.dy.gestiondestock.services.CommandeClientService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.dy.gestiondestock.utils.Constants.APP_ROOT;

@RestController
public class CommandeClientControleur {

  private CommandeClientService commandeClientService;
  @Autowired
  public CommandeClientControleur(CommandeClientService commandeClientService) {
    this.commandeClientService = commandeClientService;
  }

  @PostMapping(APP_ROOT + "/commandesclients/create")
  public ResponseEntity<CommandeClientDto> save(@RequestBody CommandeClientDto dto) {
    return ResponseEntity.ok(commandeClientService.save(dto));
  }
  @PatchMapping(APP_ROOT + "/commandesclients/update/etat/{idCommande}/{etatCommande}")
  public ResponseEntity<CommandeClientDto> updateEtatCommande(@PathVariable("idCommande")Integer idCommande, EtatCommande etatCommande) {
    return ResponseEntity.ok(commandeClientService.updateEtatCommande(idCommande, etatCommande));
  }

  @PatchMapping(APP_ROOT + "/commandesclients/update/quantite/{idCommande}/{idLigneCommande}/{quantite}")
  public ResponseEntity<CommandeClientDto> updateQuantiteCommande(@PathVariable("idCommande")Integer idCommande, Integer idLigneCommande, BigDecimal quantite) {
    return ResponseEntity.ok(commandeClientService.updateQuantiteCommande(idCommande, idLigneCommande, quantite));
  }

  @PatchMapping(APP_ROOT + "/commandesclients/update/client/{idCommande}/{idClient}")
  public ResponseEntity<CommandeClientDto> updateClient(@PathVariable("idCommande")Integer idCommande, Integer idClient) {
    return ResponseEntity.ok(commandeClientService.updateClient(idCommande, idClient));
  }

  @PatchMapping(APP_ROOT + "/commandesclients/update/article/{idCommande}/{idLigneCommande}/{idArticle}")
  public ResponseEntity<CommandeClientDto> updateArticle(@PathVariable("idCommande")Integer idCommande, Integer idLigneCommande, Integer idArticle) {
    return ResponseEntity.ok(commandeClientService.updateArticle(idCommande, idLigneCommande, idArticle));
  }

  @DeleteMapping(APP_ROOT + "/commandesclients/delete/article/{idCommande}/{idLigneCommande}")
  public ResponseEntity<CommandeClientDto> deleteArticle(@PathVariable("idCommande")Integer idCommande, Integer idLigneCommande) {
    return ResponseEntity.ok(commandeClientService.deleteArticle(idCommande, idLigneCommande));
  }

  @GetMapping(APP_ROOT + "/commandesclients/{idCommandeClient}")
  public ResponseEntity<CommandeClientDto> findById(@PathVariable Integer id) {
    return ResponseEntity.ok(commandeClientService.findById(id));
  }

  @GetMapping(APP_ROOT + "/commandesclients/filter/{codeCommandeClient}")
  public ResponseEntity<CommandeClientDto> findByCode(@PathVariable("codeCommandeClient")String code) {
    return ResponseEntity.ok(commandeClientService.findByCode(code));
  }

  @GetMapping(APP_ROOT + "/commandesclients/all")
  public ResponseEntity<List<CommandeClientDto>> findAll() {
    return ResponseEntity.ok(commandeClientService.findAll());
  }

  @GetMapping(APP_ROOT + "/commandesclients/lignesCommande/{idCommande}")
  public ResponseEntity<List<LigneCommandeClientDto>> findAllLignesCommandesClientByCommandeClientId(@PathVariable("idCommande")Integer idCommande) {
    return ResponseEntity.ok(commandeClientService.findAllLignesCommandesClientByCommandeClientId(idCommande));
  }

  @DeleteMapping(APP_ROOT + "/commandesclients/delete/{idCommandeClient}")
  public ResponseEntity<Void> delete(@PathVariable("idCommandeClient")Integer id) {
    commandeClientService.delete(id);
    return ResponseEntity.ok().build();
  }
}
