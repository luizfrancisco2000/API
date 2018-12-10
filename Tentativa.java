public void onBindViewHolder{
holder.btnDelete.setOnClickListener(view -> apagarConvite(holder.getAdapterPosition(), convite));

            holder.btnRecusar.setOnClickListener(view -> apagarConvite(holder.getAdapterPosition(), convite));

            holder.btnAceitar.setOnClickListener(view -> aceitarConvite(holder.getAdapterPosition(), convite));
}
