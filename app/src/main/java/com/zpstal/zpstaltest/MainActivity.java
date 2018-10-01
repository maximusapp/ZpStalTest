package com.zpstal.zpstaltest;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    ItemListMainAdapter itemListMainAdapter;
    RecyclerView recycler_personal;

    TextView text_main;
    LinearLayout linearLayout;

    ImageView image_sort_gender;

    Button button_sort_male;
    Button button_sort_famale;
    Button button_all;

    Button button_delete;
    Button button_cancel;

    String sort_by = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        recycler_personal = findViewById(R.id.list_of_personal_main);

        // HANDLE SORT IMAGE BUTTON.
        image_sort_gender = findViewById(R.id.sort_gender);
        image_sort_gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                @SuppressLint("InflateParams") final View view_sort = getLayoutInflater().inflate(R.layout.sort_gender_layout, null);

                button_sort_male = view_sort.findViewById(R.id.button_male);
                button_sort_famale = view_sort.findViewById(R.id.button_famale);
                button_all = view_sort.findViewById(R.id.button_all);

                mDialogBuilder.setView(view_sort);
                final AlertDialog alertDialog = mDialogBuilder.create();
                alertDialog.show();

                // Button sort male
                button_sort_male.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sort_by = "Мужской";
                        itemListMainAdapter = new ItemListMainAdapter(view_sort.getContext(), databaseHelper.sortMaleData(sort_by)){

                            @Override
                            public void OnClickLister(DataPersonal items) {
                                Intent intent_department = new Intent(MainActivity.this, DepartamentActivity.class);
                                intent_department.putExtra("id", items.getIdDep());
                                Log.d("ID", items.getIdDep());
                                startActivity(intent_department);
                            }

                            @Override
                            public void OnClickListerPosition(DataPersonal items) {
                                Intent intent_position = new Intent(MainActivity.this, PositionActivity.class);
                                intent_position.putExtra("id", items.getIdPos());
                                startActivity(intent_position);
                            }

                            @Override
                            public void OnClickDeletItem(final DataPersonal items) {

                                final AlertDialog.Builder mDialogBuilder1 = new AlertDialog.Builder(MainActivity.this);
                                @SuppressLint("InflateParams")
                                final View view_delete = getLayoutInflater().inflate(R.layout.delete_data_layout, null);

                                button_delete = view_delete.findViewById(R.id.button_delete);
                                button_cancel = view_delete.findViewById(R.id.button_cancel);

                                mDialogBuilder1.setView(view_delete);
                                final AlertDialog alertDialog1 = mDialogBuilder1.create();
                                alertDialog1.show();

                                button_delete.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        deleteData(items.getId_person());
                                        recycler_personal.setAdapter(itemListMainAdapter);
                                        itemListMainAdapter.notifyDataSetChanged();
                                        alertDialog1.dismiss();
                                    }
                                });

                                button_cancel.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        alertDialog1.dismiss();
                                    }
                                });


                            }
                        };

                        recycler_personal.setAdapter(itemListMainAdapter);
                        itemListMainAdapter.notifyDataSetChanged();
                        alertDialog.dismiss();
                    }
                });



                // Button sort female
                button_sort_famale.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sort_by = "Женский";
                        itemListMainAdapter = new ItemListMainAdapter(view_sort.getContext(), databaseHelper.sortMaleData(sort_by)) {
                            @Override
                            public void OnClickLister(DataPersonal items) {
                                Intent intent_department = new Intent(MainActivity.this, DepartamentActivity.class);
                                intent_department.putExtra("id", items.getIdDep());
                                Log.d("ID", items.getIdDep());
                                startActivity(intent_department);
                            }

                            @Override
                            public void OnClickListerPosition(DataPersonal items) {
                                Intent intent_position = new Intent(MainActivity.this, PositionActivity.class);
                                intent_position.putExtra("id", items.getIdPos());
                                startActivity(intent_position);
                            }

                            @Override
                            public void OnClickDeletItem(final DataPersonal items) {

                                final AlertDialog.Builder mDialogBuilder2 = new AlertDialog.Builder(MainActivity.this);
                                @SuppressLint("InflateParams")
                                final View view_delete = getLayoutInflater().inflate(R.layout.delete_data_layout, null);

                                button_delete = view_delete.findViewById(R.id.button_delete);
                                button_cancel = view_delete.findViewById(R.id.button_cancel);

                                mDialogBuilder2.setView(view_delete);
                                final AlertDialog alertDialog2 = mDialogBuilder2.create();
                                alertDialog2.show();

                                button_delete.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        deleteData(items.getId_person());
                                        recycler_personal.setAdapter(itemListMainAdapter);
                                        itemListMainAdapter.notifyDataSetChanged();
                                        alertDialog2.dismiss();
                                    }
                                });

                                button_cancel.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        alertDialog2.dismiss();
                                    }
                                });
                            }
                        };
                        recycler_personal.setAdapter(itemListMainAdapter);
                        itemListMainAdapter.notifyDataSetChanged();
                        alertDialog.dismiss();
                    }
                });

                // Button sort all
                button_all.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        itemListMainAdapter = new ItemListMainAdapter(view_sort.getContext(), databaseHelper.personalData()) {
                            @Override
                            public void OnClickLister(DataPersonal items) {
                                Intent intent_department = new Intent(MainActivity.this, DepartamentActivity.class);
                                intent_department.putExtra("id", items.getIdDep());
                                Log.d("ID", items.getIdDep());
                                startActivity(intent_department);
                                //Toast.makeText(getApplicationContext(), "Нажат отдел", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void OnClickListerPosition(DataPersonal items) {
                                Intent intent_position = new Intent(MainActivity.this, PositionActivity.class);
                                intent_position.putExtra("id", items.getIdPos());
                                startActivity(intent_position);
                                //Toast.makeText(getApplicationContext(), "Нажата должность", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void OnClickDeletItem(final DataPersonal items) {

                                final AlertDialog.Builder mDialogBuilder3 = new AlertDialog.Builder(MainActivity.this);
                                @SuppressLint("InflateParams")
                                final View view_delete = getLayoutInflater().inflate(R.layout.delete_data_layout, null);

                                button_delete = view_delete.findViewById(R.id.button_delete);
                                button_cancel = view_delete.findViewById(R.id.button_cancel);

                                mDialogBuilder3.setView(view_delete);
                                final AlertDialog alertDialog3 = mDialogBuilder3.create();
                                alertDialog3.show();

                                button_delete.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        deleteData(items.getId_person());
                                        recycler_personal.setAdapter(itemListMainAdapter);
                                        itemListMainAdapter.notifyDataSetChanged();
                                        alertDialog3.dismiss();
                                    }
                                });

                                button_cancel.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        alertDialog3.dismiss();
                                    }
                                });
                            }
                        };
                        recycler_personal.setAdapter(itemListMainAdapter);
                        itemListMainAdapter.notifyDataSetChanged();
                        alertDialog.dismiss();
                    }
                });


            }
        });

        text_main = findViewById(R.id.text_main_screen);
        linearLayout = findViewById(R.id.linear_main);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recycler_personal.setLayoutManager(mLayoutManager);

        itemListMainAdapter = new ItemListMainAdapter(this, databaseHelper.personalData()) {
            @Override
            public void OnClickLister(DataPersonal items) {
                Intent intent_department = new Intent(MainActivity.this, DepartamentActivity.class);
                intent_department.putExtra("id", items.getIdDep());
                Log.d("ID", items.getIdDep());
                startActivity(intent_department);
                //Toast.makeText(getApplicationContext(), "Нажат отдел", Toast.LENGTH_LONG).show();
            }

            @Override
            public void OnClickListerPosition(DataPersonal items) {
                Intent intent_position = new Intent(MainActivity.this, PositionActivity.class);
                intent_position.putExtra("id", items.getIdPos());
                startActivity(intent_position);
                //Toast.makeText(getApplicationContext(), "Нажата должность", Toast.LENGTH_LONG).show();
            }

            @Override
            public void OnClickDeletItem(final DataPersonal items) {
                final AlertDialog.Builder mDialogBuilder4 = new AlertDialog.Builder(MainActivity.this);
                @SuppressLint("InflateParams")
                final View view_delete = getLayoutInflater().inflate(R.layout.delete_data_layout, null);

                button_delete = view_delete.findViewById(R.id.button_delete);
                button_cancel = view_delete.findViewById(R.id.button_cancel);

                mDialogBuilder4.setView(view_delete);
                final AlertDialog alertDialog4 = mDialogBuilder4.create();
                alertDialog4.show();

                button_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        deleteData(items.getId_person());
                        recycler_personal.setAdapter(itemListMainAdapter);
                        itemListMainAdapter.notifyDataSetChanged();
                        alertDialog4.dismiss();
                    }
                });

                button_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog4.dismiss();
                    }
                });
            }
        };

        recycler_personal.setAdapter(itemListMainAdapter);
        itemListMainAdapter.notifyDataSetChanged();

        if (itemListMainAdapter.getItemCount() == 0) {
            recycler_personal.setVisibility(View.INVISIBLE);
            text_main.setVisibility(View.VISIBLE);
            linearLayout.setVisibility(View.INVISIBLE);
        } else {
            recycler_personal.setVisibility(View.VISIBLE);
            text_main.setVisibility(View.INVISIBLE);
            linearLayout.setVisibility(View.VISIBLE);
        }

    }

    private void deleteData(String id_person) {
        boolean deleteData = databaseHelper.deleteItem(id_person);
        if (deleteData) {
            this.recreate();
            Toast.makeText(MainActivity.this, "Data deleted", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, "Data not deleted", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_add_personal) {
            Intent intent_create_personal = new Intent(MainActivity.this, CreatePersonalActivity.class);
            startActivity(intent_create_personal);
        }
        if (id == R.id.action_add_department) {
            Intent intent_create_personal = new Intent(MainActivity.this, CreateDepartmentActivity.class);
            startActivity(intent_create_personal);
            finish();
        }
        if (id == R.id.action_add_position) {
            Intent intent_create_personal = new Intent(MainActivity.this, CreatePositionActivity.class);
            startActivity(intent_create_personal);
        }

        return super.onOptionsItemSelected(item);
    }
}