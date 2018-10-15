package cptech.com.controltutor.Interface;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import cptech.com.controltutor.Connect.DiscenteRestClient;
import cptech.com.controltutor.Connect.UserRestClient;
import cptech.com.controltutor.Controle.Discente;
import cptech.com.controltutor.Controle.API.SessionController;
import cptech.com.controltutor.Interface.Discente.CadastroDiscente;
import cptech.com.controltutor.Interface.Discente.PerfilAlunoActivity;
import cptech.com.controltutor.R;

public class MainActivity extends AppCompatActivity {
    private DiscenteRestClient discenteRestClient;
    private UserRestClient userRestClient;
    private EditText loginEdit;
    private EditText senhaEdit;
    private Button confirmar;
    private Button cadastrar;
    private AlertDialog alert;
    private SessionController sessionController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        sessionController = new SessionController(getBaseContext());


        Long id = sessionController.findAll();
        if(id!=-1){
            try {
                 new HttpVerificaConta().execute(id).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

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
                        String mensagem = sessionController.insert(aux.getNome(),aux.getId(),aux.getUsuario(),aux.getTipo());
                        if(mensagem.equals("Salvo")){
                            Intent intent = new Intent(MainActivity.this, PerfilAlunoActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(MainActivity.this, "Erro", Toast.LENGTH_SHORT).show();
                        }
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
    private class HttpVerificaConta extends AsyncTask<Long, Void, Void> {
        @Override
        protected Void doInBackground(Long... longs) {
            userRestClient = new UserRestClient();
            Log.w("id", String.valueOf(longs[0]));
            long id = longs[0];
            int resp = userRestClient.procurarDados(id);
            if(resp==1){
                Intent intent = new Intent(MainActivity.this, PerfilAlunoActivity.class);
                startActivity(intent);
            }else if(resp==2){
                Intent intent = new Intent(MainActivity.this, PerfilAlunoActivity.class);
                //startActivity(intent);
            }else if(resp==3){
                Intent intent = new Intent(MainActivity.this, PerfilAlunoActivity.class);
                // startActivity(intent);
            }else{
                Log.wtf("Deu erro","Erro");
            }

            return null;
        }
    }

    @Override
    public void onBackPressed(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Atenção").setMessage("Deseja Sair?");
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                onStop();
                System.exit(0);
            }
        });
        alerta.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        alert = alerta.create();
        alert.show();
    }
}
