package com.dy.gestiondestock.controleur;
import com.dy.gestiondestock.dto.ChangerMotDePasseUtilisateurDto;
import com.dy.gestiondestock.dto.UtilisateurDto;
import com.dy.gestiondestock.services.UtilisateurService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.dy.gestiondestock.utils.Constants.UTILISATEUR_ENDPOINT;

@RestController
public class UtilisateurControleur {

  private UtilisateurService utilisateurService;

  @Autowired
  public UtilisateurControleur(UtilisateurService utilisateurService) {
    this.utilisateurService = utilisateurService;
  }

  @PostMapping(UTILISATEUR_ENDPOINT + "/create")
  public UtilisateurDto save(@RequestBody UtilisateurDto dto) {
    return utilisateurService.save(dto);
  }

  @PostMapping(UTILISATEUR_ENDPOINT + "/update/password")
  public UtilisateurDto changerMotDePasse(@RequestBody ChangerMotDePasseUtilisateurDto dto) {
    return utilisateurService.changerMotDePasse(dto);
  }

  @GetMapping(UTILISATEUR_ENDPOINT + "/{idUtilisateur}")
  public UtilisateurDto findById(@PathVariable("idUtilisateur")Integer id) {
    return utilisateurService.findById(id);
  }

  @GetMapping(UTILISATEUR_ENDPOINT + "/find/{email}")
  public UtilisateurDto findByEmail(@PathVariable("email")String email) {
    return utilisateurService.findByEmail(email);
  }

  @GetMapping(UTILISATEUR_ENDPOINT + "/all")
  public List<UtilisateurDto> findAll() {
    return utilisateurService.findAll();
  }

  @DeleteMapping(UTILISATEUR_ENDPOINT + "/delete/{idUtilisateur}")
  public void delete(@PathVariable("idUtilisateur")Integer id) {
    utilisateurService.delete(id);
  }
}
