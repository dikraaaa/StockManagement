package com.dy.gestiondestock.services.impl;

import com.dy.gestiondestock.dto.ClientDto;
import com.dy.gestiondestock.exception.EntityNotFoundException;
import com.dy.gestiondestock.exception.ErrorCodes;
import com.dy.gestiondestock.exception.InvalidEntityException;
import com.dy.gestiondestock.exception.InvalidOperationException;
import com.dy.gestiondestock.model.Client;
import com.dy.gestiondestock.model.CommandeClient;
import com.dy.gestiondestock.repository.ClientRepository;
import com.dy.gestiondestock.repository.CommandeClientRepository;
import com.dy.gestiondestock.services.ClientService;
import com.dy.gestiondestock.validator.ClientValidator;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

  private ClientRepository clientRepository;
  private CommandeClientRepository commandeClientRepository;

  @Autowired
  public ClientServiceImpl(ClientRepository clientRepository, CommandeClientRepository commandeClientRepository) {
    this.clientRepository = clientRepository;
    this.commandeClientRepository = commandeClientRepository;
  }

  @Override
  public ClientDto save(ClientDto dto) {
    List<String> errors = ClientValidator.validate(dto);
    if (!errors.isEmpty()) {
      log.error("Client is not valid {}", dto);
      throw new InvalidEntityException("Le client n'est pas valide", ErrorCodes.CLIENT_NOT_VALID, errors);
    }

    return ClientDto.fromEntity(
        clientRepository.save(
            ClientDto.toEntity(dto)
        )
    );
  }

  @Override
  public ClientDto findById(Integer id) {
    if (id == null) {
      log.error("Client ID is null");
      return null;
    }
    return clientRepository.findById(id)
        .map(ClientDto::fromEntity)
        .orElseThrow(() -> new EntityNotFoundException(
            "Aucun Client avec l'ID = " + id + " n' ete trouve dans la BDD",
            ErrorCodes.CLIENT_NOT_FOUND)
        );
  }

  @Override
  public List<ClientDto> findAll() {
    return clientRepository.findAll().stream()
        .map(ClientDto::fromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public void delete(Integer id) {
    if (id == null) {
      log.error("Client ID is null");
      return;
    }
    List<CommandeClient> commandeClients = commandeClientRepository.findAllByClientId(id);
    if (!commandeClients.isEmpty()) {
      throw new InvalidOperationException("Impossible de supprimer un client qui a deja des commande clients",
          ErrorCodes.CLIENT_ALREADY_IN_USE);
    }
    clientRepository.deleteById(id);
  }

  @Override
  public void exportClients(Writer writer) throws IOException {
    List<Client> clients = clientRepository.findAll();
    // Écriture en CSV simple : id, nom, email par exemple
    writer.write("id,nom,email\n");
    for (Client c : clients) {
      writer.write(c.getId() + "," + c.getNom() + "," + c.getMail() + "\n");
    }
    writer.flush();
  }
  @Override
  public void importClients(MultipartFile file) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
    String line;
    boolean firstLine = true;
    while ((line = reader.readLine()) != null) {
      if (firstLine) { // sauter l'entête CSV
        firstLine = false;
        continue;
      }
      String[] fields = line.split(",");
      Client client = new Client();
      client.setId(Integer.parseInt(fields[0]));  // ou ignore si auto-generated
      client.setNom(fields[1]);
      client.setMail(fields[2]);
      clientRepository.save(client);
    }
  }

}
