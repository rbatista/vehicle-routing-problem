package com.raphaelnegrisoli.ifood.vehicleroutingproblem.repository;

import com.raphaelnegrisoli.ifood.vehicleroutingproblem.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    
}
