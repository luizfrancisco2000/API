package cptech.com.controltutor.Interface.Menus;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import cptech.com.controltutor.Connect.DiscenteRestClient;
import cptech.com.controltutor.Controle.Discente;
import cptech.com.controltutor.DAO.DiscenteDAO;
import cptech.com.controltutor.PerfilAlunoActivity;
import cptech.com.controltutor.R;

public class MainActivity extends AppCompatActivity {
    private DiscenteRestClient discenteRestClient;

    private EditText loginEdit;
    private EditText senhaEdit;
    private Button confirmar;
    private Button cadastrar;
    private DiscenteDAO dda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        dda = new DiscenteDAO();
        loginEdit = findViewById(R.id.loginDiscente);
        senhaEdit = findViewById(R.id.senhaDiscente);
        confirmar = findViewById(R.id.bottonConfirmar);
        cadastrar = findViewById(R.id.bottonCad);
        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String user, senha;
                    user = loginEdit.getText().toString();
                    senha = senhaEdit.getText().toString();
                    Log.d("user e senha", "user: "+user+" senha: "+senha);
                Discente aux = null;
                try {
                    aux = new HttpLogin().execute(user, senha).get();
                    if(aux==null){
                        Toast.makeText(MainActivity.this, "Deu erro", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this, "Passou", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, PerfilAlunoActivity.class);
                        startActivity(intent);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroDiscente.class);
                startActivity(intent);
            }
        });
    }
    private class HttpLogin extends AsyncTask<String, Void, Discente> {

        @Override
        protected Discente doInBackground(String...strings) {
            discenteRestClient = new DiscenteRestClient();

            Log.wtf("user e senha", "user: "+strings[0]+" senha: "+strings[1]);
            return discenteRestClient.loginDiscente(strings[0], strings[1]);
        }

        @Override
        protected void onPostExecute(Discente result) {
            super.onPostExecute(result);
        }
    }
}
