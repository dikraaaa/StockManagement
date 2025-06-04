package com.dy.gestiondestock.controleur;
import com.dy.gestiondestock.dto.EntrepriseDto;
import com.dy.gestiondestock.services.EntrepriseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.dy.gestiondestock.utils.Constants.ENTREPRISE_ENDPOINT;

@RestController
public class EntrepriseControleur {

  private EntrepriseService entrepriseService;

  @Autowired
  public EntrepriseControleur(EntrepriseService entrepriseService) {
    this.entrepriseService = entrepriseService;
  }

  @PostMapping(ENTREPRISE_ENDPOINT + "/create")
  public EntrepriseDto save(@RequestBody EntrepriseDto dto) {
    return entrepriseService.save(dto);
  }

  @GetMapping(ENTREPRISE_ENDPOINT + "/{idEntreprise}")
  public EntrepriseDto findById(@PathVariable("idEntreprise")Integer id) {
    return entrepriseService.findById(id);
  }

  @GetMapping(ENTREPRISE_ENDPOINT + "/all")
  public List<EntrepriseDto> findAll() {
    return entrepriseService.findAll();
  }

  @DeleteMapping(ENTREPRISE_ENDPOINT + "/delete/{idEntreprise}")
  public void delete(@PathVariable("idEntreprise")Integer id) {
    entrepriseService.delete(id);
  }
}
