package cptech.com.controltutor.Interface.Tutor;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.ExecutionException;

import cptech.com.controltutor.Controle.API.SessionController;
import cptech.com.controltutor.Controle.Notificacao;
import cptech.com.controltutor.Interface.MainActivity;
import cptech.com.controltutor.Interface.Professor.PerfilProfessor;
import cptech.com.controltutor.R;

public class PerfilTutor extends AppCompatActivity {
    private ImageButton sair;
    private SessionController session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_tutor);

        sair = findViewById(R.id.sairButtonT);
        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session = new SessionController(getBaseContext());
                String aux = session.delete();
                if(aux.equals("apagado")){
                    Toast.makeText(PerfilTutor.this, "Sessão Finalizada... \n Retornando ao menu principal", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(PerfilTutor.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        try {
            List<Notificacao> notificacoes = new HttpNotifacoes().execute().get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

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
        alerta.create().show();
    }
    public class HttpNotifacoes extends AsyncTask<Long, Void, List<Notificacao>>{
        @Override
        protected List<Notificacao> doInBackground(Long... longs) {
            return null;
        }
    }


}
