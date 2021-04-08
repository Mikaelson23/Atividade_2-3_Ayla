package com.company;

public class Amigo {
    private String nome;
    private String email;
    private String emailAmigoSorteado;


    public Amigo (String nome, String email, String emailAmigoSorteado) {
        this.email = email;
        this.nome = nome;
        this.emailAmigoSorteado = emailAmigoSorteado;
    }

    public String getNome() {
        return this.nome = nome;

    }

    public String getEmail() {
        return this.email;

    }

    public String getEmailAmigoSorteado() {
        return this.emailAmigoSorteado;

    }

    public void setNome(String nome) {
        this.nome = nome;

    }

    public void setEmail(String email) {
        this.email = email;

    }

    public void setEmailAmigoSorteado(String emailAmigoSorteado) {
        this.emailAmigoSorteado = emailAmigoSorteado;

    }
}

