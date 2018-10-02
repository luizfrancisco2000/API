package cptech.com.controltutor.Controller;

import java.io.Serializable;

/**
 * Created by Aluno on 10/08/2018.
 */

public class Nota implements Serializable {
    private Long id;
    private int bimestre;
    private int prova;
    private float nota;
    private Discente discente;

    public Nota() {
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

    public int getBimestre() {
        return bimestre;
    }

    public void setBimestre(int bimestre) {
        this.bimestre = bimestre;
    }

    public int getProva() {
        return prova;
    }

    public void setProva(int prova) {
        this.prova = prova;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }
}
