package cptech.com.controltutor.Controle.API;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Aluno on 04/10/2018.
 */

public class SessionUserController extends SQLiteOpenHelper {

    public static final String NOME_BANCO = "banco.db";
    public static final String TABELA = "logado";
    public static final String ID = "_id";
    public static final String USUARIO = "usuario";
    public static final String TIPO = "tipo";
    public static final String NOME = "nome";

    private static final int VERSAO = 1;

    public SessionUserController(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE "+TABELA+"("+ID+" bigint,"+
                USUARIO+" text,"+
                TIPO+" text,"+
                NOME+" text"+")";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
