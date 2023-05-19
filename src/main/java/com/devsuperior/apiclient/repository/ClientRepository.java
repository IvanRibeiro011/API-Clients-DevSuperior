package com.devsuperior.apiclient.repository;

import com.devsuperior.apiclient.entitiy.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
}
