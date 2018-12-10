package cptech.com.controltutor.Interface.Adapters;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.ExecutionException;

import cptech.com.controltutor.Connect.NotificacaoRestClient;
import cptech.com.controltutor.Controle.Discente;
import cptech.com.controltutor.Controle.Notificacao;
import cptech.com.controltutor.Controle.Tutor;
import cptech.com.controltutor.R;

public class NotificacoesAdapter extends RecyclerView.Adapter<NotificacoesAdapter.ViewHolder> {

    private static final String TAG = "CODIGOAdapter";

    private Context context;
    private List<Notificacao> notificacoes;

    public NotificacoesAdapter(Context context, List<Notificacao> notificacoes) {
        this.context = context;
        this.notificacoes = notificacoes;
    }

    @NonNull
    @Override
    public NotificacoesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.lista_tutor_layout, parent, false);
        NotificacoesAdapter.ViewHolder holder = new NotificacoesAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotificacoesAdapter.ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        final Notificacao notificacao = notificacoes.get(position);

    }


    @Override
    public int getItemCount() {
        return (notificacoes == null) ? 0 : notificacoes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout parentLayout;
        private TextView nome;
        private AppCompatImageButton aceitar, recusar;
        private TextView qtdeTutorandos;
        public ViewHolder(View itemView) {
            super(itemView);
            parentLayout = itemView.findViewById(R.id.layout_all_notifi);
            nome = itemView.findViewById(R.id.solicit_notifi);
            qtdeTutorandos = itemView.findViewById(R.id.mensagem_notifi);
            aceitar = itemView.findViewById(R.id.accept_notifi);
            aceitar = itemView.findViewById(R.id.decline_notifi);
        }
    }

    public class EnviarNotificacao extends AsyncTask<Notificacao, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Notificacao... notificacaos) {
            NotificacaoRestClient notificacaoRestClient = new NotificacaoRestClient();
            return notificacaoRestClient.insertNotificacao(notificacaos[0]);
        }
    }
}