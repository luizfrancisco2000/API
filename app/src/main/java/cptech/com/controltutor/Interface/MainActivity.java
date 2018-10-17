package cptech.com.controltutor.Interface;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.concurrent.ExecutionException;

import cptech.com.controltutor.Connect.DiscenteRestClient;
import cptech.com.controltutor.Connect.UserRestClient;
import cptech.com.controltutor.Controle.API.SessionController;
import cptech.com.controltutor.Controle.Professor;
import cptech.com.controltutor.Interface.Discente.CadastroDiscente;
import cptech.com.controltutor.Interface.Discente.PerfilAlunoActivity;
import cptech.com.controltutor.Interface.Login.LoginLayout;
import cptech.com.controltutor.Interface.Menus.AlunoFragment;
import cptech.com.controltutor.Interface.Menus.ProfessorFragment;
import cptech.com.controltutor.Interface.Menus.TutorFragment;
import cptech.com.controltutor.R;

public class MainActivity extends AppCompatActivity {
    private UserRestClient userRestClient;
    private AlertDialog alert;
    private SessionController sessionController;
    private FrameLayout contentsTelas;
    private ImageButton proxButton, antButton;
    private Button cadUser, loginUser;
    private char tipo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contentsTelas = findViewById(R.id.containerForFragment);
        sessionController = new SessionController(getBaseContext());
        proxButton = findViewById(R.id.button_nextUser);
        antButton = findViewById(R.id.button_prevUser);
        cadUser = findViewById(R.id.cadastrar_user);
        loginUser = findViewById(R.id.acessar_user);
        final Long id = sessionController.findAll();
        if (id != -1) {
            try {
                new HttpVerificaConta().execute(id).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        AlunoFragment fragmentAluno = new AlunoFragment();
        managerFragment(fragmentAluno, "FRAGMENT_ALUNO");
        tipo = 'A';
        proxButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tipo=='A'){
                    ProfessorFragment fragmentProfessor = new ProfessorFragment();
                    managerFragment(fragmentProfessor, "FRAGMENT_PROFESSOR");
                    tipo='P';
                }
                if(tipo=='P'){
                    TutorFragment fragmentTutor = new TutorFragment();
                    managerFragment(fragmentTutor, "FRAGMENT_TUTOR");
                    tipo='T';
                }
                if(tipo=='T'){
                    AlunoFragment fragmentAluno = new AlunoFragment();
                    managerFragment(fragmentAluno, "FRAGMENT_ALUNO");
                    tipo='A';
                }
            }
        });
        antButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tipo=='A'){
                    TutorFragment fragmentTutor = new TutorFragment();
                    managerFragment(fragmentTutor, "FRAGMENT_TUTOR");
                    tipo='T';
                }
                if(tipo=='P'){
                    AlunoFragment fragmentAluno = new AlunoFragment();
                    managerFragment(fragmentAluno, "FRAGMENT_ALUNO");
                    tipo='A';
                }
                if(tipo=='T'){
                    ProfessorFragment fragmentProfessor = new ProfessorFragment();
                    managerFragment(fragmentProfessor, "FRAGMENT_PROFESSOR");
                    tipo='P';
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
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

    private class HttpVerificaConta extends AsyncTask<Long, Void, Void> {
        @Override
        protected Void doInBackground(Long... longs) {
            userRestClient = new UserRestClient();
            Log.w("id", String.valueOf(longs[0]));
            long id = longs[0];
            int resp = userRestClient.procurarDados(id);
            if (resp == 1) {
                Intent intent = new Intent(MainActivity.this, PerfilAlunoActivity.class);
                startActivity(intent);
            } else if (resp == 2) {
                Intent intent = new Intent(MainActivity.this, PerfilAlunoActivity.class);
                //startActivity(intent);
            } else if (resp == 3) {
                Intent intent = new Intent(MainActivity.this, PerfilAlunoActivity.class);
                // startActivity(intent);
            } else {
                Log.wtf("Deu erro", "Erro");
            }

            return null;
        }
    }

    private void managerFragment(Fragment fragment, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.containerForFragment, fragment, tag);
        fragmentTransaction.commit();
    }

}
