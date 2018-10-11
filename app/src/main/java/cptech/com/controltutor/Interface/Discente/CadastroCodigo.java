package cptech.com.controltutor.Interface.Discente;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import cptech.com.controltutor.R;

public class CadastroCodigo extends AppCompatActivity {
    private Spinner assuntos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_codigo);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(CadastroCodigo.this,R.array.lp_assuntos,R.layout.padrao_spinner);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        assuntos.setAdapter(adapter);
    }
}
