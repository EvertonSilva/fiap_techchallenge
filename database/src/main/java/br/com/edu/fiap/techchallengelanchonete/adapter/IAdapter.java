package br.com.edu.fiap.techchallengelanchonete.adapter;

public interface IAdapter<Domain, Model> {
    Domain toDomain(Model model);
    Model toModel(Domain domain);
}
