package org.example;

public class Student extends Thread {

    private final String name;
    private final ExamModel examModel;

    public Student(String name, ExamModel examModel) {
        this.name = name;
        this.examModel = examModel;
    }

    @Override
    public void run() {
        examModel.studentArrives(name);
    }
}
