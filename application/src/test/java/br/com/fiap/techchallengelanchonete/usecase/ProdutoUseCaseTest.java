package br.com.fiap.techchallengelanchonete.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.edu.fiap.techchallengelanchonete.domain.Categoria;
import br.com.edu.fiap.techchallengelanchonete.domain.Produto;
import br.com.edu.fiap.techchallengelanchonete.exception.ApplicationException;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.ICategoriaPersistence;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.IProdutoPersistence;
import br.com.edu.fiap.techchallengelanchonete.usecase.ProdutoUseCase;

class ProdutoUseCaseTest {
    
    AutoCloseable mock;

    ProdutoUseCase produtoUseCase;

    @Mock
    IProdutoPersistence produtoPersistence;

    @Mock
    ICategoriaPersistence categoriaPersistence;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        produtoUseCase = new ProdutoUseCase(produtoPersistence, categoriaPersistence);
    }

    @AfterEach
    void teardown() throws Exception {
        mock.close();
    }

    @Test
    void deveBuscarProdutoPorId() {
        when(produtoPersistence.buscaId(any(Long.class)))
            .thenReturn(Optional.empty());
        
        var idProduto = new Random().nextLong();
        produtoUseCase.buscaPorId(idProduto);
        
        verify(produtoPersistence, times(1)).buscaId(any(Long.class));
    }

    @Nested
    class ListagemProdutos {
        @Test
        void deveListarProdutos_quandoCategoriaInformada() {
            when(produtoPersistence.listagem())
                .thenReturn(new ArrayList<>());
            
            produtoUseCase.listaProdutos("Lanche");
            
            verify(produtoPersistence, times(1)).buscaCategoria((any(String.class)));
        }

        @Test
        void deveListarProdutos_quandoCategoriaNaoInformada() {
            when(produtoPersistence.listagem())
                .thenReturn(new ArrayList<>());
            
            produtoUseCase.listaProdutos(null);
            
            verify(produtoPersistence, times(1)).listagem();
        }
    }

    @Nested
    class SalvaProduto {

        @Test
        void deveSalvarProduto_comCategoriaNula() {
            var produto = new Produto();
            produto.setCategoria(null);

            var applicationException = assertThrows(
                ApplicationException.class, 
                () -> produtoUseCase.salvaProduto(produto));
            
            assertThat(applicationException)
                .hasMessage("Categoria não existe!");
        }

        @Test
        void deveSalvarProduto_comCategoriaSemId() {
            var produto = new Produto();
            produto.getCategoria().setId(null);

            var applicationException = assertThrows(
                ApplicationException.class, 
                () -> produtoUseCase.salvaProduto(produto));
            
            assertThat(applicationException)
                .hasMessage("Categoria não existe!");
        }

        @Test
        void deveSalvarProduto_comCategoriaInvalida() {
            when(categoriaPersistence.buscaCategoria(any(Long.class)))
                .thenReturn(Optional.empty());
            
            var produto = new Produto();

            var applicationException = assertThrows(
                ApplicationException.class, 
                () -> produtoUseCase.salvaProduto(produto));
            
            assertThat(applicationException)
                .hasMessage("Categoria não existe!");
            verify(categoriaPersistence, times(1)).buscaCategoria(any(Long.class));
        }

        @Test
        void deveSalvarProduto_valido() {
            when(categoriaPersistence.buscaCategoria(any(Long.class)))
                .thenReturn(Optional.of(new Categoria()));
            when(produtoPersistence.cadastro(any(Produto.class)))
                .thenReturn(new Produto());

            var produto = new Produto();
            produtoUseCase.salvaProduto(produto);

            verify(categoriaPersistence, times(1)).buscaCategoria(any(Long.class));
            verify(produtoPersistence, times(1)).cadastro(any(Produto.class));        
        }
    
    }

}
