package com.example.android.demoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String DATABASE_NAME = "studentslist.db";
    private static final int DATABASE_VERSION = 1;

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_ROLLNO = "rollNo";
    private static final String KEY_Branch = "branch";
    private static final String KEY_EMAIL = "email";

    private static final String TABLE_STUDENT = "student_table";
    private static final String CREATE_STUDENT_TABLE = "CREATE TABLE " + TABLE_STUDENT + "( "
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_NAME + " TEXT NOT NULL,"
            + KEY_Branch + " TEXT NOT NULL,"
            + KEY_ROLLNO + " TEXT NOT NULL,"
            + KEY_EMAIL + " TEXT NOT NULL )";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_STUDENT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP IF TABLE EXISTS " + TABLE_STUDENT);
        onCreate(db);
    }

    public long addStudent(Student student) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, student.getName());
        values.put(KEY_ROLLNO, student.getRollNo());
        values.put(KEY_Branch, student.getBranch());
        values.put(KEY_EMAIL, student.geteMail());


        return db.insert(TABLE_STUDENT, null, values);

    }

    public List<Student> getAllStudents() {

        List<Student> studentList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * from " + TABLE_STUDENT, null);

        if (cursor.moveToFirst()){
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String rollno = cursor.getString(2);
                String branch = cursor.getString(3);
                String email = cursor.getString(4);

                Student student = new Student(id, name, rollno, branch, email);
                studentList.add(student);
            }while (cursor.moveToNext());
        }
        return studentList;
    }

    public int updateStudent(Student student) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, student.getName());
        values.put(KEY_ROLLNO, student.getRollNo());
        values.put(KEY_Branch, student.getBranch());
        values.put(KEY_EMAIL, student.geteMail());

        return db.update(TABLE_STUDENT, values, "id=?", new String[]{String.valueOf(student.getId())});
    }

    public int deleteStudent(int studentId) {

        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_STUDENT, "id=?", new String[]{String.valueOf(studentId)});
    }
}
