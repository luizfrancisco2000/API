package cptech.com.controltutor.Controle;

import java.io.Serializable;

/**
 * Created by Aluno on 10/08/2018.
 */

public class Lista  {
    private Long id;
    private String assunto;
    private byte[] lista;
    private Professor professor;

    public Lista() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public byte[] getLista() {
        return lista;
    }

    public void setLista(byte[] lista) {
        this.lista = lista;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
