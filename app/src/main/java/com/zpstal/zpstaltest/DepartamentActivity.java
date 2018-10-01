package com.zpstal.zpstaltest;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Objects;

public class DepartamentActivity extends AppCompatActivity {

    TextView text_dep;

    String id_dep;

    ItemLestDepartmentAdapter itemLestDepartmentAdapter;
    RecyclerView recycler_department;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departament);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setTitle(" Детали отделения");
        }

        text_dep = findViewById(R.id.text_department);

        Intent getIntent = getIntent();
        id_dep = getIntent.getStringExtra("id");

        recycler_department = findViewById(R.id.list_of_department_main);
        databaseHelper = new DatabaseHelper(this);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recycler_department.setLayoutManager(mLayoutManager);
        itemLestDepartmentAdapter = new ItemLestDepartmentAdapter(this, databaseHelper.departmentData(id_dep));

        recycler_department.setAdapter(itemLestDepartmentAdapter);
        itemLestDepartmentAdapter.notifyDataSetChanged();

        if (itemLestDepartmentAdapter.getItemCount() == 0) {
            text_dep.setVisibility(View.VISIBLE);
        } else {
            text_dep.setVisibility(View.GONE);
        }


    }
}
