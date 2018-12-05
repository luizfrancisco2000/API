package cptech.com.controltutor.Controle;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Aluno on 10/08/2018.
 */

public class Professor extends Usuario{
    private List<Tutor> tutores;
    private List<Discente> discentes;
    private List<Lista> listas;
    public static Professor instance;



    public Professor() {
    }

    public static Professor getInstance() {
        if(instance == null) {
            instance = new Professor();
        }
        return instance;
    }

    public static void setInstance(Professor professor){
        instance = professor;
    }

    public List<Tutor> getTutores() {
        return tutores;
    }

    public void setTutores(List<Tutor> tutores) {
        this.tutores = tutores;
    }

    public List<Discente> getDiscentes() {
        return discentes;
    }

    public void setDiscentes(List<Discente> discentes) {
        this.discentes = discentes;
    }

    public List<Lista> getListas() {
        return listas;
    }

    public void setListas(List<Lista> listas) {
        this.listas = listas;
    }
}
