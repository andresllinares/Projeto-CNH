package com.fiap.juarez.cnh.repository;

import com.fiap.juarez.cnh.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface CarroRepository extends JpaRepository<Carro, Long>{
    Optional<Carro> findByPlaca(String placa);
}
