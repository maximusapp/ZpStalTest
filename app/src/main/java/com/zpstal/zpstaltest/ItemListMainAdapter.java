package com.zpstal.zpstaltest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public abstract class ItemListMainAdapter extends RecyclerView.Adapter<ItemListMainAdapter.ViewHolder> {

    private List<DataPersonal> items;
    private Context context;

    public ItemListMainAdapter(Context context, List<DataPersonal> items) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemListMainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_personnal_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemListMainAdapter.ViewHolder holder, final int position) {

        holder.tv_id.setText(items.get(position).getId_person());
        holder.tv_fio.setText(items.get(position).getFio());
        holder.tv_birth.setText(items.get(position).getBirth());
        holder.tv_gender.setText(items.get(position).getGender());
        holder.tv_idDep.setText(items.get(position).getIdDep());
        holder.tv_idPos.setText(items.get(position).getIdPos());

        holder.tv_idDep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnClickLister(items.get(position));
            }
        });

        holder.tv_idPos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnClickListerPosition(items.get(position));
            }
        });

        holder.tv_fio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnClickDeletItem(items.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView tv_id;
        TextView tv_fio;
        TextView tv_birth;
        TextView tv_gender;
        TextView tv_idDep;
        TextView tv_idPos;

        public ViewHolder(View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardView);
            tv_id = itemView.findViewById(R.id.id);
            tv_fio = itemView.findViewById(R.id.fio);
            tv_birth = itemView.findViewById(R.id.data_birth);
            tv_gender = itemView.findViewById(R.id.gender);
            tv_idDep = itemView.findViewById(R.id.id_dep_item);
            tv_idPos = itemView.findViewById(R.id.id_pos_item);

        }
    }

    public abstract void OnClickLister(DataPersonal items);
    public abstract void OnClickListerPosition(DataPersonal items);
    public abstract void OnClickDeletItem(DataPersonal items);

}
