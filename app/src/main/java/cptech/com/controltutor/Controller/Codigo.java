package cptech.com.controltutor.Controller;

import java.io.Serializable;

/**
 * Created by Aluno on 10/08/2018.
 */

public class Codigo implements Serializable{
    private Long id;
    private String enunciado;
    private byte[] resolucao;
    private int avaliacao;
    private Discente discente;

    public Codigo() {
    }

    public Discente getDiscente() {
        return discente;
    }

    public void setDiscente(Discente discente) {
        this.discente = discente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public byte[] getResolucao() {
        return resolucao;
    }

    public void setResolucao(byte[] resolucao) {
        this.resolucao = resolucao;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }
}
