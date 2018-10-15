package cptech.com.controltutor.Interface.Discente;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import cptech.com.controltutor.Connect.DiscenteRestClient;
import cptech.com.controltutor.Controle.Discente;
import cptech.com.controltutor.R;

public class CadastroDiscente extends AppCompatActivity {
    //atributos e declarações
    private Discente discenteCad;
    private DiscenteRestClient discenteRestClient;
    // componentes
    private EditText campoNome;
    private EditText campoSenha;
    private EditText campoUsuario;
    private Spinner turmas;
    private Button buttonCad;
    private Button buttonBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_discente);
        campoNome = findViewById(R.id.nomeCadEditId);
        campoSenha = findViewById(R.id.senhaCadEditId);
        campoUsuario = findViewById(R.id.usuarioCadEditId);
        turmas = findViewById(R.id.spinnerTurma);
        buttonBack = findViewById(R.id.cancelarCadId);
        buttonCad = findViewById(R.id.salvarCadId);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(CadastroDiscente.this,R.array.Turmas,R.layout.padrao_spinner);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        turmas.setAdapter(adapter);
        discenteCad = new Discente();
        discenteCad.setTipo('A');
        buttonCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseToDiscente();
                salvarDiscente();
            }
        });

        if(!campoUsuario.hasSelection()){
            procurarUsuario(campoUsuario.getText().toString());
        }
    }

    private void procurarUsuario(String s) {
        if(s.equals("luiz 123"))
            Toast.makeText(this, "Usuario existe", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Usuario não existe", Toast.LENGTH_SHORT).show();
    }

    public void parseToDiscente(){
        discenteCad.setNome(campoNome.getText().toString());
        discenteCad.setTurma(turmas.getSelectedItem().toString());
        discenteCad.setSenha(campoSenha.getText().toString());
        discenteCad.setUsuario(campoUsuario.getText().toString());
        discenteCad.setCodigos(null);
        discenteCad.setNotas(null);
        discenteCad.setProfessor(null);
        discenteCad.setTutor(null);
        discenteCad.setAtivo(false);

    }
    public void salvarDiscente() {
        try{
            if(new HttpAddDiscente().execute(discenteCad).get()){
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
    private class HttpAddDiscente extends AsyncTask<Discente, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Discente...discentes) {
            discenteRestClient = new DiscenteRestClient();
            return discenteRestClient.insertDiscente(discentes[0]);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
        }
    }
}