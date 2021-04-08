package com.company.src.com.company;

import com.company.Mensagem;

public class MensagemParaAlguem extends Mensagem {
    private String emailDestinatario;

    public MensagemParaAlguem(String texto, String emailRemetente, String emailDestinatario, boolean anonima) {
        super(texto, emailRemetente, anonima);
        this.emailDestinatario = emailDestinatario;
    }

    @Override
    public String getTextoCompletoAExibir() {
        if(super.EhAnonima()){
            return "Mensagem para todos. texto "+ super.getTexto();

        }else{
            return "mensagem de"+ super.getEmailRemetente()+" para todos. Texto "+ super.getTexto();
        }
    }
}
