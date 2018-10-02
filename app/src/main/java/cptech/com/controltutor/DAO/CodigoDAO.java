package cptech.com.controltutor.DAO;


import android.os.AsyncTask;

import org.springframework.http.ResponseEntity;

import cptech.com.controltutor.Controller.Codigo;
import cptech.com.controltutor.Controller.Discente;

/**
 * Created by Aluno on 13/08/2018.
 */

public class CodigoDAO extends AsyncTask<Codigo, Integer, ResponseEntity<Codigo>>{
    /**
     * Comandos para fazer a função desejada lá nos servlet
     */

    /**
     *URL da página*/
    private static String URL = "localhost:8000/api/discente";


    /**
     * Atributos para fazer o programa rodar*/

    public String adicionar(Codigo codigo){

        return null;
    }

    public String excluir() {
        return null;
    }

    public String alterar() {
        return null;
    }

    public Discente buscarByID() {
        return null;
    }
    @Override
    protected ResponseEntity<Codigo> doInBackground(Codigo... codigos) {
        return null;
    }
}
