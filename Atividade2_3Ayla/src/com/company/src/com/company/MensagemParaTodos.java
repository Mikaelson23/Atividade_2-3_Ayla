package com.company.src.com.company;

import com.company.Mensagem;

public class MensagemParaTodos extends Mensagem {

    public MensagemParaTodos(String texto, String emailRemetente, boolean anonima) {
        super(texto, emailRemetente, anonima);
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
