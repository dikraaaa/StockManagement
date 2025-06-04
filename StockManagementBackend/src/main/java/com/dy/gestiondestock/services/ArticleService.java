package com.dy.gestiondestock.services;

import com.dy.gestiondestock.dto.ArticleDto;
import com.dy.gestiondestock.dto.LigneCommandeClientDto;
import com.dy.gestiondestock.dto.LigneCommandeFournisseurDto;
import com.dy.gestiondestock.dto.LigneVenteDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

public interface ArticleService {

  ArticleDto save(ArticleDto dto);

  ArticleDto findById(Integer id);

  ArticleDto findByCodeArticle(String codeArticle);

  List<ArticleDto> findAll();

  List<LigneVenteDto> findHistoriqueVentes(Integer idArticle);

  List<LigneCommandeClientDto> findHistoriqueCommandeClient(Integer idArticle);

  List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseur(Integer idArticle);

  List<ArticleDto> findAllArticleByIdCategory(Integer idCategory);

  void delete(Integer id);

  //void importArticles(MultipartFile file) throws IOException;

  void exportArticles(Writer writer) throws IOException;



 // void exportArticles(Writer writer) throws IOException;
}
