package br.com.edu.fiap.techchallengelanchonete.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {

    Optional<ClienteModel> findByNome(String nome);
    Optional<ClienteModel> findBycpf(String cpf);
}
