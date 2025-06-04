package com.dy.gestiondestock.controleur;
import com.dy.gestiondestock.dto.VentesDto;
import com.dy.gestiondestock.services.VentesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.dy.gestiondestock.utils.Constants.VENTES_ENDPOINT;

@RestController
public class VentesControleur {

  private VentesService ventesService;

  @Autowired
  public VentesControleur(VentesService ventesService) {
    this.ventesService = ventesService;
  }

  @PostMapping(VENTES_ENDPOINT + "/create")
  public VentesDto save(@RequestBody VentesDto dto) {
    return ventesService.save(dto);
  }

  @GetMapping(VENTES_ENDPOINT + "/{idVente}")
  public VentesDto findById(@PathVariable("idVente")Integer id) {
    return ventesService.findById(id);
  }

  @GetMapping(VENTES_ENDPOINT + "/{codeVente}")
  public VentesDto findByCode(@PathVariable("codeVente")String code) {
    return ventesService.findByCode(code);
  }

  @GetMapping(VENTES_ENDPOINT + "/all")
  public List<VentesDto> findAll() {
    return ventesService.findAll();
  }

  @DeleteMapping(VENTES_ENDPOINT + "/delete/{idVente}")
  public void delete(@PathVariable("idVente")Integer id) {
    ventesService.delete(id);
  }
}
