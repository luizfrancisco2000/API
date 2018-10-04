package cptech.com.controltutor.Controle;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Aluno on 04/10/2018.
 */

public class SessionController {
    private SessionUserController session;
    private SQLiteDatabase db;
    private String sql;
    public SessionController(Context context){
        session = new SessionUserController(context);
    }

    public String insert(String nome,long id,String usuario, char tipo){
        ContentValues values;
        long resultado;
        db = session.getWritableDatabase();
        values = new ContentValues();
        values.put(SessionUserController.ID, id);
        values.put(SessionUserController.NOME, nome);
        values.put(SessionUserController.TIPO, String.valueOf(tipo));
        values.put(SessionUserController.USUARIO, usuario);
        resultado = db.insert(SessionUserController.TABELA,null, values);
        db.close();
        if(resultado==-1){
            Log.d("Tag", "Não cadastrado");
            return "Coco";
        }else{
            Log.e("Salvo","Salvo");
        }
        return "Salvo";
    }

    public String delete(){
        String sql = SessionUserController.ID+"IS NOT NULL";
        int situacao = db.delete(SessionUserController.TABELA,sql,null);
        if(situacao==-1){
            return "coco";
        }else{
            return "apagado";
        }
    }
}
