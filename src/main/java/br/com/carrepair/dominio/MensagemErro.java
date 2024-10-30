package br.com.carrepair.dominio;

public class MensagemErro {
    private String mensagem;

    public MensagemErro() {}

    public MensagemErro(String mensagem) {
        this();
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
