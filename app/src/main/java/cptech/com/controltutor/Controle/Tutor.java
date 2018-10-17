package cptech.com.controltutor.Controle;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Aluno on 10/08/2018.
 */

public class Tutor extends Usuario implements Serializable {

    private List<Discente> discentes;
    private Professor professor;
    private List<Lista> listas;

    public static Tutor instance;
    public static Tutor getInstance() {
        if(instance == null) {
            instance = new Tutor();
        }
        return instance;
    }

    public static void setInstance(Tutor tutor){
        instance = tutor;
    }

    public List<Discente> getDiscentes() {
        return discentes;
    }

    public void setDiscentes(List<Discente> discentes) {
        this.discentes = discentes;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Lista> getListas() {
        return listas;
    }

    public void setListas(List<Lista> listas) {
        this.listas = listas;
    }
}
