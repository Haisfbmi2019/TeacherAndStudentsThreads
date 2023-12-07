package org.example;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

public class ExamModel {

    private final BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    private final Semaphore teacherSemaphore = new Semaphore(1); // Лише один викладач

    public void studentArrives(String studentName) {
        queue.add(studentName);
        System.out.println(studentName + " прибув на екзамен");
    }

    public void teacherConductsExam() {
        try {
            teacherSemaphore.acquire(); // Отримання доступу для викладача
            while (!queue.isEmpty()) {
                long examTime = getRandomExamTime();
                String student = queue.take(); // Викладач приймає студента з черги
                System.out.println("Викладач проводить екзамен для " + student +
                        ", час виконання: " + ((double) examTime / 1000) + " секунд");
                // Проведення екзамену...
                Thread.sleep(examTime); // Моделюємо тривалість екзамену
            }
            System.out.println("Викладач закінчив проведення екзамену");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            teacherSemaphore.release(); // Звільняємо доступ для викладача
        }
    }

    public static long getRandomExamTime() {
        long min = 2_000;
        long max = 4_000;
        return (long) (Math.random() * (max - min + 1) + min);
    }

}
