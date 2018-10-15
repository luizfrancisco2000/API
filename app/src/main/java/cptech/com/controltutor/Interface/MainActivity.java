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
import cptech.com.controltutor.Interface.*;
import cptech.com.controltutor.R;

public class MainActivity extends AppCompatActivity {
    private DiscenteRestClient discenteRestClient;
    private UserRestClient userRestClient;
    private EditText loginEdit;
    private EditText senhaEdit;
    private Button entrar;
    private Button cadastrar;
    private AlertDialog alert;
    private SessionController sessionController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        sessionController = new SessionController(getBaseContext());
        entrar = findViewById(R.id.acessar_user);
        cadastrar = findViewById(R.id.cadastrar_user);
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
        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent = new Intent(MainActivity.class, LoginLayout.class);
              startActivity(intent);
            }
            });
              entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent = new Intent(MainActivity.class, CadastroDiscente.class);
              startActivity(intent);
            }
            });
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
