package com.company.src.com.company;

import com.company.*;

import javax.swing.*;
import java.util.List;

public class TestaSistemaAmigo {

    public static void main(String [] args) throws AmigoJaExistenteException {

        SistemaAmigo sistema = new SistemaAmigo();

            try {
                //A,B,C,D,E
                sistema.cadastraAmigo("José",
                        "Jose@.com");
                sistema.cadastraAmigo("Maria",
                        "Maria@.com");

                sistema.configuraAmigoSecretoDe("Jose@.com",
                        "Maria@.com");
                sistema.configuraAmigoSecretoDe("Maria@.com",
                        "Jose@.com");

                sistema.enviarMensagemParaAlguem("Oi jose",
                        "Maria@.com",
                        "Jose@.com", true);
                sistema.enviarMensagemParaTodos("Oi pessoal",
                        "Maria@.com", true);

                List <Mensagem> mensagemsAnonimas = sistema.pesquisaMensagensAnonimas();
                for (Mensagem i : mensagemsAnonimas){
                    System.out.println(i.getTextoCompletoAExibir());
                }

                List <Mensagem> todasAsMensagens = sistema.pesquisaTodasAsMensagens();
                for (Mensagem i : todasAsMensagens) {
                    System.out.println(i.getTextoCompletoAExibir());
                }

                String teste = sistema.pesquisaAmigoSecretoDe("Jose@.com");
                if(teste == "Maria@.com"){
                    System.out.println(" ok");
                }

            }catch (AmigoJaExistenteException | AmigoInexistenteException | AmigoNaoSorteadoException e){
                JOptionPane.showInputDialog(null, "Erro");
                e.printStackTrace();
            }

    }
}
