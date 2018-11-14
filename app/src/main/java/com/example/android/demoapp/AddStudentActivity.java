package com.example.android.demoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddStudentActivity extends AppCompatActivity {

    EditText mName, mRollNo, mBranch, mEmailId;
    Button mAddStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        mName = (EditText)findViewById(R.id.name);
        mRollNo = (EditText)findViewById(R.id.rollNo);
        mBranch = (EditText)findViewById(R.id.branch);
        mEmailId = (EditText)findViewById(R.id.email);
        mAddStudent = (Button)findViewById(R.id.addStudent);
    }

    public void add(View view) {

        switch (view.getId()){
            case R.id.addStudent:
                String name = mName.getText().toString();

                if (TextUtils.isEmpty(name)){
                    mName.setError("please enter a name");
                    return;
                }
                String rollNumber = mRollNo.getText().toString();

                if (TextUtils.isEmpty(rollNumber)){
                    mRollNo.setError("please provide your rollNo");
                    return;
                }
                String branch = mBranch.getText().toString();

                if (TextUtils.isEmpty(branch)){
                    mBranch.setError("please enter your branch");
                    return;
                }
                String emailId = mEmailId.getText().toString();

                if (TextUtils.isEmpty(emailId)){
                    mEmailId.setError("please provide us with your email ID");
                    return;
                }

                DatabaseHelper databaseHelper = new DatabaseHelper(this);

                Student student = new Student(name, rollNumber, branch, emailId);
                long result = databaseHelper.addStudent(student);

               if (result != -1){
                   Toast.makeText(this, "Student Data Saved", Toast.LENGTH_SHORT).show();
               }else{
                   Toast.makeText(this, "Insertion Failed", Toast.LENGTH_SHORT).show();
               }

                Intent intent = new Intent(AddStudentActivity.this,ListViewActivity.class);
               startActivity(intent);
                break;

        }
    }
}
