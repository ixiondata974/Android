package com.example.appvehicule.Onglet.Liste_entret.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appvehicule.Onglet.Liste_entret.obj.EssRdHerit;
import com.example.appvehicule.Onglet.ui.main.PlaceholderFragment;
import com.example.appvehicule.R;

import java.util.List;

public class AdapterHerit extends RecyclerView.Adapter<AdapterHerit.viewHolder> {

    private List<EssRdHerit> listHerit;
    private PlaceholderFragment fragment;

    public class viewHolder extends RecyclerView.ViewHolder {

        LinearLayout parent_Layout;
        TextView message;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            parent_Layout = itemView.findViewById(R.id.layout_onglet);
            message = itemView.findViewById(R.id.mess);
        }
    }

    public AdapterHerit(List<EssRdHerit> listHerit, PlaceholderFragment fragment){
        this.listHerit = listHerit;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_onglet, parent, false);
        viewHolder hold = new viewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        EssRdHerit essRdHerit = listHerit.get(position);
        holder.message.setText(essRdHerit.toString());
    }

    @Override
    public int getItemCount() {
        return listHerit.size();
    }

}
