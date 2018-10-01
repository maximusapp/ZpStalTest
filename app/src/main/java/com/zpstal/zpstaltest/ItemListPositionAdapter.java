package com.zpstal.zpstaltest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ItemListPositionAdapter extends RecyclerView.Adapter<ItemListPositionAdapter.ViewHolder> {

    private List<DataPosition>items;
    private Context context;

    public ItemListPositionAdapter(Context context, List<DataPosition> items) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemListPositionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view_position = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_position_list, parent, false);
        return new ViewHolder(view_position);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemListPositionAdapter.ViewHolder holder, int position) {

        holder.text_id.setText(items.get(position).getId());
        holder.text_name.setText(items.get(position).getName());
        holder.text_salary.setText(items.get(position).getSalary());
        holder.text_note.setText(items.get(position).getNote());

    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView text_id;
        TextView text_name;
        TextView text_salary;
        TextView text_note;

        public ViewHolder(View itemView) {
            super(itemView);

            text_id = itemView.findViewById(R.id.id_position_item);
            text_name = itemView.findViewById(R.id.name_pos_item);
            text_salary = itemView.findViewById(R.id.salary_pos_item);
            text_note = itemView.findViewById(R.id.note_pos_item);

        }
    }
}
