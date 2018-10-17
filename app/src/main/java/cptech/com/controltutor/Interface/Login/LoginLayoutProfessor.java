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
import cptech.com.controltutor.Connect.ProfessorRestClient;
import cptech.com.controltutor.Connect.UserRestClient;
import cptech.com.controltutor.Controle.API.SessionController;
import cptech.com.controltutor.Controle.Discente;
import cptech.com.controltutor.Controle.Professor;
import cptech.com.controltutor.Interface.Discente.CadastroDiscente;
import cptech.com.controltutor.Interface.Discente.PerfilAlunoActivity;
import cptech.com.controltutor.Interface.MainActivity;
import cptech.com.controltutor.Interface.Professor.PerfilProfessor;
import cptech.com.controltutor.R;

public class LoginLayoutProfessor extends AppCompatActivity {
    private ProfessorRestClient professorRestClient;
    private UserRestClient userRestClient;
    private EditText loginEdit;
    private EditText senhaEdit;
    private Button confirmar;
    private Button cadastrar;
    private TextView boasVindas;
    private AlertDialog alert;
    private ProgressBar progresso;
    private SessionController sessionController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        progresso = findViewById(R.id.progressLogin);
        sessionController = new SessionController(getBaseContext());
        loginEdit = findViewById(R.id.login);
        senhaEdit = findViewById(R.id.senha);
        loginEdit.setTextColor(getResources().getColor(R.color.colorCharacter));
        senhaEdit.setTextColor(getResources().getColor(R.color.colorCharacter));
        confirmar = findViewById(R.id.bottonConfirmar);
        boasVindas = findViewById(R.id.boas_vindas);
        boasVindas.setText(boasVindas.getText()+"Professor");

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String user, senha;
                    user = loginEdit.getText().toString();
                    senha = senhaEdit.getText().toString();
                    Log.d("user e senha", "user: "+user+" senha: "+senha);
                Professor aux = null;
                try {
                    progresso.setVisibility(View.VISIBLE);
                    aux = new HttpLogin().execute(user, senha).get();
                    if(aux==null){
                        Toast.makeText(LoginLayoutProfessor.this, "Login ou senha incorreto", Toast.LENGTH_SHORT).show();
                        progresso.setVisibility(View.INVISIBLE);
                    }else{
                        Toast.makeText(LoginLayoutProfessor.this, "Passou", Toast.LENGTH_SHORT).show();
                        String mensagem = sessionController.insert(aux.getNome(),aux.getId(),aux.getUsuario(),aux.getTipo());
                        if(mensagem.equals("Salvo")){
                            Intent intent = new Intent(LoginLayoutProfessor.this, PerfilProfessor.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(LoginLayoutProfessor.this, "Erro ao iniciar a sessão", Toast.LENGTH_SHORT).show();
                            progresso.setVisibility(View.INVISIBLE);
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    progresso.setVisibility(View.INVISIBLE);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private class HttpLogin extends AsyncTask<String, Void, Professor> {

        @Override
        protected Professor doInBackground(String...strings) {
            professorRestClient = new ProfessorRestClient();

            Log.wtf("user e senha", "user: "+strings[0]+" senha: "+strings[1]);
            return professorRestClient.loginProfessor(strings[0], strings[1]);
        }

        @Override
        protected void onPostExecute(Professor result) {
            super.onPostExecute(result);
        }
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(LoginLayoutProfessor.this, MainActivity.class);
        startActivity(intent);

    }
}
