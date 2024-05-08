package br.com.fiap.techchallengelanchonete.domain;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import br.com.edu.fiap.techchallengelanchonete.domain.ItemPedido;
import br.com.edu.fiap.techchallengelanchonete.domain.Pedido;
import br.com.edu.fiap.techchallengelanchonete.domain.StatusPagamento;
import br.com.edu.fiap.techchallengelanchonete.domain.StatusPedido;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.DataCriacao;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Quantidade;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Valor;

class PedidoTest {
    
    @Test
    void deveCriarPedidoVazio() {
        var pedido = new Pedido();

        assertAll("Atributos vazios", 
            () -> assertThat(pedido).isNotNull(),
            () -> assertThat(pedido.getId()).isNotNull(),
            () -> assertThat(pedido.getCliente()).isNotNull(),
            () -> assertThat(pedido.getCodigo()).isNotNull(),
            () -> assertThat(pedido.getData()).isNotNull(),
            () -> assertThat(pedido.getItens())
                    .isNotNull()
                    .isEmpty(),
            () -> assertThat(pedido.getPagamento()).isNotNull(),
            () -> assertThat(pedido.getStatus())
                    .isNotNull()
                    .isEqualTo(StatusPedido.AGUARDANDO_PAGAMENTO)
        );
    }

    @Nested
    class TrocaStatusPedido {

        @Nested
        class DeAguardandoPagamentoParaRecebido {
            @Test
            void deveAvancarStatus_deAguardandoPagamentoParaRecebido_comStatusPagamentoAprovado() {
                var pedido = new Pedido();
                pedido.getPagamento().setStatus(StatusPagamento.APROVADO);

                var trocaStatusValida = pedido.validaProximoStatus(StatusPedido.RECEBIDO);

                assertTrue(trocaStatusValida);
            }

            @ParameterizedTest
            @EnumSource(
                value = StatusPagamento.class, 
                names = { "AGUARDANDO", "REPROVADO" })
            void deveAvancarStatus_deAguardandoPagamentoParaRecebido_comStatusAguardandoPagamentoOuPagamentoReprovado(
                StatusPagamento statusPagamento) {
                var pedido = new Pedido();
                pedido.getPagamento().setStatus(statusPagamento);

                var trocaStatusValida = pedido.validaProximoStatus(StatusPedido.RECEBIDO);

                assertFalse(trocaStatusValida);
            }

            @ParameterizedTest
            @EnumSource(
                value = StatusPedido.class, 
                names = { "AGUARDANDO_PAGAMENTO", "EM_PREPARACAO", "PRONTO", "FINALIZADO" })
            void deveAvancarStatus_deAguardandoPagamentoQualquerOutro_comStatusPagamentoAprovado(StatusPedido statusPedidoDestino) {
                var pedido = new Pedido();
                pedido.getPagamento().setStatus(StatusPagamento.APROVADO);

                var trocaStatusValida = pedido.validaProximoStatus(statusPedidoDestino);

                assertFalse(trocaStatusValida);
            }
        }

        @Nested
        class ParaEmPreparacao {
            @Test
            void deveAvancarStatus_deRecidoParaEmPreparacao() {
                var pedido = new Pedido();
                pedido.setStatus(StatusPedido.RECEBIDO);

                var trocaStatusValida = pedido.validaProximoStatus(StatusPedido.EM_PREPARACAO);

                assertTrue(trocaStatusValida);
            }

            @ParameterizedTest
            @EnumSource(
                value = StatusPedido.class, 
                names = { "AGUARDANDO_PAGAMENTO", "EM_PREPARACAO", "PRONTO", "FINALIZADO", "CANCELADO" })
            void deveAvancarStatus_deStatusInvalidosParaEmPreparacao(StatusPedido statusPedidoOriginal) {
                var pedido = new Pedido();
                pedido.setStatus(statusPedidoOriginal);

                var trocaStatusValida = pedido.validaProximoStatus(StatusPedido.EM_PREPARACAO);

                assertFalse(trocaStatusValida);
            }
        }

        @Nested
        class ParaPronto {
            @Test
            void deveAvancarStatus_deEmPreparacaoParaPronto() {
                var pedido = new Pedido();
                pedido.setStatus(StatusPedido.EM_PREPARACAO);

                var trocaStatusValida = pedido.validaProximoStatus(StatusPedido.PRONTO);

                assertTrue(trocaStatusValida);
            }

            @ParameterizedTest
            @EnumSource(
                value = StatusPedido.class, 
                names = { "AGUARDANDO_PAGAMENTO", "RECEBIDO", "PRONTO", "FINALIZADO", "CANCELADO" })
            void deveAvancarStatus_deStatusInvalidosParaPronto(StatusPedido statusPedidoOriginal) {
                var pedido = new Pedido();
                pedido.setStatus(statusPedidoOriginal);

                var trocaStatusValida = pedido.validaProximoStatus(StatusPedido.PRONTO);

                assertFalse(trocaStatusValida);
            }
        }

        @Nested
        class ParaFinalizado {
            @Test
            void deveAvancarStatus_deProntoParaFinalizado() {
                var pedido = new Pedido();
                pedido.setStatus(StatusPedido.PRONTO);

                var trocaStatusValida = pedido.validaProximoStatus(StatusPedido.FINALIZADO);

                assertTrue(trocaStatusValida);
            }

            @ParameterizedTest
            @EnumSource(
                value = StatusPedido.class, 
                names = { "AGUARDANDO_PAGAMENTO", "RECEBIDO", "EM_PREPARACAO", "FINALIZADO", "CANCELADO" })
            void deveAvancarStatus_deProntoParaFinalizado(StatusPedido statusPedidoOriginal) {
                var pedido = new Pedido();
                pedido.setStatus(statusPedidoOriginal);

                var trocaStatusValida = pedido.validaProximoStatus(StatusPedido.FINALIZADO);

                assertFalse(trocaStatusValida);
            }
        }        

        @Nested
        class ParaCancelado {
            @ParameterizedTest
            @EnumSource(
                value = StatusPedido.class, 
                names = { "AGUARDANDO_PAGAMENTO", "RECEBIDO", "EM_PREPARACAO", "PRONTO" })
            void deveAvancarStatus_paraCancelado(StatusPedido statusPedidoOriginal) {
                var pedido = new Pedido();
                pedido.setStatus(statusPedidoOriginal);

                var trocaStatusValida = pedido.validaProximoStatus(StatusPedido.CANCELADO);

                assertTrue(trocaStatusValida);
            }

            @ParameterizedTest
            @EnumSource(
                value = StatusPedido.class, 
                names = { "FINALIZADO", "CANCELADO" })
            void deveAvancarStatus_deStatusInvalidosParaCancelado(StatusPedido statusPedidoOriginal) {
                var pedido = new Pedido();
                pedido.setStatus(statusPedidoOriginal);

                var trocaStatusValida = pedido.validaProximoStatus(StatusPedido.CANCELADO);

                assertFalse(trocaStatusValida);
            }
        }

        @Nested
        class ComStatusNulo {
            @ParameterizedTest
            @EnumSource(value = StatusPedido.class)
            void deveAvancarStatus_deQualquerStatusParaNulo(StatusPedido statusPedidoOrigem) {
                var pedido = new Pedido();
                pedido.setStatus(statusPedidoOrigem);
    
                var trocaStatusValida = pedido.validaProximoStatus(null);
    
                assertFalse(trocaStatusValida);
            }
    
            @ParameterizedTest
            @EnumSource(value = StatusPedido.class)
            void deveAvancarStatus_deNuloParaQualquerStatus(StatusPedido statusPedidoDestino) {
                var pedido = new Pedido();
                pedido.setStatus(null);
    
                var trocaStatusValida = pedido.validaProximoStatus(statusPedidoDestino);
    
                assertFalse(trocaStatusValida);
            }   
        }

        @Test
        void deveAvancarStatus_quandoPagamentoNulo() {
            var pedido = new Pedido();
            pedido.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO);
            pedido.setPagamento(null);

            var trocaStatusValida = pedido.validaProximoStatus(StatusPedido.RECEBIDO);

            assertFalse(trocaStatusValida);
        }

    }

    @Nested
    class OrdenacaoPedidos {
        @Nested
        class OrdenarPedidosPorStatusRecebidoEmPreparacaoPronto {
            @Test
            void deveOrdenarPedidosPorStatusRecebidoEmPreparacaoPronto() {
                var listaStatusPedido = Arrays.asList(StatusPedido.values());
                var pedidosNaoOrdenados = new ArrayList<Pedido>();

                listaStatusPedido.forEach(statusPedido -> {
                    var pedido = new Pedido();
                    pedido.setStatus(statusPedido);
                    pedidosNaoOrdenados.add(pedido);
                });

                for (int i = 0; i < listaStatusPedido.size(); i++) {
                    assertEquals(listaStatusPedido.get(i), pedidosNaoOrdenados.get(i).getStatus());
                }

                var pedidosOrdenados = Pedido.ordenaPorStatus(pedidosNaoOrdenados);

                var listaStatusPedidoOrdenados = Arrays.asList(StatusPedido.RECEBIDO, StatusPedido.EM_PREPARACAO, StatusPedido.PRONTO);
                for (int i = 0; i < listaStatusPedidoOrdenados.size(); i++) {
                    assertEquals(listaStatusPedidoOrdenados.get(i), pedidosOrdenados.get(i).getStatus());
                }
            }

            @Test
            void deveOrdenarPedidosPorStatusRecebidoEmPreparacaoPronto_quandoListaNula() {
                List<Pedido> pedidos = null;
                pedidos = Pedido.ordenaPorStatus(pedidos);
                assertThat(pedidos)
                    .isNotNull()
                    .isEmpty();
            }
        }
        
        @Nested
        class OrdenarPedidosPorDataMaisAntiga {
            @Test
            void deveOrdenarPedidosPorDataMaisAntiga() {
                var agora = Instant.now().getEpochSecond();
                var listaStatusPedido = Arrays.asList(StatusPedido.values());
                var pedidosNaoOrdenados = new ArrayList<Pedido>();

                for (int i = 0; i < listaStatusPedido.size(); i++) {
                    var pedido = new Pedido();
                    pedido.setStatus(listaStatusPedido.get(i));
                    pedido.setData(new DataCriacao(new Date(agora + i*10)));
                    pedidosNaoOrdenados.add(pedido);
                }

                for (int i = 0; i < listaStatusPedido.size(); i++) {
                    assertEquals(listaStatusPedido.get(i), pedidosNaoOrdenados.get(i).getStatus());
                }

                var pedidosOrdenados = Pedido.ordenaPorDataMaisAntiga(pedidosNaoOrdenados);

                for (int i = 0; i < pedidosOrdenados.size(); i++) {
                    var pedido = pedidosOrdenados.get(i);
                    var proximoPedido = (i+1 < pedidosOrdenados.size()) ? pedidosOrdenados.get(i+1) : null;

                    if (proximoPedido != null)
                        assertThat(pedido.getData().getValor().getTime())
                            .isLessThan(proximoPedido.getData().getValor().getTime());
                }
            }

            @Test
            void deveOrdenarPedidosPorDataMaisAntiga_quandoListaNula() {
                List<Pedido> pedidos = null;
                pedidos = Pedido.ordenaPorDataMaisAntiga(pedidos);
                assertThat(pedidos)
                    .isNotNull()
                    .isEmpty();
            }
        }
        
        @Nested
        class FiltrarPedidosPorStatusDiferentesDeAguardandoPagamentoFinalizado {
            @Test
            void deveFiltrarPedidosPorStatusDiferentesDeAguardandoPagamentoFinalizado() {
                var listaStatusPedido = Arrays.asList(StatusPedido.values());
                var pedidosNaoFiltrados = new ArrayList<Pedido>();

                listaStatusPedido.forEach(statusPedido -> {
                    var pedido = new Pedido();
                    pedido.setStatus(statusPedido);
                    pedidosNaoFiltrados.add(pedido);
                });

                for (int i = 0; i < listaStatusPedido.size(); i++) {
                    assertEquals(listaStatusPedido.get(i), pedidosNaoFiltrados.get(i).getStatus());
                }

                var pedidosFiltrados = Pedido.filtraPorStatus(pedidosNaoFiltrados);

                var pedidosAguardandoPagamentoOuFinalizados = 
                    pedidosFiltrados
                        .stream()
                        .filter(x -> 
                            x.getStatus().equals(StatusPedido.AGUARDANDO_PAGAMENTO)
                            || x.getStatus().equals(StatusPedido.FINALIZADO))
                        .collect(Collectors.toList());
                
                assertThat(pedidosAguardandoPagamentoOuFinalizados)
                    .isEmpty();
            }

            @Test
            void deveFiltrarPedidosPorStatusDiferentesDeAguardandoPagamentoFinalizado_quandoListaNula() {
                List<Pedido> pedidos = null;
                pedidos = Pedido.filtraPorStatus(pedidos);
                assertThat(pedidos)
                    .isNotNull()
                    .isEmpty();
            }
        }

        @Nested
        class OrdenarPedidosPorStatusDataCriacaoFiltrarPorStatus {
            @Test
            void deveOrdenarPedidosPorStatusDataCriacaoFiltrarPorStatus_quandoListaVazia() {
                List<Pedido> pedidos = new ArrayList<Pedido>();
                pedidos = Pedido.ordenaPorStatusDataCriacaoFiltraPorStatus(pedidos);
                assertThat(pedidos)
                    .isNotNull()
                    .isEmpty();
            }

            @Test
            void deveOrdenarPedidosPorStatusDataCriacaoFiltrarPorStatus_quandoListaNula() {
                List<Pedido> pedidos = null;
                pedidos = Pedido.ordenaPorStatusDataCriacaoFiltraPorStatus(pedidos);
                assertThat(pedidos)
                    .isNotNull()
                    .isEmpty();
            }
        }

    }

    @Nested
    class ValorTotal {
        @Test
        void deveRetornarValorTotal_quandoItensVazios() {
            var pedido = new Pedido();

            assertThat(pedido.getValorTotal())
                .isNotNull();
            assertThat(pedido.getValorTotal().getValor())
                .isNotNull()
                .isZero();
        }

        @Test
        void deveRetornarValorTotal_quandoItensNulo() {
            var pedido = new Pedido();
            pedido.setItens(null);

            assertThat(pedido.getValorTotal())
                .isNotNull();
            assertThat(pedido.getValorTotal().getValor())
                .isNotNull()
                .isZero();
        }

        @Test
        void deveRetornarValorTotal_quandoValorFracionario() {
            var itemPedidoA = new ItemPedido();
            itemPedidoA.setQuantidade(new Quantidade(7));
            itemPedidoA.getProduto().setPreco(new Valor(BigDecimal.valueOf(2.89)));

            var itemPedidoB = new ItemPedido();
            itemPedidoB.setQuantidade(new Quantidade(3));
            itemPedidoB.getProduto().setPreco(new Valor(BigDecimal.valueOf(13.98)));

            var pedido = new Pedido();
            pedido.getItens().addAll(Arrays.asList(itemPedidoA, itemPedidoB));
            var total = pedido.getValorTotal();

            assertThat(total)
                .isNotNull();
            assertThat(total.getValor())
                .isNotNull();
            assertThat(total.getValor().doubleValue())
                .isEqualTo(62.17);
        }

        @Test
        void deveRetornarValorTotal_quandoValor0_005() {
            var itemPedidoA = new ItemPedido();
            itemPedidoA.setQuantidade(new Quantidade(1));
            itemPedidoA.getProduto().setPreco(new Valor(BigDecimal.valueOf(10.071)));
            
            assertThat(itemPedidoA.getSubTotal())
                .isNotNull();
            assertThat(itemPedidoA.getSubTotal().getValor())
                .isNotNull();
            assertThat(itemPedidoA.getSubTotal().getValor().doubleValue())
                .isEqualTo(10.07);

            var itemPedidoB = new ItemPedido();
            itemPedidoB.setQuantidade(new Quantidade(1));
            itemPedidoB.getProduto().setPreco(new Valor(BigDecimal.valueOf(10.075)));

            assertThat(itemPedidoB.getSubTotal())
                .isNotNull();
            assertThat(itemPedidoB.getSubTotal().getValor())
                .isNotNull();
            assertThat(itemPedidoB.getSubTotal().getValor().doubleValue())
                .isEqualTo(10.07);

            var pedido = new Pedido();
            pedido.getItens().addAll(Arrays.asList(itemPedidoA, itemPedidoB));
            var total = pedido.getValorTotal();

            assertThat(total)
                .isNotNull();
            assertThat(total.getValor())
                .isNotNull();
            assertThat(total.getValor().doubleValue())
                .isEqualTo(20.14);
        }

        @Test
        void deveRetornarValorTotal_quandoValor0_006() {
            var itemPedidoA = new ItemPedido();
            itemPedidoA.setQuantidade(new Quantidade(1));
            itemPedidoA.getProduto().setPreco(new Valor(BigDecimal.valueOf(10.081)));

            assertThat(itemPedidoA.getSubTotal())
                .isNotNull();
            assertThat(itemPedidoA.getSubTotal().getValor())
                .isNotNull();
            assertThat(itemPedidoA.getSubTotal().getValor().doubleValue())
                .isEqualTo(10.08);

            var itemPedidoB = new ItemPedido();
            itemPedidoB.setQuantidade(new Quantidade(1));
            itemPedidoB.getProduto().setPreco(new Valor(BigDecimal.valueOf(10.085)));

            assertThat(itemPedidoB.getSubTotal())
                .isNotNull();
            assertThat(itemPedidoB.getSubTotal().getValor())
                .isNotNull();
            assertThat(itemPedidoB.getSubTotal().getValor().doubleValue())
                .isEqualTo(10.08);

            var pedido = new Pedido();
            pedido.getItens().addAll(Arrays.asList(itemPedidoA, itemPedidoB));
            var total = pedido.getValorTotal();

            assertThat(total)
                .isNotNull();
            assertThat(total.getValor())
                .isNotNull();
            assertThat(total.getValor().doubleValue())
                .isEqualTo(20.16);
        }
    }

}
