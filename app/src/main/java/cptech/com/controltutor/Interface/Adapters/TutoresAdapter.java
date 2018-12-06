package cptech.com.controltutor.Interface.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

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

        Tutor tutor = tutores.get(position);

        holder.nome.setText(tutor.getNome());
        holder.qtdeTutorandos.setText(tutor.getDiscentes().size());
    }

    @Override
    public int getItemCount() {
        return (tutores == null) ? 0 : tutores.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout parentLayout;
        private TextView nome;
        private TextView qtdeTutorandos;
        public ViewHolder(View itemView) {
            super(itemView);
            parentLayout = itemView.findViewById(R.id.procurarTutor);
            nome = itemView.findViewById(R.id.usuario_tutor);
            qtdeTutorandos = itemView.findViewById(R.id.quantidade_tutorandos);
        }
    }
}