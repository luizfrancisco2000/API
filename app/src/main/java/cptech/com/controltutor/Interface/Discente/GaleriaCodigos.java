package cptech.com.controltutor.Interface.Discente;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.List;

import cptech.com.controltutor.Connect.CodigoRestClient;
import cptech.com.controltutor.R;

public class GaleriaCodigos extends AppCompatActivity {
    CodigoRestClient codigoRestClient;
    FloatingActionButton cad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria_codigos);
        cad = findViewById(R.id.floatingActionButton11);
        cad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GaleriaCodigos.this, CadastroCodigo.class);
                startActivity(intent);
            }
        });
    }

    public class HttpProcuraCodigos extends AsyncTask<Void,Void,List>{

        @Override
        protected List doInBackground(Void... voids) {
            codigoRestClient = new CodigoRestClient();
            codigoRestClient.listAllCodigosByDiscente();
            return null;
        }
    }

}
