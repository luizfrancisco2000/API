package cptech.com.controltutor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import cptech.com.controltutor.Controle.SessionController;

public class PerfilAlunoActivity extends AppCompatActivity {
    private ImageButton sair;
    private SessionController session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_aluno);
        sair = findViewById(R.id.sairButton);
        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session = new SessionController(getBaseContext());

            }
        });
    }
}
