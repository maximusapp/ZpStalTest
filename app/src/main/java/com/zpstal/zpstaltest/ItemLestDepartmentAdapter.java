package com.zpstal.zpstaltest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ItemLestDepartmentAdapter extends RecyclerView.Adapter<ItemLestDepartmentAdapter.ViewHolder> {

    private List<DataDepartment>items;
    private Context context;

    public ItemLestDepartmentAdapter(Context context, List<DataDepartment> items) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemLestDepartmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view_dep = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_department_list, parent, false);
        return new ViewHolder(view_dep);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemLestDepartmentAdapter.ViewHolder holder, int position) {

        holder.tv_id.setText(items.get(position).getId());
        holder.tv_name.setText(items.get(position).getName());
        holder.tv_note.setText(items.get(position).getNote());

    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_id;
        TextView tv_name;
        TextView tv_note;

        public ViewHolder(View itemView) {
            super(itemView);

            tv_id = itemView.findViewById(R.id.id);
            tv_name = itemView.findViewById(R.id.name);
            tv_note = itemView.findViewById(R.id.note);

        }
    }
}
