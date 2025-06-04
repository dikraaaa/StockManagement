package com.dy.gestiondestock.controleur;
import com.dy.gestiondestock.dto.MvtStkDto;
import com.dy.gestiondestock.services.MvtStkService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.dy.gestiondestock.utils.Constants.APP_ROOT;

@RestController
public class MvtStkControleur {

  private MvtStkService service;

  @Autowired
  public MvtStkControleur(MvtStkService service) {
    this.service = service;
  }

  @GetMapping(APP_ROOT + "/mvtstk/stockreel/{idArticle}")
  public BigDecimal stockReelArticle(@PathVariable("idArticle")Integer idArticle) {
    return service.stockReelArticle(idArticle);
  }

  @GetMapping(APP_ROOT + "/mvtstk/filter/article/{idArticle}")
  public List<MvtStkDto> mvtStkArticle(@PathVariable("idArticle")Integer idArticle) {
    return service.mvtStkArticle(idArticle);
  }

  @PostMapping(APP_ROOT + "/mvtstk/entree")
  public MvtStkDto entreeStock(@RequestBody MvtStkDto dto) {
    return service.entreeStock(dto);
  }

  @PostMapping(APP_ROOT + "/mvtstk/sortie")
  public MvtStkDto sortieStock(@RequestBody MvtStkDto dto) {
    return service.sortieStock(dto);
  }

  @PostMapping(APP_ROOT + "/mvtstk/correctionpos")
  public MvtStkDto correctionStockPos(@RequestBody MvtStkDto dto) {
    return service.correctionStockPos(dto);
  }

  @PostMapping(APP_ROOT + "/mvtstk/correctionneg")
  public MvtStkDto correctionStockNeg(@RequestBody MvtStkDto dto) {
    return service.correctionStockNeg(dto);
  }
}
