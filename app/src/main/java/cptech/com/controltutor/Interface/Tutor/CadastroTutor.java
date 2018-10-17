package cptech.com.controltutor.Interface.Tutor;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import cptech.com.controltutor.Connect.TutorRestClient;
import cptech.com.controltutor.Connect.UserRestClient;
import cptech.com.controltutor.Controle.Tutor;
import cptech.com.controltutor.Interface.MainActivity;
import cptech.com.controltutor.Interface.Professor.CadastroProfessor;
import cptech.com.controltutor.R;

public class CadastroTutor extends AppCompatActivity {
    /*usuarioTCadEditId
     * nomeTCadEditId
     * senhaTCadEditId
     * cancelarTCadId
     * salvarTCadId*/

    private EditText nomeCadTutor;
    private EditText usuarioCadTutor;
    private EditText senhaCadTutor;
    private Button salvarCadTutor;
    private Button cancelarCadTutor;
    private Tutor tutor;

    //Dados
    private TutorRestClient tutorRestClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_tutor);
        nomeCadTutor = findViewById(R.id.nomeTCadEditId);
        usuarioCadTutor = findViewById(R.id.usuarioTCadEditId);
        senhaCadTutor = findViewById(R.id.senhaTCadEditId);
        salvarCadTutor = findViewById(R.id.salvarTCadId);
        cancelarCadTutor = findViewById(R.id.cancelarTCadId);
        tutor = Tutor.getInstance();
        tutor.setTipo('T');
        usuarioCadTutor.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) { //perdeu o foco
                    try {
                        if (new HttpProcurarUserTutor().execute(usuarioCadTutor.getText().toString()).get()) {
                            Toast.makeText(CadastroTutor.this, "Certo", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(CadastroTutor.this, "Usuario já cadastrado... utilize outro", Toast.LENGTH_SHORT).show();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        salvarCadTutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseTutor();
                salvarTutor();
            }
        });

        cancelarCadTutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroTutor.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


    private void salvarTutor() {
        try{
            if(new HttpAddTutor().execute(tutor).get()){
                Toast.makeText(this, "Funcionou", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Deu coco", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Deu coco", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this, "Salvo!", Toast.LENGTH_SHORT).show();
    }

    private void parseTutor() {
        tutor.setDiscentes(null);
        tutor.setListas(null);
        tutor.setNome(nomeCadTutor.getText().toString());
        tutor.setSenha(senhaCadTutor.getText().toString());
        tutor.setUsuario(usuarioCadTutor.getText().toString());
    }
    private class HttpProcurarUserTutor extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... strings) {
            UserRestClient userRestClient = new UserRestClient();
            return userRestClient.listar(strings[0]);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
        }
    }
    private class HttpAddTutor extends AsyncTask<Tutor, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Tutor...tutores) {
            tutorRestClient = new TutorRestClient();
            return tutorRestClient.insertTutor(tutores[0]);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
        }
    }
}
