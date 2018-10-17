package cptech.com.controltutor.Interface.Login;

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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import cptech.com.controltutor.Connect.DiscenteRestClient;
import cptech.com.controltutor.Connect.UserRestClient;
import cptech.com.controltutor.Controle.Discente;
import cptech.com.controltutor.Controle.API.SessionController;
import cptech.com.controltutor.Interface.Discente.CadastroDiscente;
import cptech.com.controltutor.Interface.Discente.PerfilAlunoActivity;
import cptech.com.controltutor.Interface.MainActivity;
import cptech.com.controltutor.R;

public class LoginLayoutAluno extends AppCompatActivity {
    private DiscenteRestClient discenteRestClient;
    private TextView boasVindas;
    private EditText loginEdit;
    private EditText senhaEdit;
    private Button confirmar;
    private Button cadastrar;
    private ProgressBar progresso;
    private AlertDialog alert;
    private SessionController sessionController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        loginEdit = findViewById(R.id.login);
        senhaEdit = findViewById(R.id.senha);
        sessionController = new SessionController(getBaseContext());
        boasVindas = findViewById(R.id.boas_vindas);
        confirmar = findViewById(R.id.bottonConfirmar);
        loginEdit.setTextColor(getResources().getColor(R.color.colorCharacter));
        senhaEdit.setTextColor(getResources().getColor(R.color.colorCharacter));
        progresso = findViewById(R.id.progressLogin);
        boasVindas.setText(boasVindas.getText()+"Aluno");
        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    progresso.setVisibility(View.VISIBLE);
                    String user, senha;
                    user = loginEdit.getText().toString();
                    senha = senhaEdit.getText().toString();
                    Log.d("user e senha", "user: "+user+" senha: "+senha);
                Discente aux = null;
                try {
                    aux = new HttpLogin().execute(user, senha).get();
                    if(aux==null){
                        Toast.makeText(LoginLayoutAluno.this, "Login ou senha incorreto", Toast.LENGTH_SHORT).show();
                        progresso.setVisibility(View.INVISIBLE);
                    }else{
                        Toast.makeText(LoginLayoutAluno.this, "Passou", Toast.LENGTH_SHORT).show();
                        String mensagem = sessionController.insert(aux.getNome(),aux.getId(),aux.getUsuario(),aux.getTipo());
                        if(mensagem.equals("Salvo")){
                            Intent intent = new Intent(LoginLayoutAluno.this, PerfilAlunoActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(LoginLayoutAluno.this, "Erro", Toast.LENGTH_SHORT).show();
                            progresso.setVisibility(View.INVISIBLE);
                        }
                    }
                } catch (InterruptedException e) {
                    progresso.setVisibility(View.INVISIBLE);
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    progresso.setVisibility(View.INVISIBLE);
                    e.printStackTrace();
                }
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


    @Override
    public void onBackPressed(){
        Intent intent = new Intent(LoginLayoutAluno.this, MainActivity.class);
        startActivity(intent);
    }
}
