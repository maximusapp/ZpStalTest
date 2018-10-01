package com.zpstal.zpstaltest;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class CreatePersonalActivity extends AppCompatActivity {

    // Handle press back button.
    public boolean onSupportNavigateUp(){
        onBackPressed();
        Intent intent_main = new Intent(CreatePersonalActivity.this, MainActivity.class);
        startActivity(intent_main);
        finish();
        return true;
    }

    EditText id_personal;
    EditText edit_fio;
    EditText edit_birthday;
    EditText edit_gender;
    EditText edit_idDepartment;
    EditText edit_idPosition;

    int idPersonal;
    String fio;
    String birthday;
    String gender;
    int idDep;
    int idPos;

    Button create_personal;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_personal);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        }
        // Set title of ToolBar.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setTitle("Создать персонал");
        }

        id_personal = findViewById(R.id.enter_idPersonal);
        edit_fio = findViewById(R.id.enter_fio);
        edit_birthday = findViewById(R.id.enter_birthdate);
        edit_gender = findViewById(R.id.enter_gender);
        edit_idDepartment = findViewById(R.id.enter_idDepartment);
        edit_idPosition = findViewById(R.id.enter_idPosition);

        databaseHelper = new DatabaseHelper(this);

        create_personal = findViewById(R.id.create_personal);
        create_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                idPersonal = Integer.parseInt(id_personal.getText().toString().trim());
                fio = edit_fio.getText().toString().trim();
                birthday = edit_birthday.getText().toString().trim();
                gender = edit_gender.getText().toString().trim();
                idDep = Integer.parseInt(edit_idDepartment.getText().toString().trim());
                idPos = Integer.parseInt(edit_idPosition.getText().toString().trim());

                boolean id = databaseHelper.existOrNot(String.valueOf(idPersonal));

                if (id_personal.getText().toString().trim().isEmpty()) {
                    id_personal.setError("Введите Id работника");
                } else if (edit_fio.getText().toString().trim().isEmpty()) {
                    edit_fio.setError("Введите ФИО");
                } else if (edit_birthday.getText().toString().trim().isEmpty()) {
                    edit_birthday.setError("Введите дату рождения");
                } else if (edit_gender.getText().toString().isEmpty()) {
                    edit_gender.setError("Введите пол");
                } else if (edit_idDepartment.getText().toString().isEmpty()) {
                    edit_idDepartment.setError("Введите Id отдела");
                } else if (edit_idPosition.getText().toString().isEmpty()) {
                    edit_idPosition.setError("Ввдите Id должности");
                } else if (id) {
                    id_personal.setError("Id работника уже есть");
                    Toast.makeText(getApplicationContext(), "ID уже есть в базе", Toast.LENGTH_LONG).show();
                } else {
                    Add(idPersonal, fio, birthday, gender, idDep, idPos);
                }

            }
        });

    }

    private void Add(int idPersonal, String fio, String birthday, String gender, int idDep, int idPos) {

        boolean create_worker = databaseHelper.addWorker(idPersonal, fio, birthday, gender, idDep, idPos);

        if (create_worker) {
            Toast.makeText(getApplicationContext(), "Работник создан", Toast.LENGTH_LONG).show();
            id_personal.setText("");
            edit_fio.setText("");
            edit_birthday.setText("");
            edit_gender.setText("");
            edit_idDepartment.setText("");
            edit_idPosition.setText("");
        } else {
            Toast.makeText(getApplicationContext(), "Работник не создан", Toast.LENGTH_LONG).show();
        }

    }

}