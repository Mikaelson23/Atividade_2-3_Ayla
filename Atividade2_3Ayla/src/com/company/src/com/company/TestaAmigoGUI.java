package com.company.src.com.company;

import com.company.Amigo;
import com.company.AmigoInexistenteException;
import com.company.AmigoJaExistenteException;
import com.company.SistemaAmigo;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TestaAmigoGUI {

    public static void main (String [] args){
        //4 A ,B, C, D, E
        int maximoDeMensagens = Integer.parseInt(JOptionPane.showInputDialog("Qual a quantidade de mensagens? "));
        SistemaAmigo sistema = new SistemaAmigo(maximoDeMensagens);
        int numeroDeAmigos = Integer.parseInt(JOptionPane.showInputDialog("Qual a quantidade de amigos? "));
        for(int k = 0;k < numeroDeAmigos; k++){
            String nomeAmigo = JOptionPane.showInputDialog("Nome da pessoa: ");
            String emailAmigo = JOptionPane.showInputDialog("Email da pessoa: ");
            try{
                sistema.cadastraAmigo(nomeAmigo,emailAmigo);
            } catch (AmigoJaExistenteException e) {
                JOptionPane.showMessageDialog(null, "Não foi possivel cadastrar");
                e.printStackTrace();
            }
        }
        List <Amigo> todosOsParticipantes = sistema.pesquisaTodosOsParticipantes();
        for(Amigo i: todosOsParticipantes){
           String emailSorteado = JOptionPane.showInputDialog("Quem é o amigo de"+ i.getEmail()+ " ? ");
            try {
                sistema.configuraAmigoSecretoDe(i.getEmail(), emailSorteado);
            } catch (AmigoInexistenteException e) {
                JOptionPane.showMessageDialog(null,"Problema no sorteio");
                e.printStackTrace();
            }

        }
        String texto = JOptionPane.showInputDialog("Digite o texto da mensagem ");
        String emailRemetente = JOptionPane.showInputDialog("Digite o seu email cadastrado ");
        String mensagemEhAnonima = JOptionPane.showInputDialog("A mensagem é anonima? /n (S) sim ou (N) Não ");
        boolean ehAnonima = false;
        if (mensagemEhAnonima.equals("S")){
            ehAnonima = true;
        }
        sistema.enviarMensagemParaTodos(texto, emailRemetente, ehAnonima);


    }
}
