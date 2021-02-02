package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University  {
    private String name;
    private int age;
    private List<Student> students = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public University(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        Student result = null;
        for (Student student : students){
            if (student.getAverageGrade() == averageGrade){
                result = student;
            }
        }
        return result;
    }

    public Student getStudentWithMaxAverageGrade() {
        Student result = null;
        double max = Double.MIN_VALUE;
        for (Student student : students){
            double averageGrade = student.getAverageGrade();
            if (averageGrade>max){
               max = averageGrade;
               result = student;
           }
        }
        return result;
    }

    public Student getStudentWithMinAverageGrade() {
        Student result = null;
        double min = Double.MAX_VALUE;
        for (Student student : students){
            double averageGrade = student.getAverageGrade();
            if (averageGrade<min){
                min = averageGrade;
                result = student;
            }
        }
        return result;
    }

    public void expel(Student student){
        students.remove(student);
    }
}