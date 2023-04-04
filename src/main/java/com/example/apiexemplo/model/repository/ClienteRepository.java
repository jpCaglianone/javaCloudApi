package com.example.apiexemplo.model.repository;

import com.example.apiexemplo.model.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Clientes, Integer> {
}
