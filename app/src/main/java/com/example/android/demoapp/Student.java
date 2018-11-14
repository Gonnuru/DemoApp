package com.example.android.demoapp;

import java.io.Serializable;

public class Student implements Serializable {

    int id;
    String name;
    String rollNo;
    String branch;
    String eMail;

    public Student(String name, String rollNo, String branch, String eMail) {
        this.name = name;
        this.rollNo = rollNo;
        this.branch = branch;
        this.eMail = eMail;
    }

    public Student(int id, String name, String rollNo, String branch, String eMail) {
        this.id = id;
        this.name = name;
        this.rollNo = rollNo;
        this.branch = branch;
        this.eMail = eMail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    @Override
    public String toString(){
        return name;
    }
}
