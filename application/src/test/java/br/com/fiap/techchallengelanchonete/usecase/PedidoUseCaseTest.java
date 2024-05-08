package br.com.fiap.techchallengelanchonete.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.edu.fiap.techchallengelanchonete.domain.ItemPedido;
import br.com.edu.fiap.techchallengelanchonete.domain.Pedido;
import br.com.edu.fiap.techchallengelanchonete.domain.Produto;
import br.com.edu.fiap.techchallengelanchonete.domain.StatusPagamento;
import br.com.edu.fiap.techchallengelanchonete.domain.StatusPedido;
import br.com.edu.fiap.techchallengelanchonete.domain.Cliente.Cliente;
import br.com.edu.fiap.techchallengelanchonete.domain.Cliente.ClienteNulo;
import br.com.edu.fiap.techchallengelanchonete.exception.ApplicationException;
import br.com.edu.fiap.techchallengelanchonete.exception.NotFoundResourceException;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.IClientePersistence;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.IPedidoPersistence;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.IProdutoPersistence;
import br.com.edu.fiap.techchallengelanchonete.integration.gatewaypagamento.IGatewayPagamentoRegistrador;
import br.com.edu.fiap.techchallengelanchonete.usecase.PedidoUseCase;

class PedidoUseCaseTest {
    
    AutoCloseable mock;

    PedidoUseCase pedidoUseCase;

    @Mock
    IPedidoPersistence pedidoPersistence;
    @Mock
    IProdutoPersistence produtoPersistence;
    @Mock
    IClientePersistence clientePersistence;
    @Mock
    IGatewayPagamentoRegistrador gatewayPagamentoRegistrador;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        pedidoUseCase = new PedidoUseCase(pedidoPersistence, produtoPersistence, clientePersistence, gatewayPagamentoRegistrador);
    }

    @AfterEach
    void teardown() throws Exception {
        mock.close();
    }

    @Nested
    class RegistraPedido {

        @Nested
        class ProdutosInexistentes {
            @Test
            void deveRegistrarPedido_quandoListaItensNula() {
                var pedido = new Pedido();
                pedido.setItens(null);

                var applicationException = assertThrows(
                    ApplicationException.class, 
                    () -> pedidoUseCase.registraPedido(pedido));

                assertThat(applicationException)
                    .hasMessageContaining("Produto(s) inexistente(s)!");
            }

            @Test
            void deveRegistrarPedido_quandoListaItensVazia() {
                var pedido = new Pedido();

                var applicationException = assertThrows(
                    ApplicationException.class, 
                    () -> pedidoUseCase.registraPedido(pedido));

                assertThat(applicationException)
                    .hasMessageContaining("Produto(s) inexistente(s)!");
            }

            @Test
            void deveRegistrarPedido_quandoListaItensComProdutoNulo() {
                var item = new ItemPedido();
                item.setProduto(null);

                var pedido = new Pedido();
                pedido.getItens().add(item);

                var applicationException = assertThrows(
                    ApplicationException.class, 
                    () -> pedidoUseCase.registraPedido(pedido));

                assertThat(applicationException)
                    .hasMessageContaining("Produto(s) inexistente(s)!");
            }

            @Test
            void deveRegistrarPedido_quandoListaItensComProdutoNaoEncontrado() {
                when(produtoPersistence.buscaId(any(Long.class)))
                    .thenReturn(Optional.empty());

                var pedido = new Pedido();
                pedido.getItens().add(new ItemPedido());

                var applicationException = assertThrows(
                    NotFoundResourceException.class, 
                    () -> pedidoUseCase.registraPedido(pedido));

                assertThat(applicationException)
                    .hasMessageContaining("Produto não encontrado!");
                
                verify(produtoPersistence, times(1)).buscaId(any(Long.class));
            }
        }

        @Test
        void deveRegistrarPedido_quandoClienteNaoEncontrado() {
            when(produtoPersistence.buscaId(any(Long.class)))
                .thenReturn(Optional.of(new Produto()));
            when(clientePersistence.buscaId(any(Long.class)))
                .thenReturn(new ClienteNulo());

            var pedido = new Pedido();
            pedido.getItens().add(new ItemPedido());

            var applicationException = assertThrows(
                NotFoundResourceException.class, 
                () -> pedidoUseCase.registraPedido(pedido));

            assertThat(applicationException)
                .hasMessageContaining("Cliente não encontrado!");
            
            verify(produtoPersistence, times(1)).buscaId(any(Long.class));
            verify(clientePersistence, times(1)).buscaId(any(Long.class));
        }

        @Test
        void deveRegistrarPedido_quandoClienteEncontrado() {
            when(produtoPersistence.buscaId(any(Long.class)))
                .thenReturn(Optional.of(new Produto()));
            when(clientePersistence.buscaId(any(Long.class)))
                .thenReturn(new Cliente());
            doNothing().when(gatewayPagamentoRegistrador).registroPagamento(any(Pedido.class));
            when(pedidoPersistence.registraPedido(any(Pedido.class)))
                .thenReturn(new Pedido());

            var pedido = new Pedido();
            pedido.getItens().add(new ItemPedido());

            pedidoUseCase.registraPedido(pedido);
            
            verify(produtoPersistence, times(1)).buscaId(any(Long.class));
            verify(clientePersistence, times(1)).buscaId(any(Long.class));
            verify(gatewayPagamentoRegistrador, times(1)).registroPagamento(any(Pedido.class));
            verify(pedidoPersistence, times(1)).registraPedido(any(Pedido.class));
        }

        @Test
        void deveRegistrarPedido_quandoClienteNuloNaoInformadoEncontrado() {
            when(produtoPersistence.buscaId(any(Long.class)))
                .thenReturn(Optional.of(new Produto()));
            doNothing().when(gatewayPagamentoRegistrador).registroPagamento(any(Pedido.class));
            when(pedidoPersistence.registraPedido(any(Pedido.class)))
                .thenReturn(new Pedido());

            var pedido = new Pedido();
            pedido.getItens().add(new ItemPedido());
            pedido.setCliente(null);

            pedidoUseCase.registraPedido(pedido);
            
            verify(produtoPersistence, times(1)).buscaId(any(Long.class));
            verify(gatewayPagamentoRegistrador, times(1)).registroPagamento(any(Pedido.class));
            verify(pedidoPersistence, times(1)).registraPedido(any(Pedido.class));
        }

        @Test
        void deveRegistrarPedido_quandoClienteNaoInformadoEncontradoIdNulo() {
            when(produtoPersistence.buscaId(any(Long.class)))
                .thenReturn(Optional.of(new Produto()));
            doNothing().when(gatewayPagamentoRegistrador).registroPagamento(any(Pedido.class));
            when(pedidoPersistence.registraPedido(any(Pedido.class)))
                .thenReturn(new Pedido());

            var pedido = new Pedido();
            pedido.getItens().add(new ItemPedido());
            pedido.getCliente().setId(null);

            pedidoUseCase.registraPedido(pedido);
            
            verify(produtoPersistence, times(1)).buscaId(any(Long.class));
            verify(gatewayPagamentoRegistrador, times(1)).registroPagamento(any(Pedido.class));
            verify(pedidoPersistence, times(1)).registraPedido(any(Pedido.class));
        }
    }

    @Test
    void deveAtualizarPedido() {
        when(pedidoPersistence.atualizarPedido(any(Pedido.class)))
            .thenAnswer(i -> (Pedido)i.getArgument(0));

        pedidoUseCase.atualizaPedido(new Pedido());

        verify(pedidoPersistence, times(1)).atualizarPedido(any(Pedido.class));
    }

    @Test
    void deveConsultarPedidoPorCodigo() {
        when(pedidoPersistence.consultaPedidoPorCodigo(any(String.class)))
            .thenReturn(Optional.empty());

        pedidoUseCase.consultaPedido(UUID.randomUUID().toString());

        verify(pedidoPersistence, times(1)).consultaPedidoPorCodigo(any(String.class));
    }

    @Nested
    class ConfirmacaoPagamento {
        @Test
        void deveConfirmarPagamento_quandoPedidoNaoEncontrado() {
            when(pedidoPersistence.consultaPedidoPorCodigo(any(String.class)))
                .thenReturn(Optional.empty());

            var uuid = UUID.randomUUID().toString();
            var notFoundResourceException = assertThrows(
                NotFoundResourceException.class, 
                () -> pedidoUseCase.confirmacaoPagamento(uuid, StatusPagamento.APROVADO));

            assertThat(notFoundResourceException)
                .hasMessage("Pedido não encontrado!");
            
            verify(pedidoPersistence, times(1)).consultaPedidoPorCodigo(any(String.class));
        }

        @Test
        void deveConfirmarPagamento_quandoPedidoEncontradoStatusAprovado() {
            when(pedidoPersistence.consultaPedidoPorCodigo(any(String.class)))
                .thenReturn(Optional.of(new Pedido()));
            when(pedidoPersistence.atualizarPedido(any(Pedido.class)))
                .thenAnswer(i -> (Pedido)i.getArgument(0));

            pedidoUseCase.confirmacaoPagamento(UUID.randomUUID().toString(), StatusPagamento.APROVADO);

            verify(pedidoPersistence, times(1)).consultaPedidoPorCodigo(any(String.class));
            verify(pedidoPersistence, times(1)).atualizarPedido(any(Pedido.class));
        }

        @Test
        void deveConfirmarPagamento_quandoPedidoEncontradoStatusReprovado() {
            when(pedidoPersistence.consultaPedidoPorCodigo(any(String.class)))
                .thenReturn(Optional.of(new Pedido()));
            when(pedidoPersistence.atualizarPedido(any(Pedido.class)))
                .thenAnswer(i -> (Pedido)i.getArgument(0));

            pedidoUseCase.confirmacaoPagamento(UUID.randomUUID().toString(), StatusPagamento.REPROVADO);
            
            verify(pedidoPersistence, times(1)).consultaPedidoPorCodigo(any(String.class));
            verify(pedidoPersistence, times(1)).atualizarPedido(any(Pedido.class));
        }
    }

    @Test
    void deveBuscarPedidoPorId() {
        when(pedidoPersistence.pedidoPorId(any(Long.class)))
            .thenReturn(Optional.empty());

        pedidoUseCase.buscaPorId(1L);

        verify(pedidoPersistence, times(1)).pedidoPorId(any(Long.class));
    }

    @Nested
    class ListagemPedidos {
        @Test
        void deveListarPedido_quandoStatusInformado() {
            when(pedidoPersistence.listaPedidosPorStatus(any(StatusPedido.class)))
                .thenReturn(new ArrayList<>());
            
            pedidoUseCase.listaPedidos(Optional.of(StatusPedido.AGUARDANDO_PAGAMENTO));

            verify(pedidoPersistence, times(1)).listaPedidosPorStatus(any(StatusPedido.class));
        }

        @Test
        void deveListarPedido_quandoStatusNaoInformado() {
            when(pedidoPersistence.listaPedidos())
                .thenReturn(new ArrayList<>());
            
            pedidoUseCase.listaPedidos(Optional.empty());

            verify(pedidoPersistence, times(1)).listaPedidos();
        }
    }

    @Nested
    class AtualizaStatusPedido {
        @Test
        void deveAtualizarStatusPedido_quandoPedidoNaoEncontrado() {
            when(pedidoPersistence.pedidoPorId(any(Long.class)))
                .thenReturn(Optional.empty());

            var notFoundResourceException = 
                assertThrows(NotFoundResourceException.class, 
                () -> pedidoUseCase.atualizaStatusPedido(1L, StatusPedido.FINALIZADO));

            assertThat(notFoundResourceException)
                .hasMessage("Pedido não encontrado!");
            
            verify(pedidoPersistence, times(1)).pedidoPorId(any(Long.class));
        }

        @Test
        void deveAtualizarStatusPedido_quandoStatusIncoerente() {
            when(pedidoPersistence.pedidoPorId(any(Long.class)))
                .thenReturn(Optional.of(new Pedido()));

            var applicationException = 
                assertThrows(ApplicationException.class, 
                () -> pedidoUseCase.atualizaStatusPedido(1L, StatusPedido.FINALIZADO));

            assertThat(applicationException)
                .hasMessage("Status incoerente!");
            
            verify(pedidoPersistence, times(1)).pedidoPorId(any(Long.class));
        }

        @Test
        void deveAtualizarStatusPedido_quandoAtualizacaoValida() {
            var pedidoRecebido = new Pedido();
            pedidoRecebido.setStatus(StatusPedido.RECEBIDO);

            when(pedidoPersistence.pedidoPorId(any(Long.class)))
                .thenReturn(Optional.of(pedidoRecebido));
            when(pedidoPersistence.registraPedido(any(Pedido.class)))
                .thenAnswer(i -> i.getArgument(0));

            pedidoUseCase.atualizaStatusPedido(1L, StatusPedido.EM_PREPARACAO);
            
            verify(pedidoPersistence, times(1)).pedidoPorId(any(Long.class));
            verify(pedidoPersistence, times(1)).registraPedido(any(Pedido.class));
        }
    }

}
