package com.example.android.demoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditDataActivity extends AppCompatActivity {

    private static final String TAG = "EditData Activity";

    EditText mEditName, mEditRollNo, mEditBranch, mEmailId;
    DatabaseHelper databaseHelper;

    int id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_data_layout);

        databaseHelper = new DatabaseHelper(this);

        mEditName = (EditText)findViewById(R.id.edit_name);
        mEditRollNo = (EditText)findViewById(R.id.edit_rollNo);
        mEditBranch = (EditText)findViewById(R.id.edit_branch);
        mEmailId = (EditText)findViewById(R.id.edit_email);

        Student student = (Student) getIntent().getExtras().getSerializable("STUDENT");
        id = student.getId();
        mEditName.setText(student.getName());
        mEditRollNo.setText(student.getRollNo());
        mEditBranch.setText(student.getBranch());
        mEmailId.setText(student.geteMail());

    }

    public void update(View view) {

        String name = mEditName.getText().toString();
        String editRollNo = mEditRollNo.getText().toString();
        String editbranch = mEditBranch.getText().toString();
        String emailID = mEmailId.getText().toString();

        Student student = new Student(id, name, editRollNo, editbranch, emailID);

        int result = databaseHelper.updateStudent(student);

        if (result > 0){
            Toast.makeText(this, "Student Information Updated", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
        }

        Intent intent = new Intent(EditDataActivity.this, ListViewActivity.class);
        startActivity(intent);
    }
}
