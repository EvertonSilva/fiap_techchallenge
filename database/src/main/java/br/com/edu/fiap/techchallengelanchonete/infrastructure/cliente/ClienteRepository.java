package br.com.edu.fiap.techchallengelanchonete.infrastructure.cliente;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {

    Optional<ClienteModel> findByNome(String nome);
    Optional<ClienteModel> findByEmail(String email);
    Optional<ClienteModel> findBycpf(String cpf);
    Optional<ClienteModel> findBycpfAndEmail(String cpf, String email);

}
