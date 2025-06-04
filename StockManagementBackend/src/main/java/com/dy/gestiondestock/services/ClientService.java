package com.dy.gestiondestock.services;

import com.dy.gestiondestock.dto.ClientDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;

public interface ClientService {

  ClientDto save(ClientDto dto);

  ClientDto findById(Integer id);

  List<ClientDto> findAll();

  void delete(Integer id);

  void exportClients(Writer writer) throws IOException;

  void importClients(MultipartFile file) throws IOException;
}
