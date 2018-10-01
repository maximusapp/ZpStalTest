package com.zpstal.zpstaltest;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class CreateDepartmentActivity extends AppCompatActivity {

    // Handle press back button.
    public boolean onSupportNavigateUp(){
        onBackPressed();
        Intent intent_main = new Intent(CreateDepartmentActivity.this, MainActivity.class);
        startActivity(intent_main);
        finish();
        return true;
    }

    EditText edit_id_department;
    EditText edit_name;
    EditText edit_note;

    int id_department;
    String name;
    String note;

    Button button_create_department;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_department);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setTitle("Создать департамент");
        }

        databaseHelper = new DatabaseHelper(this);

        edit_id_department = findViewById(R.id.enter_idDepartment);
        edit_name = findViewById(R.id.enter_name);
        edit_note = findViewById(R.id.enter_note);

        button_create_department = findViewById(R.id.create_department);
        button_create_department.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                id_department = Integer.parseInt(edit_id_department.getText().toString().trim());
                name = edit_name.getText().toString().trim();
                note = edit_note.getText().toString().trim();

                boolean id_dep = databaseHelper.existOrNotIdDepartment(String.valueOf(id_department));

                if (edit_id_department.getText().toString().isEmpty()) {
                    edit_id_department.setError("Введите Id департамента");
                } else if (edit_name.getText().toString().isEmpty()) {
                    edit_name.setError("Введите название департамента");
                } else if (edit_note.getText().toString().isEmpty()) {
                    edit_note.setError("Введите описание департамента");
                } else if (id_dep) {
                    edit_id_department.setError("Такой Id уже есть");
                } else {
                    AddDepartment(id_department, name, note);
                }

            }
        });

    }

    private void AddDepartment(int id_department, String name, String note) {

        boolean create_department = databaseHelper.addDepartment(id_department, name, note);

        if (create_department) {
            Toast.makeText(getApplicationContext(), "Департамент создан", Toast.LENGTH_LONG).show();
            edit_id_department.setText("");
            edit_name.setText("");
            edit_note.setText("");
        } else {
            Toast.makeText(getApplicationContext(), "Департамент не создан, " +
                    "какая-то ошибка", Toast.LENGTH_LONG).show();
        }

    }

}
