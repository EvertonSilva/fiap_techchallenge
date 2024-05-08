package br.com.fiap.techchallengelanchonete.domain.valueobject;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Nome;

class NomeTest {
    
    @Nested
    class PrimeiroNomeTest {
        @Test
        void deveApresentarPrimeiroNome_quandoHaApenasPrimeiroNome() {
            var pseudoNomeRandomico = geraPseudoNomeRandomico();
            var nome = new Nome(pseudoNomeRandomico);

            verificaNomeVazio(nome);
            assertThat(nome.getPrimeiro())
                .isNotBlank()
                .isEqualTo(pseudoNomeRandomico);
        }

        @Test
        void deveApresentarPrimeiroNome_quandoHaNomeCompleto() {
            var pseudoPrimeiroNomeRandomico = geraPseudoNomeRandomico();
            var pseudoSobrenomeRandomico = geraPseudoNomeRandomico();
            var nome = new Nome(formaNomeCompleto(pseudoPrimeiroNomeRandomico, pseudoSobrenomeRandomico));

            verificaNomeVazio(nome);
            assertThat(nome.getPrimeiro())
                .isNotBlank()
                .isEqualTo(pseudoPrimeiroNomeRandomico);
        }

        @Test
        void deveApresentarPrimeiroNome_quandoValorNulo() {
            var nome = new Nome(null);

            assertThat(nome)
                .isNotNull();
            assertThat(nome.getPrimeiro())
                .isBlank();
        }
    }

    @Nested
    class SobrenomeTest {
        @Test
        void deveApresentarSobrenome_quandoHaApenasPrimeiroNome() {
            var pseudoNomeRandomico = geraPseudoNomeRandomico();
            var nome = new Nome(pseudoNomeRandomico);
    
            verificaNomeVazio(nome);
            assertThat(nome.getSobrenomes())
                .isEmpty();
        }
    
        @Test
        void deveApresentarSobrenome_quandoHaNomeCompletoComUmSobrenomes() {
            var pseudoPrimeiroNomeRandomico = geraPseudoNomeRandomico();
            var pseudoSobrenomeRandomico = geraPseudoNomeRandomico();
            var nome = new Nome(formaNomeCompleto(pseudoPrimeiroNomeRandomico, pseudoSobrenomeRandomico));
    
            verificaNomeVazio(nome);
            assertThat(nome.getSobrenomes())
                .isNotBlank()
                .isEqualTo(pseudoSobrenomeRandomico);
        }
    
        @Test
        void deveApresentarSobrenome_quandoHaNomeCompletoComDoisSobrenomes() {
            var pseudoPrimeiroNomeRandomico = geraPseudoNomeRandomico();
            var pseudoPrimeiroSobrenomeRandomico = geraPseudoNomeRandomico();
            var pseudoSegundoSobrenomeRandomico = geraPseudoNomeRandomico();
            var nome = new Nome(formaNomeCompleto(pseudoPrimeiroNomeRandomico, pseudoPrimeiroSobrenomeRandomico, pseudoSegundoSobrenomeRandomico));
    
            var sobrenomes = formaNomeCompleto(pseudoPrimeiroSobrenomeRandomico, pseudoSegundoSobrenomeRandomico);
    
            verificaNomeVazio(nome);
            assertThat(nome.getSobrenomes())
                .isNotBlank()
                .isEqualTo(sobrenomes);
        }

        @Test
        void deveApresentarSobrenome_quandoValorNulo() {
            var nome = new Nome(null);

            assertThat(nome)
                .isNotNull();
            assertThat(nome.getSobrenomes())
                .isBlank();
        }
    }
    
    /**
     * Verifica se o value-object Nome está nulo ou com o valor vazio, em caso positivo, quebra o teste.
     * @param nome a ser validado.
     */
    private void verificaNomeVazio(Nome nome) {
        assertThat(nome)
            .isNotNull();
        assertThat(nome.getValor())
            .isNotBlank();
    }

    /**
     * Gera uma string randômica simulando um nome.
     * @return pseudo nome randômico.
     */
    private String geraPseudoNomeRandomico() {
        return RandomStringUtils.randomAlphabetic(ThreadLocalRandom.current().nextInt(5, 10 + 1));
    }

    /**
     * Concatena os nomes recebidos com espaço entre eles.
     * @return nomes formatados como nome completo.
     */
    private String formaNomeCompleto(String... nomes) {
        return String.join(" ", nomes);
    }

}
