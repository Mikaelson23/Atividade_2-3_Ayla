package com.company;

import com.company.src.com.company.MensagemParaAlguem;
import com.company.src.com.company.MensagemParaTodos;

import java.util.ArrayList;
import java.util.List;

public class SistemaAmigo {

    private List<Mensagem> mensagens;
    private List<Amigo> amigo;
    private int maxMensagem;

    public static final int DEFAULT_MAX_MENSAGENS = 1000;

    public SistemaAmigo() {
        this (DEFAULT_MAX_MENSAGENS);
        this.amigo = new ArrayList<>();
        this.mensagens = new ArrayList<>();
    }

    public int getMaxMensagem() {
        return maxMensagem;
    }

    public void setMaxMensagem(int maxMensagem) {
        this.maxMensagem = maxMensagem;
    }

    public SistemaAmigo(int DEFAULT_MAX_MENSAGENS) {

        this.amigo = new ArrayList<>();
        this.mensagens = new ArrayList<>();
        this.maxMensagem = DEFAULT_MAX_MENSAGENS;
    }

    public void cadastraAmigo(String emailAmigo, String nomeAmigo) throws AmigoJaExistenteException {
        Amigo novoAmigo = new Amigo(emailAmigo, nomeAmigo, null);

        if(!this.amigo.contains(nomeAmigo)){
            this.amigo.add(novoAmigo);

        }else{
            throw new AmigoJaExistenteException("Já existe esse amigo no sistema");
        }
    }

    public Amigo pesquisaAmigo(String emailAmigo) {
        return null;
    }

    public void enviarMensagemParaTodos(String texto, String emailRemetente, boolean anonima) {
        Mensagem e = new MensagemParaTodos(texto, emailRemetente, anonima);
        this.mensagens.add(e);
        System.out.println(e.getTextoCompletoAExibir());
    }

    public void enviarMensagemParaAlguem(String texto, String emailRemetente,
                                         String emailDestinatario, boolean anonima) {
        Mensagem e = new MensagemParaAlguem(texto, emailRemetente, emailRemetente, anonima);
        this.mensagens.add(e);
        System.out.println(e.getTextoCompletoAExibir());
    }

    public List<Mensagem> pesquisaMensagensAnonimas() {
        List <Mensagem> mensagensAnonimas = new ArrayList<>();

        for(Mensagem i: mensagens) {
            if (i.EhAnonima()) {
                mensagensAnonimas.add(i);
            }
        }
        return mensagensAnonimas;
    }

    public List<Mensagem> pesquisaTodasAsMensagens() {
        List <Mensagem> todasAsMensagens = new ArrayList<>();

        for(Mensagem i : mensagens) {
            todasAsMensagens.add(i);
        }
        return todasAsMensagens;
    }

    public void configuraAmigoSecretoDe(String emailDaPessoa, String emailAmigoSorteado)
            throws AmigoInexistenteException {

        for (Amigo i : amigo) {
            if (i.getEmail().equals(emailDaPessoa)) {
                i.setEmailAmigoSorteado(emailAmigoSorteado);
            }
        }
        if (emailDaPessoa.isEmpty()) {
            throw new AmigoInexistenteException("Email da pessoa não cadastrado");
        }
    }

    public String pesquisaAmigoSecretoDe(String emailDaPessoa)
            throws AmigoInexistenteException, AmigoNaoSorteadoException {

        for (Amigo i : amigo) {
            if (i.getEmail().equals(emailDaPessoa)) {
                if (i.getEmailAmigoSorteado() != null) {
                    return i.getEmailAmigoSorteado();

                } else {
                    throw new AmigoNaoSorteadoException("Não há amigo sorteado nesse email");
                }
            }
        }
        if (emailDaPessoa.isEmpty()) {
            throw new AmigoInexistenteException("Email da pessoa não cadastrado");
        }

        return "Não tem";
    }

    public List<Amigo> pesquisaTodosOsParticipantes() {
        return null;
    }
}
