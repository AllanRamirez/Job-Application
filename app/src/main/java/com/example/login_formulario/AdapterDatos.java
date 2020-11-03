package com.example.login_formulario;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class AdapterDatos extends RecyclerView.Adapter<AdapterDatos.ViewHolderDatos> {

    private ArrayList<String> names;
    private ArrayList<String> emails;
    private ArrayList<String> positions;
    private ArrayList<String> resume_ids;

    public AdapterDatos(ArrayList<String> names, ArrayList<String> emails, ArrayList<String> positions, ArrayList<String> resume_ids) {
        this.names = names;
        this.emails = emails;
        this.positions = positions;
        this.resume_ids = resume_ids;

    }

    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolderDatos(view); // pass the view to View Holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderDatos holder, int position) {
        // set the data in items
        holder.name.setText(names.get(position));
        holder.email.setText(emails.get(position));
        holder.position.setText(positions.get(position));
        holder.resume_id.setText(resume_ids.get(position));

        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.names.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.name);
            this.email = itemView.findViewById(R.id.email);
            this.position = itemView.findViewById(R.id.position);
            this.resume_id = itemView.findViewById(R.id.resume_id);
        }

        private TextView name, email, position, resume_id;
    }

}
