package br.com.fiap.techchallengelanchonete.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.edu.fiap.techchallengelanchonete.domain.Cliente.Cliente;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.CPF;
import br.com.edu.fiap.techchallengelanchonete.exception.ApplicationException;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.IClientePersistence;
import br.com.edu.fiap.techchallengelanchonete.usecase.ClienteUseCase;

class ClienteUseCaseTest {

    AutoCloseable mock;

    ClienteUseCase clienteUseCase;

    @Mock
    IClientePersistence clientePersistence;

    @BeforeEach
    void startup() {
        mock = MockitoAnnotations.openMocks(this);
        clienteUseCase = new ClienteUseCase(clientePersistence);
    }

    @AfterEach
    void teardown() throws Exception {
        mock.close();
    }

    @Nested
    class SalvaCliente {
        @Test
        void deveSalvarCliente_comCPFDuplicado() {
            when(clientePersistence.buscaCPF(any(CPF.class)))
                .thenReturn(new Cliente());
            
            var cliente = new Cliente();
            var applicationException = assertThrows(
                ApplicationException.class, () -> {
                clienteUseCase.salvaCliente(cliente);
            });

            assertThat(applicationException)
                .hasMessage("Cliente já existe!");
            verify(clientePersistence, times(1)).buscaCPF(any(CPF.class));
        }

        @Test
        void deveSalvarCliente_valido() {
            var clienteInexistente = new Cliente();
            clienteInexistente.setId(null);

            when(clientePersistence.buscaCPF(any(CPF.class)))
                .thenReturn(clienteInexistente);
            when(clientePersistence.cadastro(any(Cliente.class)))
                .thenReturn(new Cliente());

            clienteUseCase.salvaCliente(new Cliente());

            verify(clientePersistence, times(1)).buscaCPF(any(CPF.class));
            verify(clientePersistence, times(1)).cadastro(any(Cliente.class));            
        }
    }

    @Test
    void deveBuscaClientePorCPF() {
        when(clientePersistence.buscaCPF(any(CPF.class)))
            .thenReturn(new Cliente());
        
        clienteUseCase.buscaClientePorCPF("111111111111");

        verify(clientePersistence, times(1)).buscaCPF(any(CPF.class));
    }

}
