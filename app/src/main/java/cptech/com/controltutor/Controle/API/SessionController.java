package cptech.com.controltutor.Controle.API;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import cptech.com.controltutor.Connect.UserRestClient;

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
        sql = SessionUserController.ID+" IS NOT NULL";
        db = session.getWritableDatabase();
        int situacao = db.delete(SessionUserController.TABELA, sql,null);
        if(situacao==-1){
            return "coco";
        }else{
            return "apagado";
        }
    }

    public long findAll(){
        Cursor cursor;
        String elements[] = new String[]{SessionUserController.ID, SessionUserController.NOME, SessionUserController.TIPO, SessionUserController.USUARIO};
        db = session.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT COUNT(*) FROM "+ SessionUserController.TABELA, null);
        long id=-1;
        if (cur != null) {
            cur.moveToFirst();
            if (cur.getInt (0) == 0) {
                return id;
            } else {
                cur.close();
                try {
                    cursor = db.query(SessionUserController.TABELA, elements, null, null, null, null, null, null);
                    if (cursor != null) {
                        cursor.moveToFirst();
                        id = cursor.getLong(cursor.getColumnIndex(SessionUserController.ID));
                        UserRestClient.tipo = cursor.getString(cursor.getColumnIndex(SessionUserController.TIPO)).charAt(0);
                        Log.d("Teste", String.valueOf(id));
                        return id;
                    }
                }catch (CursorIndexOutOfBoundsException e){
                    e.printStackTrace();
                }
                finally {
                    return id;
                }
            }
        }
        return id;
    }
}
