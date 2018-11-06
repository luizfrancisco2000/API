package cptech.com.controltutor.Interface.Discente.TelaDeIntroducao;

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
import cptech.com.controltutor.Interface.Discente.TelaDeIntroducao.Fragment.IntroducaoAluno;
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

public class PerfilAlunoActivityIntroducao extends AppCompatActivity {
    private UserRestClient userRestClient;
    private AlertDialog alert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        IntroducaoAluno fragmentIntroAluno = new IntroducaoAluno();
        managerFragment(fragmentIntroAluno, "FRAGMENT_ALUNO_1");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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


    private void managerFragment(Fragment fragment, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.containerForFragment, fragment, tag);
        fragmentTransaction.commit();
    }

}
