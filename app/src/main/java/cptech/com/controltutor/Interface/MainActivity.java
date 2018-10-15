package cptech.com.controltutor.Interface;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
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
    private ImageView imagem;
    private ImageButton buttonProx, buttonAnt;
    private AlertDialog alert;
    private SessionController sessionController;
    private int contador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contador = 1;
        sessionController = new SessionController(getBaseContext());
        entrar = findViewById(R.id.acessar_user);
        cadastrar = findViewById(R.id.cadastrar_user);
        buttonAnt = findViewById(R.id.button_prevUser);
        buttonProx = findViewById(R.id.button_nextUser);
        imagem = findViewById(R.id.image_menu);
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
        buttonProx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    if (contador < 3) {
                        contador++;
                        if (contador == 2) {
                            Log.d("Professor", "if do professor");
                            imagem.setBackground(getResources().getDrawable(R.drawable.professor));
                        } else if (contador == 3) {
                            Log.d("tutor", "if do tutor");
                            imagem.setBackgroundResource(R.drawable.tutor);
                        }
                    } else if (contador == 3) {
                        contador = 1;
                        imagem.setBackgroundResource(R.drawable.aluno);
                    }
                }
            }
        });
        buttonAnt.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                if (contador > 1) {
                    contador--;
                } else if (contador == 1) {
                    contador = 3;
                }
            }
        });

        entrar.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginLayout.class);
                startActivity(intent);
            }
        });
        entrar.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CadastroDiscente.class);
                startActivity(intent);
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

}
