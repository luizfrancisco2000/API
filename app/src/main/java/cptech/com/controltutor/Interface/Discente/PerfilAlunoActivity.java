package cptech.com.controltutor.Interface.Discente;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cptech.com.controltutor.Controle.API.SessionController;
import cptech.com.controltutor.Interface.MainActivity;
import cptech.com.controltutor.R;

public class PerfilAlunoActivity extends AppCompatActivity {
    private ImageButton sair;
    private SessionController session;
    private AlertDialog alert;
    private ImageView imgTutor, imgLista, imgGaleria;
    private TextView txtTutor, txtLista, txtGaleria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_aluno);
        sair = findViewById(R.id.sairButton);

        //imagens para acesso
        imgGaleria = findViewById(R.id.imgGaleria);
        imgLista = findViewById(R.id.imgLista);
        imgTutor = findViewById(R.id.imgTutor);

        //textos para acesso
        txtGaleria = findViewById(R.id.txtGaleria);
        txtLista = findViewById(R.id.txtLista);
        txtTutor = findViewById(R.id.txtTutor);


        //Acesso para Materiais
        imgGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acessoGaleria();
            }
        });
        txtGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acessoGaleria();
            }
        });

        //Acesso para Materiais
        imgTutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acessoTutor();
            }
        });
        txtTutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acessoTutor();
            }
        });

        //Acesso para Materiais
        imgLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acessoLista();
            }
        });
        txtLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acessoLista();
            }
        });

        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session = new SessionController(getBaseContext());
                String aux = session.delete();
                if(aux.equals("apagado")){
                    Toast.makeText(PerfilAlunoActivity.this, "Sessão Finalizada... \n Retornando ao menu principal", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(PerfilAlunoActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
    @Override
    public void onBackPressed(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Atenção").setMessage("Deseja finalizar sessão?");
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                session = new SessionController(getBaseContext());
                String aux = session.delete();
                if(aux.equals("apagado")){
                    Toast.makeText(PerfilAlunoActivity.this, "Sessão Finalizada... \n Retornando ao menu principal", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(PerfilAlunoActivity.this, MainActivity.class);
                    startActivity(intent);
                }
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

    public void acessoTutor(){
        Intent intent = new Intent(this, MateriasTutor.class);
        startActivity(intent);
    }

    public void acessoLista(){
        Intent intent = new Intent(this, ListaProfessor.class);
        startActivity(intent);
    }
    public void acessoGaleria(){
        Intent intent = new Intent(this, GaleriaCodigos.class);
        startActivity(intent);
    }
}
