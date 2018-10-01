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

public class PositionActivity extends AppCompatActivity {

    TextView text_pos;
    String id_pos;

    ItemListPositionAdapter itemListPositionAdapter;
    DatabaseHelper databaseHelperl;
    RecyclerView recycler_position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setTitle("Детали о должности");
        }

        Intent getIntent = getIntent();
        id_pos = getIntent.getStringExtra("id");
        Log.d("ID_POS", id_pos);

        databaseHelperl = new DatabaseHelper(this);

        text_pos = findViewById(R.id.text_position_main);
        recycler_position = findViewById(R.id.list_of_position_main);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recycler_position.setLayoutManager(mLayoutManager);
        itemListPositionAdapter = new ItemListPositionAdapter(this, databaseHelperl.positionData(id_pos));

        recycler_position.setAdapter(itemListPositionAdapter);
        itemListPositionAdapter.notifyDataSetChanged();

        if (itemListPositionAdapter.getItemCount() == 0) {
            text_pos.setVisibility(View.VISIBLE);
        } else {
            text_pos.setVisibility(View.GONE);
        }



    }
}
