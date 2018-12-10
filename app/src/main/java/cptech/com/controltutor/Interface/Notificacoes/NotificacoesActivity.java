package cptech.com.controltutor.Interface.Notificacoes;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.ExecutionException;

import cptech.com.controltutor.Connect.NotificacaoRestClient;
import cptech.com.controltutor.Controle.API.SessionController;
import cptech.com.controltutor.Controle.Codigo;
import cptech.com.controltutor.Controle.Notificacao;
import cptech.com.controltutor.Interface.Adapters.CodigoAdapter;
import cptech.com.controltutor.Interface.Adapters.NotificacoesAdapter;
import cptech.com.controltutor.R;

public class NotificacoesActivity extends AppCompatActivity {
    private RecyclerView listaNotify;
    private NotificacaoRestClient nrc;
    private SessionController session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificacoes);
        listaNotify = findViewById(R.id.notif_recycler);
        session = new SessionController(getApplicationContext());
        NotificacoesAdapter adapter = null;
        try {
            adapter = new NotificacoesAdapter(getApplicationContext(), new NoticacoesTela().execute(session.findAll()).get());
            listaNotify.setAdapter(adapter);
            listaNotify.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


    public class NoticacoesTela extends AsyncTask<Long,Void,List<Notificacao>>{
        @Override
        protected List<Notificacao> doInBackground(Long... longs) {
            nrc = new NotificacaoRestClient();
            return nrc.pequisaNotificacao(longs[0]);
        }
    }
}
