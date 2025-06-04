package com.dy.gestiondestock.controleur;
import com.dy.gestiondestock.dto.CategoryDto;
import com.dy.gestiondestock.services.CategoryService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static com.dy.gestiondestock.utils.Constants.APP_ROOT;

@RestController
public class CategoryControleur {

  private CategoryService categoryService;
  @Autowired
  public CategoryControleur(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @PostMapping(value = APP_ROOT + "/categories/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public CategoryDto save(@RequestBody CategoryDto dto) {
    return categoryService.save(dto);
  }

  @GetMapping(value = APP_ROOT + "/categories/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
  public CategoryDto findById(@PathVariable("idCategory")Integer idCategory) {
    return categoryService.findById(idCategory);
  }

  @GetMapping(value = APP_ROOT + "/categories/filter/{codeCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
  public CategoryDto findByCode(@PathVariable("codeCategory")String codeCategory) {
    return categoryService.findByCode(codeCategory);
  }

  @GetMapping(value = APP_ROOT + "/categories/all", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<CategoryDto> findAll() {
    return categoryService.findAll();
  }

  @DeleteMapping(value = APP_ROOT + "/categories/delete/{idCategory}")
  public void delete(@PathVariable("idCategory")Integer id) {
    categoryService.delete(id);
  }
}
