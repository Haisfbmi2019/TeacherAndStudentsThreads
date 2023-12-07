package org.example;

public class ExamSimulation {

    public static void main(String[] args) {
        int studentsCount = 10;
        ExamModel examModel = new ExamModel();

        System.out.println("Створення черги студентів:");

        // Створення групи студентів
        for (int i = 1; i <= studentsCount; i++) {
            Thread student = new Student("Студент " + i, examModel);
            student.start();
        }

        System.out.println("Екзамен розпочався");

        // Проведення екзамену викладачем
        examModel.teacherConductsExam();
    }
}