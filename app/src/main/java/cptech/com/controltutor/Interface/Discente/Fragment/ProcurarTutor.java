package cptech.com.controltutor.Interface.Discente.Fragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;

import java.util.List;

import cptech.com.controltutor.Connect.TutorRestClient;
import cptech.com.controltutor.Controle.Tutor;
import cptech.com.controltutor.Interface.Adapters.TutoresAdapter;
import cptech.com.controltutor.R;

public class ProcurarTutor extends AppCompatActivity {
    private SearchView pesquisarTutor;
    private RecyclerView listaTutores;
    private TutorRestClient tutorRestClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_procurar_tutor);
        listaTutores = findViewById(R.id.tutores_procurar);
        pesquisarTutor = findViewById(R.id.procurarTutor);
        pesquisarTutor.setSubmitButtonEnabled(true);
        tutorRestClient = new TutorRestClient();
        pesquisarTutor.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                List<Tutor> tutores = tutorRestClient.listar(query);
                if(tutores==null){
                    return false;
                }else{
                    TutoresAdapter adapter = new TutoresAdapter(getApplicationContext(), tutores);
                    listaTutores.setAdapter(adapter);
                    listaTutores.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    return true;
                }

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("TAG", newText);
                List<Tutor> tutores = tutorRestClient.listar(newText);
                if(tutores==null){
                    return false;
                }else{
                    TutoresAdapter adapter = new TutoresAdapter(getApplicationContext(), tutores);
                    listaTutores.setAdapter(adapter);
                    listaTutores.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    return true;
                }
            }
        });
    }

}
