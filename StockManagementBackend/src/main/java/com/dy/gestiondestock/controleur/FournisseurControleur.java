package com.dy.gestiondestock.controleur;
import com.dy.gestiondestock.dto.FournisseurDto;
import com.dy.gestiondestock.services.FournisseurService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.dy.gestiondestock.utils.Constants.FOURNISSEUR_ENDPOINT;

@RestController
public class FournisseurControleur {

  private FournisseurService fournisseurService;

  @Autowired
  public FournisseurControleur(FournisseurService fournisseurService) {
    this.fournisseurService = fournisseurService;
  }
  @PostMapping(FOURNISSEUR_ENDPOINT + "/create")
  public FournisseurDto save(@RequestBody FournisseurDto dto) {
    return fournisseurService.save(dto);
  }

  @GetMapping(FOURNISSEUR_ENDPOINT + "/{idFournisseur}")
  public FournisseurDto findById(@PathVariable("idFournisseur")Integer id) {
    return fournisseurService.findById(id);
  }

  @GetMapping(FOURNISSEUR_ENDPOINT + "/all")
  public List<FournisseurDto> findAll() {
    return fournisseurService.findAll();
  }

  @DeleteMapping(FOURNISSEUR_ENDPOINT + "/delete/{idFournisseur}")
  public void delete(@PathVariable("idFournisseur")Integer id) {
    fournisseurService.delete(id);
  }
}
