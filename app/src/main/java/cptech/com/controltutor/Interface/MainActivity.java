package cptech.com.controltutor.Interface;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

import cptech.com.controltutor.Connect.UserRestClient;
import cptech.com.controltutor.Controle.API.SessionController;
import cptech.com.controltutor.Interface.Discente.CadastroDiscente;
import cptech.com.controltutor.Interface.Discente.PerfilAlunoActivity;
import cptech.com.controltutor.Interface.Login.LoginLayoutAluno;
import cptech.com.controltutor.Interface.Login.LoginLayoutProfessor;
import cptech.com.controltutor.Interface.Login.LoginLayoutTutor;
import cptech.com.controltutor.Interface.Menus.AlunoFragment;
import cptech.com.controltutor.Interface.Menus.ProfessorFragment;
import cptech.com.controltutor.Interface.Menus.TutorFragment;
import cptech.com.controltutor.Interface.Professor.CadastroProfessor;
import cptech.com.controltutor.Interface.Professor.PerfilProfessor;
import cptech.com.controltutor.Interface.Tutor.CadastroTutor;
import cptech.com.controltutor.Interface.Tutor.PerfilTutor;
import cptech.com.controltutor.R;

public class MainActivity extends AppCompatActivity {
    private UserRestClient userRestClient;
    private AlertDialog alert;
    private SessionController sessionController;
    private FrameLayout contentsTelas;
    private ImageButton proxButton, antButton;
    private Button cadUser, loginUser;
    private char tipo;
    private TextView typeMenu;

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
        typeMenu = findViewById(R.id.title_menu);
        sessionController.delete();
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
                if (tipo == 'A') {
                    ProfessorFragment fragmentProfessor = new ProfessorFragment();
                    managerFragment(fragmentProfessor, "FRAGMENT_PROFESSOR");
                    typeMenu.setText(R.string.tela_professor);
                    tipo = 'P';
                    Log.d("Professor", String.valueOf(tipo));
                } else if (tipo == 'P') {
                    TutorFragment fragmentTutor = new TutorFragment();
                    managerFragment(fragmentTutor, "FRAGMENT_TUTOR");
                    typeMenu.setText(R.string.tela_tutor);
                    tipo = 'T';
                    Log.d("Tutor", String.valueOf(tipo));
                } else if (tipo == 'T') {
                    AlunoFragment fragmentAluno = new AlunoFragment();
                    managerFragment(fragmentAluno, "FRAGMENT_ALUNO");
                    Log.d("Aluno", String.valueOf(tipo));
                    typeMenu.setText(R.string.tela_aluno);
                    tipo = 'A';
                }
            }
        });
        antButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tipo == 'A') {
                    TutorFragment fragmentTutor = new TutorFragment();
                    managerFragment(fragmentTutor, "FRAGMENT_TUTOR");
                    typeMenu.setText(R.string.tela_tutor);
                    tipo = 'T';
                } else if (tipo == 'P') {
                    AlunoFragment fragmentAluno = new AlunoFragment();
                    managerFragment(fragmentAluno, "FRAGMENT_ALUNO");
                    typeMenu.setText(R.string.tela_aluno);
                    tipo = 'A';
                } else if (tipo == 'T') {
                    ProfessorFragment fragmentProfessor = new ProfessorFragment();
                    managerFragment(fragmentProfessor, "FRAGMENT_PROFESSOR");
                    typeMenu.setText(R.string.tela_professor);
                    tipo = 'P';
                }
            }
        });

        loginUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tipo == 'A') {
                    abrirLoginAluno();
                } else if (tipo == 'T') {
                    abrirLoginTutor();
                } else if (tipo == 'P') {
                    abrirLoginProfessor();
                }
            }
        });

        cadUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tipo == 'A') {
                    abrirCadastroAluno();
                } else if (tipo == 'T') {
                    abrirCadastroTutor();
                } else if (tipo == 'P') {
                    abrirCadastroProfessor();
                }
            }
        });

    }

    public void abrirLoginAluno() {
        Intent intent = new Intent(MainActivity.this, LoginLayoutAluno.class);
        startActivity(intent);
    }


    public void abrirCadastroAluno() {
        Intent intent = new Intent(MainActivity.this, CadastroDiscente.class);
        startActivity(intent);
    }

    public void abrirLoginProfessor() {
        Intent intent = new Intent(MainActivity.this, LoginLayoutProfessor.class);
        startActivity(intent);
    }


    public void abrirCadastroProfessor() {
        Intent intent = new Intent(MainActivity.this, CadastroProfessor.class);
        startActivity(intent);
    }

    public void abrirLoginTutor() {
        Intent intent = new Intent(MainActivity.this, LoginLayoutTutor.class);
        startActivity(intent);
    }


    public void abrirCadastroTutor() {
        Intent intent = new Intent(MainActivity.this, CadastroTutor.class);
        startActivity(intent);
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
                Intent intent = new Intent(MainActivity.this, PerfilTutor.class);
                startActivity(intent);
            } else if (resp == 3) {
                Intent intent = new Intent(MainActivity.this, PerfilProfessor.class);
                startActivity(intent);
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
