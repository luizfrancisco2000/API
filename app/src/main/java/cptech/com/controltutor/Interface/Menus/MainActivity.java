package cptech.com.controltutor.Interface.Menus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cptech.com.controltutor.Controle.Discente;
import cptech.com.controltutor.DAO.DiscenteDAO;
import cptech.com.controltutor.R;

public class MainActivity extends AppCompatActivity {

    private EditText loginEdit;
    private EditText senhaEdit;
    private Button confirmar;
    private DiscenteDAO dda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        dda = new DiscenteDAO();
        loginEdit = findViewById(R.id.loginDiscente);
        senhaEdit = findViewById(R.id.senhaDiscente);
        confirmar = findViewById(R.id.bottonConfirmar);
        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Discente aux = dda.loginDiscente(loginEdit.getText().toString(), senhaEdit.getText().toString());
            }
        });
    }
}
