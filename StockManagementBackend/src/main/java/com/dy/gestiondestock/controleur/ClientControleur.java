package com.dy.gestiondestock.controleur;
import com.dy.gestiondestock.dto.ClientDto;
import com.dy.gestiondestock.services.ClientService;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;

import static com.dy.gestiondestock.utils.Constants.APP_ROOT;

@RestController
public class ClientControleur {

  private ClientService clientService;
  @Autowired
  public ClientControleur(ClientService clientService) {
    this.clientService = clientService;
  }

  @PostMapping(value = APP_ROOT + "/clients/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ClientDto save(@RequestBody ClientDto dto) {
    return clientService.save(dto);
  }

  @GetMapping(value = APP_ROOT + "/clients/{idClient}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ClientDto findById(@PathVariable("idClient")Integer id) {
    return clientService.findById(id);
  }

  @GetMapping(value = APP_ROOT + "/clients/all", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<ClientDto> findAll() {
    return clientService.findAll();
  }

  @DeleteMapping(value = APP_ROOT + "/clients/delete/{idClient}")
  public void delete(@PathVariable("idClient")Integer id) {
    clientService.delete(id);
  }
  @GetMapping(value = APP_ROOT +"/clients/export")
  public void exportClients(HttpServletResponse response) {
    response.setContentType("text/csv");
    response.setHeader("Content-Disposition", "attachment; filename=clients.csv");
    try {
      clientService.exportClients(response.getWriter());
    } catch (IOException e) {
      // gérer l’exception, logger ou renvoyer une erreur HTTP
      e.printStackTrace();
    }
  }
  @PostMapping(value = APP_ROOT +"/clients/import")
  public ResponseEntity<String> importClients(@RequestParam("file") MultipartFile file) {
    try {
      clientService.importClients(file);
      return ResponseEntity.ok("Import réussi !");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur d'import : " + e.getMessage());
    }
  }

}

