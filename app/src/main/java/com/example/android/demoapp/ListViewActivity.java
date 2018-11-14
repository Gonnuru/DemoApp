package com.example.android.demoapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    private static final String TAG = "ListDataActivity";

    DatabaseHelper mDatabaseHelper;

    ListView mListViewStudents;
    List<Student> list;
    ArrayAdapter<Student> arrayAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);

        mListViewStudents = (ListView)findViewById(R.id.listview);
        mDatabaseHelper = new DatabaseHelper(this);



        mListViewStudents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Student student = list.get(position);

                Intent intent = new Intent(ListViewActivity.this, EditDataActivity.class);
                intent.putExtra("STUDENT", student);
                startActivity(intent);
            }
        });


        mListViewStudents.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {


                AlertDialog.Builder builder = new AlertDialog.Builder(ListViewActivity.this);
                builder.setMessage("Are you Sure you want to delete ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Student student = list.get(position);
                        int studentId = student.getId();

                        int result = mDatabaseHelper.deleteStudent(studentId);

                        if (result > 0){
                            Toast.makeText(ListViewActivity.this, "Student deleted", Toast.LENGTH_SHORT).show();
                            list.remove(student);
                            arrayAdapter.notifyDataSetChanged();
                        }else{
                            Toast.makeText(ListViewActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("No",null);
                builder.show();



                return true;
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        list = mDatabaseHelper.getAllStudents();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        mListViewStudents.setAdapter(arrayAdapter);

    }
}
