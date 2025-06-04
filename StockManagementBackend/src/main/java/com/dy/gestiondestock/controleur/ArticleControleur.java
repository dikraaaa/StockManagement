package com.dy.gestiondestock.controleur;

import com.dy.gestiondestock.dto.ArticleDto;
import com.dy.gestiondestock.dto.LigneCommandeClientDto;
import com.dy.gestiondestock.dto.LigneCommandeFournisseurDto;
import com.dy.gestiondestock.dto.LigneVenteDto;
import com.dy.gestiondestock.services.ArticleService;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import static com.dy.gestiondestock.utils.Constants.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT + "/articles")
public class ArticleControleur {

  private final ArticleService articleService;

  @Autowired
  public ArticleControleur(ArticleService articleService) {
    this.articleService = articleService;
  }

  @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ArticleDto save(@RequestBody ArticleDto dto) {
    return articleService.save(dto);
  }

  @GetMapping(value = "/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ArticleDto findById(@PathVariable("idArticle") Integer id) {
    return articleService.findById(id);
  }

  @GetMapping(value = "/filter/{codeArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ArticleDto findByCodeArticle(@PathVariable("codeArticle") String codeArticle) {
    return articleService.findByCodeArticle(codeArticle);
  }

  @GetMapping("/all")
  public List<ArticleDto> findAll() {
    return articleService.findAll();
  }

  @GetMapping(value = "/historique/vente/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<LigneVenteDto> findHistoriqueVentes(@PathVariable("idArticle") Integer idArticle) {
    return articleService.findHistoriqueVentes(idArticle);
  }

  @GetMapping(value = "/historique/commandeclient/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<LigneCommandeClientDto> findHistoriqueCommandeClient(@PathVariable("idArticle") Integer idArticle) {
    return articleService.findHistoriqueCommandeClient(idArticle);
  }

  @GetMapping(value = "/historique/commandefournisseur/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseur(@PathVariable("idArticle") Integer idArticle) {
    return articleService.findHistoriqueCommandeFournisseur(idArticle);
  }

  @GetMapping(value = "/filter/category/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<ArticleDto> findAllArticleByIdCategory(@PathVariable("idCategory") Integer idCategory) {
    return articleService.findAllArticleByIdCategory(idCategory);
  }

  @DeleteMapping(value = "/delete/{idArticle}")
  public void delete(@PathVariable("idArticle") Integer id) {
    articleService.delete(id);
  }

  @GetMapping("/export")
  public void exportArticles(HttpServletResponse response) {
    response.setContentType("text/csv");
    response.setHeader("Content-Disposition", "attachment; filename=articles.csv");
    try (OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8)) {
      articleService.exportArticles(writer);
    } catch (IOException e) {
      throw new RuntimeException("Erreur lors de l'export des articles", e);
    }
  }

  // Si tu veux activer l'import plus tard :
  /*
  @PostMapping("/import")
  public ResponseEntity<String> importArticles(@RequestParam("file") MultipartFile file) throws IOException {
    if (file == null || file.isEmpty()) {
      return ResponseEntity.badRequest().body("Fichier non fourni ou vide");
    }
    articleService.importArticles(file);
    return ResponseEntity.ok("Import des articles réussi");
  }
  */
}
