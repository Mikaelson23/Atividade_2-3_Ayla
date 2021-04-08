package com.company.src.com.company;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.util.List;
import java.util.HashMap;

import com.company.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SistemaAmigoMapTest {

    SistemaAmigoMap sistema;

    @BeforeEach
    void setUp()  {
        this.sistema = new SistemaAmigoMap();
    }
    @Test
    void testSistemaAmigo() {
        assertTrue(sistema.pesquisaTodasAsMensagens().isEmpty());
        assertThrows(AmigoInexistenteException.class, ()-> sistema.pesquisaAmigo("fulano@teste.com"));
    }

    @Test
    void testPesquisaECadastraAmigo() throws AmigoInexistenteException{

        try{
            sistema.pesquisaAmigo("fulano@teste.com");
            fail("Deveria falhar, não existe ainda");
        }catch(AmigoInexistenteException e) {
            //OK
        }

        try{
            sistema.cadastraAmigo("fulano", "fulano@teste.com");
            Amigo a = sistema.pesquisaAmigo("fulano@teste.com");
            assertEquals("fulano", a.getNome());
            assertEquals("fulano@teste.com", a.getEmail());
        }catch(AmigoInexistenteException | AmigoJaExistenteException e) {
            fail("Não deveria lançar exceção");
        }
    }

    @Test
    void testEnviarMensagemParaTodos() {
        assertTrue(sistema.pesquisaTodasAsMensagens().isEmpty());
        sistema.enviarMensagemParaTodos("texto", "fulano@teste.com", true);
        HashMap<Integer, Mensagem> mensagensEncontradas = sistema.pesquisaTodasAsMensagens();
        assertTrue(mensagensEncontradas.size()==1);
        assertTrue(mensagensEncontradas.get(1).getEmailRemetente().equals("fulano@teste.com"));
    }

    @Test
    void testEnviarMensagemParaAlguem() {
        assertTrue(sistema.pesquisaTodasAsMensagens().isEmpty());
        sistema.enviarMensagemParaAlguem("texto", "fulano@teste.com", "beltranio@teste.com", true);
        HashMap<Integer, Mensagem> mensagensEncontradas = sistema.pesquisaTodasAsMensagens();
        assertEquals(1, mensagensEncontradas.size());
        assertTrue(mensagensEncontradas.size()==1);
        assertTrue(mensagensEncontradas.get(1).getTexto().equals("texto"));
    }

    @Test
    void testPesquisaMensagensAnonimas() {
        assertTrue(sistema.pesquisaTodasAsMensagens().isEmpty());
        sistema.enviarMensagemParaAlguem("texto 1", "fulano@teste.com", "beltranio@teste.com", false);
        assertTrue(sistema.pesquisaMensagensAnonimas().isEmpty());
        sistema.enviarMensagemParaAlguem("texto 2", "fulano@teste.com", "beltranio@teste.com", true);
        assertTrue(sistema.pesquisaMensagensAnonimas().size()==1);
    }

    @Test
    void testPesquisaTodasAsMensagens() {
        assertTrue(sistema.pesquisaTodasAsMensagens().isEmpty());
        sistema.enviarMensagemParaAlguem("texto 1", "fulano@teste.com", "beltranio@teste.com", false);
        assertTrue(sistema.pesquisaTodasAsMensagens().size()==1);
        sistema.enviarMensagemParaAlguem("texto 2", "fulano@teste.com", "beltranio@teste.com", true);
        assertTrue(sistema.pesquisaTodasAsMensagens().size()==2);
    }

    @Test
    void testPesquisaAmigoEConfiguraAmigoSecretoDe() {
        assertThrows(AmigoInexistenteException.class,
                ()-> sistema.pesquisaAmigoSecretoDe("fulano@teste.com"));
        try {
            sistema.cadastraAmigo("fulano", "fulano@teste.com");
            sistema.cadastraAmigo("beltranio", "beltranio@teste.com");
            sistema.configuraAmigoSecretoDe("fulano@teste.com", "beltranio@teste.com");
            sistema.configuraAmigoSecretoDe("joselia@teste.com", "fulano@teste.com");
            assertEquals("beltranio@teste.com", sistema.pesquisaAmigoSecretoDe("fulano@teste.com"));
            assertEquals("fulano@teste.com", sistema.pesquisaAmigoSecretoDe("beltranio@teste.com"));
        } catch (AmigoInexistenteException | AmigoJaExistenteException | AmigoNaoSorteadoException e) {
            fail("Não deveria lançar exceção");
        }
    }
}
