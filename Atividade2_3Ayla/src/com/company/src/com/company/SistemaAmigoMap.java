package com.company.src.com.company;

import com.company.*;

import java.util.HashMap;
import java.util.List;

public class SistemaAmigoMap {
    private HashMap<Integer, Mensagem> mensagens;
    private HashMap<String, Amigo> amigo;

    public SistemaAmigoMap() {
        this.amigo = new HashMap<String, Amigo>();
        this.mensagens = new HashMap<Integer, Mensagem>();
    }

    public void cadastraAmigo(String emailAmigo, String nomeAmigo) throws AmigoJaExistenteException {
        Amigo novoAmigo = new Amigo(emailAmigo, nomeAmigo, null);
        if(!amigo.containsValue(novoAmigo)){
            amigo.put(novoAmigo.getEmail(), novoAmigo);
        }else{
            throw new AmigoJaExistenteException("Já existe esse amigo no sistema");
        }
    }

    public Amigo pesquisaAmigo(String emailAmigo) throws AmigoInexistenteException {
        if(!amigo.containsKey(emailAmigo)){
            throw new AmigoInexistenteException("Esse amigo não esta cadastrado");
        }
        return amigo.get(emailAmigo);
    }

    public void enviarMensagemParaTodos(String texto, String emailRemetente, boolean anonima) {
        MensagemParaTodos e = new MensagemParaTodos(texto, emailRemetente, anonima);
        int key = mensagens.size();
        mensagens.put(key+1,e);
        System.out.println(e.getTextoCompletoAExibir());
    }

    public void enviarMensagemParaAlguem(String texto, String emailRemetente,
                                         String emailDestinatario, boolean anonima) {
        MensagemParaAlguem e = new MensagemParaAlguem(texto, emailRemetente, emailDestinatario, anonima);
        int key = mensagens.size();
        mensagens.put(key+1,e);
        System.out.println(e.getTextoCompletoAExibir());
    }

    public HashMap<Integer, Mensagem> pesquisaMensagensAnonimas() {
        HashMap<Integer, Mensagem> mensagensAnonimas = new HashMap<>();
        for(int k = 1; k <= mensagens.size(); k++) {
            if (mensagens.get(k).EhAnonima()) {
                int key = mensagens.size();
                mensagensAnonimas.put(key+1,mensagens.get(k));
            }
        }
        return mensagensAnonimas;
    }

    public HashMap<Integer, Mensagem> pesquisaTodasAsMensagens() {
        HashMap<Integer, Mensagem> todasAsMensagens = new HashMap<>();

        for(int k = 1; k <= mensagens.size(); k++) {
            int key = mensagens.size();
            todasAsMensagens.put(key,mensagens.get(k));
        }
        return todasAsMensagens;
    }

    public void configuraAmigoSecretoDe(String emailDaPessoa, String emailAmigoSorteado)
            throws AmigoInexistenteException {

        for (int k = 1; k <= mensagens.size(); k++) {
            if (this.amigo.containsValue(emailDaPessoa)) {
                amigo.get(emailDaPessoa).setEmailAmigoSorteado(emailAmigoSorteado);

            } else {
                throw new AmigoInexistenteException("Email da pessoa não cadastrado");
            }
        }
    }
    public String pesquisaAmigoSecretoDe(String emailDaPessoa)
            throws AmigoInexistenteException, AmigoNaoSorteadoException {
        if(!amigo.containsKey(emailDaPessoa)) {
            throw new AmigoInexistenteException("Não existe esse amigo no sistema");
        }else{
            if(amigo.get(emailDaPessoa).getEmailAmigoSorteado() != null) {
                return amigo.get(emailDaPessoa).getEmailAmigoSorteado();
            }else {
                throw new AmigoNaoSorteadoException("Amigo não foi configurado o amigo sorteado");
            }
        }
    }

    public List<Amigo> pesquisaTodosOsParticipantes() {
        return null;
    }
}
