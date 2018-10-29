package cptech.com.controltutor.Interface.Professor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import cptech.com.controltutor.Controle.API.SessionController;
import cptech.com.controltutor.Interface.Discente.PerfilAlunoActivity;
import cptech.com.controltutor.Interface.MainActivity;
import cptech.com.controltutor.R;

public class PerfilProfessor extends AppCompatActivity {
    private ImageButton sair;
    private SessionController session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_professor);
        sair = findViewById(R.id.sairButtonP);
        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session = new SessionController(getBaseContext());
                String aux = session.delete();
                if(aux.equals("apagado")){
                    Toast.makeText(PerfilProfessor.this, "Sessão Finalizada... \n Retornando ao menu principal", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(PerfilProfessor.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
