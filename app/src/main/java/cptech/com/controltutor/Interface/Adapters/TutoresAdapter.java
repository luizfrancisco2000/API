package cptech.com.controltutor.Interface.Adapters;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
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

public class TutoresAdapter extends RecyclerView.Adapter<TutoresAdapter.ViewHolder>{

    private static final String TAG = "CODIGOAdapter";

    private Context context;
    private List<Tutor> tutores;

    public TutoresAdapter(Context context, List<Tutor> tutores) {
        this.context = context;
        this.tutores = tutores;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.lista_tutor_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        final Tutor tutor = tutores.get(position);

        holder.nome.setText(tutor.getNome());
        holder.qtdeTutorandos.setText("0");
        holder.aceitar.setOnClickListener(view -> enviarNotificacao(holder.getAdapterPosition(), tutor));
    }

    private void enviarNotificacao(int adapterPosition, Tutor tutor) {
        Notificacao notificacao = new Notificacao();
        notificacao.setMensagem("Deseja se tornar seu tutorando");
        notificacao.setId_solicitante(Discente.getInstance().getId());
        notificacao.setVisto(false);
        notificacao.setUsuario(tutor);
        try {
            boolean situ = new EnviarNotificacao().execute(notificacao).get();
            if(situ){
                Toast.makeText(context, "Convite Enviado com sucesso", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context, "Convite Não Enviado", Toast.LENGTH_SHORT).show();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return (tutores == null) ? 0 : tutores.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout parentLayout;
        private TextView nome;
        private AppCompatImageButton aceitar;
        private TextView qtdeTutorandos;
        public ViewHolder(View itemView) {
            super(itemView);
            parentLayout = itemView.findViewById(R.id.layout_pesquisa_tutor);
            nome = itemView.findViewById(R.id.usuario_tutor);
            qtdeTutorandos = itemView.findViewById(R.id.quantidade_tutorandos);
            aceitar = itemView.findViewById(R.id.send_solicit);
        }
    }

    public class EnviarNotificacao extends AsyncTask<Notificacao, Void, Boolean>{
        @Override
        protected Boolean doInBackground(Notificacao... notificacaos) {
            NotificacaoRestClient notificacaoRestClient = new NotificacaoRestClient();
            return notificacaoRestClient.insertNotificacao(notificacaos[0]);
        }
    }
}