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
import cptech.com.controltutor.Connect.TutorRestClient;
import cptech.com.controltutor.Connect.UserRestClient;
import cptech.com.controltutor.Controle.API.SessionController;
import cptech.com.controltutor.Controle.Discente;
import cptech.com.controltutor.Controle.Tutor;
import cptech.com.controltutor.Interface.Discente.CadastroDiscente;
import cptech.com.controltutor.Interface.Discente.PerfilAlunoActivity;
import cptech.com.controltutor.Interface.MainActivity;
import cptech.com.controltutor.Interface.Professor.PerfilProfessor;
import cptech.com.controltutor.Interface.Tutor.PerfilTutor;
import cptech.com.controltutor.R;

public class LoginLayoutTutor extends AppCompatActivity {
    private TutorRestClient tutorRestClient;
    private EditText loginEdit;
    private EditText senhaEdit;
    private Button confirmar;
    private Button cadastrar;
    private ProgressBar progresso;
    private AlertDialog alert;
    private SessionController sessionController;
    private TextView boasVindas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        sessionController = new SessionController(getBaseContext());
        loginEdit = findViewById(R.id.login);
        senhaEdit = findViewById(R.id.senha);
        loginEdit.setTextColor(getResources().getColor(R.color.colorCharacter));
        senhaEdit.setTextColor(getResources().getColor(R.color.colorCharacter));
        confirmar = findViewById(R.id.bottonConfirmar);
        progresso = findViewById(R.id.progressLogin);
        boasVindas = findViewById(R.id.boas_vindas);
        boasVindas.setText(boasVindas.getText() + "Tutor");
        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progresso.setVisibility(View.VISIBLE);
                String user, senha;
                user = loginEdit.getText().toString();
                senha = senhaEdit.getText().toString();
                Log.d("user e senha", "user: " + user + " senha: " + senha);
                Tutor aux = null;
                try {
                    aux = new HttpLogin().execute(user, senha).get();
                    if (aux == null) {
                        Toast.makeText(LoginLayoutTutor.this, "Login ou senha incorreto", Toast.LENGTH_SHORT).show();
                        progresso.setVisibility(View.INVISIBLE);
                    } else {
                        Toast.makeText(LoginLayoutTutor.this, "Passou", Toast.LENGTH_SHORT).show();
                        String mensagem = sessionController.insert(aux.getNome(), aux.getId(), aux.getUsuario(), aux.getTipo());
                        if (mensagem.equals("Salvo")) {
                            Intent intent = new Intent(LoginLayoutTutor.this, PerfilTutor.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginLayoutTutor.this, "Erro", Toast.LENGTH_SHORT).show();
                            progresso.setVisibility(View.INVISIBLE);
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    progresso.setVisibility(View.INVISIBLE);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                    progresso.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    private class HttpLogin extends AsyncTask<String, Void, Tutor> {

        @Override
        protected Tutor doInBackground(String... strings) {
            tutorRestClient = new TutorRestClient();

            Log.wtf("user e senha", "user: " + strings[0] + " senha: " + strings[1]);
            return tutorRestClient.loginTutor(strings[0], strings[1]);
        }

        @Override
        protected void onPostExecute(Tutor result) {
            super.onPostExecute(result);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(LoginLayoutTutor.this, MainActivity.class);
        startActivity(intent);

    }
}
