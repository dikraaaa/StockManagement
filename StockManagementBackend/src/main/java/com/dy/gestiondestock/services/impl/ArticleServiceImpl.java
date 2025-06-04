package com.dy.gestiondestock.services.impl;

import com.dy.gestiondestock.dto.ArticleDto;
import com.dy.gestiondestock.dto.LigneCommandeClientDto;
import com.dy.gestiondestock.dto.LigneCommandeFournisseurDto;
import com.dy.gestiondestock.dto.LigneVenteDto;
import com.dy.gestiondestock.exception.EntityNotFoundException;
import com.dy.gestiondestock.exception.ErrorCodes;
import com.dy.gestiondestock.exception.InvalidEntityException;
import com.dy.gestiondestock.exception.InvalidOperationException;
import com.dy.gestiondestock.model.Article;
import com.dy.gestiondestock.model.LigneCommandeClient;
import com.dy.gestiondestock.model.LigneCommandeFournisseur;
import com.dy.gestiondestock.model.LigneVente;
import com.dy.gestiondestock.repository.ArticleRepository;
import com.dy.gestiondestock.repository.LigneCommandeClientRepository;
import com.dy.gestiondestock.repository.LigneCommandeFournisseurRepository;
import com.dy.gestiondestock.repository.LigneVenteRepository;
import com.dy.gestiondestock.services.ArticleService;
import com.dy.gestiondestock.validator.ArticleValidator;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

  private ArticleRepository articleRepository;
  private LigneVenteRepository venteRepository;
  private LigneCommandeFournisseurRepository commandeFournisseurRepository;
  private LigneCommandeClientRepository commandeClientRepository;

  @Autowired
  public ArticleServiceImpl(
      ArticleRepository articleRepository,
      LigneVenteRepository venteRepository, LigneCommandeFournisseurRepository commandeFournisseurRepository,
      LigneCommandeClientRepository commandeClientRepository) {
    this.articleRepository = articleRepository;
    this.venteRepository = venteRepository;
    this.commandeFournisseurRepository = commandeFournisseurRepository;
    this.commandeClientRepository = commandeClientRepository;
  }

  @Override
  public ArticleDto save(ArticleDto dto) {
    List<String> errors = ArticleValidator.validate(dto);
    if (!errors.isEmpty()) {
      log.error("Article is not valid {}", dto);
      throw new InvalidEntityException("L'article n'est pas valide", ErrorCodes.ARTICLE_NOT_VALID, errors);
    }

    return ArticleDto.fromEntity(
        articleRepository.save(
            ArticleDto.toEntity(dto)
        )
    );
  }

  @Override
  public ArticleDto findById(Integer id) {
    if (id == null) {
      log.error("Article ID is null");
      return null;
    }

    return articleRepository.findById(id).map(ArticleDto::fromEntity).orElseThrow(() ->
        new EntityNotFoundException(
            "Aucun article avec l'ID = " + id + " n' ete trouve dans la BDD",
            ErrorCodes.ARTICLE_NOT_FOUND)
    );
  }

  @Override
  public ArticleDto findByCodeArticle(String codeArticle) {
    if (!StringUtils.hasLength(codeArticle)) {
      log.error("Article CODE is null");
      return null;
    }

    return articleRepository.findArticleByCodeArticle(codeArticle)
        .map(ArticleDto::fromEntity)
        .orElseThrow(() ->
            new EntityNotFoundException(
                "Aucun article avec le CODE = " + codeArticle + " n' ete trouve dans la BDD",
                ErrorCodes.ARTICLE_NOT_FOUND)
        );
  }

  @Override
  public List<ArticleDto> findAll() {
    List<ArticleDto> list = articleRepository.findAll().stream()
            .map(ArticleDto::fromEntity)
            .collect(Collectors.toList());
    log.info("Nombre d'articles trouvés : {}", list.size());
    return list;
  }

  @Override
  public List<LigneVenteDto> findHistoriqueVentes(Integer idArticle) {
    return venteRepository.findAllByArticleId(idArticle).stream()
        .map(LigneVenteDto::fromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public List<LigneCommandeClientDto> findHistoriqueCommandeClient(Integer idArticle) {
    return commandeClientRepository.findAllByArticleId(idArticle).stream()
        .map(LigneCommandeClientDto::fromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseur(Integer idArticle) {
    return commandeFournisseurRepository.findAllByArticleId(idArticle).stream()
        .map(LigneCommandeFournisseurDto::fromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public List<ArticleDto> findAllArticleByIdCategory(Integer idCategory) {
    return articleRepository.findAllByCategoryId(idCategory).stream()
        .map(ArticleDto::fromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public void delete(Integer id) {
    if (id == null) {
      log.error("Article ID is null");
      return;
    }
    List<LigneCommandeClient> ligneCommandeClients = commandeClientRepository.findAllByArticleId(id);
    if (!ligneCommandeClients.isEmpty()) {
      throw new InvalidOperationException("Impossible de supprimer un article deja utilise dans des commandes client", ErrorCodes.ARTICLE_ALREADY_IN_USE);
    }
    List<LigneCommandeFournisseur> ligneCommandeFournisseurs = commandeFournisseurRepository.findAllByArticleId(id);
    if (!ligneCommandeFournisseurs.isEmpty()) {
      throw new InvalidOperationException("Impossible de supprimer un article deja utilise dans des commandes fournisseur",
          ErrorCodes.ARTICLE_ALREADY_IN_USE);
    }
    List<LigneVente> ligneVentes = venteRepository.findAllByArticleId(id);
    if (!ligneVentes.isEmpty()) {
      throw new InvalidOperationException("Impossible de supprimer un article deja utilise dans des ventes",
          ErrorCodes.ARTICLE_ALREADY_IN_USE);
    }
    articleRepository.deleteById(id);
  }
  @Override
  public void exportArticles(Writer writer) throws IOException {
    List<Article> articles = articleRepository.findAll();
    writer.write("id,codeArticle,designation,prixUnitaire,tauxTva\n");
    for (Article article : articles) {
      writer.write(article.getId() + ","
              + article.getCodeArticle() + ","
              + article.getDesignation() + ","
              + article.getPrixUnitaireTtc() + ","
              + article.getTauxTva() + "\n");
    }
    writer.flush();
  }

  //@Override
  //public void importArticles(MultipartFile file) throws IOException {
    //try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
      //String line;
      //boolean firstLine = true;
      //while ((line = reader.readLine()) != null) {
        //if (firstLine) { // sauter la ligne d'entête
          //firstLine = false;
         // continue;
        //}
        //String[] fields = line.split(",");
        //Article article = new Article();
        // Si ID auto-généré, ne pas setter l'id ici (sinon faire Long.parseLong(fields[0]))
        //article.setCodeArticle(fields[1]);
        //article.setDesignation(fields[2]);
        //article.setPrixUnitaireTtc(BigDecimal.valueOf(fields[3]));
        //article.setTauxTva(BigDecimal.valueOf(fields[4]));
        //articleRepository.save(article);
      //}
    //}
  //}



}
