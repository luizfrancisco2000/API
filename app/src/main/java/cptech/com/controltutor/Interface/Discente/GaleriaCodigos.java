package cptech.com.controltutor.Interface.Discente;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.ExecutionException;

import cptech.com.controltutor.Connect.CodigoRestClient;
import cptech.com.controltutor.Connect.DiscenteRestClient;
import cptech.com.controltutor.Connect.UserRestClient;
import cptech.com.controltutor.Controle.API.SessionController;
import cptech.com.controltutor.Controle.Codigo;
import cptech.com.controltutor.Controle.Discente;
import cptech.com.controltutor.Interface.Adapters.CodigoAdapter;
import cptech.com.controltutor.R;

public class GaleriaCodigos extends AppCompatActivity{
    private SessionController session;
    private CodigoRestClient codigoRestClient;
    private FloatingActionButton cad;
    private SessionController sessionController;
    private List<Codigo> codigos;
    private UserRestClient userRestClient;
    private RecyclerView codigosRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria_codigos);
        sessionController = new SessionController(getApplicationContext());
        codigosRecycler = findViewById(R.id.codigos_discente);
        cad = findViewById(R.id.floatingActionButton11);
        final Long id = sessionController.findAll();
        if (id != -1) {
            try {
                new GaleriaCodigos.HttpVerificaConta().execute(id).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        cad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GaleriaCodigos.this, CadastroCodigo.class);
                startActivity(intent);
            }
        });
        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout1);
        //ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        //      this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //drawer.addDrawerListener(toggle);
        //toggle.syncState();
    }



    private class HttpVerificaConta extends AsyncTask<Long, Void, Void> {
        @Override
        protected Void doInBackground(Long... longs) {

            DiscenteRestClient drc = new DiscenteRestClient();
            Log.w("id", String.valueOf(longs[0]));
            long id = longs[0];
            Discente resp = drc.procurarId(id);
            codigoRestClient = new CodigoRestClient();
            codigos = codigoRestClient.listAllCodigosByDiscente(longs[0]);
            if (codigos != null){
                Log.d("CODIGO", String.valueOf(codigos.size()));
                CodigoAdapter adapter = new CodigoAdapter(getApplicationContext(), codigos);
                codigosRecycler.setAdapter(adapter);
                codigosRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }
            return null;
        }
    }
}
