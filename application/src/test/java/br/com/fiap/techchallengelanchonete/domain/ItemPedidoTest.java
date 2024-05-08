package br.com.fiap.techchallengelanchonete.domain;

import static org.junit.jupiter.api.Assertions.assertAll;

import java.math.BigDecimal;

import org.junit.jupiter.api.Nested;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import br.com.edu.fiap.techchallengelanchonete.domain.ItemPedido;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Quantidade;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Valor;

class ItemPedidoTest {
    
    @Test
    void deveCriarItemPedidoVazio() {
        var itemPedido = new ItemPedido();

        assertAll("Atributos vazios", 
            () -> assertThat(itemPedido).isNotNull(),
            () -> assertThat(itemPedido.getId()).isNotNull(),
            () -> assertThat(itemPedido.getQuantidade()).isNotNull(),
            () -> assertThat(itemPedido.getProduto()).isNotNull()
        );
    }

    @Nested
    class SubTotal {
        @Test
        void deveRetornarSubTotal_quandoItemVazio() {
            var itemPedido = new ItemPedido();
            var subTotal = itemPedido.getSubTotal();

            assertThat(subTotal)
                .isNotNull();
            assertThat(subTotal.getValor())
                .isNotNull()
                .isZero();
        }

        @Test
        void deveRetornarSubTotal_quandoItemSemQuantidade() {
            var itemPedido = new ItemPedido();
            itemPedido.setQuantidade(null);
            var subTotal = itemPedido.getSubTotal();

            assertThat(subTotal)
                .isNotNull();
            assertThat(subTotal.getValor())
                .isNotNull()
                .isZero();
        }

        @Test
        void deveRetornarSubTotal_quandoProdutoNulo() {
            var itemPedido = new ItemPedido();
            itemPedido.setProduto(null);
            var subTotal = itemPedido.getSubTotal();

            assertThat(subTotal)
                .isNotNull();
            assertThat(subTotal.getValor())
                .isNotNull()
                .isZero();
        }

        @Test
        void deveRetornarSubTotal_quandoValorFracionario() {
            var itemPedido = new ItemPedido();
            itemPedido.setQuantidade(new Quantidade(15));
            itemPedido.getProduto().setPreco(new Valor(BigDecimal.valueOf(8.99)));

            var subTotal = itemPedido.getSubTotal();

            assertThat(subTotal)
                .isNotNull();
            assertThat(subTotal.getValor())
                .isNotNull();
            assertThat(subTotal.getValor().doubleValue())
                .isEqualTo(134.85);
        }

        @Test
        void deveRetornarSubTotal_quandoValor0_005() {
            var itemPedido = new ItemPedido();
            itemPedido.setQuantidade(new Quantidade(1));
            itemPedido.getProduto().setPreco(new Valor(BigDecimal.valueOf(3.555)));

            var subTotal = itemPedido.getSubTotal();

            assertThat(subTotal)
                .isNotNull();
            assertThat(subTotal.getValor())
                .isNotNull();
            assertThat(subTotal.getValor().doubleValue())
                .isEqualTo(3.55);
        }

        @Test
        void deveRetornarSubTotal_quandoValor0_006() {
            var itemPedido = new ItemPedido();
            itemPedido.setQuantidade(new Quantidade(1));
            itemPedido.getProduto().setPreco(new Valor(BigDecimal.valueOf(3.556)));
            itemPedido.getSubTotal();

            var subTotal = itemPedido.getSubTotal();

            assertThat(subTotal)
                .isNotNull();
            assertThat(subTotal.getValor())
                .isNotNull();
            assertThat(subTotal.getValor().doubleValue())
                .isEqualTo(3.56);
        }
        
    } 

}
