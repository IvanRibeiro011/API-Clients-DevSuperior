package com.devsuperior.apiclient.service;

import com.devsuperior.apiclient.dtos.ClientDTO;
import com.devsuperior.apiclient.entitiy.Client;
import com.devsuperior.apiclient.exceptions.ResourceNotFoundException;
import com.devsuperior.apiclient.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        return new ClientDTO(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado")));
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(ClientDTO::new);
    }

    @Transactional
    public ClientDTO insert(ClientDTO dto) {
        Client client = new Client();
        copyDtoToClient(client, dto);
        return new ClientDTO(repository.save(client));
    }
    @Transactional
    public ClientDTO update(Long id,ClientDTO dto) {
        try{
            Client client = repository.getReferenceById(id);
            copyDtoToClient(client, dto);
            return new ClientDTO(repository.save(client));
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    @Transactional
    public void delete(Long id) {
        ClientDTO dto = this.findById(id);
        Client client = new Client();
        copyDtoToClient(client,dto);
        repository.delete(client);
    }

    private void copyDtoToClient(Client client, ClientDTO dto) {
        client.setName(dto.getName());
        client.setCpf(dto.getCpf());
        client.setIncome(dto.getIncome());
        client.setBirthDate(dto.getBirthDate());
        client.setChildren(dto.getChildren());
    }
}
