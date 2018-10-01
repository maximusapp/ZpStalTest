package com.zpstal.zpstaltest;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class CreatePositionActivity extends AppCompatActivity {

    // Handle press back button.
    public boolean onSupportNavigateUp(){
        onBackPressed();
        finish();
        return true;
    }

    EditText edit_id_position;
    EditText edit_name;
    EditText edit_salery;
    EditText edit_note;

    int position;
    String name;
    int salery;
    String note;

    Button button_create_position;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_position);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setTitle("Создать должность");
        }

        databaseHelper = new DatabaseHelper(this);

        edit_id_position = findViewById(R.id.enter_idPosition);
        edit_name = findViewById(R.id.enter_name_position);
        edit_salery = findViewById(R.id.enter_salery);
        edit_note = findViewById(R.id.enter_note);

        button_create_position = findViewById(R.id.create_position);
        button_create_position.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                position = Integer.parseInt(edit_id_position.getText().toString().trim());
                name = edit_name.getText().toString().trim();
                salery = Integer.parseInt(edit_salery.getText().toString().trim());
                note = edit_note.getText().toString().trim();

                boolean id_pos = databaseHelper.existOrNotIdPosition(String.valueOf(position));

                if (edit_id_position.getText().toString().isEmpty()) {
                    edit_id_position.setError("Введите Id должности");
                } else if (edit_name.getText().toString().isEmpty()) {
                    edit_name.setError("Введите название должности");
                } else if (edit_salery.getText().toString().isEmpty()) {
                    edit_salery.setError("Введите оклад");
                } else if (edit_note.getText().toString().isEmpty()) {
                    edit_note.setError("Введжите описание");
                } if (id_pos) {
                    edit_id_position.setError("Такой Id уже существует");
                } else {
                    AddPosition(position, name, salery, note);
                }


            }
        });

    }

    private void AddPosition(int position, String name, int salery, String note) {

        boolean create_position = databaseHelper.addPosition(position, name, salery, note);

        if (create_position) {
            Toast.makeText(getApplicationContext(), "Должность " + name + " создана успешно", Toast.LENGTH_LONG).show();
            edit_id_position.setText("");
            edit_name.setText("");
            edit_salery.setText("");
            edit_note.setText("");
        } else {
            Toast.makeText(getApplicationContext(), "Должность не создана", Toast.LENGTH_LONG).show();
        }

    }

}
