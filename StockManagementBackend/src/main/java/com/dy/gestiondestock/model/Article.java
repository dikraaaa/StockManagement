package com.dy.gestiondestock.model;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "article")
public class Article extends AbstractEntity {

  @Column(name = "codearticle")
  private String codeArticle;

  @Column(name = "designation")
  private String designation;

  @Column(name = "prixunitaireht")
  private BigDecimal prixUnitaireHt;

  @Column(name = "tauxtva")
  private BigDecimal tauxTva;

  @Column(name = "prixunitairettc")
  private BigDecimal prixUnitaireTtc;

  @Column(name = "photo")
  private String photo;

  @Column(name = "identreprise")
  private Integer idEntreprise;
  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "idcategory")
  private Category category;

  @JsonIgnore
  @Fetch(FetchMode.SUBSELECT)  // Optimise le chargement des collections
  @OneToMany(mappedBy = "article")
  private List<LigneVente> ligneVentes;

  // Appliquez la même annotation aux autres collections
  @JsonIgnore
  @Fetch(FetchMode.SUBSELECT)
  @OneToMany(mappedBy = "article")
  private List<LigneCommandeClient> ligneCommandeClients;

  @JsonIgnore
  @Fetch(FetchMode.SUBSELECT)
  @OneToMany(mappedBy = "article")
  private List<LigneCommandeFournisseur> ligneCommandeFournisseurs;

  @JsonIgnore
  @Fetch(FetchMode.SUBSELECT)
  @OneToMany(mappedBy = "article")
  private List<MvtStk> mvtStks;

  public String getCodeArticle() {
    return codeArticle;
  }

  public BigDecimal getPrixUnitaireHt() {
    return prixUnitaireHt;
  }

  public void setPrixUnitaireHt(BigDecimal prixUnitaireHt) {
    this.prixUnitaireHt = prixUnitaireHt;
  }

  public String getDesignation() {
    return designation;
  }

  public void setDesignation(String designation) {
    this.designation = designation;
  }

  public void setCodeArticle(String codeArticle) {
    this.codeArticle = codeArticle;
  }

  public BigDecimal getTauxTva() {
    return tauxTva;
  }

  public BigDecimal getPrixUnitaireTtc() {
    return prixUnitaireTtc;
  }

  public void setPrixUnitaireTtc(BigDecimal prixUnitaireTtc) {
    this.prixUnitaireTtc = prixUnitaireTtc;
  }

  public void setTauxTva(BigDecimal tauxTva) {
    this.tauxTva = tauxTva;
  }
}
