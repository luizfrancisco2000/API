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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cptech.com.controltutor.Controle.Codigo;
import cptech.com.controltutor.R;

public class CodigoAdapter extends RecyclerView.Adapter<CodigoAdapter.ViewHolder>{

    private static final String TAG = "CODIGOAdapter";

    private Context context;
    private List<Codigo> codigos;

    public CodigoAdapter(Context context, List<Codigo> codigos) {
        this.context = context;
        this.codigos = codigos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.lista_codigos_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Codigo codigo = codigos.get(position);

        holder.enunciado.setText(codigo.getEnunciado());
        holder.assunto.setText(codigo.getAssunto());
        holder.avaliacao.setRating(5);
    }

    @Override
    public int getItemCount() {
        return (codigos == null) ? 0 : codigos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout parentLayout;
        private TextView enunciado;
        private TextView assunto;
        private RatingBar avaliacao;
        public ViewHolder(View itemView) {
            super(itemView);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            enunciado = itemView.findViewById(R.id.enunciado_cod);
            assunto = itemView.findViewById(R.id.assunto_cod);
            avaliacao = itemView.findViewById(R.id.avaliacao_cod);
            avaliacao.setIsIndicator(true);
        }
    }
}