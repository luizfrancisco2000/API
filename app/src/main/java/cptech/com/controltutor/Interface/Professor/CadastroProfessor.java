package cptech.com.controltutor.Interface.Professor;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import cptech.com.controltutor.Connect.ProfessorRestClient;
import cptech.com.controltutor.Connect.UserRestClient;
import cptech.com.controltutor.Controle.Professor;
import cptech.com.controltutor.Interface.Discente.CadastroDiscente;
import cptech.com.controltutor.Interface.Login.LoginLayoutAluno;
import cptech.com.controltutor.Interface.MainActivity;
import cptech.com.controltutor.R;

public class CadastroProfessor extends AppCompatActivity {
/*
* nomePCadEditId
* usuarioPCadEditId
* senhaPCadEditId
* salvarPCadId*/

    private EditText nomeCadProf;
    private EditText usuarioCadProf;
    private EditText senhaCadProf;
    private Button salvarCadProf;
    private Button cancelarCadProf;
    private Professor professor;
    //Dados
    private ProfessorRestClient professorRestClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_professor);
        nomeCadProf = findViewById(R.id.nomePCadEditId);
        usuarioCadProf = findViewById(R.id.usuarioPCadEditId);
        senhaCadProf = findViewById(R.id.senhaPCadEditId);
        salvarCadProf = findViewById(R.id.salvarPCadId);
        cancelarCadProf = findViewById(R.id.cancelarPCadId);
        professor = Professor.getInstance();
        professor.setTipo('P');
        usuarioCadProf.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) { //perdeu o foco
                    try {
                        if(new HttpProcurarUserProfessor().execute(usuarioCadProf.getText().toString()).get()){
                            Toast.makeText(CadastroProfessor.this, "Certo", Toast.LENGTH_SHORT).show();
                            salvarCadProf.setEnabled(true);

                        }else{
                            Toast.makeText(CadastroProfessor.this, "Usuario já cadastrado... utilize outro", Toast.LENGTH_SHORT).show();
                            salvarCadProf.setEnabled(false);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        salvarCadProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseProfessor();
                salvarProfessor();
            }
        });
        cancelarCadProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroProfessor.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void salvarProfessor() {
        try{
            if(new HttpAddProfessor().execute(professor).get()){
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

    private void parseProfessor() {
        professor.setDiscentes(null);
        professor.setListas(null);
        professor.setTutores(null);
        professor.setNome(nomeCadProf.getText().toString());
        professor.setSenha(senhaCadProf.getText().toString());
        professor.setUsuario(usuarioCadProf.getText().toString());
    }


    private class HttpProcurarUserProfessor extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String...strings) {
            UserRestClient userRestClient = new UserRestClient();
            return userRestClient.listar(strings[0]);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
        }
    }

    private class HttpAddProfessor extends AsyncTask<Professor, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Professor...professores) {
            professorRestClient = new ProfessorRestClient();
            return professorRestClient.insertProfessor(professores[0]);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
        }
    }
}
