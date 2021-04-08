package com.company;

public abstract class Mensagem {

    private String texto;
    private String emailRemetente;
    private final boolean anonima;


    public Mensagem (String texto, String emailRemetente, boolean anonima) {
        this.texto = texto;
        this.emailRemetente = emailRemetente;
        this.anonima = anonima;
    }

    public String getTexto() {
        return this.texto;

    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getEmailRemetente(){
        return this.emailRemetente = emailRemetente;

    }

    public void setEmailRemetente(String emailRemetente) {
        this.emailRemetente = emailRemetente;

    }

    public boolean EhAnonima() {
        return this.anonima;

    }

    public abstract String getTextoCompletoAExibir();

    }


