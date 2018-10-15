package cptech.com.controltutor.Interface.Discente;

import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import cptech.com.controltutor.Connect.CodigoRestClient;
import cptech.com.controltutor.R;

public class GaleriaCodigos extends AppCompatActivity {
    CodigoRestClient codigoRestClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria_codigos);
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
